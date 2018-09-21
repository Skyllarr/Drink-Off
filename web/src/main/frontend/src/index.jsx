import React from 'react'
import ReactDOM from 'react-dom'
import {Provider} from 'react-redux'
import 'bootstrap'
import 'bootstrap/dist/css/bootstrap.css'

import './index.css'
import App from './components/App'
import registerServiceWorker from './registerServiceWorker'
import {getPersistor, getStore} from './store.js'
import {setAuthDetails} from "./actions/actionCreators"
import {LoadingRoute} from "./routes/LoadingRoute"

export const GET_AUTH_SESSION_STORAGE = 'drinkOff/getAuthSessionStorage'
export const REMOVE_AUTH_SESSION_STORAGE = 'drinkOff/removeAuthSessionStorage'
export const AUTH_SESSION_STORAGE = 'drinkOff/authSessionStorage'

function handleLocalStorageEvents(event) {
    const auth = getStore().getState().auth
    const {username, password, token} = auth

    if (event.key === GET_AUTH_SESSION_STORAGE) {
        // Some tab asked for the sessionStorage -> send it
        if (username && password) {
            localStorage.setItem(AUTH_SESSION_STORAGE, JSON.stringify(getStore().getState().auth))
            localStorage.removeItem(AUTH_SESSION_STORAGE)
        }
    } else if (event.key === AUTH_SESSION_STORAGE && (!username || !password || !token)) {
        // sessionStorage is empty -> fill it
        let data = JSON.parse(event.newValue)
        if (data) {
            getStore().dispatch(setAuthDetails(data.username, data.password, data.token))
        }
    } else if (event.key === REMOVE_AUTH_SESSION_STORAGE) {
        getStore().dispatch(setAuthDetails("", "", null))
    }
}

(function () {
    function sessionStorageEventHandling() {
        let persistor = getPersistor()
        let {bootstrapped} = persistor.getState()
        if (bootstrapped) {
            const {username, password, token} = getStore().getState().auth
            window.addEventListener('storage', handleLocalStorageEvents)
            if (!username || !password || !token) {
                // Ask other tabs for session storage
                localStorage.setItem(GET_AUTH_SESSION_STORAGE, Date.now())
            }
            persistorUnsubscribe()
        }
    }

    let persistorUnsubscribe = getPersistor().subscribe(sessionStorageEventHandling)
    sessionStorageEventHandling()
})()

ReactDOM.render(
    <Provider store={getStore()}>
        <LoadingRoute persistor={getPersistor()}>
            <App/>
        </LoadingRoute>
    </Provider>, document.getElementById('root'))

registerServiceWorker()
