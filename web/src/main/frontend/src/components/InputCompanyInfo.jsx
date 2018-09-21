import React from 'react'

import SignUpDialog from './SignUpDialog'

const InputCompanyInfo = ({...rest}) => {
    let route
    if (rest.history.location.pathname === '/update') {
        route = (
            <SignUpDialog
                {...rest}
                onSubmit='update'
                key={"auth-route1"}/>
        )
    } else {
        route = (
            <SignUpDialog
                {...rest}
                onSubmit='signup'
                key={"auth-route1"}/>
        )
    }
    return route
}

export default InputCompanyInfo
