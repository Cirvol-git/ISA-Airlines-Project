import React from 'react';

const ZahteviList = ({zahtevi, accept, delet}) => {
    return (
        <div className="section">
            {zahtevi.length ? (
                <div>
                <h5>Friend Requests</h5>
                <ul>
                    { zahtevi && zahtevi.map(z => {
                        return (
                            <li>
                                <p>Name: {z.ret.ime} Last Name: {z.ret.prezime} </p>
                                <p><button onClick= {() => {accept(z.id)}}>confirm</button> <button onClick= {() => {delet(z.id)}}>reject</button></p>
                            </li>
                        )
                    })}
                </ul>
                </div>
            ): (null)
            }
        </div>
    )
}

export default ZahteviList;