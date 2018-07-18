import {REMOVE_COMPANY_DATA, SET_AUTH_DETAILS, SET_CREDENTIALS, SET_PASSWORD, SET_TOKEN} from "../actions/actionTypes"

export default (auth = {username: "", password: "", token: null}, action) => {
    switch (action.type) {
        case SET_AUTH_DETAILS:
            return Object.assign({}, auth, {
                username: action.username,
                password: action.password,
                token: action.token,
            })

        case SET_CREDENTIALS:
            return Object.assign({}, auth, {
                username: action.username,
                password: action.password,
            })

        case SET_TOKEN:
            return Object.assign({}, auth, {
                token: action.token,
            })

        case SET_PASSWORD:
            return Object.assign({}, auth, {
                password: action.password,
            })

        case REMOVE_COMPANY_DATA:
            return Object.assign({}, auth, {
                username: "",
                password: "",
                token: null,
            })

        default:
            return auth
    }
}

