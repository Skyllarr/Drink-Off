import React from 'react'

const FormRow = ({name, children}) => {
    return (
        <div className="form-group row">
            <label htmlFor="houseNumber" className="col-4 col-form-label">{name}</label>
            <div className="col-8">
                {children}
            </div>
        </div>)
}

export default FormRow
