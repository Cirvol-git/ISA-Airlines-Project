import React, {Component} from 'react';
import {connect} from 'react-redux';
import {createFastRes} from '../../store/actions/RezervacijaActions'
import { getLets } from '../../store/actions/LetActions';
import {Button, Modal} from 'react-materialize';

class ListaBrzihRez extends Component{

    state = {}

    componentDidMount() {
        this.props.getLets(this.props.match.params.id);
    }

    setChosen = (x) => {
        console.log(x);
        this.setState(x);
    }

    resrve = () => {
        this.props.createFastRes(this.props.korisnik.id,this.state,"");
        //this.props.history.push('/avios');
    }

    render() {
        const listOfRows = this.props.letovi && this.props.letovi.map((pri) => {
                
                let a = new Date(pri.polece);
                let b = new Date(pri.slece);
                return (
                pri.rows.filter(x => x.tip == 4 && x.rezervisano == null && x.odobreno == true).map(r=> {
                    return (
                        <tr>
                            <td>
                                {pri.odDest.grad}
                            </td>
                            <td>
                                {pri.doDest.grad}
                            </td>
                            <td>
                                {a.getUTCDate()}. {a.getUTCMonth()}. {a.getUTCFullYear()}.
                            </td>
                            <td>
                                {b.getUTCDate()}. {b.getUTCMonth()}. {b.getUTCFullYear()}.
                            </td>
                            <td>
                                {a.getUTCHours()}:{a.getUTCMinutes()}
                            </td>
                            <td>
                                {b.getUTCHours()}:{b.getMinutes()}
                            </td>
                            <td>
                                {r.red}
                            </td>
                            <td>
                                {r.kolona}
                            </td>
                            <td>
                                ${pri.cEconomy}
                            </td>
                            {this.props.korisnik.admin === false ? (
                                <td>
                                    <Button onClick={()=>{this.setChosen(r)}} floating medium href="#modal1" className="modal-trigger green" waves="light" icon="add_circle_outline"/>
                                </td>
                            ) : (
                                null
                            )}
                            
                        </tr>
                    )
            }))
        })
        
        return (
            <div>
            <table className="highlight striped">
                <thead>
                    <tr>
                        <th>Takes of from</th>
                        <th>Arives to</th>
                        <th>Polece date</th>
                        <th>Slece date</th>
                        <th>Polece</th>
                        <th>Slece</th>
                        <th>Row</th>
                        <th>Column</th>
                        <th>Cena</th>
                        {this.props.korisnik.admin === false ? (
                            <th>Reserve</th>
                        ) : (
                            null
                        )}
                    </tr>
                </thead>
                <tbody>
                    {listOfRows}
                </tbody>
            </table>
            <Modal id="modal1" actions = {<div><Button modal="close" onClick={this.resrve} floating className="green" waves="light" icon ="check"/>
                                                        <Button floating modal="close" waves="light" className="red" icon="clear"/></div>} >
                Are you sure you want to confirm the reservation?
            </Modal>
            </div>
        )
    }
}

const mapStateToProps = (state) => {
    return {
        letovi: state.lets.letsOfAvio,
        korisnik: state.log.log
    }
}

const mapDispatchToProps = (dispatch) => {
    return {
        getLets: (id) => dispatch(getLets(id)),
        createFastRes: (korisnk,avio,seat) => dispatch(createFastRes(korisnk,avio,seat))
    }
}

export default connect(mapStateToProps,mapDispatchToProps)(ListaBrzihRez);