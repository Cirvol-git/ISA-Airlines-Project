import React, {Component} from 'react';
import {connect} from 'react-redux';
import {Table, Tabs, Tab, Button, Modal } from 'react-materialize';
import { getReservations, getRezInvites, confirmReservation, cancelReservation } from '../../store/actions/RezervacijaActions';

class RezervacijeList extends Component {

    state = {
        chosen: {}
    }

    componentDidMount() {
        this.props.getReservations(this.props.korisnik.id);
        this.props.getRezInvites(this.props.korisnik.id);
    }

    setChosen = (x) => {
        console.log(x);
        this.setState({
            chosen : x
        })
    }

    onClickPotvrdi = () => {
        this.props.confirmReservation(this.state.chosen);
    }

    onClickOtkazi = (tip) => {
        this.props.cancelReservation(this.state.chosen,tip);
    }


    render() {
        return (
            <div className="container">
                
            <Tabs className="tab-demo white-text grey darken-2 z-depth-1 tabs-fixed-width" >
                <Tab title="Confirmed" active >

                    <Table options={{centered: true,striped: true}} >
                        <thead>
                            <tr>
                                <th data-field="id">
                                    From 
                                </th>
                                <th data-field="name">
                                    To
                                </th>
                                <th data-field="price">
                                    Takes of
                                </th>
                                <th data-field="price">
                                    Lands
                                </th>
                                <th data-field="price">
                                    Seat class
                                </th>
                                <th data-field="price">
                                    Row
                                </th>
                                <th data-field="price">
                                    Seat
                                </th>
                                <th data-field="price">
                                    Price
                                </th>
                                <th data-field="price">
                                    Cancel
                                </th>
                            </tr>
                        </thead>
                        <tbody>
                            {this.props.rezervacije && this.props.rezervacije.map((x, i, niz)=> {
                                
                                let a = new Date(x.let.polece);
                                let b = new Date(x.let.slece);
                                return (
                                    <tr>
                                        <td>
                                            {x.let.odDest.grad}
                                        </td>
                                        <td>
                                            {x.let.doDest.grad}
                                        </td>
                                        <td>
                                            {a.getUTCDate()}. {a.getUTCMonth()}. {a.getUTCFullYear()}. {a.getUTCHours()}:{a.getUTCMinutes()} 
                                        </td>
                                        <td>
                                            {b.getUTCDate()}. {b.getUTCMonth()}. {b.getUTCFullYear()}. {b.getUTCHours()}:{b.getMinutes()}
                                        </td>
                                        <td>
                                            {(x.tip === 1 ? "First" : (x.tip === 2 ? "Business" : (x.tip === 3 ? "Economy" : "Fast Reservation")))}
                                        </td>
                                        <td>
                                            {x.red+1}
                                        </td>
                                        <td>
                                            {x.kolona+1}
                                        </td>
                                        <td>
                                            {(x.tip === 1 ? x.let.cFirst : (x.tip === 2 ? x.let.cBusiness : x.let.cEconomy))}
                                        </td>
                                        <td>
                                            {new Date(x.rezervisano).getTime() <= new Date().getTime() ? 
                                                    <Button onClick={() => this.setChosen(x)} floating medium href="#modal1" className="modal-trigger red" waves="light" icon="clear"/> 
                                                        : 
                                                    null
                                            }
                                        </td>
                                    </tr>
                                )
                            })}
                        </tbody>
                    </Table>
                </Tab>
                <Tab title="Invites">
                    <Table options={{centered: true,striped: true}}>
                        <thead>
                            <tr>
                                <th data-field="id">
                                    Invited by 
                                </th>
                                <th data-field="id">
                                    From 
                                </th>
                                <th data-field="name">
                                    To
                                </th>
                                <th data-field="price">
                                    Takes of
                                </th>
                                <th data-field="price">
                                    Lands
                                </th>
                                <th data-field="price">
                                    Seat class
                                </th>
                                <th data-field="price">
                                    Row
                                </th>
                                <th data-field="price">
                                    Seat
                                </th>
                                <th data-field="price">
                                    Price
                                </th>
                                <th data-field="price">
                                    Accept
                                </th>
                                <th data-field="price">
                                    Decline
                                </th>
                            </tr>
                        </thead>
                        <tbody>
                            {this.props.invites && this.props.invites.map((x, i, niz)=> {
                                
                                let a = new Date(x.let.polece);
                                let b = new Date(x.let.slece);
                                return (
                                    <tr>
                                        <td>
                                            {x.invOd.ime} {x.invOd.prezime}
                                        </td>
                                        <td>
                                            {x.let.odDest.grad}
                                        </td>
                                        <td>
                                            {x.let.doDest.grad}
                                        </td>
                                        <td>
                                            {a.getUTCDate()}. {a.getUTCMonth()}. {a.getUTCFullYear()}. {a.getUTCHours()}:{a.getUTCMinutes()} 
                                        </td>
                                        <td>
                                            {b.getUTCDate()}. {b.getUTCMonth()}. {b.getUTCFullYear()}. {b.getUTCHours()}:{b.getMinutes()}
                                        </td>
                                        <td>
                                            {(x.tip === 1 ? "First" : (x.tip === 2 ? "Business" : (x.tip === 3 ? "Economy" : "Fast Reservation")))}
                                        </td>
                                        <td>
                                            {x.red+1}
                                        </td>
                                        <td>
                                            {x.kolona+1}
                                        </td>
                                        <td>
                                            {(x.tip === 1 ? x.let.cFirst : (x.tip === 2 ? x.let.cBusiness : x.let.cEconomy))}
                                        </td>
                                        <td>
                                            {new Date(x.let.polece).getTime() <= new Date().getTime() ? 
                                                    <Button onClick={()=> this.setChosen(x)} href="#modal3" floating className="modal-trigger green" waves="light" icon="add_circle_outline"/> 
                                                : 
                                                    null
                                            }
                                        </td>
                                        <td>
                                            {new Date(x.let.polece).getTime() <= new Date().getTime() ? 
                                                    <Button onClick={()=> this.setChosen(x)} href="#modal2" floating className="modal-trigger red" waves="light" icon="clear"/> 
                                                : 
                                                    null
                                            }
                                        </td>
                                    </tr>
                                )
                            })}
                        </tbody>
                    </Table>
                </Tab>
                <Tab title="Test 3">
                Test 3
                </Tab>
            </Tabs>
            <Modal id="modal1" actions = {<div><Button modal="close" onClick={()=>this.onClickOtkazi(1)} floating className="green" waves="light" icon ="check"/>
                                                        <Button floating modal="close" waves="light" className="red" icon="clear"/></div>} >
                Are you sure you want to cancel the reservation?
            </Modal>

            <Modal id="modal2" actions = {<div><Button modal="close" onClick={()=>this.onClickOtkazi(2)} floating className="green" waves="light" icon ="check"/>
                                                        <Button floating modal="close" waves="light" className="red" icon="clear"/></div>} >
                Are you sure you want to decline the reservation?
            </Modal>

            <Modal id="modal3" actions = {<div><Button modal="close" onClick={this.onClickPotvrdi} floating className="green" waves="light" icon ="check"/>
                                                        <Button floating modal="close" waves="light" className="red" icon="clear"/></div>} >
                Are you sure you want to confirm the reservation?
            </Modal>
            </div>
        )
    }
}

const mapStateToProps = (state) => {
    return {
        korisnik: state.log.log,
        rezervacije: state.rez.rezervacije,
        invites : state.rez.invites
    }
}

const mapDispatchToProps = (dispatch) => {
    return {
        getReservations: (id) => dispatch(getReservations(id)),
        getRezInvites: (id) => dispatch(getRezInvites(id)),
        confirmReservation: (r) => dispatch(confirmReservation(r)),
        cancelReservation: (r,tip) => dispatch(cancelReservation(r,tip))
    }
}

export default connect(mapStateToProps,mapDispatchToProps)(RezervacijeList);