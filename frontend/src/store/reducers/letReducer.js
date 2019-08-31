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
        default: 
            return state;
    }
}

export default letReducer;