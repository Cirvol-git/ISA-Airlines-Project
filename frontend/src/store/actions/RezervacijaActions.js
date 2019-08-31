import axios from 'axios';

export const getReservations = (id) => {
    return (dispatch, getState) => {
        axios.get(
            'http://localhost:8080/api/rezervacija/rezervacije/' + id
        ).then(res => {
            console.log(res.data)
            dispatch({ type: "GET_REZERVACIJE" , rez: res.data});
        }).catch(e => {
            console.log(e);
        })
    }
}

export const getRezInvites = (id) => {
    return (dispatch, getState) => {
        axios.get(
            'http://localhost:8080/api/rezervacija/invites/' + id
        ).then(res => {
            console.log(res.data)
            dispatch({ type: "GET_REZ_INVITES" , rez: res.data});
        }).catch(e => {
            console.log(e);
        })
    }
}

export const confirmReservation = (r) => {
    return (dispatch, getState) => {
        axios({
            url: 'http://localhost:8080/api/rezervacija/potvrdi',
            method: 'put',
            data: r
        }).then(res => {
            console.log(res.data)
            dispatch({ type: "CONFIRM_RESERVATION" , rez: res.data});
        }).catch(e => {
            console.log(e);
        })
    }
}

export const cancelReservation = (r,tip) => {
    return (dispatch, getState) => {
        axios({
            url: 'http://localhost:8080/api/rezervacija/otkazi',
            method: 'put',
            data: r
        }).then(res => {
            console.log(res.data)
            if(tip == 1) {
                dispatch({ type: "DECLINE_RESERVATION1" , rez: res.data});
            }else {
                dispatch({ type: "DECLINE_RESERVATION2" , rez: res.data});
            }
            }).catch(e => {
            console.log(e);
        })
    }
}

export const saveMarkedSeats = (seats) => {
    return (dispatch, getState) => {
        console.log("seats for save",seats);
        dispatch({ type: "SAVE_MARKED_SEATS" , seats: seats});
    }
}

export const createReservations = (sedista,dodeljeni,imena,pasos,id) => {
    return (dispatch, getState) => {
        let r = []
        sedista.map((s,i) => {
            let ret = s;
            
            var date = new Date(); 
            var now_utc =  Date.UTC(date.getUTCFullYear(), date.getUTCMonth(), date.getUTCDate(),
                            date.getUTCHours(), date.getUTCMinutes(), date.getUTCSeconds());



            ret.rezervisano = new Date(now_utc);
            ret.id_korisnik = i<dodeljeni.length?dodeljeni[i].id:null;
            ret.id_sediste = s.id;
            ret.inv = id;
            ret.ime = (i<dodeljeni.length?dodeljeni[i].ime:imena[i].ime);
            ret.prezime = (i<dodeljeni.length?dodeljeni[i].prezime:imena[i].prezime);
            ret.pasos = pasos[i];

            r = [...r,ret];
        })
        console.log("return",r);
        axios({
            url: 'http://localhost:8080/api/rezervacija/create',
            method: 'post',
            data: r
        }).then(res => {
            console.log(res.data)
        }).catch(e => {
            console.log(e);
        })
    }
}

export const createFastRes = (korisnik,r,pasos) => {
    return (dispatch, getState) => {
        let ret = r;

        var date = new Date(); 
        var now_utc =  Date.UTC(date.getUTCFullYear(), date.getUTCMonth(), date.getUTCDate(),
                        date.getUTCHours(), date.getUTCMinutes(), date.getUTCSeconds());

        ret.rezervisano = new Date(now_utc);
        ret.id_korisnik = korisnik;
        ret.pasos = pasos;

        axios({
            url: 'http://localhost:8080/api/rezervacija/createFast',
            method: 'post',
            data: ret
        }).then(res => {
            console.log(res.data)
            dispatch({ type: "CONFIRM_FAST_RES" , rez: res.data});
        }).catch(e => {
            console.log(e);
        })
    }
}