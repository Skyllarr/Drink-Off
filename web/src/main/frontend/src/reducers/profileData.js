import {SET_PROFILE_DATA} from "../actions/actionTypes.js"
import {REMOVE_COMPANY_DATA} from "../actions/actionTypes"

export default (profileData = {}, action) => {
    switch (action.type) {
        case SET_PROFILE_DATA:
            return action.profileData

        case REMOVE_COMPANY_DATA:
            return {}

        default:
            return profileData
    }
}
