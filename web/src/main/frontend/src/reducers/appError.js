import {SET_ERROR, REMOVE_ERROR} from "../actions/actionTypes.js"

export default (appError = null, action) => {
    switch (action.type) {
        case SET_ERROR:
            return action.appError

        case REMOVE_ERROR:
            return null
        default:
            return appError
    }
}
