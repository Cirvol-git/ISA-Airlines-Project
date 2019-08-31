import * as React from 'react';

const LetoviList = ({letovi, detalji}) => {
    return (
        <div className="section">
            <table className="highlight">
                <thead>
                    <tr>
                        <th>Polece</th>
                        <th>Slece</th>
                        <th>Broj presedanja</th>
                        <th>Traje</th>
                        <th>Aviokompanija</th>
                        <th>Cena</th>
                    </tr>
                </thead>
                <tbody>
                    { letovi && letovi.map((pri) => {
                        const a = new Date(pri.polece);
                        const b = new Date(pri.slece);
                        return (
                            <tr onClick = {()=> {detalji(pri.id)}}>
                                <td>
                                    {a.getUTCHours()}:{a.getUTCMinutes()}
                                </td>
                                <td>
                                    {b.getUTCHours()}:{b.getUTCMinutes()}
                                </td>
                                <td>
                                    {pri.brojPresedanja}
                                </td>
                                <td>
                                    {pri.vreme>=60?Math.round(pri.vreme/60): '00'}h {pri.vreme%60}min
                                </td>
                                <td>
                                    {pri.nazivAvio}
                                </td>
                                <td>
                                    ${ pri.klasa === 1 ? pri.cFirst : (pri.klasa === 2 ? pri.cBusiness : pri.cEconomy) }
                                </td>
                            </tr>
                        )
                    })}
                </tbody>
            </table>
        </div>
    )
}
export default LetoviList;