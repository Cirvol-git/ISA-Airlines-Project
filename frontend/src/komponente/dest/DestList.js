import React from 'react';
import DestOpis from './DestOpis';


const DestList = ({dests}) => {
    console.log('destsList',dests);
    return (
        <div className="section">
            { dests && dests.map(dest => {
                console.log(dest.id);
                return (
                    
                        <DestOpis dest={dest}/>
                    
                )
            })}
        </div>
    )
}

export default DestList;