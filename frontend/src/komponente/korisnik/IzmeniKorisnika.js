import React, {Component} from 'react';
import {connect} from 'react-redux';
import { updateKorisnik } from '../../store/actions/KorisnikActions';

class IzmeniKorisnika extends Component {
    state = {
        id : this.props.log.id,
        email : this.props.log.email,
        pass : null,
        potvrdiPass: null,
        ime : this.props.log.ime,
	    prezime : this.props.log.prezime,
	    grad : this.props.log.grad,
	    telefon : this.props.log.telefon
    }
    handleChange = (e) => {
        this.setState({
            [e.target.id] : e.target.value
        })
    }
    handleSubmit = (e) => {
        e.preventDefault();
        let a = this.state.pass;
        let b = this.state.potvrdiPass;
        if(a === '' || b === '' ||  a !== b) {
            alert("You must enter same password two times");
        }else {
            this.props.update(this.state, this.props);
        }
        
    }

    render() {
        return(
            <div className="container">
                <form className="white" onSubmit={this.handleSubmit}>
                    <h5 className="grey-text text-darken-3">Change user</h5>
                    <div className="input-field">
                        <input type="text" id='ime' value={this.state.ime} onChange={this.handleChange} />
                        <label htmlFor="ime">Name</label>
                    </div>
                    <div className="input-field">
                        <input type="text" id='prezime' value={this.state.prezime} onChange={this.handleChange} />
                        <label htmlFor="prezime">Last Name</label>
                    </div>
                    <div className="input-field">
                        <input type="text" id='email' value={this.state.email} onChange={this.handleChange} />
                        <label htmlFor="email">Email</label>
                    </div>
                    <div className="input-field">
                        <input type="password" id='pass' onChange={this.handleChange} />
                        <label htmlFor="pass">Password</label>
                    </div>
                    <div className="input-field">
                        <input type="password" id='potvrdiPass' onChange={this.handleChange} />
                        <label htmlFor="potvrdiPass">Confirm Password</label>
                    </div>
                    <div className="input-field">
                        <input type="text" id='grad' value={this.state.grad} onChange={this.handleChange} />
                        <label htmlFor="grad">City</label>
                    </div>
                    <div className="input-field">
                        <input type="text" id='telefon' value={this.state.telefon} onChange={this.handleChange} />
                        <label htmlFor="telefon">Phone</label>
                    </div>
                    <div className="input-field">
                        <button className="btn grey">Confirm changes</button>
                    </div>
                </form>
            </div>
        )
    }
}

const mapStateToProps = (state) => {
    return {
        log : state.log.log
    }
}

const mapDispatchToProps = (dispatch) => {
    return {
        update: (k,props) => dispatch(updateKorisnik(k,props))
    }
}

export default connect(mapStateToProps,mapDispatchToProps)(IzmeniKorisnika);