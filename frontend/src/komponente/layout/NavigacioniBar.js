import React, {Component} from 'react';
import { Link } from 'react-router-dom'
import KorisnikLinkovi from './KorisnikLinkovi';
import AdminLinkovi from './AdminLinkovi';
import NeulogovanLinkovi from './NeulogovanLinkovi'
import {connect} from 'react-redux'

class NavigacioniBar extends Component {

    render() {
        return (
            <nav className = "nav-wrapper grey darken-3">
                <div className="container">
                    <Link to='/' className="brand-logo">ISA</Link>
                    <Link to='/' className="right">Logout</Link>
                    {
                        this.props.ulogovan === null ? (
                            <NeulogovanLinkovi />
                        ) : (
                            this.props.ulogovan.admin === true ? (
                                <AdminLinkovi />
                            ) : (
                                <KorisnikLinkovi />
                            )
                        )
                    }
                    
                </div>
            </nav>
        )
    }
}

const mapStateToProps = (state) => {
    return {
        ulogovan: state.log.log
    }
}

export default connect(mapStateToProps,null)(NavigacioniBar);