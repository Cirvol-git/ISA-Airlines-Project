import React, {Component} from 'react';
import {connect} from 'react-redux';
import { logIn, logOut } from '../../store/actions/LoginAction';
import axios from 'axios'

class Login extends Component {
    state = {
        email: '',
        pass: ''
    }
    componentDidMount() {
        this.props.logOut();
    }

    handleChange = (e) => {
        this.setState({
            [e.target.id] : e.target.value
        })
    }
    handleSubmit = (e) => {
        e.preventDefault();
        if(this.state.email === '' || this.state.pass === '') {
            alert("You didn't enter email or password")
        }else {
            axios({
                url: 'http://localhost:8080/api/korisnik/login',
                method: 'post',
                data: {
                    email: this.state.email,
                    pass: this.state.pass
                }
            }).then(res => {
                console.log(res);
                this.props.history.push("/home");
                this.props.logIn(res.data);   
            }).catch(e => {
                alert("Pogresno unesen email ili lozinka")
            })
            
        }
    }
    render() {
        return(
            <div className="container">
                <form className="white" onSubmit={this.handleSubmit}>
                    <h5 className="grey-text text-darken-3">Enter your email and password</h5>
                    <div className="input-field">
                        <input type="text" id='email' onChange={this.handleChange} />
                        <label htmlFor="email">Email</label>
                    </div>
                    <div className="input-field">
                        <input type="password" id='pass' onChange={this.handleChange} />
                        <label htmlFor="pass">Password</label>
                    </div>
                    <div className="input-field">
                        <button className="btn grey">Login</button>
                    </div>
                </form>
            </div>
        )
    }
}

const mapDispatchToProps = (dispatch) => {
    return {
        logIn: (k) => dispatch(logIn(k)),
        logOut: () => dispatch(logOut())
    }
}

export default connect(null,mapDispatchToProps)(Login);