import React, {Component} from 'react';
import { connect } from 'react-redux';
import {createDestination, getDesiExeFor} from '../../store/actions/DestActions'

class createDest extends Component {
    state = {
        ime: '',
        adresa: '',
        grad: '',
        checked : false,
        sel : "0"
    }
    componentDidMount() {
        this.props.getDestforSel(this.props.korisnik.adminOd)
    }

    handleChange = (e) => {
        this.setState({
            [e.target.id] : e.target.value
        })
    }

    handleCheck = (e) => {
        let stanje = this.state.checked;
        this.setState({
            ['sel'] : "0",
            [e.target.id] : !stanje
        })
    }

    handleSubmit = (e) => {
        e.preventDefault();
        let a = this.state.checked;
        let b = this.state.sel;
        console.log(a,b)
        if(!(a === true && b === "0")) {
            this.props.createDestination(this.state, this.props.korisnik.adminOd);
            this.props.history.push('/avio');
        }else {
            alert("You must choose or enter a destinaton, or you can go back.")
        }
    }
    render() {
        return (
            <div className="container">
                <form className="white" onSubmit={this.handleSubmit}>
                    {this.state.checked === false ? (
                        <div>
                            <h5 className="grey-text text-darken-3">Create New Destination</h5>
                            <div className="input-field">
                                <input type="text" id='ime' value = {this.state.ime} onChange={this.handleChange} />
                                <label htmlFor="ime">Name</label>
                            </div>
                            <div className="input-field">
                                <input type="text" id='grad' value= {this.state.grad} onChange={this.handleChange} />
                                <label htmlFor="grad">City</label>
                            </div>
                            <div className="input-field">
                                <input type="text" id='adresa' value = {this.state.adresa} onChange={this.handleChange} />
                                <label htmlFor="adresa">Address</label>
                            </div>
                        </div>
                    ) : (
                        <div>
                            <select defaultValue="0" className="input-field browser-default" id='sel' onChange={this.handleChange}>
                                <option value="0" disabled >Destinations</option>
                                {this.props.destinacije && this.props.destinacije.map(dest => {
                                return(
                                    <option value={dest.id}>{dest.grad}, {dest.ime}</option>
                                )
                                })}
                            </select>
                        </div>
                    )}
                    <div className="input-field">
                        
                        <label>
                            <input type="checkbox" id='checked' class="filled-in grey" checked = {this.state.checked} onChange={this.handleCheck} />
                            <span>Select from already entered Destinations</span>
                        </label>
                    </div>
                    <br/>
                    <br/>
                    <div className="input-field">
                        <button className="btn grey">Create</button>
                    </div>
                </form>
            </div>
        )
    }
}

const mapStateToProps = (state) => {
    return {
        destinacije : state.dests.dests,
        korisnik : state.log.log
    }
}

const mapDispatchToProps = (dispatch) => {
    return {
        getDestforSel : (id) => dispatch(getDesiExeFor(id)),
        createDestination : (dest, idAvio) => dispatch(createDestination(dest,idAvio))

    }
}

export default connect(mapStateToProps,mapDispatchToProps)(createDest);