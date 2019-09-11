import React, {Component} from 'react';
import { connect } from 'react-redux';
import {updateDestination} from '../../store/actions/DestActions'

class updateDest extends Component {
    state = {
        id: this.props.match.params.id,
        ime: this.props.destinacije.find(x=> x.id == this.props.match.params.id).ime,
        adresa: this.props.destinacije.find(x=> x.id == this.props.match.params.id).adresa,
        grad:  this.props.destinacije.find(x=> x.id == this.props.match.params.id).grad
    }

    handleChange = (e) => {
        this.setState({
            [e.target.id] : e.target.value
        })
    }
    handleSubmit = (e) => {
        e.preventDefault();
        this.props.updateDestination(this.state);
        this.props.history.push('/avio');
    }
    render() {
        console.log('updateprops',this.props);
        return (
            <div className="container">
                <form className="white" onSubmit={this.handleSubmit}>
                    <h5 className="grey-text text-darken-3">Update destination</h5>
                    <div className="input-field">
                        <input type="text" id='ime' value={this.state.ime} onChange={this.handleChange} />
                        <label className="active" htmlFor="ime">Name</label>
                    </div>
                    <div className="input-field">
                        <input type="text" id='adresa' value={this.state.adresa} onChange={this.handleChange} />
                        <label className="active" htmlFor="adresa">Address</label>
                    </div>
                    <div className="input-field">
                        <input type="text" id='grad' value={this.state.grad} onChange={this.handleChange} />
                        <label className="active" htmlFor="grad">City</label>
                    </div>
                    <div className="input-field">
                        <button className="btn grey">Update</button>
                    </div>
                </form>
            </div>
        )
    }
}

const mapStateToProps = (state) => {
    return {
        destinacije: state.avios.dests
    }
}

const mapDispatchToProps = (dispatch) => {
    return {
        updateDestination : (dest) => dispatch(updateDestination(dest))
    }
}

export default connect(mapStateToProps,mapDispatchToProps)(updateDest);