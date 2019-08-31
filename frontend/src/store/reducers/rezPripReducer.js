const initState = {
    seats: []
}

const rezPripReducer = (state = initState, action) => {
    switch(action.type) {
        case 'SAVE_MARKED_SEATS':
            return {
                ...state,
                seats: action.seats
            }
        default: 
            return state;
    }
}

export default rezPripReducer;