import {setAuthDetails, setDiscounts, setError, setPassword, setProfileData} from "../actions/actionCreators"
import {getStore} from "../store"

import {buildUrlWithParameters, request} from "./requestUtils"

export const COMPANY_CLIENT_AUTHORIZATION_STRING = "Basic Q09NUEFOWV9XRUI6ZmVkMDM1ZWItM2NjYy00ZDM0LThkMGYtOWQ1YzMzNTVkNzA3"
export const AUTH_URL = "/oauth/token"

export function postRequest(url, body = undefined, headers = "") {
    if (body) {
        body = JSON.stringify(body)
    }
    return request('POST', url, body, headers)
}

export function getRequest(url) {
    return request('GET', url)
}

export function signUpAndLogIn(company) {
    return new Promise((resolve, reject) => {
        postRequest('/api/company/signup', company)
            .then(() => {
                authenticate(company.email, company.password)
                    .then(() => {
                        resolve()
                    })
                    .catch(() => {
                        reject({message: "Could not authenticate."})
                    })
            })
            .catch(responseObject => {
                reject({
                    response: responseObject.response,
                    message: responseObject.message ? responseObject.message : "Could not sign up."
                })
            })
    })
}

export function authenticate(username, password) {
    if (!(username && password)) {
        return new Promise.reject({message: "Please provide username and password"})
    }
    let store = getStore()
    store.dispatch(setAuthDetails("", "", null))
    return new Promise((resolve, reject) => {
        postRequest(
            buildUrlWithParameters(AUTH_URL, {grant_type: 'password', username: username, password: password}),
            undefined,
            {'Authorization': COMPANY_CLIENT_AUTHORIZATION_STRING}
        )
            .then(data => {
                store.dispatch(setAuthDetails(username, password, data.access_token))
                resolve({data})
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

export function loadMyData() {
    return getRequest('/api/company/me')
        .then(
            (response) => {
                getStore().dispatch(setProfileData(
                    response
                ))
            }
        )
        .catch((response) => {
            getStore().dispatch(setError(
                response.message || "Server error: could not load my profile"
            ))
        })
}

export function loadMyDiscounts() {
    return getRequest('/api/discounts/mine')
        .then(
            (discounts) => {
                getStore().dispatch(setDiscounts(
                    discounts
                ))
            }
        )
        .catch((response) => {
            getStore().dispatch(setError(
                response.message || "Server error: could not load discounts"
            ))
        })
}

export function editCompanyInfo(company) {
    const profileData = getStore().getState().profileData
    let companyToEdit = {...company}
    companyToEdit.address['id'] = profileData.address.id
    companyToEdit.user = {
        'nick': companyToEdit.name,
        'email': company.email,
        'id': profileData.user.id,
        'type': 'COMPANY'
    }
    delete companyToEdit['password']
    delete companyToEdit['email']
    companyToEdit['id'] = profileData.id
    return new Promise((resolve, reject) => {
        postRequest('/api/company/' + companyToEdit.id, companyToEdit)
            .then((updatedInfo) => {
                    getStore().dispatch(setProfileData(
                        updatedInfo
                    ))
                    resolve(updatedInfo)
                }
            )
            .catch((responseObject) => {
                    getStore().dispatch(setError(
                        responseObject.message || "Server error: could not edit profile"
                    ))
                    reject({
                        response: responseObject.response,
                        message: responseObject.message ? responseObject.message : "Cannot authenticate to this page."
                    })
                }
            )
    })
}
