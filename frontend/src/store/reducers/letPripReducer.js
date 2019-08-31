const initState = {
    letp: {}
}

const letPripReducer = (state = initState, action) => {
    switch(action.type) {
        case 'SAVE_LET_STATE':
            return {
                ...state,
                letp: action.letp
            }
        case 'GET_LET':
            return {
                ...state,
                letp: action.letp
            }
        default: 
            return state;
    }
}

export default letPripReducer;