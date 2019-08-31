import axios from 'axios';

export const getKorisnici = (pretraga) => {
    return (dispatch, getState) => {
        console.log(pretraga);
        axios({
            url:'http://localhost:8080/api/korisnik/pretraga',
            method: 'post',
            data: {
                ime: pretraga.ime,
                prezime: pretraga.prezime
            }
        }).then(res => {
            dispatch({ type: 'GET_KORISNICI', korisnici:res.data})
        }).catch(e => {
            console.log(e);
        })
    }
}

export const updateKorisnik = (k,props) => {
    return (dispatch, getState) => {
        console.log(k);
        axios({
            url:'http://localhost:8080/api/korisnik',
            method: 'put',
            data: {
                id : k.id,
                email : k.email,
                pass : k.pass,
                ime : k.ime,
                prezime : k.prezime,
                grad : k.grad,
                telefon : k.telefon
            }
        }).then(res => {
            dispatch({ type: 'UPDATE_KORISNIK', log:res.data})
            props.history.push('/home')
        }).catch(e => {
            console.log(e);
            alert("There was an error while saving changes")
        })
    }
}