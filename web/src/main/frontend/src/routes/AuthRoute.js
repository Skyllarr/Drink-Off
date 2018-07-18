import React from 'react'

import {Redirect, Route} from 'react-router'
import {connect} from 'react-redux'

import {isAuthenticated} from "../utils/authUtils"

const AuthRoute = ({component, auth, isAuthenticated, ...rest}) => {
    let route
    if (isAuthenticated) {
        route = (
            <Route
                {...rest}
                component={component}
                key={"auth-route1"}/>
        )
    } else {
        route = (
            <Route
                {...rest}
                key={"auth-route2"}
                render={() => (<Redirect to={{pathname: '/login', state: {from: rest.location}}}/>)}/>
        )
    }
    return route
}

export default connect(
    (store) => ({
        auth: store.auth,
        isAuthenticated: isAuthenticated(store.auth)
    }),
)(AuthRoute)
