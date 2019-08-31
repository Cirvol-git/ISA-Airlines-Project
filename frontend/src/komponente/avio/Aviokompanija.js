import React, {Component} from 'react';
import { connect } from 'react-redux';
import DestList from '../dest/DestList';
import AvioList from './AvioList';
import {getAvio} from '../../store/actions/AvioActions'
import {Link} from 'react-router-dom';

class Aviokompanija extends Component {
    componentDidMount() {
        this.props.getAvio(this.props.id);
    }
    render() {
        return (
            <div>
                <div>
                    <Link to={'avio/' + this.props.id} className="centar">
                        <AvioList avios={this.props.avio}/>
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