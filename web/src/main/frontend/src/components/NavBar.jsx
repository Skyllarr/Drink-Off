import React, {Component} from 'react'
import {setLanguage} from "../actions/actionCreators"
import {connect} from 'react-redux'
import {LOCALES} from '../config/locales'
import './NavBar.css'

import {Dropdown, DropdownItem, DropdownMenu, DropdownToggle} from 'reactstrap'
import {isAuthenticated, logout} from "../utils/authUtils"
import {nextPath} from "../routes/history"

const RightSideNavBar = ({isAuthenticated}) => {
    if (isAuthenticated) {
        return (
            <React.Fragment>
                <a key="logout" className="nav-item nav-link menu-link" role="button" onClick={logout}>Logout</a>
            </React.Fragment>
        )
    } else {
        return (
            <React.Fragment>
                <a key="signup" className="nav-item nav-link menu-link" role="button" onClick={() => {
                    nextPath('/signup')
                }}>Sign Up</a>
                <a key="login" className="nav-item nav-link menu-link" role="button" onClick={() => {
                    nextPath('/login')
                }}>Log In</a>
            </React.Fragment>
        )
    }
}

const LeftSideNavBar = () => (
    <React.Fragment>
        <a className="nav-item nav-link menu-link" role="button" onClick={() => {
            nextPath('/profile')
        }}>Profile</a>
        <a className="nav-item nav-link menu-link" role="button" onClick={() => {
            nextPath('/discounts')
        }}>Discounts</a>
    </React.Fragment>
)

class NavBar extends Component {

    handleSelect = (event) => {
        this.props.setLanguage(event.target.innerText)
    }

    constructor(props) {
        super(props)

        this.toggle = this.toggle.bind(this)
        this.state = {
            dropdownOpen: false
        }
    }

    toggle() {
        this.setState(prevState => ({
            dropdownOpen: !prevState.dropdownOpen
        }))
    }

    render() {
        const options = LOCALES.map((x, y) => (
            <DropdownItem className="dropdown-a" key={y}>{x.toUpperCase()}</DropdownItem>))
        return (
            <nav className="navbar navbar-expand-lg  navbar-dark background-navbar">
                <a className="navbar-brand nav-margin-left navbar-nav app-name" role="button" onClick={() => {
                        nextPath('/')
                    }} >Drink Off</a>
                <button className="navbar-toggler " type="button" data-toggle="collapse"
                            data-target=".navbar-collapse-items"
                            aria-controls="navbar-collapse-items" aria-expanded="false" aria-label="Toggle navigation">
                    <span className="navbar-toggler-icon"/>
                </button>
                <div className="collapse navbar-collapse navbar-collapse-items">
                    <div className="navbar-nav mr-auto">
                        {this.props.isAuthenticated ? <LeftSideNavBar/> : null}
                    </div>
                    <div className="navbar-nav nav-margin-right">
                        <RightSideNavBar isAuthenticated={this.props.isAuthenticated}/>
                        <Dropdown isOpen={this.state.dropdownOpen} toggle={this.toggle} inNavbar={true} className="dropdown-a not-functional">
                            <DropdownToggle caret nav={true} className="dropdown-a">
                                {this.props.chosenLocale.toUpperCase()}
                            </DropdownToggle>
                            <DropdownMenu onClick={this.handleSelect}>
                                {options}
                            </DropdownMenu>
                        </Dropdown>
                    </div>
                </div>
            </nav>
        )
    }
}

export default connect(
    (store) => ({
        chosenLocale: store.locale,
        auth: store.auth,
        isAuthenticated: isAuthenticated(store.auth),
    }),
    dispatch => ({
        setLanguage: language => dispatch(setLanguage(language)),
    })
)(NavBar)
