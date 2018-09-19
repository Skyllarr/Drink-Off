import React, {Component} from 'react'

import {Alert, Button, Col, Form, FormGroup, Input, Label} from 'reactstrap'
import {changePassword} from "../utils/request"
import {nextPath} from "../routes/history"

class ChangePasswordDialog extends Component {

    constructor() {
        super()
        this.state = {
            oldPassword: "",
            newPassword: "",
            passwordCheck: "",
        }
        this.handleSubmit = this.handleSubmit.bind(this)
    }

    handleChange(name, event) {
        this.setState({[name]: event.target.value})
    }

    handleSubmit(event) {
        event.preventDefault()
        this.setState({signUpError: null})
        changePassword(this.state.oldPassword, this.state.newPassword).then(
            () => {
                nextPath('/profile')
            }
        ).catch((result) => {
            if (result.message != null) {
                this.setState({signUpError: result.message})
            }
        })
    }

    render() {
        let errorLabel = null
        if (this.state.signUpError) {
            errorLabel = (
                <Alert color="danger">
                    {this.state.signUpError}
                </Alert>)
        }
        return <Col>
            <Form onSubmit={this.handleSubmit}>
                <Col sm="12" md={{size: 8, offset: 2}} lg={{size: 8, offset: 2}} xl={{size: 6, offset: 3}}>
                    <FormGroup row>
                        <Label for="password" lg={4}>Current Password</Label>
                        <Col lg={8}>
                            <Input type="password"
                                   className="form-control"
                                   id="password"
                                   value={this.state.oldPassword}
                                   onChange={this.handleChange.bind(this, "oldPassword")}
                                   required="required"/>
                        </Col>
                    </FormGroup>
                    <FormGroup row>
                        <Label for="new-password" lg={4}>New Password</Label>
                        <Col lg={8}>
                            <Input type="password"
                                   className="form-control"
                                   id="new-password"
                                   value={this.state.newPassword}
                                   onChange={this.handleChange.bind(this, "newPassword")}
                                   required="required"/>
                        </Col>
                    </FormGroup>
                    <FormGroup row>
                        <Label for="confirm-password" lg={4}>Confirm Password</Label>
                        <Col lg={8}>
                            <Input type="password"
                                   className="form-control"
                                   id="confirm-password"
                                   value={this.state.passwordCheck}
                                   onChange={this.handleChange.bind(this, "passwordCheck")}
                                   required="required"/>
                        </Col>
                    </FormGroup>
                    {errorLabel}
                    <div className="action-row">
                        <Button type="submit" className="btn btn-primary">Change Password</Button>
                    </div>
                </Col>
            </Form>
        </Col>
    }
}

export default ChangePasswordDialog
