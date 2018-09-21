import {persistReducer, persistStore} from 'redux-persist'
import {createStore} from 'redux'

import reducers from './reducers'
import {rootPersistConfig} from './reducers/persistorConfig'

let store
let persistor

export function getStore() {
    if (!store) {
        store = createStore(persistReducer(rootPersistConfig, reducers))
    }
    return store
}

export function getPersistor() {
    if (!persistor) {
        persistor = persistStore(getStore())
    }
    return persistor
}
