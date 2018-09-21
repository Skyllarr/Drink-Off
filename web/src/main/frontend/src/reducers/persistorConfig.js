import storageSession from "redux-persist/lib/storage/session"
import storage from "redux-persist/lib/storage"

export const authPersistConfig = {
    key: 'auth',
    storage: storageSession,
}

export const rootPersistConfig = {
    key: 'root',
    storage: storage,
    blacklist: ['auth'],
}
