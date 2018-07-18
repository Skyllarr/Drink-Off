import reducers from './reducers'
import {persistReducer, persistStore} from 'redux-persist'
import {rootPersistConfig} from './reducers/persistorConfig'
import {createStore} from 'redux'

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
