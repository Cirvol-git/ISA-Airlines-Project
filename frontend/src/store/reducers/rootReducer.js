import logReducer from './logReducer';
import aviosReducer from './aviosReducer';
import destReducer from './destReducer';
import letPripReducer from './letPripReducer';
import letReducer from './letReducer';
import prijateljiReducer from './prijateljiReducer';
import rezPripReducer from './rezPripReducer';
import rezReducer from './rezReducer';
import { combineReducers } from 'redux';

const rootReducer = combineReducers({
    log: logReducer,
    avios: aviosReducer,
    dests: destReducer,
    letp: letPripReducer,
    lets: letReducer,
    prijatelji: prijateljiReducer,
    rezp : rezPripReducer,
    rez : rezReducer
});

export default rootReducer;