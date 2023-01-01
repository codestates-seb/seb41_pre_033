import { Link, useNavigate } from "react-router-dom";
import { useState } from 'react';
import './signup.css';
import axios from 'axios';

function Signup ({setLogin, setMyUserId}) {
  const navigate = useNavigate();
  const [formValue, setformValue] = useState({
    email: '',
    nickname: '',
    password: ''
  });
  const handleChange = (event) => {
    setformValue({
      ...formValue,
      [event.target.name]: event.target.value
    });
  }

      const signUp = (e) => {
        e.preventDefault();
        axios.post("/users/sign-up", formValue)
        .then((response) => {
          console.log("res is ", response);
          if (response.status===201) {
            alert("Signed up successfully!");
            setLogin();
            setMyUserId(response.data.userId);
            navigate("/");
          }
        })
        .catch((err) => {
          if (err.response.status===400){
            alert("Incorrect Email form!")
          }
          if (err.response.status===409) {
            alert("Account already exist!")
          }
        });
      };

    return (
    <div id="signup-wrapper">
        <div id="signup-container">
            <form id="signup-form">
            <label for="nick">Display name</label>
            <input name="nickname" type="text" id="nickname-input" onChange={handleChange}/>
            <label for="email">Email</label>
            <input name="email" type="email" id="email-input" onChange={handleChange}/>
            <label for="password">Password</label>
            <input name="password" type="password" id="password-input" onChange={handleChange}/>
            <Link to="/"><button id="signup-page-button" onClick={signUp} type="submit">Sign up</button></Link>
            </form>
        </div>
    </div>)
}

export default Signup;