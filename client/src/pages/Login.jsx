// import React from "react";
// import { Link } from "react-router-dom"
// import "../App.css"
// import {SignIn} from "../services/Signin";
// import {getAllTasks} from '../services/UserTasks'
// import {doSomeTesting} from '../services/UserTasks'
// import {useNavigate} from 'react-router-dom'
//
// export default class Login extends React.Component {
//
//
//     constructor(props) {
//         super(props);
//         this.state = {
//             usernameEmail: '',
//             password: '',
//             rememberMeCheck: false
//         };
//     }
//     usernameEmailChangeHandler = (e) => {
//         this.setState({ usernameEmail: e.target.value.toLowerCase() })
//     }
//
//     passwordChangeHandler = (e) => {
//         this.setState({ password: e.target.value })
//     }
//
//     rememberMeCheckHandler = (e) => {
//         this.setState({ rememberMeCheck: e.target.checked })
//     }
//
//     onSubmitHandler =   (e) => {
//         e.preventDefault();
//         console.log("did you just clicked submit? wow", this.state);
//         this.signInHandler();
//     }
//
//
//     signInHandler =  async () => {
//         let serverResponse;
//         try {
//             serverResponse = await SignIn(this.state);
//             console.log("SignIn was called successfully. ", serverResponse);
//         } catch (error) {
//             console.error("Error in SignIn:", error);
//         }
//         if(serverResponse.status === 200) {
//             const navigate = useNavigate();
//             navigate("/home");
//         } else {
//             alert("bad");
//         }
//         return serverResponse;
//     }
//
//     render() {
//         return (
//             <div className="container">
//                 <h1 className="login-heading">Sign in and start ToDoing</h1>
//                 <form className="ui form" method="POST" onSubmit={this.onSubmitHandler } >
//                     <div className="field">
//                         <input type="text" name="usernameEmail" value={this.state.usernameEmail} onChange={this.usernameEmailChangeHandler} placeholder="User Name or Email" />
//                     </div>
//                     <div className="field">
//                         <input type="password" name="password" value={this.state.password} onChange={this.passwordChangeHandler} placeholder="Password" />
//                     </div>
//                     <div className="field">
//                         <div className="ui checkbox">
//                             <input type="checkbox" name="rememberMe" isChecked={this.state.rememberMeCheck} onChange={this.rememberMeCheckHandler} />
//                             <label>Remember Me</label>
//                         </div>
//                     </div>
//                     <button  className="ui button centered login-signup-button" type="submit">SIGN IN</button>
//                     <p className="forgot-password centered"><Link to="/password-reset">Forgot Password ?</Link></p>
//                     <button onClick={this.onDebugHandler} className="ui button centered login-google-button">Sign In With Google</button>
//                     <p className="signUpFromLogin centered">Don't have an account? &nbsp; <Link to="/register"> Sign Up</Link></p>
//                 </form>
//             </div>
//         )
//     }
//
//
// }

import React, { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import "../App.css";
import { SignIn } from "../services/Auth";

const Login = () => {
    const [usernameEmail, setUsernameEmail] = useState("");
    const [password, setPassword] = useState("");
    const [rememberMeCheck, setRememberMeCheck] = useState(false);
    const navigate = useNavigate();

    const usernameEmailChangeHandler = (e) => {
        setUsernameEmail(e.target.value.toLowerCase());
    };

    const passwordChangeHandler = (e) => {
        setPassword(e.target.value);
    };

    const rememberMeCheckHandler = (e) => {
        setRememberMeCheck(e.target.checked);
    };

    const onSubmitHandler = async (e) => {
        e.preventDefault();
        console.log("Did you just click submit? Wow", { usernameEmail, password, rememberMeCheck });
        signInHandler();
    };

    const signInHandler = async () => {
        let serverResponse;
        try {
            serverResponse = await SignIn({ usernameEmail, password, rememberMeCheck });
            console.log("SignIn was called successfully. ", serverResponse);
        } catch (error) {
            console.error("Error in SignIn:", error);
        }
        if (serverResponse.status === 200) {
            navigate("/home");
        } else {
            alert("Bad");
        }
        return serverResponse;
    };

    return (
        <div className="container">
            <h1 className="login-heading">Sign in and start ToDoing</h1>
            <form className="ui form" method="POST" onSubmit={onSubmitHandler}>
                <div className="field">
                    <input
                        type="text"
                        name="usernameEmail"
                        value={usernameEmail}
                        onChange={usernameEmailChangeHandler}
                        placeholder="User Name or Email"
                    />
                </div>
                <div className="field">
                    <input
                        type="password"
                        name="password"
                        value={password}
                        onChange={passwordChangeHandler}
                        placeholder="Password"
                    />
                </div>
                <div className="field">
                    <div className="ui checkbox">
                        <input
                            type="checkbox"
                            name="rememberMe"
                            checked={rememberMeCheck}
                            onChange={rememberMeCheckHandler}
                        />
                        <label>Remember Me</label>
                    </div>
                </div>
                <button className="ui button centered login-signup-button" type="submit">
                    SIGN IN
                </button>
                <p className="forgot-password centered">
                    <Link to="/password-reset">Forgot Password?</Link>
                </p>
                <button className="ui button centered login-google-button">
                    Sign In With Google
                </button>
                <p className="signUpFromLogin centered">
                    Don't have an account? &nbsp; <Link to="/register">Sign Up</Link>
                </p>
            </form>
        </div>
    );
};

export default Login;
