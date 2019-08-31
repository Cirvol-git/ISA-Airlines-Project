const initState = {
    prijatelji: [],
    zahtevi: [],
    poslati: [],
    korisnici: []
}
const prijateljiReducer = (state = initState, action) => {
    switch (action.type) {
        case 'GET_PRIJATELJI':
            console.log("akcja PRIJATELJI: ", action.prijatelji);
            return {
                ...state,
                prijatelji: [...action.prijatelji]
            }

        case 'GET_ZAHTEVI':
            console.log("akcja Zahtevi: ", action.zahtevi);
            return {
                ...state,
                zahtevi: [...action.zahtevi]
            }
            
        case 'GET_POSLATI_ZAHTEVI':
            console.log("akcja Poslati: ", action.poslati);
            return {
                ...state,
                poslati: [...action.poslati]
            }
        
        case 'ACCEPT_PRIJATELJ':
            console.log("akcja accept FRIEND: ", action.id);
            console.log("state: ", state);
            let noviZahtevi = state.zahtevi.filter((z)=> {
                return z.id !== action.id
            })
            console.log("novi zahtevi",noviZahtevi);
            let noviPrijatelj = state.zahtevi.filter((z)=> {
                return z.id === action.id
            })
            let r = [...state.prijatelji, ...noviPrijatelj]
            console.log("novi prijatelj",noviPrijatelj);
            console.log("vraca", r)
            return {
                ...state,
                zahtevi: [...noviZahtevi],
                prijatelji: r
                
            }   

        case 'DELETE_PRIJATELJ':
            console.log("akcja delete FRIEND: ", action.id);
            console.log("state: ", state);
            let test = state.prijatelji.filter((p)=> {
                return p.id === action.id
            })
            let ret;
            if(test.length === 0 ){
                ret = state.zahtevi.filter((p)=> {
                    return p.id !== action.id
                })
                return {
                    ...state,
                    zahtevi: [...ret]
                }
            }else{
                console.log("prepravljena lista",ret)
                ret = state.prijatelji.filter((p)=> {
                    return p.id !== action.id
                })
                return {
                    ...state,
                    prijatelji: [...ret]
                }
            }
            
        case 'GET_KORISNICI':
            console.log("akcja KORISNICI: ", action.korisnici);
            return {
                ...state,
                korisnici: [...action.korisnici]
            }
        case 'CREATE_PRIJATELJI':
            return {
                ...state,
            }
        default:
            return state;
    }
}

export default prijateljiReducer;