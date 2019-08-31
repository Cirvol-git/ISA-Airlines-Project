import axios from 'axios';

export const saveLetState = (letp) => {
    return (dispatch, getState) => {
        
        var rows = [];
        var row;
        for (let i = 0; i < letp.redova; i++) {
            row = []
            for (let j = 0; j < 6; j++) {
                var element = {
                    red: i,
                    kolona: j,
                    zauzeto: false,
                    tip : '',
                    odobreno: true,
                    rezervisano : null,
                    potvrdjeno : false,
                
                }
                if(i === 0) {
                    element.tip = "1";
                }else if(i===1){
                    element.tip = "2";
                }else if(i === letp.redova-1) {
                    element.tip = "4";
                }else {
                    element.tip = "3";
                }

                row = [...row, element]
            }
            rows = [...rows , row]
        }
        letp.rows = rows;
       console.log('Stanje letp',letp);
       dispatch({ type: 'SAVE_LET_STATE', letp : letp})
    }
}

export const createLet = (letp) => {
    return (dispatch, getState) => {
        console.log(letp);
        let ret = [];
        letp.rows.map(row => {
            ret = [...ret, ...row];
        })
        console.log("niz sedista", ret)
        axios({
            url: 'http://localhost:8080/api/let/create',
            method: 'post',
            data: {
                od: letp.od,
                doo: letp.do,
                brojPresedanja: letp.presedanja,
                tipLeta: letp.tipleta,
                polece : letp.polece,
                slece : letp.slece,
                vreme : letp.vreme,
                duzina: letp.duzina,
                cEconomy: letp.ceconomy,
                cBusiness: letp.cbusiness,
                cFirst: letp.cfirst,
                redova: letp.redova,
                idAvio : letp.idavio,
                rows : ret,
                prtljag : letp.prtljag
            }
 //           headers: {crossdomain: true}
        }).then(res => {
            console.log(res);
            dispatch({ type: 'CREATE_LET', letp: res.data })
        }).catch(e => {
            console.log(e);
        })
    }
}

export const pretragaLetova = (pretraga) => {
    return (dispatch, getState) => {
        console.log(pretraga);
        axios({
            url: 'http://localhost:8080/api/let/pretraga',
            method: 'post',
            data: {
                od: pretraga.od,
                doo: pretraga.do,
                polece: new Date(pretraga.polece),
                slece: new Date(pretraga.slece),
                tipLeta: pretraga.tipleta,
                brojOsoba: pretraga.brojosoba,
                klasa: pretraga.klasa,
                prtljag: pretraga.prtljag
            }
 //           headers: {crossdomain: true}
        }).then(res => {
            console.log(res);
            dispatch({ type: 'PRETRAGA_LET', pretraga: res.data })
        }).catch(e => {
            console.log(e);
        })
    }
}

export const getLet = (id) => {
    return (dispatch, getState) => {
        console.log(id);
        axios({
            url: 'http://localhost:8080/api/let/'+id,
            method: 'get'
        }).then(res => {
            console.log(res);
            let l = res.data;
            let red = [], ret = [];
            for(let i = 0; i<l.redova; i++) {
                red = l.rows.filter(r => {
                    return r.red === i
                })
                red.sort((a,b) => {return a.kolona-b.kolona})
                ret = [...ret , red];
            }
            console.log("redovi prepravka",ret);
            l.rows = ret;
            dispatch({ type: 'GET_LET', letp: l })
        }).catch(e => {
            console.log(e);
        })
    }
}

export const getLets = (id) => {
    return (dispatch, getState) => {
        console.log(id);
        axios({
            url: 'http://localhost:8080/api/let/lets/'+id,
            method: 'get'
        }).then(res => {
            console.log("Letovi sa sedistima za brzu rez", res.data)
            dispatch({ type: 'GET_LETS', letp: res.data })
        }).catch(e => {
            console.log(e);
        })
    }
}