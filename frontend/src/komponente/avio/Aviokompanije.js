import React, {Component} from 'react';
import { connect } from 'react-redux';
import AvioList from './AvioList';
import { getAvios } from '../../store/actions/AvioActions';
import {Link} from 'react-router-dom';

class Aviokompanije extends Component {
    componentDidMount() {
        this.props.getAvios();
    }
    render() {
        return (
            <div className="container">
                <Link to={'/pretragaLetova'}><button>Pretraga Letova</button></Link>
                <AvioList avios= {this.props.avios}/>
            </div>
        )
    }
}

const mapStateToProps = (state) => {
    return {
        avios: state.avios.avios
    }
}

const mapDispatchToProps = (dispatch) => {
    return {
        getAvios: () => dispatch(getAvios())
    }
}

export default connect(mapStateToProps, mapDispatchToProps)(Aviokompanije);