import {PureComponent} from 'react' // eslint-disable-line import/no-unresolved // eslint-disable-line import/no-unresolved

export class LoadingRoute extends PureComponent {
    static defaultProps = {
        loading: null,
    }

    handlePersistorState = () => {
        const {persistor} = this.props
        let {bootstrapped} = persistor.getState()
        if (bootstrapped) {
            if (this.props.onBeforeLift) {
                Promise.resolve(this.props.onBeforeLift())
                    .then(() => this.setState({bootstrapped: true}))
                    .catch(() => this.setState({bootstrapped: true}))
            } else {
                this.setState({bootstrapped: true})
            }
            this._unsubscribe && this._unsubscribe()
        }
    }

    constructor() {
        super()
        this.state = {
            bootstrapped: false,
            timeoutPassed: false,
        }
    }

    componentDidMount() {
        this._unsubscribe = this.props.persistor.subscribe(
            this.handlePersistorState
        )
        this.handlePersistorState()
        window.setTimeout(() => {
            this.setState({timeoutPassed: true})
        }, 100)
    }

    componentWillUnmount() {
        this._unsubscribe && this._unsubscribe()
    }

    render() {
        if (process.env.NODE_ENV !== 'production') {
            if (typeof this.props.children === 'function' && this.props.loading)
                console.error(
                    'redux-persist: PersistGate expects either a function child or loading prop, but not both. The loading prop will be ignored.'
                )
        }
        if (typeof this.props.children === 'function') {
            return this.props.children(this.state.bootstrapped)
        }
        return this.state.bootstrapped && this.state.timeoutPassed ? this.props.children : this.props.loading
    }
}
