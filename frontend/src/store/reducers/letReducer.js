const initState = {
    lets: [],
    pretraga: [],
    letsOfAvio: []
}

const letReducer = (state = initState, action) => {
    switch(action.type) {
        case 'CREATE_LET':
            return {
                ...state,
                lets: [...state.lets, action.letp]
            }
        case 'PRETRAGA_LET':
            return {
                ...state,
                pretraga: [...action.pretraga]
            }
        case 'GET_LETS':
            return {
                ...state,
                letsOfAvio: [...action.letp]
            }
        
        case 'CONFIRM_FAST_RES':

            let ret = state.letsOfAvio.map(x=> {let resList = x.rows.filter(y=> y.id != action.rez.id); x.rows = resList; return x;});
            console.log(ret)
            
            return {
                ...state,
                letsOfAvio: ret
            }

        default: 
            return state;
    }
}

export default letReducer;