import React from 'react'
import {Route, Router, Switch} from 'react-router'
import {Col} from 'reactstrap'
import {connect} from 'react-redux'

import {getHistory} from '../routes/history'
import AuthRoute from '../routes/AuthRoute'
import LoginRoute from '../routes/LoginRoute'
import {removeError} from '../actions/actionCreators'

import Home from "./Home"
import Profile from "./Profile"
import NavBar from './NavBar'
import InputCompanyInfo from "./InputCompanyInfo"
import Discounts from './Discounts'
import ChangePasswordDialog from "./ChangePasswordDialog"
import ErrorBar from './ErrorBar'

const App = ({appError, dismissError}) => (
    <Router history={getHistory()}>
        <div>
            <NavBar/>
            {appError ?
                <ErrorBar appError={appError} dismissError={dismissError}/> : null}
            <Col sm="12" lg={{size: 8, offset: 2}} className='mt-5'>
                <Switch>
                    <Route exact path="/" component={Home}/>
                    <LoginRoute exact path="/login"/>
                    <Route exact path="/signup" component={InputCompanyInfo}/>
                    <AuthRoute exact path="/discounts" component={Discounts}/>
                    <AuthRoute exact path="/profile" component={Profile}/>
                    <AuthRoute exact path="/update" component={InputCompanyInfo}/>
                    <AuthRoute exact path="/password" component={ChangePasswordDialog}/>
                </Switch>
            </Col>
        </div>
    </Router>
)

export default connect(
    (state) => ({
        appError: state.appError,
    }), (dispatch) => ({
        dismissError: () => dispatch(removeError())
    }),
)(App)
