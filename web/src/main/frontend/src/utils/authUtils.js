import {getStore} from "../store"
import {REMOVE_AUTH_SESSION_STORAGE} from "../index"
import {removeCompanyData} from "../actions/actionCreators"
import {nextPath} from "../routes/history"

export function logout() {
    getStore().dispatch(removeCompanyData())
    localStorage.setItem(REMOVE_AUTH_SESSION_STORAGE, Date.now())
    localStorage.removeItem(REMOVE_AUTH_SESSION_STORAGE)
    nextPath("/")
}

export function isAuthenticated(auth) {
    return auth.token != null
}
