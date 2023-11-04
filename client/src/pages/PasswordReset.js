import React from "react";

export default class PasswordReset extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            email: '',
            tempPassword: "",
            newPassword: "",
            confirmPassword: "",
        };
    }

    emailChangeHandler = (e) => {
        this.setState({ email: e.target.value.toLowerCase() });
    };
    tempPasswordChangeHandler = (e) => {
        this.setState({ tempPassword: e.target.value });
    };
    newPasswordChangeHandler = (e) => {
        this.setState({ newPassword: e.target.value });
    };
    confirmPasswordChangeHandler = (e) => {
        this.setState({ confirmPassword: e.target.value });
    };

    render() {
        return (
            <div className="container">
                <form className="ui form" onSubmit={(e) => {
                    e.preventDefault();
                }
                }>
                    <div class="field">
                        <input
                            type="email"
                            name="email"
                            placeholder="Email"
                            value={this.state.email}
                            onChange={this.emailChangeHandler}
                        />
                    </div>
                    <div className="field">
                        <input
                            type="text"
                            name="tempPassword"
                            placeholder="Password sent to you"
                            value={this.state.tempPassword}
                            onChange={this.tempPasswordChangeHandler}
                        />
                    </div>
                    <div class="field">
                        <input
                            type="password"
                            name="newPassword"
                            placeholder="Enter new password"
                            value={this.state.newPassword}
                            onChange={this.newPasswordChangeHandler}
                        />
                    </div>
                    <div class="field">
                        <input
                            type="password"
                            name="confirmPassword"
                            placeholder="Confirm password"
                            value={this.state.confirmPassword}
                            onChange={this.confirmPasswordChangeHandler}
                        />
                    </div>

                    <button class="ui button centered password-reset-button" type="submit">Reset Password</button>
                </form>
            </div>
        )
    }
}