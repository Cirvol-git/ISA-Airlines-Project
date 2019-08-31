import React from 'react';
import { Link } from 'react-router-dom'

const NeulogovanLinkovi = () => {
    return (
        <ul className="right">
            <li><Link to='/'>Login</Link></li>
        </ul>
    )
}

export default NeulogovanLinkovi;