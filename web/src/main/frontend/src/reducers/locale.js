import {SET_LANGUAGE} from "../actions/actionTypes"

export default (language = "en", action) => {
    switch (action.type) {
        case SET_LANGUAGE:
            return action.language
        default:
            return language
    }
}
