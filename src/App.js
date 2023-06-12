import Register from "./Register.js";
import Login from "./Login.js"
import { userForm } from "react-hook-form";
import { Route, Routes } from "react-router-dom"
import "./App.css";
import 'react-bootstrap';

function App() {
  // const { register, handleSubmit, errors } = userForm();

  return (
    <div className="App">
      <Routes>
        <Route path="/login" Component={Login} />
        <Route path="/register" Component={Register} />
      </Routes>
    </div>

  );
}

export default App;
