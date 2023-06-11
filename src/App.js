import Register from "./Register.js";
import Login from "./Login.js"
import { userForm } from "react-hook-form";
import "./App.css";

function App() {
  // const { register, handleSubmit, errors } = userForm();

  return (
    <div className="App container">
      <Login />
    </div>
  );
}

export default App;
