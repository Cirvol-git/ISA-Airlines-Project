//import axios from 'axios';

export const logIn = (k) => {
    return (dispatch, getState) => {
        /*
        axios({
            url: 'http://localhost:8080/api/korisnik/login',
            method: 'post',
            data: {
                email: k.email,
                pass: k.pass
            }
        }).then(res => {
            console.log(res);
            this.props.history.push("/home");
            dispatch({ type: 'LOGIN', log: res.data })
        }).catch(e => {
            alert("Pogresno unesen email ili lozinka")
        })
        */
        dispatch({ type: 'LOGIN', log: k })
       
    }
}

export const logOut = () => {
    return (dispatch, getState) => {
        console.log("logged out")
        dispatch({ type: 'LOGOUT'})   
    }
}