import React from 'react';
import { NavLink } from 'react-router-dom'

const KorisnikLinkovi = () => {
    return (
        <ul className="right">
            <li><NavLink to='/avios'>Companies</NavLink></li>
            <li><NavLink to='/prijatelji'>Friends</NavLink></li>
            <li><NavLink to='/rezList'>My Reservations</NavLink></li>
        </ul>
    )
}

export default KorisnikLinkovi;