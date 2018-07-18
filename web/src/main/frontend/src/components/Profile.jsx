import React, {Component} from 'react'
import {connect} from 'react-redux'
import {loadMyData, loadMyDiscounts} from "../utils/request"
import {Button} from 'reactstrap'
import {nextPath} from "../routes/history"

class Profile extends Component {

    handleSubmit = (event) => {
        event.preventDefault()
        nextPath('/update')
    }

    constructor() {
        super()
        this.state = {
            errorMessage: null,
        }
    }

    componentDidMount() {
        loadMyData()
        loadMyDiscounts()
    }

    render() {
        return (
            <div>
                <div className="row">
                    <div className="col-md-9">
                        <div className="card">
                            <div className="card-body">
                                <div className="row">
                                    <div className="col-md-12">
                                        <h4>Company Profile</h4>
                                        <hr/>
                                    </div>
                                </div>
                                <div className="row">
                                    <div className="col-md-12">
                                        <form onSubmit={this.handleSubmit}>
                                            <div className="form-group row">
                                                <label htmlFor="username"
                                                       className="col-4 col-form-label">Name</label>
                                                <label
                                                    className="col-8 col-form-label"> {this.props.profileData.name} </label>
                                            </div>
                                            <div className="form-group row">
                                                <label htmlFor="name" className="col-4 col-form-label">Email</label>
                                                <div className="col-8 col-form-label">
                                                    <label> {this.props.profileData.user ? this.props.profileData.user.email : null} </label>
                                                </div>
                                            </div>
                                            <div className="form-group row">
                                                <label htmlFor="lastname" className="col-4 col-form-label">Phone
                                                    Number</label>
                                                <div className="col-8 col-form-label">
                                                    <label> {this.props.profileData.phoneNumber} </label>
                                                </div>
                                            </div>
                                            <div className="form-group row">
                                                <label htmlFor="text" className="col-4 col-form-label">URL</label>
                                                <div className="col-8 col-form-label">
                                                    <label> {this.props.profileData.url} </label>
                                                </div>
                                            </div>
                                            <div className="form-group row">
                                                <label htmlFor="text" className="col-4 col-form-label">CRN</label>
                                                <div className="col-8 col-form-label">
                                                    <label> {this.props.profileData.crn} </label>
                                                </div>
                                            </div>
                                            <div className="form-group row">
                                                <label htmlFor="email"
                                                       className="col-4 col-form-label">Description</label>
                                                <div className="col-8 col-form-label">
                                                    <label> {this.props.profileData.description} </label>
                                                </div>
                                            </div>
                                            <div className="form-group row">
                                                <label htmlFor="website"
                                                       className="col-4 col-form-label">Address</label>
                                            </div>
                                            {this.props.profileData.address ?
                                                <div className="ml-4">
                                                    <div className="form-group row">
                                                        <label htmlFor="publicinfo"
                                                               className="col-4 col-form-label">Street</label>
                                                        <div className="col-8 col-form-label">
                                                            <label> {this.props.profileData.address.street} </label>
                                                        </div>
                                                    </div>
                                                    <div className="form-group row">
                                                        <label htmlFor="newpass" className="col-4 col-form-label">House
                                                            Number</label>
                                                        <div className="col-8 col-form-label">
                                                            <label> {this.props.profileData.address.houseNumber} </label>
                                                        </div>
                                                    </div>
                                                    <div className="form-group row">
                                                        <label htmlFor="newpass"
                                                               className="col-4 col-form-label">Zipcode</label>
                                                        <div className="col-8 col-form-label">
                                                            <label> {this.props.profileData.address.zipcode} </label>
                                                        </div>
                                                    </div>
                                                    <div className="form-group row">
                                                        <label htmlFor="newpass"
                                                               className="col-4 col-form-label">City</label>
                                                        <div className="col-8 col-form-label">
                                                            <label> {this.props.profileData.address.city} </label>
                                                        </div>
                                                    </div>
                                                    <div className="form-group row">
                                                        <label htmlFor="newpass"
                                                               className="col-4 col-form-label">State</label>
                                                        <div className="col-8 col-form-label">
                                                            <label> {this.props.profileData.address.state} </label>
                                                        </div>
                                                    </div>
                                                    <div className="form-group row">
                                                        <label htmlFor="newpass"
                                                               className="col-4 col-form-label">Country</label>
                                                        <div className="col-8 col-form-label">
                                                            <label> {this.props.profileData.address.country} </label>
                                                        </div>
                                                    </div>
                                                </div> : null}
                                            <div className="btn-toolbar row ml-2 action-row">
                                                <Button type="button" className="btn btn-primary" onClick={() => {
                                                    nextPath('/update')
                                                }}>Update Profile
                                                </Button>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                {this.props.errorMessage}
            </div>
        )
    }
}

export default connect(
    (state) => ({
        profileData: state.profileData,
        discounts: state.discounts,
    }),
)(Profile)
