import React from 'react'
import {connect} from 'react-redux'

const Discounts = ({discounts}) => (
    <div>
        <label> Your discounts:</label>
        <ul>
            {
                discounts.map(discount => {
                    return <li key={`discount-${discount.id}`}>{discount.product}</li>
                })
            }
        </ul>
    </div>
)

export default connect(
    (state) => ({
        discounts: state.discounts,
    }),
)(Discounts)
