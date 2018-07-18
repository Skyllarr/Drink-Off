import React, {Component} from 'react'
import {Alert, Button} from 'reactstrap'
import {connect} from 'react-redux'

import {editCompanyInfo, signUpAndLogIn} from "../utils/request"
import {logout} from "../utils/authUtils"
import {nextPath} from "../routes/history"

import AddressDialog from './AddressDialog'
import FormRow from "./FormRow"

class SignUpDialog extends Component {
    checkPasswordsMatch = () => {
        let match = this.state.company.password === this.state.passwordCheck
        if (!match) {
            this.setState({signUpError: "Passwords do not match"})
        }
        return match
    }

    handleSubmit = (event) => {
        event.preventDefault()
        this.setState({signUpError: null})
        if (this.props.onSubmit === 'update') {
            editCompanyInfo(this.state.company)
                .then(() => {
                    nextPath('/profile')
                })
                .catch((responseObject) => {
                    if (responseObject.message != null) {
                        this.setState({signUpError: responseObject.message})
                    }
                })
        } else {
            if (this.checkPasswordsMatch()) {
                logout()
                signUpAndLogIn(this.state.company)
                    .then(() => {
                        nextPath('/profile')
                    })
                    .catch((responseObject) => {
                        if (responseObject.message != null) {
                            this.setState({signUpError: responseObject.message})
                        }
                    })
            }
        }
    }

    constructor(props) {
        super(props)
        this.state = {
            company: {
                name: props.profileData.name || "",
                password: "",
                email: props.profileData.user ? props.profileData.user.email : "",
                url: props.profileData.url || "",
                crn: props.profileData.crn || "",
                description: props.profileData.description || "",
                phoneNumber: props.profileData.phoneNumber || "",
                address: {
                    street: props.profileData.address ? props.profileData.address.street : "",
                    houseNumber: props.profileData.address ? props.profileData.address.houseNumber : "",
                    city: props.profileData.address ? props.profileData.address.city : "",
                    state: props.profileData.address ? props.profileData.address.state : "",
                    country: props.profileData.address ? props.profileData.address.country : "",
                    zipcode: props.profileData.address ? props.profileData.address.zipcode : ""
                },
            },
            signUpError: null,
            passwordCheck: "",
        }
        this.handleSubmit = this.handleSubmit.bind(this)
    }

    nextPath(path) {
        this.props.history.push(path)
    }

    /**
     * @param obj Array where every value represents name of component's property or nested property
     *        Each value is child of property previously in the array
     * @param event Holds value to update component's most inner property specified in the obj param
     *
     * Function will make a copy of state and traverse in this copy to the most inner property specified in obj.
     * It will update this property with value specified in event.
     * The component's state will be updated with the most outer property to force component's render
     */
    handleChange(obj, event) {
        // name of the state's property that needs to be changed
        const keyToUpdate = obj[0]
        let currentState = {...this.state}
        let copyOfState = currentState
        // traverse to nested states in obj and update deepest value
        for (let i = 0; i < obj.length; i++) {
            if (i === obj.length - 1) {
                currentState[obj[i]] = event.target.value
            } else {
                currentState = currentState[obj[i]]
            }
        }
        // update state with the most outer property
        this.setState({
            [keyToUpdate]: copyOfState[keyToUpdate]
        })
    }

    render() {
        let signUpErrorLabel = null
        if (this.state.signUpError) {
            signUpErrorLabel = (
                <Alert color="danger">
                    {this.state.signUpError}
                </Alert>
            )
        }

        return (
            <div className="row">
                <div className="col-md-9">
                    <div className="card">
                        <div className="card-body">
                            <div className="row">
                                <div className="col-md-12">
                                    <h4>{this.props.header ? this.props.header : 'Company Profile'} </h4>
                                    <hr/>
                                </div>
                            </div>
                            <div className="row">
                                <div className="col-md-12">
                                    <form onSubmit={this.handleSubmit}>
                                        <FormRow name="Name">
                                            <input id="name" name="name" placeholder="Name"
                                                   className="form-control"
                                                   minLength="2"
                                                   value={this.state.company.name}
                                                   onChange={this.handleChange.bind(this, ['company', 'name'])}
                                                   maxLength="50"
                                                   type="text" required/>
                                        </FormRow>
                                        <FormRow name="Email">
                                            <input id="email" name="email" placeholder="Email"
                                                   className="form-control" type="email"
                                                   value={this.state.company.email}
                                                   onChange={this.handleChange.bind(this, ['company', "email"])}
                                                   required/>
                                        </FormRow>

                                        {this.props.onSubmit === 'update' ? null :
                                        <React.Fragment>
                                            <FormRow name="Password">
                                                <input id="password" name="password" placeholder="Password"
                                                           className="form-control" required="required"
                                                           type="password"
                                                           value={this.state.company.password}
                                                           onChange={this.handleChange.bind(this, ['company', 'password'])}
                                                           minLength="5"
                                                           maxLength="100"/>
                                            </FormRow>
                                            <FormRow name="Confirm Password">
                                                <input id="confirm-password" name="password"
                                                           placeholder="Password re-enter"
                                                           className="form-control" required="required"
                                                           type="password"
                                                           value={this.state.passwordCheck}
                                                           onChange={this.handleChange.bind(this, ['passwordCheck'])}
                                                           maxLength="100"/>
                                            </FormRow>
                                        </React.Fragment>
                                        }
                                        <FormRow name="Phone Number">
                                            <input id="phoneNumber" name="phoneNumber" placeholder="Phone Number"
                                                   className="form-control" type="text"
                                                   value={this.state.company.phoneNumber}
                                                   onChange={this.handleChange.bind(this, ['company', "phoneNumber"])}
                                                   maxLength="20" required/>
                                        </FormRow>

                                        <FormRow name="URL">
                                            <input id="text" name="text" placeholder="URL"
                                                   className="form-control" required="required"
                                                   type="text"
                                                   value={this.state.company.url}
                                                   onChange={this.handleChange.bind(this, ['company', "url"])}
                                                   maxLength="2000"/>
                                        </FormRow>

                                        <FormRow name="CRN">
                                            <input id="crn" name="crn" placeholder="CRN"
                                                   className="form-control" required="required"
                                                   type="text"
                                                   value={this.state.company.crn}
                                                   onChange={this.handleChange.bind(this, ['company', "crn"])}
                                                   maxLength="200"/>
                                        </FormRow>

                                        <FormRow name="Description">
                                            <textarea id="description" name="description" cols="40"
                                                          rows="4" className="form-control"
                                                          value={this.state.company.description}
                                                          onChange={this.handleChange.bind(this, ['company', "description"])}
                                                          maxLength="2500"/>
                                        </FormRow>

                                        <AddressDialog address={this.state.company.address}
                                                       handleChange={(field, event) => {
                                                           this.handleChange(['company', 'address', field], event)
                                                       }}/>
                                        {signUpErrorLabel}
                                        <div className="form-group row action-row">
                                            <Button type="submit" className="btn btn-primary">
                                                Submit
                                            </Button>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        )
    }
}

export default connect(
    (state) => ({
        profileData: state.profileData,
    }),
)(SignUpDialog)
