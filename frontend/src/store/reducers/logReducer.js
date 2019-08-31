const initState = {
    log : null
}
const logReducer = (state = initState, action) => {
    switch(action.type) {
        case 'LOGIN': 
            return{
                ...state,
                log : action.log
            }
        case 'LOGOUT': 
            return{
                ...state,
                log : null
            }
        case 'UPDATE_KORISNIK': 
            return{
                ...state,
                log : action.log
            }
        default: return state;
    }
}

export default logReducer;