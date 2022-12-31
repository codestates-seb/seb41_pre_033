import { Link } from 'react-router-dom';
import React, { useState } from 'react';
import axios from 'axios';
import './login.css';

function Login ({setLogin}) {
    const [formValue, setformValue] = useState({
        username: '',
        password: ''
      });
      const handleChange = (event) => {
        setformValue({
          ...formValue,
          [event.target.name]: event.target.value
        });
      }
    
    //   const handleSubmit = async() => {
    //     const loginFormData = new FormData();
    //     loginFormData.append("username", formValue.username)
    //     loginFormData.append("password", formValue.password)

    //     axios.post(`http://ec2-13-125-198-24.ap-northeast-2.compute.amazonaws.com:8080/users/login`, loginFormData)
    //         .then( response => {
    //             console.log('response : ', JSON.stringify(response, null, 2))
    //         }).catch( error => {
    //             console.log('failed', error)
    //         })

    // //     try {
    // //     // make axios post request
    // //         const response = await axios({
    // //             method: "post",
    // //             url: domain+"/users/login",
    // //             data: loginFormData,
    // //             headers: { "Content-Type": "multipart/form-data" },
    // //         });
    // //     } catch(error) {
    // //       console.log(error)
    // //     }
    //     }
    const clickLogin = (e) => {
        e.preventDefault();
        const loginFormData = new FormData();
        loginFormData.append("username", formValue.username)
        loginFormData.append("password", formValue.password)
        fetch ("http://ec2-43-201-146-208.ap-northeast-2.compute.amazonaws.com:8080/users/login", {
           method: "POST",
           headers: {
            Accept: "application/json, text/plain, */*",
            "Content-Type": "application/json",
          },
           body: JSON.stringify(loginFormData),
      })
        .then((response) => response.json())
        .then((result) => {
          console.log("result is ",result);
        });
        setLogin();
      }


    return (
        <div id="login-wrapper">
            <div id="login-container">
            <form id="login-form">
            <label for="email" className='label'>Email</label>
            <input name="username" type="email" className='login-input' value={formValue.username} onChange={handleChange}/>
            <label for="password" className='label'>Password</label>
            <input name="password" type="password" className='login-input' value={formValue.password} onChange={handleChange}/>
            <div id="login-button-container">
                <Link to="/"><button onClick={clickLogin} type="submit" className='login-page-button'>Log in</button></Link>
                <Link to="/users/account-recovery"><button className='login-page-button'>Forgot Password</button></Link>
            </div>
            </form>
            </div>
        </div>
    )
}

export default Login;