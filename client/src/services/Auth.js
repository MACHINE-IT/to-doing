import BaseURL from './commons/BaseURL'
import {axiosClient} from './commons/BaseURL'
import {parse} from "@fortawesome/fontawesome-svg-core";


let isLoggedIn = () => {
    let item = localStorage.getItem("jwt");
    if(item != null) {
        let parsedItem = JSON.parse(item);
        let expirationTime = parsedItem.expirationTime;
        if(new Date().getTime() > expirationTime) {
            
        }
        let token = parsedItem.value;

    }

}


const signInURL = `${BaseURL}/auth/signin`;
const isAuthenticatedURL = `${BaseURL}/auth`

let SignIn = async (credentials) => {
    let serverResponse;
    try {
        let payload = payloadCreator(credentials);
        serverResponse = await axiosClient.post(signInURL, payload);
        let jwt = {
            value: serverResponse.data.split(",")[2],
            expiration: new Date().getTime() + 60000
        }
        localStorage.setItem("jwt", JSON.stringify(jwt));
    } catch (error) {
        console.log(error);
        serverResponse = error;
    }
    return serverResponse;
}

let payloadCreator = (payload) => {
    return {
        emailOrUsername: payload.usernameEmail,
        password: payload.password
    }
}

let isAuthenticatedCheck = () => {

}

export {SignIn};