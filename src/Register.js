import React from "react";
import "./App.css";
//import { useState, useRef, useEffect } from "react";
import {
    faCheck,
    faTimes,
    faInfoCircle,
} from "@fortawesome/fontawesome-svg-core";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";

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
            isChecked: false
        };
    }

    onUserNameChangeHandler = (e) => {
        this.setState({ username: e.target.value });
    };

    onEmailChangeHandler = (e) => {
        this.setState({ email: e.target.value });
    };
    onPasswordChangeHandler = (e) => {
        this.setState({ password: e.target.value });
    };

    checkboxCheckHandler = (e) => {
        this.setState({ isChecked: e.target.checked })
    }

    render() {
        return (
            <div className="container">
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
                    <div class="field">
                        <input
                            type="password"
                            name="password"
                            placeholder="Password"
                            value={this.state.password}
                            onChange={this.onPasswordChangeHandler}
                        />
                    </div>
                    <div class="field">
                        <div class="ui checkbox">
                            <input type="checkbox" name="checkbox" checked={this.state.isChecked} onChange={this.checkboxCheckHandler} />
                            <label>I agree to the Terms and Conditions</label>
                        </div>
                    </div>
                    <button class="ui button centered-button" type="submit">Register</button>
                </form>
            </div >
        );
    }
}
