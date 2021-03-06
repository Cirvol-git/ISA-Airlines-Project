import axios from 'axios';

export const getPrijatelji = (id) => {
    return (dispatch, getState) => {
        axios.get('http://localhost:8080/api/prijatelji/allp'+ id)
            .then(res => {
                dispatch({ type: 'GET_PRIJATELJI', prijatelji:res.data})
            }).catch(e => {
                console.log(e);
            })
    }
}

export const getZahtevi = (id) => {
    return (dispatch, getState) => {
        axios.get('http://localhost:8080/api/prijatelji/allz'+ id)
            .then(res => {
                dispatch({ type: 'GET_ZAHTEVI', zahtevi:res.data})
            }).catch(e => {
                console.log(e);
            })
    }
}

export const getPoslatiZahtevi = (id) => {
    return (dispatch, getState) => {
        axios.get('http://localhost:8080/api/prijatelji/allpz'+ id)
            .then(res => {
                dispatch({ type: 'GET_POSLATI_ZAHTEVI', poslati:res.data})
            }).catch(e => {
                console.log(e);
            })
    }
}

export const createPrijatelji = (je,od) => {
    return (dispatch, getState) => {
        axios({
            url:'http://localhost:8080/api/prijatelji/create',
            method: 'post',
            data: {
                je: je,
                od: od
            }    
        }).then(res => {
            dispatch({ type: 'CREATE_PRIJATELJI', prijatelj:res.data})
        }).catch(e => {
            console.log(e);
        })
    }
}

export const deletePrijatelj = (x) => {
    return (dispatch, getState) => {
        
        axios.delete('http://localhost:8080/api/prijatelji/delete/'+x.id)
            .then(res => {
                dispatch({ type: 'DELETE_PRIJATELJ', id: x.id})
            }).catch(e => {
                console.log(e);
            })
    }
}

export const acceptPrijatelj = (x) => {
    return (dispatch, getState) => {
        
        axios.put('http://localhost:8080/api/prijatelji/update/'+x.id)
            .then(res => {
                dispatch({ type: 'ACCEPT_PRIJATELJ', id: x.id})
            }).catch(e => {
                console.log(e);
            })
    }
}