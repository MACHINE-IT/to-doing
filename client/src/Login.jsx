import React from "react";
import { Link } from "react-router-dom"
import "./App.css"

export default class Login extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            usernameEmail: '',
            password: '',
            rememberMeCheck: false
        };
    }
    usernameEmailChangeHandler = (e) => {
        this.setState({ usernameEmail: e.target.value.toLowerCase() })
    }

    passwordChangeHandler = (e) => {
        this.setState({ password: e.target.value })
    }

    rememberMeCheckHandler = (e) => {
        this.setState({ rememberMeCheck: e.target.checked })
    }

    render() {
        return (
            <div className="container">
                <h1 className="login-heading">Sign in and start ToDoing</h1>
                <form className="ui form" onSubmit={(e) => {
                    e.preventDefault();
                }}>
                    <div className="field">
                        <input type="text" name="usernameEmail" value={this.state.usernameEmail} onChange={this.usernameEmailChangeHandler} placeholder="User Name or Email" />
                    </div>
                    <div className="field">
                        <input type="password" name="password" value={this.state.password} onChange={this.passwordChangeHandler} placeholder="Password" />
                    </div>
                    <div className="field">
                        <div class="ui checkbox">
                            <input type="checkbox" name="rememberMe" isChecked={this.state.rememberMeCheck} onChange={this.rememberMeCheckHandler} />
                            <label>Remember Me</label>
                        </div>
                    </div>
                    <button class="ui button centered login-signup-button" type="submit">SIGN IN</button>
                    <p className="forgot-password centered"><Link to="/password-reset">Forgot Password ?</Link></p>
                    <button class="ui button centered login-google-button" type="submit">Sign In With Google</button>
                    <p className="signUpFromLogin centered">Don't have an account? &nbsp; <Link to="/register"> Sign Up</Link></p>
                </form>
            </div>
        )
    }
}