import React from 'react'

import FormRow from './FormRow'

const AddressDialog = ({address, handleChange}) => (
    <React.Fragment>
        <div className="form-group row">
            <label htmlFor="text"
                   className="col-4 col-form-label">Address</label>
        </div>
        <div className="ml-4">
            <FormRow name="Street">
                <input id="street" name="street" placeholder="Street"
                       className="form-control" required="required"
                       type="text"
                       value={address.street}
                       onChange={handleChange.bind(this, 'street')}
                       maxLength="250"/>
            </FormRow>
            <FormRow name="House number">
                <input id="houseNumber" name="houseNumber"
                       placeholder="House Number"
                       className="form-control" required="required"
                       type="text"
                       value={address.houseNumber}
                       onChange={handleChange.bind(this, 'houseNumber')}
                       maxLength="50"/>
            </FormRow>
            <FormRow name="Zipcode">
                <input id="zipcode" name="zipcode" placeholder="Zipcode"
                       className="form-control" required="required"
                       type="text"
                       value={address.zipcode}
                       onChange={handleChange.bind(this, 'zipcode')}
                       maxLength="20"/>
            </FormRow>
            <FormRow name="City">
                <input id="city" name="city" placeholder="City"
                       className="form-control" required="required"
                       type="text"
                       value={address.city}
                       onChange={handleChange.bind(this, 'city')}
                       maxLength="100"/>
            </FormRow>
            <FormRow name="State">
                <input id="state" name="state" placeholder="State"
                       className="form-control" required="required"
                       type="text"
                       value={address.state}
                       onChange={handleChange.bind(this, 'state')}
                       maxLength="100"/>
            </FormRow>
            <FormRow name="Country">
                <input id="country" name="country" placeholder="Country"
                       className="form-control" required="required"
                       type="text"
                       value={address.country}
                       onChange={handleChange.bind(this, 'country')}
                       maxLength="100"/>
            </FormRow>
        </div>
    </React.Fragment>
)

export default AddressDialog
