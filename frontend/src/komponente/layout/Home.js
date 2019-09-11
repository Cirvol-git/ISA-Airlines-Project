import React, {Component} from 'react';
import { connect } from 'react-redux';

class Home extends Component {

    handleClick = () => {
        this.props.history.push('/azuriranje')
    }

    render() {
        return (
            <div className='centar'>
                {
                    this.props.log === null ? (
                        <h2>Loading...</h2>
                    ) : (
                        <div className = "center container">
                            <div className="row">
                                <div className = "col s12 m12">
                                    <div className="card grey darken-2 card-panel hoverable">
                                        <div className="card-content white-text left-align">
                                            <span className="card-title"><b>{this.props.log.ime} {this.props.log.prezime}</b></span>
                                            <div className="divider white"></div>
                                            <br/>
                                            <p>Email: {this.props.log.email}</p>
                                            <p>Phone number: {this.props.log.telefon}</p>
                                            <p></p>
                                            <br/>
                                            <div className="divider white"></div>
                                            <br/>
                                            <p>City: {this.props.log.grad}</p>
                                            <br/>
                                            <div className="divider white"></div>
                                            <br/>
                                            <button className="btn waves-effect waves-light lighten-1 centar-align" onClick={this.handleClick}>Izmeni podatke</button>      
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    )
                }
            </div>
        )
    }
}

const MapStateToProps = (state) => {
    return {
        log : state.log.log
    }
}

export default connect(MapStateToProps)(Home)