import React, {Component} from 'react';
import { connect } from 'react-redux';
import {Table, Tabs, Tab, Button, Modal } from 'react-materialize';
import { getAvios } from '../../store/actions/AvioActions';
import {Link} from 'react-router-dom';
import axios from 'axios';

class Aviokompanije extends Component {
    state = {
        allOcene: [],
        allProsek : [],
        chosen : {},
        rating : 0
    }
    
    componentDidMount() {
        this.props.getAvios();
        this.sveOceneKorisnika(this.props.korisnik.id);
        this.allProsek();
    }

    oceni = () => {
        
        if(this.state.rating > 5 || this.state.rating < 1) {
            alert("Rating must be between 1 and 5");
            return;
        }

        let found = this.state.allOcene.find(x=> x.idAorL == this.state.chosen.id);
        console.log("found",found)


        if(found == undefined) {
            axios({
                url: 'http://localhost:8080/api/ocenaa/oceni',
                method: 'post',
                data : {
                    idAorL : this.state.chosen.id,
                    idKorisnika : this.props.korisnik.id,
                    vrednost : this.state.rating
                }
            }).then(rez => {
                if(rez.status == 200) {
                    alert("You can not rate a company you have not flown by")
                }else if(rez.status == 201) {
                    alert("Succsesfuly rated")
                    this.sveOceneKorisnika(this.props.korisnik.id);
                    this.allProsek();
                }
                
            }).catch(e => {
                console.log(e);
            })
        }else if(found.vrednost == this.state.rating) {
            alert("Allready rated with that grade");
        }else {
            axios({
                url: 'http://localhost:8080/api/ocenaa/oceni',
                method: 'post',
                data : {
                    idAorL : this.state.chosen.id,
                    idKorisnika : this.props.korisnik.id,
                    vrednost : this.state.rating
                }
            }).then(rez => {
                if(rez.status == 200) {
                    alert("You can not rate a company you have not flown by")
                }else if(rez.status == 201) {
                    alert("Succsesfuly rated")
                    this.sveOceneKorisnika(this.props.korisnik.id);
                    this.allProsek();
                }
                
            }).catch(e => {
                console.log(e);
            })
        }

        
    }

    sveOceneKorisnika = (id) => {
        axios.get('http://localhost:8080/api/ocenaa/allfrom/'+id)
        .then(rez => {
            console.log("sveOceneKorisnika", rez.data)

            this.setState({
                ...this.state,
                allOcene : rez.data
            });
        })
    }

    allProsek =() => {
        axios.get('http://localhost:8080/api/ocenaa/allprosek')
        .then(rez => {
            console.log("prosekall", rez.data)

            this.setState({
                ...this.state,
                allProsek: rez.data
            })
        })
    }

    setChosen = (x) => {
        console.log(x);
        this.setState({
            ...this.state,
            rating : 5,
            chosen : x
        })
    }

    onClickRate = (rating) => {
        console.log(rating );
        this.setState({
            ...this.state,
            rating
        })
    }

    render() {
        return (
            <div className="center container">
                <Link to={'/pretragaLetova'}><button className="btn waves-effect waves-light lighten-1 centar-align">Search flights</button></Link>
                <div className="section">
                    { this.props.avios && this.props.avios.map(avio => {

                        let prosekAvio = this.state.allProsek ? this.state.allProsek.find(x=>x.idAorL == avio.id) : undefined;
                        prosekAvio = prosekAvio == undefined ? 0 : prosekAvio;

                        let tvojaOcena = this.state.allOcene ? this.state.allOcene.find(x=>x.idAorL == avio.id) : undefined;
                        tvojaOcena = tvojaOcena == undefined ? 0 : tvojaOcena;

                        return (
                            <div className = "row">
                                <div className="card grey darken-2 card-panel hoverable">
                                    <div className="card-content white-text left-align">
                                        <p className="card-title">{avio.ime}</p>
                                        <p>Adress: {avio.adresa}</p>
                                        <p>Description: {avio.opis}</p>
                                        <div className="divider white"></div>
                                        <p>Rates: {prosekAvio.count == 0 ? "0" : (prosekAvio.sum / prosekAvio.count).toFixed(1)} Voted: {prosekAvio.count == 0 ? "0" : prosekAvio.count}</p>
                                        <p>Your rating: {tvojaOcena == 0 ? "0" : tvojaOcena.vrednost} <Button onClick={() => this.setChosen(avio)} floating medium href="#modal1" className="modal-trigger green" waves="light" icon="grade"/></p>
                                    </div>
                                    <Link to={'/brzeRezervacije/' + avio.id}><button className="btn waves-effect waves-light lighten-1 centar-align">Tickets for fast reservations</button></Link>
                                </div>
                                
                            </div>
                        )
                    })}
                </div>
                <Modal id="modal1" actions = {<div><Button modal="close" onClick={this.oceni} floating className="green" waves="light" icon ="check"/>
                                                        <Button floating modal="close" waves="light" className="red" icon="clear"/></div>} >
                    Your rating : {this.state.rating}
                    <div>
                        <Button onClick={()=>this.onClickRate(1)} floating waves="light" className="green" icon="looks_one"/>
                        <Button onClick={()=>this.onClickRate(2)} floating waves="light" className="green" icon="looks_two"/>
                        <Button onClick={()=>this.onClickRate(3)} floating waves="light" className="green" icon="looks_3"/>
                        <Button onClick={()=>this.onClickRate(4)} floating waves="light" className="green" icon="looks_4"/>
                        <Button onClick={()=>this.onClickRate(5)} floating waves="light" className="green" icon="looks_5"/>
                    </div>
                </Modal>
            </div>
        )
    }
}

const mapStateToProps = (state) => {
    return {
        avios: state.avios.avios,
        korisnik: state.log.log
    }
}

const mapDispatchToProps = (dispatch) => {
    return {
        getAvios: () => dispatch(getAvios())
    }
}

export default connect(mapStateToProps, mapDispatchToProps)(Aviokompanije);