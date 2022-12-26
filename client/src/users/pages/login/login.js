import { Link } from 'react-router-dom';
import './login.css';

function Login ({setLogin}) {

    const changeLogin = () => {
        console.log("logged in")
        setLogin()};
    return (
        <div id="login-wrapper">
            <div id="login-container">
            <form id="login-form">
            <label for="email">Email</label>
            <input name="email" type="text" id="email-input" />
            <label for="password">Password</label>
            <input name="password" type="text" id="password-input" />
            <div id="login-button-container">
                <Link to="/"><button onClick={changeLogin} type="submit">Log in</button></Link>
                <Link to="/users/sign-up"><button>Sign up</button></Link>
            </div>
            </form>
            </div>
        </div>
    )
}

export default Login;