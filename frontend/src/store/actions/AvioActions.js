import axios from 'axios';

export const getAvios = () => {
    return (dispatch, getState) => {
        var avios = [];
        axios.get('http://localhost:8080/api/avio/all')
            .then(res => {
                avios = res.data;
                dispatch({ type: 'GET_AVIOS', avios})
            }).catch(e => {
                console.log(e);
            })
    }
}

export const getAvio = (id) => {
    return (dispatch, getState) => {
        var avio = [];
        axios.get('http://localhost:8080/api/avio/'+ id)
            .then(res => {
                avio = res.data;
                dispatch({type: 'GET_AVIO', avio:avio })
            }).catch(e => {
                console.log(e);
            })
    }
}

export const updateAvio = (avio) => {
    return (dispatch, getState) => {
        let ret = getState().avios.avios.find(x=> x.id == avio.id);

        ret.ime = avio.ime;
        ret.adresa = avio.adresa;
        ret.opis = avio.opis;

        axios({
            url: 'http://localhost:8080/api/avio/update',
            method: 'put',
            data: ret
        }).then(res => {
            console.log(res)
            dispatch({type: 'UPDATE_AVIO', avio: res.data})
        }).catch(e => {
            console.log(e);
        })
    }
}