import React from "react";
import { Link } from "react-router-dom";
import "./App.css";
//import { useState, useRef, useEffect } from "react";
import {
    faCheck,
    faTimes,
    faInfoCircle,
} from "@fortawesome/fontawesome-svg-core";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import 'react-bootstrap';

const userName_REGEX = /^[A-Za-z][0-9-_]{3,23}/;
const pwd_REGEX =
    /^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*()-_+=~]).{8, 24}/;

export default class Register extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            username: "",
            email: "",
            password: ""
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

    render() {
        return (
            <div className="container">
                <div className="register-heading">
                    <h1>Get Started</h1>
                    <p>Create your account now and start ToDoing!</p>
                </div>
                <form className="ui form" onSubmit={(e) => {
                    e.preventDefault();
                }
                }>
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
        );
    }
}
