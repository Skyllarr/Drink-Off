import React from 'react'

import {Redirect, Route} from 'react-router'
import {connect} from 'react-redux'
import LogInDialog from "../components/LogInDialog"
import {isAuthenticated} from "../utils/authUtils"

const LoginRoute = ({auth, isAuthenticated, ...rest}) => {
    let route
    if (isAuthenticated) {
        route = (
            <Route
                {...rest}
                key={"login-route-1"}
                render={(props) => {
                    const pathname = (
                        props.location.state && props.location.state.from && props.location.state.from.pathname) ||
                        '/profile'
                    return <Redirect
                        to={{
                            pathname,
                        }}/>
                }}/>
        )
    } else {
        route = (
            <Route
                {...rest}
                key={"login-route2"}
                component={LogInDialog}
            />
        )
    }
    return route
}

export default connect(
    (store) => ({
        auth: store.auth,
        isAuthenticated: isAuthenticated(store.auth),
    })
)(LoginRoute)
