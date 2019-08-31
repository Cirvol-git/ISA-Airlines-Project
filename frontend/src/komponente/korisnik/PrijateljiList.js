import React from 'react';

const PrijateljiList = ({prijatelji, delet}) => {
    return (
        <div className="section">
            <ul>
                { prijatelji && prijatelji.map(pri => {
                    return (
                        <li>
                            <p>Name: {pri.ret.ime} Last Name: {pri.ret.prezime} <button onClick= {() => {delet(pri.id)}}>unfriend</button></p>
                        </li>
                    )
                })}
            </ul>
        </div>
    )
}

export default PrijateljiList;