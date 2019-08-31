const initState = {
    dests: []
}
const destReducer = (state = initState, action) => {
    switch (action.type) {
        case 'CREATE_DEST' : 
            console.log(action.dest);
            return {
                ...state,
                dests : [...state.dests, action.dest]
            }

        case 'GET_DESTS' : 
            console.log(action.dest);
            return {
                ...state,
                dests : [...action.dest]
            }

        case 'UPDATE_DEST' : 
        console.log(action.dest);
        var novi = state.dests.filter(dest => {
            return dest.id !== action.dest.id
          });
          
        return {
            ...state,
            dests : [...novi, action.dest]
        }
        default: return state;
    }
}

export default destReducer;