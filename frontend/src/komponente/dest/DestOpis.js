import React from 'react';
import {Link} from 'react-router-dom';

const DestOpis = ({dest}) => {
    return (
        <Link to={'dest/' + dest.id} className="centar">
        <div className="card grey darken-2 card-panel hoverable">
            <div className="card-content white-text left-align">
                <p className="card-title">{dest.ime}</p>
                <p>Adress: {dest.adresa}</p>
                <p>City: {dest.grad}</p>
            </div>
        </div>
        </Link>
    )
}

export default DestOpis;