import Register from "./Register.js";
import Login from "./Login.jsx"
import PasswordReset from "./PasswordReset.js";
import TermsAndConditions from "./TermsAndConditions.js";
import { userForm } from "react-hook-form";
import { Route, Routes } from "react-router-dom"
import "./App.css";
import 'react-bootstrap';

function App() {
  // const { register, handleSubmit, errors } = userForm();

  return (
    <div className="App">
      <Routes>
        <Route path="/login" element={<Login />} />
        <Route path="/password-reset" element={<PasswordReset />} />
        <Route path="/register" element={<Register />} />
        <Route path="/terms-of-service" element={<TermsAndConditions />} />
        <Route path="/privacy-policy" element={<TermsAndConditions />} />
      </Routes>
    </div>

  );
}

export default App;
