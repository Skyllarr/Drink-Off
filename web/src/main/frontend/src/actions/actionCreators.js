import {
    REMOVE_COMPANY_DATA,
    SET_AUTH_DETAILS,
    SET_DISCOUNTS,
    SET_LANGUAGE,
    SET_PROFILE_DATA,
    SET_TOKEN,
    SET_PASSWORD,
    REMOVE_ERROR,
    SET_ERROR,
} from './actionTypes'

export const setLanguage = language => ({
    type: SET_LANGUAGE,
    language,
})

export const setAuthDetails = (username, password, token) => ({
    type: SET_AUTH_DETAILS,
    username,
    password,
    token,
})

export const setPassword = (password) => ({
    type: SET_PASSWORD,
    password,
})

export const setToken = (token) => ({
    type: SET_TOKEN,
    token,
})

export const setProfileData = (profileData) => ({
    type: SET_PROFILE_DATA,
    profileData,
})

export const setDiscounts = (discounts) => ({
    type: SET_DISCOUNTS,
    discounts,
})

export const removeCompanyData = () => ({
    type: REMOVE_COMPANY_DATA
})

export const removeError = () => ({
    type: REMOVE_ERROR
})

export const setError = (appError) => ({
    type: SET_ERROR,
    appError,
})
