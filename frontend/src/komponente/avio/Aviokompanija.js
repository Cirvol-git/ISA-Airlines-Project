import React, {Component} from 'react';
import { connect } from 'react-redux';
import DestList from '../dest/DestList';
import AvioList from './AvioList';
import {getAvio} from '../../store/actions/AvioActions'
import {Link} from 'react-router-dom';
import axios from 'axios';

class Aviokompanija extends Component {
    state = {
        sum : 0,
        counter : 0
    }

    componentDidMount() {
        this.props.getAvio(this.props.id);
        this.prosek(this.props.id);
    }

    prosek =(id) => {
        axios.get('http://localhost:8080/api/ocenaa/prosek/'+id)
        .then(rez => {
            let sum = 0;
            rez.data.forEach(x=> {
                sum = sum + x.vrednost
            })
            this.setState({
                sum,
                counter : rez.data.length
            });
        })
    }

    render() {
        return (
            <div className = "center container">
                <div className = "row">
                    <Link to={'avio/' + this.props.id} className="centar">
                    { this.props.avio && this.props.avio.map(avio => {
                        return (
                        <div className = "col s12 m12">
                            <div>
                                <div className="card grey darken-2 card-panel hoverable">
                                    <div className="card-content white-text left-align">
                                        <p className="card-title">{avio.ime}</p>
                                        <p>Adress: {avio.adresa}</p>
                                        <p>Description: {avio.opis}</p>
                                        <div className="divider white"></div>
                                        <p>Rates: {this.state.sum == 0 ? "0" : (this.state.sum / this.state.counter).toFixed(1)} Voted: {this.state.counter}</p>
                                    </div>
                                    <Link to={'/brzeRezervacije/' + avio.id}><button className="btn waves-effect waves-light lighten-1 centar-align">Tickets for fast reservations</button></Link>
                                </div>
                                
                            </div>
                        </div>
                        )
                    })}  
                    </Link>
                </div>
                
                
                <div className="container">
                    <DestList dests ={this.props.destinacije} />
                </div>
            </div>
        )
    }
}

const mapStateToProps = (state) => {
    console.log('stanje',state);
    return {
        avio: state.avios.avios,
        destinacije: state.avios.dests,
        id: state.log.log.adminOd
    }
}

const mapDispatchToProps = (dispatch) => {
    return {
        getAvio: (id) => dispatch(getAvio(id))
    }
}

export default connect(mapStateToProps,mapDispatchToProps)(Aviokompanija);