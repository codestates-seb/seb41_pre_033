import { Link } from "react-router-dom";
import './signup.css';

function Signup ({setLogin}) {
    const changeLogin = () => setLogin();
    const form = {
        username: document.querySelector("#email-input"),
        password: document.querySelector("#password-input"),
        nickname: document.querySelector("#nickname-input"),
        submit: document.querySelector("#signup-page-button"),
      };
      const signUp = (e) => {
        const signup = "https://localhost:3001/users/sign-up";
      
        fetch(signup, {
          method: "POST",
          body: JSON.stringify({
            username: form.username.value,
            password: form.password.value,
          }),
        })
          .then((response) => response.json())
          .then((data) => {
            console.log(data);
            // code here //
            if (data.error) {
              alert("Error Password or Username"); /*displays error message*/
            } else {
                alert("Signed up successfully!")
            }
          })
          .catch((err) => {
            console.log(err);
          });
      };

    return (
    <div id="signup-wrapper">
        <div id="signup-container">
            <form id="signup-form">
            <label for="nick">Display name</label>
            <input name="nick" type="text" id="nickname-input"/>
            <label for="email">Email</label>
            <input name="email" type="email" id="email-input" />
            <label for="password">Password</label>
            <input name="password" type="password" id="password-input" />
            <Link to="/"><button id="signup-page-button" onClick={signUp} type="submit">Sign up</button></Link>
            </form>
        </div>
    </div>)
}

export default Signup;