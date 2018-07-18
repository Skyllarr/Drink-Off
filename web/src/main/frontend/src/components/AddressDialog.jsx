import React, {Component} from 'react'
import FormRow from './FormRow'

class AddressDialog extends Component {

    render() {
        return (<React.Fragment>
            <div className="form-group row">
                <label htmlFor="text"
                       className="col-4 col-form-label">Address</label>
            </div>
            <div className="ml-4">
                <FormRow name="Street">
                    <input id="street" name="street" placeholder="Street"
                           className="form-control" required="required"
                           type="text"
                           value={this.props.address.street}
                           onChange={this.props.handleChange.bind(this, 'street')}
                           maxLength="250"/>
                </FormRow>
                <FormRow name="House number">
                    <input id="houseNumber" name="houseNumber"
                           placeholder="House Number"
                           className="form-control" required="required"
                           type="text"
                           value={this.props.address.houseNumber}
                           onChange={this.props.handleChange.bind(this, 'houseNumber')}
                           maxLength="50"/>
                </FormRow>
                <FormRow name="Zipcode">
                    <input id="zipcode" name="zipcode" placeholder="Zipcode"
                           className="form-control" required="required"
                           type="text"
                           value={this.props.address.zipcode}
                           onChange={this.props.handleChange.bind(this, 'zipcode')}
                           maxLength="20"/>
                </FormRow>
                <FormRow name="City">
                    <input id="city" name="city" placeholder="City"
                           className="form-control" required="required"
                           type="text"
                           value={this.props.address.city}
                           onChange={this.props.handleChange.bind(this, 'city')}
                           maxLength="100"/>
                </FormRow>
                <FormRow name="State">
                    <input id="state" name="state" placeholder="State"
                           className="form-control" required="required"
                           type="text"
                           value={this.props.address.state}
                           onChange={this.props.handleChange.bind(this, 'state')}
                           maxLength="100"/>
                </FormRow>
                <FormRow name="Country">
                    <input id="country" name="country" placeholder="Country"
                           className="form-control" required="required"
                           type="text"
                           value={this.props.address.country}
                           onChange={this.props.handleChange.bind(this, 'country')}
                           maxLength="100"/>
                </FormRow>
            </div>
        </React.Fragment>)
    }
}

export default AddressDialog
