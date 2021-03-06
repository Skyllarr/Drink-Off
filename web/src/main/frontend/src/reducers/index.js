import {combineReducers} from 'redux'
import {persistReducer} from 'redux-persist'

import locale from './locale.js'
import auth from './auth.js'
import profileData from './profileData.js'
import discounts from './discounts'
import {authPersistConfig} from "./persistorConfig"
import appError from './appError'

export default combineReducers({
    locale,
    auth: persistReducer(authPersistConfig, auth),
    profileData,
    discounts,
    appError,
})
