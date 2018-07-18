import React, {Component} from 'react'
import {authenticate} from "../utils/request"
import {Alert, Button, Col, Form, FormGroup, Input, Label} from 'reactstrap'
import {connect} from 'react-redux'
import {setAuthDetails} from "../actions/actionCreators"

class LogInDialog extends Component {
    constructor() {
        super()
        this.state = {
            username: "",
            password: "",
        }
    }

    handleChange(name, event) {
        this.setState({[name]: event.target.value})
    }

    handleSubmit = (event) => {
        const username = this.state.username
        const password = this.state.password
        event.preventDefault()
        this.setState({logInError: null})
        authenticate(username, password)
            .then(result => {
                this.props.setAuthDetails(username, password, result.data.access_token)
            })
            .catch(result => {
                if (result.message != null) {
                    this.setState({logInError: result.message})
                }
            })
    }

    render() {
        let signUpErrorLabel = null
        if (this.state.logInError) {
            signUpErrorLabel = (
                <Alert color="danger">
                    {this.state.logInError}
                </Alert>)
        }
        return (
            <Col>
                <Form onSubmit={this.handleSubmit}>
                    <Col sm="12" md={{size: 8, offset: 2}} lg={{size: 8, offset: 2}} xl={{size: 6, offset: 3}}>
                        <FormGroup row>
                            <Label for="username" lg={4}>Email</Label>
                            <Col lg={8}>
                                <Input type="email"
                                       className="form-control"
                                       id="username"
                                       value={this.state.username}
                                       onChange={this.handleChange.bind(this, "username")}
                                       required/>
                            </Col>
                        </FormGroup>
                        <FormGroup row>
                            <Label for="password" lg={4}>Password</Label>
                            <Col lg ={8}>
                                <Input type="password"
                                       className="form-control"
                                       id="password"
                                       value={this.state.password}
                                       onChange={this.handleChange.bind(this, "password")}
                                       required/>
                            </Col>
                        </FormGroup>
                        {signUpErrorLabel}
                        <div className="action-row">
                            <Button type="submit" className="btn btn-primary">Log In</Button>
                        </div>
                    </Col>
                </Form>
            </Col>
        )
    }
}

export default connect(
    (store) => ({
        username: store.auth.username,
        password: store.auth.password,
    }),
    (dispatch) => ({
        setAuthDetails: (username, password, token) => dispatch(setAuthDetails(username, password, token))
    })
)(LogInDialog)

