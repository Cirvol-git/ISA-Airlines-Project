import React from 'react';
import AvioOpis from './AvioOpis';
import {Link} from 'react-router-dom';

const AvioList = ({avios}) => {
    return (
        <div className="section">
            { avios && avios.map(avio => {
                return (
                    <div>
                        <AvioOpis avio={avio} key = {avio.id}/>
                        <Link to={'/brzeRezervacije/' + avio.id}><button className="btn waves-effect waves-light lighten-1 centar-align">Tickets for fast reservations</button></Link>
                    </div>
                )
            })}
        </div>
    )
}

export default AvioList;