import React, {Component} from 'react'
import {connect} from 'react-redux'

class Discounts extends Component {

    render() {
        return (
            <div>
                <label> Your discounts:</label>
                <ul>
                    {
                        this.props.discounts.map(discount => {
                            return <li key={`discount-${discount.id}`}>{discount.product}</li>
                        })
                    }
                </ul>
            </div>
        )
    }
}

export default connect(
    (state) => ({
        discounts: state.discounts,
    }),
)(Discounts)
