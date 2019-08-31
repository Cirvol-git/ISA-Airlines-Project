const initState = {
    avios: [],
    dests: []
}
const aviosReducer = (state = initState, action) => {
    switch (action.type) {
        case 'GET_AVIOS':
            console.log("akcja AVIOS: ", action.avios);
            return {
                ...state,
                avios: [...action.avios]
            }
        case 'GET_AVIO':
            console.log("akcja AVIO: ", action.avio);
            return {
                ...state,
                avios: [action.avio],
                dests: [...action.avio.destinacije]
            }
        case 'UPDATE_AVIO':
            console.log("akcja AVIO: ", action.avio);
            return {
                ...state,
                avios: [action.avio]
            }

        default: return state;
    }
}

export default aviosReducer;