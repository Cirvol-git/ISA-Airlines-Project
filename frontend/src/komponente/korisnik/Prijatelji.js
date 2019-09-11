import React, {Component} from 'react';
import { connect } from 'react-redux';
import {Link} from 'react-router-dom';
import {Table, Tabs, Tab, Button, Modal } from 'react-materialize';
import { getPrijatelji, deletePrijatelj, acceptPrijatelj, getZahtevi } from '../../store/actions/PrijateljActions';

class Prijatelji extends Component {

    state = {ret : {ime : "",prezime:""}}

    componentDidMount() {
        this.props.getPrijatelji(this.props.korisnik);
        this.props.getZahtevi(this.props.korisnik)
    }

    setChosen = (x) => {
        console.log(x);
        this.setState(
            x
        )
    }

    delete = () => {
        console.log(this.state);
        this.props.deletePrijatelj(this.state);
    }

    accept = () => {
        console.log(this.state);
        this.props.acceptPrijatelj(this.state);
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
                                        Name
                                    </th>
                                    <th data-field="name">
                                        LastName
                                    </th>
                                    <th data-field="price">
                                        Email
                                    </th>
                                    <th data-field="price">
                                        Unfriend
                                    </th>
                                </tr>
                            </thead>
                            <tbody>
                                {this.props.prijatelji && this.props.prijatelji.map((x, i, niz)=> {
                                    return (
                                        <tr>
                                            <td>
                                                {x.ret.ime}
                                            </td>
                                            <td>
                                                {x.ret.prezime}
                                            </td>
                                            <td>
                                                {x.ret.email}
                                            </td>
                                            <td> 
                                                <Button onClick={() => this.setChosen(x)} floating medium href="#modal1" className="modal-trigger red" waves="light" icon="clear"/> 
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
                                        Name
                                    </th>
                                    <th data-field="id">
                                        LastName 
                                    </th>
                                    <th data-field="name">
                                        Email
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
                                {this.props.zahtevi && this.props.zahtevi.map((x, i, niz)=> {
                                    return (
                                        <tr>
                                            <td>
                                                {x.ret.ime}
                                            </td>
                                            <td>
                                                {x.ret.prezime}
                                            </td>
                                            <td>
                                                {x.ret.email}
                                            </td>
                                            <td>
                                                <Button onClick={()=> this.setChosen(x)} href="#modal3" floating className="modal-trigger green" waves="light" icon="add_circle_outline"/> 
                                            </td>
                                            <td>
                                                <Button onClick={()=> this.setChosen(x)} href="#modal2" floating className="modal-trigger red" waves="light" icon="clear"/> 
                                            </td>
                                        </tr>
                                    )
                                })}
                            </tbody>
                        </Table>
                    </Tab>
                </Tabs>
                <Modal id="modal1" actions = {<div><Button modal="close" onClick={this.delete} floating className="green" waves="light" icon ="check"/>
                                                            <Button floating modal="close" waves="light" className="red" icon="clear"/></div>} >
                    Are you sure you want to unfriend {this.state.ret.ime} {this.state.ret.prezime} ?
                </Modal>

                <Modal id="modal2" actions = {<div><Button modal="close" onClick={this.delete} floating className="green" waves="light" icon ="check"/>
                                                            <Button floating modal="close" waves="light" className="red" icon="clear"/></div>} >
                    Are you sure you want to decline the the friend request from {this.state.ret.ime} {this.state.ret.prezime} ?
                </Modal>

                <Modal id="modal3" actions = {<div><Button modal="close" onClick={this.accept} floating className="green" waves="light" icon ="check"/>
                                                            <Button floating modal="close" waves="light" className="red" icon="clear"/></div>} >
                    Are you sure you want to accept the friend request {this.state.ret.ime} {this.state.ret.prezime} ?
                </Modal>
                <Link to='/pretragaKorisnika'><button className="btn waves-effect waves-light lighten-1 centar-align">Add friend</button></Link>
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