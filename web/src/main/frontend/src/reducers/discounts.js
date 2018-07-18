import {REMOVE_COMPANY_DATA, SET_DISCOUNTS} from "../actions/actionTypes"

export default (discounts = [], action) => {
    switch (action.type) {
        case SET_DISCOUNTS:
            return action.discounts

        case REMOVE_COMPANY_DATA:
            return []

        default:
            return discounts
    }
}
