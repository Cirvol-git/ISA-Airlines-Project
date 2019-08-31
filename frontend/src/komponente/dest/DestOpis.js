import React from 'react';
import {Link} from 'react-router-dom';

const DestOpis = ({dest}) => {
    return (
        <Link to={'dest/' + dest.id} className="centar">
        <div className="card z-depth-0">
            <div className="card-content gray-text text-darken-3">
                <p className="card-title">{dest.ime}</p>
                <p>{dest.adresa}</p>
            </div>
        </div>
        </Link>
    )
}

export default DestOpis;