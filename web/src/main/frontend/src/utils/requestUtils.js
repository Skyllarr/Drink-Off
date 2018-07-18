import {getStore} from '../store.js'
import {setAuthDetails, setToken} from "../actions/actionCreators"
import {nextPath} from '../routes/history'

import {AUTH_URL, COMPANY_CLIENT_AUTHORIZATION_STRING} from "./request"

export function buildUrlWithParameters(url, parameters) {
    if (!parameters.isEmpty) {
        url += "?"
    }
    Object.keys(parameters).map((key) => {
        if (parameters.hasOwnProperty(key)) {
            return url += key + "=" + encodeURIComponent(parameters[key]) + "&"
        } else {
            return url
        }
    })
    return url.slice(0, -1)  //remove last &
}

function refreshToken(username, password) {
    let store = getStore()
    store.dispatch(setToken(null))
    return new Promise((resolve, reject) => {
        fetch(
            buildUrlWithParameters(AUTH_URL, {
                "grant_type": "password",
                username: username,
                password: password
            }), {
                method: 'POST',
                headers: {'Authorization': COMPANY_CLIENT_AUTHORIZATION_STRING},
                body: undefined
            })
            .then(response => {
                if (response.status === 200) {
                    response.json().then(data => {
                        store.dispatch(setAuthDetails(username, password, data.access_token))
                        resolve()
                    })
                } else {
                    reject()
                }
            })
            .catch(responseObject => {
                store.dispatch(setAuthDetails("", "", null))
                if (responseObject.response != null && responseObject.response.status === 401) {
                    reject({
                        response: responseObject.response,
                        message: "Incorrect credentials."
                    })
                } else {
                    reject({
                        response: responseObject.response,
                        message: responseObject.message ? responseObject.message : "Cannot authenticate to this page."
                    })
                }
            })
    })
}

function repeatOriginalRequest(url, type, headers, body, resolve, reject, redirectionNotAllowed) {
    fetch(url, {
        method: type,
        headers: Object.assign(headers,
            {'Authorization': 'Bearer ' + getStore().getState().auth.token}),
        body
    })
        .then(response => {
            if (response.status === 403) {
                rejectAndRedirect(redirectionNotAllowed, reject, "You are not allowed to access this page.")
            } else if (response.status === 401) {
                rejectAndRedirect(redirectionNotAllowed, reject, "Authentication failed.")
            } else if (response.status === 400) {
                response.body.json().then(data => {
                    console.log(data)
                })
            }
            response.json()
                .then(data => {
                    if (response.status === 200) {
                        if (data.isEmpty) {
                            data = null
                        }
                        resolve(data)
                    } else if (response.status === 409) {
                        reject('errors' in data ? {response: response, message: data.errors[0]} : {response: response})
                    }
                })
                .catch(() => { //response body other than json is always on bad requests
                    reject({message: "Response not in json format", response: response})
                })
        })
        .catch(response => {
            reject({response: response})
        })
}

function redirectToLogin() {
    nextPath("/login")
}

function rejectAndRedirect(redirectionNotAllowed, reject, message) {
    if (!redirectionNotAllowed) {
        redirectToLogin()
    }
    reject({message})
}

/**
 * @param type HTTP method type (GET, POST, DELETE...)
 * @param body of the request
 * @param headers of the request
 * @param redirectionNotAllowed if true will prevent redirection to login page in the case of failed authentication
 */
export function request(type, url, body = undefined, headers = "", redirectionNotAllowed = false) {
    return new Promise((resolve, reject) => {
        headers = Object.assign({'Content-Type': 'application/json'}, headers)
        let storeState = getStore().getState()
        if (storeState.auth.token) {
            Object.assign(headers, {'Authorization': 'Bearer ' + storeState.auth.token})
        }
        fetch(url, {method: type, headers, body})
            .then(response => {
                const status = response.status
                if (status === 403) {
                    rejectAndRedirect(redirectionNotAllowed, reject, "You are not allowed to access this page.")
                }
                if (status === 400) {
                    response.body.json().then(data => {
                        reject({response: response, message: data.errors})
                    })
                }
                response.json().then(data => {
                    if (status === 401) {
                        if ('error' in data && data.error === "invalid_token") {
                            getStore().dispatch(setToken(null))
                            let {username, password} = getStore().getState().auth
                            if (username && password) {
                                refreshToken(username, password)
                                    .then(() => {
                                        repeatOriginalRequest(url, type, headers, body, resolve, reject, redirectionNotAllowed)
                                    })
                                    .catch(() => {
                                        rejectAndRedirect(redirectionNotAllowed, reject, "Authentication failed.")
                                    })
                            } else {
                                rejectAndRedirect(redirectionNotAllowed, reject, "Username and password is unknown.")
                            }
                        } else {
                            reject({response: response})
                        }
                    } else if (status === 409) {
                        reject('errors' in data ? {response: response, message: data.errors[0]} : {response: response})
                    }
                    else if (status === 200) {
                        if (data.isEmpty) {
                            data = null
                        }
                        resolve(data)
                    } else {
                        reject({response: response})
                    }
                }).catch(() => { //response body other than json is always on bad requests
                    reject({response: response})
                })
            })
            .catch(response => {
                reject({response: response})
            })
    })
}

