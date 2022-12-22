import React from 'react';
import { Link } from 'react-router-dom';
import './header.css';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faUser, faArrowRightFromBracket } from "@fortawesome/free-solid-svg-icons";


function MyPageDropDown () {
    return (
        <div id="dropdown-body">
            <div id="dropdown">Welcome, User Aa!</div>
            <Link to="/users/id" className="link"><div id="dropdown-mypage"><FontAwesomeIcon className="icon" icon={faUser} /> My Page</div></Link>
            <div id="dropdown-logout"><FontAwesomeIcon className="icon" icon={faArrowRightFromBracket} />Log Out</div>
        </div>
    )
}

export default MyPageDropDown;