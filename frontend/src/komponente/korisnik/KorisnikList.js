import React from 'react';

const KorisnikList = ({prijatelji, korisnici, zahtevi, poslati, id, req}) => {
    const bezUlogovanog = korisnici.filter(k => {
        return k.id !== id
    })
    
    const bezUlogovanogIPoslatih = bezUlogovanog.filter(function(k) {
        let i = 0;
        for(i = 0; i<poslati.length; i++) {
            if(poslati[i].ret.id === k.id) {
                return false;
            }
        }
        for(i = 0; i<zahtevi.length; i++) {
            if(zahtevi[i].ret.id === k.id) {
                return false;
            }
        }
        for(i = 0; i<prijatelji.length; i++) {
            if(prijatelji[i].ret.id === k.id) {
                return false;
            }
        }
        return true;
    })
    /*
    const bezUlogIPosIPrijatelja = bezUlogovanogIPoslatih.filter(function(k) {
        for(let i = 0; i++; zahtevi.length) {
            if(zahtevi[i].je === k.id) {
                return false;
            }
        }
        return true;
    })
    */
    return (
        <div className="section">
            <ul>
                { bezUlogovanogIPoslatih && bezUlogovanogIPoslatih.map(pri => {
                    return (
                        <li>
                            <p>{pri.ime} {pri.prezime} {pri.email}<button onClick= {() => {req(pri.id)}}>add</button></p>
                        </li>
                    )
                })}
            </ul>
        </div>
    )
}

export default KorisnikList;