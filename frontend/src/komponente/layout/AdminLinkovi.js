import React from 'react';
import { NavLink } from 'react-router-dom'

const AdminLinkovi = () => {
    return (
        <ul className="right">
            <li><NavLink to='/avio'>Aviokompanija</NavLink></li>
            <li><NavLink to='/createDest'>Dodaj destinaciju</NavLink></li>
            <li><NavLink to='/createLet'>Dodaj let</NavLink></li>
        </ul>
    )
}

export default AdminLinkovi;