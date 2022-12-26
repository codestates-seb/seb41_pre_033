import { Link } from "react-router-dom";
import './signup.css';

function Signup ({setLogin}) {
    const changeLogin = () => setLogin();
    return (
    <div id="signup-wrapper">
        <div id="signup-container">
            <form id="signup-form">
            <label for="nick">Display name</label>
            <input name="nick" />
            <label for="email">Email</label>
            <input name="email" type="text" id="email-input" />
            <label for="password">Password</label>
            <input name="password" type="text" id="password-input" />
            <Link to="/"><button id="signup-page-button" onClick={changeLogin} type="submit">Sign up</button></Link>
            </form>
        </div>
    </div>)
}

export default Signup;