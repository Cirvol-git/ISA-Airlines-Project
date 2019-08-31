import React, {Component} from 'react';
import {connect} from 'react-redux';
import {pretragaLetova} from '../../store/actions/LetActions';
import {getDestinations} from '../../store/actions/DestActions';
import LetoviList from './LetoviList';

class PretragaLeta extends Component {
    state = {
        od: '0',
        do: '0',
        polece: '',
        slece: '',
        tipleta: '0',
        brojosoba: '0',
        klasa: '0',
        prtljag: '0'
    }
    componentDidMount() {
        this.props.getDestinations();
    }
    handleChange = (e) => {
        this.setState({
            [e.target.id] : e.target.value
        })
    }
    handleSubmit = (e) => {
        e.preventDefault();
        if(this.state.od === '0' || this.state.do === '0') {
            alert("You must enter take off and landing points")
            return;
        }
        if(this.state.polece === '' || this.state.slece === '') {
            alert("You must enter the dates for takeoff and landing")
            return;
        }
        if(new Date(this.state.polece) > new Date(this.state.slece)) {
            alert("The date of taking off must before the one of landing")
            return;
        }
        this.props.pretragaLetova(this.state);
    }

    detalji = (id) => {
        this.props.history.push('/rezervacija/' + id)
    }

    render() {
        return(
            <div className="container">
                <form className="white" onSubmit={this.handleSubmit}>
                    <h5 className="grey-text text-darken-3">Create New Destination</h5>
                    <div>
                        <select defaultValue='0' className="input-field browser-default" id='od' onChange={this.handleChange}>
                            <option value='0' >Taking off from</option>
                            {this.props.destinacije && this.props.destinacije.map(dest => {
                            return(
                                <option value={dest.id}>{dest.grad}, {dest.ime}</option>
                            )
                            })}
                        </select>
                    </div>

                    <div >
                        <select defaultValue='0' className="input-field browser-default" id='do' onChange={this.handleChange}>
                            <option value='0' >Landing to</option>
                            {this.props.destinacije && this.props.destinacije.map(dest => {
                            return(
                                <option value={dest.id}>{dest.grad}, {dest.ime}</option>
                            )
                            })}
                        </select>
                    </div>

                    <div className="input-field">
                        Takes off:
                        <input type="date" id='polece' value = {this.state.polece} onChange={this.handleChange} />
                    </div>

                    <div className="input-field">
                        Lands:
                        <input type="date" id='slece' value = {this.state.slece} onChange={this.handleChange} />
                    </div>       
                    
                    <div className="input-field">
                        <input type="number" id='brojosoba' value={this.state.brojosoba} onChange={this.handleChange} />
                        <label htmlFor="brojosoba">Number of people</label>
                    </div>

                    <div >
                        <select defaultValue="0" className="input-field browser-default" id='tipleta' onChange={this.handleChange}>
                            <option value="0">Type of flight</option>
                            <option value="1">Round-trip</option>
                            <option value="2">One-way</option>
                            <option value="3">Multi-city</option>
                        </select>
                    </div>
                    
                    <div className="input-field">
                        <label htmlFor="tip">City</label>
                        <select defaultValue="0" className="input-field browser-default" id='klasa' onChange={this.handleChange}>
                            <option value="0" >Class</option>
                            <option value="1">First class</option>
                            <option value="2">Business class</option>
                            <option value="3">Economy class</option>
                        </select>
                    </div>

                    <div className="input-field">
                        <input type="number" id='prtljag' value={this.state.prtljag} onChange={this.handleChange} />
                        <label htmlFor="prtljag">Quantity of baggage</label>
                    </div>

                    <div className="input-field">
                        <button className="btn grey">Search</button>
                    </div>
                </form>
                
                <LetoviList 
                    letovi= {this.props.letovi}
                    detalji={this.detalji.bind(this)}
                />
            </div>
        )
    }
}

const mapStateToProps = (state) => {
    return {
        letovi: state.lets.pretraga,
        destinacije : state.dests.dests
    }
}

const mapDispatchToProps = (dispatch) => {
    return {
        pretragaLetova: (k) => dispatch(pretragaLetova(k)),
        getDestinations: () => dispatch(getDestinations())
    }
}

export default connect(mapStateToProps,mapDispatchToProps)(PretragaLeta);