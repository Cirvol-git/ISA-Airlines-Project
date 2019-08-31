import React, {Component} from 'react';
import { connect } from 'react-redux';
import {Link} from 'react-router-dom';
import PrijateljiList from './PrijateljiList';
import ZahteviList from './ZahteviList';
import { getPrijatelji, deletePrijatelj, acceptPrijatelj, getZahtevi } from '../../store/actions/PrijateljActions';

class Prijatelji extends Component {
    componentDidMount() {
        this.props.getPrijatelji(this.props.korisnik);
        this.props.getZahtevi(this.props.korisnik)
    }
    delete = (id) => {
        console.log(id);
        this.props.deletePrijatelj(id);
    }


    accept = (id) => {
        console.log(id);
        this.props.acceptPrijatelj(id);
    }

    render() {
        return (
            <div className="container">
                <PrijateljiList 
                    prijatelji= {this.props.prijatelji}
                    delet = { this.delete.bind(this)}
                />

                <ZahteviList 
                    zahtevi= {this.props.zahtevi}
                    accept = { this.accept.bind(this)}
                    delet = { this.delete.bind(this)}
                />
                <Link to='/pretragaKorisnika'><button className="grey centar">Add Friend</button></Link>
            </div>
        )
    }
}

const mapStateToProps = (state) => {
    return {
        prijatelji: state.prijatelji.prijatelji,
        zahtevi: state.prijatelji.zahtevi,
        korisnik : state.log.log.id
    }
}

const mapDispatchToProps = (dispatch) => {
    return {
        getPrijatelji: (id) => dispatch(getPrijatelji(id)),
        getZahtevi: (id) => dispatch(getZahtevi(id)),
        acceptPrijatelj: (id) => dispatch(acceptPrijatelj(id)),
        deletePrijatelj: (id) => dispatch(deletePrijatelj(id))
    }
}

export default connect(mapStateToProps, mapDispatchToProps)(Prijatelji);