import React from 'react';

const AvioOpis = ({avio}) => {
    return (
        <div className="card z-depth-0">
            <div className="card-content gray-text text-darken-3">
                <p className="card-title">{avio.ime}</p>
                <p>{avio.adresa}</p>
                <p>{avio.opis}</p>
            </div>
        </div>
    )
}

export default AvioOpis;