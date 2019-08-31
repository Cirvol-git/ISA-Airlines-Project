import axios from 'axios';

export const createDestination = (dest, idAvio) => {
    return (dispatch, getState) => {
        console.log('Stanje stora',getState);
        if(dest.checked === false) {
            axios({
                url: 'http://localhost:8080/api/aero/create',
                method: 'post',
                data: {
                    idAvio,
                    ime: dest.ime,
                    adresa: dest.adresa,
                    grad: dest.grad
                }
    //           headers: {crossdomain: true}
            }
            ).then(res => {
                console.log('create destinacija', res.data);
                dispatch({ type: 'CREATE_DEST', dest: res.data })
            }).catch(e => {
                console.log(e);
            })
        }else {
            axios({
                url: 'http://localhost:8080/api/aero/adddep',
                method: 'put',
                data: {
                idAvio : idAvio,
                idAero : dest.sel
                }
            }
            ).then(res => {
                console.log('add destinacija dep', res.data);
                dispatch({ type: 'CREATE_DEST', dest: res.data })
            }).catch(e => {
                console.log(e);
            })
        }
    }
}

export const getDestinations = () => {
    return (dispatch, getState) => {
        axios({
            url: 'http://localhost:8080/api/aero/all',
            method: 'get'
 //           headers: {crossdomain: true}
        }
        ).then(res => {
            console.log(res);
            dispatch({ type: 'GET_DESTS', dest: res.data })
        }).catch(e => {
            console.log(e);
        })
    }
}

export const getDesiExeFor = (id) => {
    return (dispatch, getState) => {
        console.log('Stanje stora',getState);
        axios({
            url: 'http://localhost:8080/api/aero/allexceptfor/' + id,
            method: 'get'
        }
        ).then(res => {
            console.log('allexeptfor',res.data);
            dispatch({ type: 'GET_DESTS', dest: res.data })
        }).catch(e => {
            console.log(e);
        })
    }
}

export const updateDestination = (dest) => {
    return (dispatch,getState) => {
        let ret = getState().avios.dests.find(x=> x.id == dest.id)
        
        ret.ime = dest.ime;
        ret.adresa = dest.adresa;
        ret.grad = dest.grad;

        axios({
            url: 'http://localhost:8080/api/aero/update',
            method: 'put',
            data: ret,
            headers: {crossdomain: true}
        }).then(res => {
            console.log(res)
            dispatch({type: 'UPDATE_DEST', dest: dest})
        }).catch(e => {
            console.log(e);
        })
    }
}