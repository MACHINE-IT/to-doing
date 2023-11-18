    import styled from "@emotion/styled";
import { Alert, Box } from "@mui/material";
import React, { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import "../../App.css";
import { SignIn } from "../../services/Auth";
import { isNullOrEmpty } from "../../services/NullChecker";



    const Login = () => {
        const [usernameEmail, setUsernameEmail] = useState("");
        const [password, setPassword] = useState("");
        const [rememberMeCheck, setRememberMeCheck] = useState(false);
        const [signInError, setSignInError] = useState(false);
        const navigate = useNavigate();

        const StyledAlert = styled(Alert) ({
            // display: () => signInError ? 'inline-block' : 'none',
            transition: 'ease-in-out 0.32s' 
        })

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
            if(isNullOrEmpty(usernameEmail)) {
                setSignInError("username or email cannot be empty")
            } else if(isNullOrEmpty(password)) {
                setSignInError("please provide password")
            } else {
                signInHandler();
            }
            // console.log("Did you just click submit? Wow", { usernameEmail, password, rememberMeCheck });
        };

        const signInHandler = async () => {
            let serverResponse;
            try {
                serverResponse = await SignIn({ usernameEmail, password, rememberMeCheck });
                console.log("SignIn was called successfully. ", serverResponse);
            } catch (error) {
                setSignInError(error)
                console.error("Error in SignIn:", error);
            }
            if (serverResponse !== undefined && serverResponse.status === 202) {
                navigate("/my-day");
            } else if(serverResponse === undefined || serverResponse.code === "ERR_NETWORK") {
                setSignInError(serverResponse.message + ", Please try again.");
            } else {
                setSignInError(serverResponse.response.data);
                alert("Bad");
            }
            return serverResponse;
        };

        return (
            <Box sx={{ display: "flex", justifyContent: "center", alignItems: "center"}}>
                <div className="container d-flex justify-content-center h-50">
                    <h1 className="login-heading">Sign in and start ToDoing</h1>
                    <form className="ui form" method="POST" onSubmit={onSubmitHandler}>
                        <div className="field">
                            { 
                            signInError &&
                                <StyledAlert severity="error">{signInError}</StyledAlert>
                            }
                        </div>

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
            </Box>
        );
    };

    export default Login;
