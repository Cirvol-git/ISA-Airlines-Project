const initState = {
    rezervacije: [],
    invites : []
}

const rezReducer = (state = initState, action) => {
    switch(action.type) {
        case 'GET_REZERVACIJE':
            return {
                ...state,
                rezervacije: action.rez
            }
        
        case 'CONFIRM_RESERVATION':
            return {
                ...state,
                rezervacije: [state.rezervacije, action.rez],
                invites: state.invites.filter(x=> x.id != action.rez.id)
            }

        case 'GET_REZ_INVITES':
            return {
                ...state,
                invites: action.rez
            }
        
        case 'DECLINE_RESERVATION1':
            return {
                ...state,
                rezervacije: state.rezervacije.filter(x=> x.id != action.rez.id)
            }
        
        case 'DECLINE_RESERVATION2':
            return {
                ...state,
                invites: state.invites.filter(x=> x.id != action.rez.id)
            }
        default: 
            return state;
    }
}

export default rezReducer;