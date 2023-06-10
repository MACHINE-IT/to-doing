import Register from "./Register.js";
import { userForm } from "react-hook-form";
import "./App.css";

function App() {
  // const { register, handleSubmit, errors } = userForm();

  return (
    <div className="container">
      <Register />
      {/* <form onSubmit={handleSubmit(onSubmit)}>
        <h1>Create Account</h1>
        <div className="ui divider"></div>
        <div className="ui form">
          <div className="field">
            <label>Username</label>
            <input
              type="text"
              name="username"
              placeholder="Username"
              ref={register}
            />
          </div>
          <div className="field">
            <label>Email</label>
            <input
              type="email"
              name="email"
              placeholder="Email ID"
              ref={register}
            />
          </div>
          <div className="field">
            <label>Password</label>
            <input
              type="password"
              name="password"
              placeholder="Password"
              ref={register}
            />
            <button className="fluid ui button blue submit-button">
              Submit
            </button>
          </div>
        </div>
      </form> */}
    </div>
  );
}

export default App;
