import React, {Component} from 'react';

const DrawGridRez = ({rows, rezervisano, onClickData}) => {
    return (
        <div className="container">
        <table className="grid">
            <tbody>
                {rows && rows.map((row,i) => {
                    return (
                        <tr>
                            { row.map( (seat,j) => 
                               
                                seat.odobreno === true && seat.rezervisano === null && seat.tip !== 4 ? 
                                    <td 
                                        className= {(seat.tip === 1 ? "card-panel blue" : (seat.tip === 2 ? 'card-panel yellow' : 'card-panel green')) + ((rezervisano.filter(rez => {return rez.id === seat.id})).length !== 0 ? ' lighten-4': '')}
                                        key={seat.id} onClick = {() => onClickData(seat)}>
                                            class: { seat.tip === 1 ? "First" : (seat.tip === 2 ? "Business" : "Economy") } 
                                    </td>
                                :(seat.odobreno === false ?  
                                    <td
                                        className={"card-panel gray darken-4"}>
                                            class: "Closed"
                                    </td>
                                :(seat.rezervisano == null ? 
                                    <td
                                        className={"card-panel red darken-4"}>
                                            class: "FastReservaion"
                                    </td>
                                :
                                    <td
                                        className={"card-panel brown"}>
                                            class: "Reserved"
                                    </td>                    
                                )))}
                        </tr>)
                })}
            </tbody>
        </table>
        </div>
    )  
}

export default DrawGridRez;