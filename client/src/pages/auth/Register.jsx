import { Alert, Box } from "@mui/material";
import React from "react";
import 'react-bootstrap';
import { Link } from "react-router-dom";
import "../../App.css";

import { isNullOrEmpty } from "../../services/NullChecker";


const userName_REGEX = /^[A-Za-z][0-9-_]{3,23}/;
const pwd_REGEX =
    /^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*()-_+=~]).{8, 24}/;

export default class Register extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            username: "",
            email: "",
            password: "",
            registerError: ""
        };
    }

    onUserNameChangeHandler = (e) => {
        this.setState({ username: e.target.value.toLowerCase() });
    };

    onEmailChangeHandler = (e) => {
        this.setState({ email: e.target.value.toLowerCase() });
    };
    onPasswordChangeHandler = (e) => {
        this.setState({ password: e.target.value });
    };

    onSubmitHandler = (e) => {
        console.log("run");
        e.preventDefault();
            if(isNullOrEmpty(this.state.username)) {
                // setSignInError("username cannot be empty")
                this.setState({registerError: "username cannot be empty"})
            } else if(isNullOrEmpty(this.state.email)) {
                this.setState({registerError: "email cannot be empty"})
                // setSignInError("")
            } else if(isNullOrEmpty(this.state.password)){
                this.setState({registerError: "please provide password"})
            }
            // signInHandler();
    }

    render() {
        return (
            <Box sx={{ display: "flex", justifyContent: "center", alignItems: "center"}}>
                <div className="container d-flex justify-content-center h-50">
                    <div className="register-heading">
                        <h1>Get Started</h1>
                        <p>Create your account now and start ToDoing!</p>
                    </div>
                    <form className="ui form" onSubmit={this.onSubmitHandler}>
                        <div className="field">
                            { 
                            this.state.registerError &&
                                <Alert severity="error">{this.state.registerError}</Alert>
                            }
                        </div>
                        <div className="field">
                            <input
                                type="text"
                                name="username"
                                placeholder="User Name"
                                value={this.state.username}
                                onChange={this.onUserNameChangeHandler}
                            />
                        </div>
                        <div class="field">
                            <input
                                type="email"
                                name="email"
                                placeholder="Email"
                                value={this.state.email}
                                onChange={this.onEmailChangeHandler}
                            />
                        </div>
                        <div className="field">
                            <input
                                type="password"
                                name="password"
                                placeholder="Password"
                                value={this.state.password}
                                onChange={this.onPasswordChangeHandler}
                            />
                        </div>

                        <button className="ui button centered login-signup-button" type="submit">Register</button>
                        <p className="terms-and-conditions">By creating an account, you are agreeing to our &nbsp;<Link to="/terms-of-service" target="_blank">Terms of Service</Link>&nbsp;and&nbsp;<Link to="/privacy-policy" target="_blank">Privacy Policy</Link></p>
                        <button className="ui button centered login-google-button" type="submit">Sign Up With Google</button>
                        <p className="signUpFormLogin centered">Already have an account? &nbsp; <Link to="/login">Login</Link> </p>
                    </form>
                </div >
            </Box>
        );
    }
}
