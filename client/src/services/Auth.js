import BaseURL, { axiosClient } from './commons/BaseURL';


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


const signInURL = `${BaseURL}/auth/sign-in`;
const signOutURL = `${BaseURL}/auth/sign-out`;
const isAuthenticatedURL = `${BaseURL}/auth`

let SignIn = async (credentials) => {
    let serverResponse;
    try {
        let payload = payloadCreator(credentials);
        serverResponse = await axiosClient.post(signInURL, payload);
    } catch (error) {
        console.log(error);
        serverResponse = error;
    }
    return serverResponse;
}

let SignOut = async () => {
    let serverResponse;
    try {
        serverResponse = await axiosClient.get(signOutURL);
    } catch (error) {
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

export { SignIn, SignOut };

