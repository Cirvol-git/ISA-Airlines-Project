import React, {Component} from 'react';
import {connect} from 'react-redux';
import {getKorisnici} from '../../store/actions/KorisnikActions';
import {createPrijatelji, getPoslatiZahtevi} from '../../store/actions/PrijateljActions'
import KorisnikList from './KorisnikList';

class DodajPrijatelja extends Component {
    state = {
        ime: '',
        prezime: ''
    }

    componentDidMount() {
        this.props.getPoslati(this.props.korisnik);
    }

    handleChange = (e) => {
        this.setState({
            [e.target.id] : e.target.value
        })
    }

    handleSubmit = (e) => {
        e.preventDefault();
        this.props.getKorisnici(this.state);
    }

    req = (id) => {
        console.log(this.props.korisnik);
        console.log(id);
        let uZahtevima = [];
        let uPrijateljima = [];
        uZahtevima = this.props.zahtevi.filter((z)=> {
            return z.ret.id === id
        })
        console.log(uZahtevima);
        uPrijateljima = this.props.prijatelji.filter((z)=> {
            return z.ret.id === id
        })
        console.log(uPrijateljima);
        if(uZahtevima.length === 0 && uPrijateljima.length === 0) {
            console.log("Upao di treba")
            this.props.createPrijatelji(this.props.korisnik,id)
            this.props.history.push('/prijatelji');
        }
    }

    render() {
        return(
            <div className="container">
                <form className="white" onSubmit={this.handleSubmit}>
                    <h5 className="grey-text text-darken-3">Search for a friend</h5>
                    <div className="input-field">
                        <input type="text" id='ime' onChange={this.handleChange} />
                        <label htmlFor="ime">Name</label>
                    </div>
                    <div className="input-field">
                        <input type="text" id='prezime' onChange={this.handleChange} />
                        <label htmlFor="prezime">Last Name</label>
                    </div>
                    <div className="input-field">
                        <button className="btn waves-effect waves-light lighten-1 centar-align">Search</button>
                    </div>
                </form>
                <KorisnikList 
                    prijatelji = {this.props.prijatelji}
                    korisnici= {this.props.korisnici}
                    zahtevi = {this.props.zahtevi}
                    poslati = {this.props.poslati}
                    id = {this.props.korisnik}
                    req={this.req.bind(this)}
                />
            </div>
        )
    }
}

const mapStateToProps = (state) => {
    return {
        prijatelji: state.prijatelji.prijatelji,
        zahtevi: state.prijatelji.zahtevi,
        poslati: state.prijatelji.poslati,
        korisnik : state.log.log.id,
        korisnici: state.prijatelji.korisnici
    }
}

const mapDispatchToProps = (dispatch) => {
    return {
        getKorisnici: (k) => dispatch(getKorisnici(k)),
        createPrijatelji: (je,id) => dispatch(createPrijatelji(je,id)),
        getPoslati: (id) => dispatch(getPoslatiZahtevi(id))
    }
}

export default connect(mapStateToProps,mapDispatchToProps)(DodajPrijatelja);