import React, {Component} from 'react';
import {connect} from 'react-redux';
import { updateAvio } from '../../store/actions/AvioActions';

class AvioIzmeni extends Component {
    state = {
        id: this.props.match.params.id,
        ime: this.props.avio.find(x=> x.id == this.props.match.params.id).ime,
        adresa: this.props.avio.find(x=> x.id == this.props.match.params.id).adresa,
        opis: this.props.avio.find(x=> x.id == this.props.match.params.id).opis
    }

    handleChange = (e) => {
        this.setState({
            [e.target.id] : e.target.value
        })
    }
    handleSubmit = (e) => {
        e.preventDefault();
        this.props.updateAvio(this.state);
        this.props.history.push('/avio');
    }
    render() {
        return(
            <div className="container">
                <form className="white" onSubmit={this.handleSubmit}>
                    <h5 className="grey-text text-darken-3">Update company info</h5>
                    <div className="input-field">
                        <input type="text" id='ime' value={this.state.ime} onChange={this.handleChange} />
                        <label htmlFor="ime">Name</label>
                    </div>
                    <div className="input-field">
                        <input type="text" id='adresa' value={this.state.adresa} onChange={this.handleChange} />
                        <label htmlFor="adresa">Address</label>
                    </div>
                    <div className="input-field">
                        <input type="text" id='opis' value={this.state.opis} onChange={this.handleChange} />
                        <label htmlFor="opis">Opis</label>
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
    console.log('stanje',state);
    return {
        avio: state.avios.avios
    }
}

const mapDispatchToProps = (dispatch) => {
    return {
        updateAvio: (avio) => dispatch(updateAvio(avio))
    }
}

export default connect(mapStateToProps,mapDispatchToProps)(AvioIzmeni);