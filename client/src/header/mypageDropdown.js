import React from 'react';
import { Link } from 'react-router-dom';
import './header.css';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faUser, faArrowRightFromBracket } from "@fortawesome/free-solid-svg-icons";


function MyPageDropDown ({setLogin}) {
    return (
        <div id="dropdown-body">
            <div id="dropdown">Welcome, User A Name!</div>
            <Link to="/users/9463/A-Name" className="link"><div id="dropdown-mypage"><FontAwesomeIcon className="icon" icon={faUser} />My Page</div></Link>
            <Link to="/" className='link'><div id="dropdown-logout" onClick={() => {setLogin()}}><FontAwesomeIcon className="icon" icon={faArrowRightFromBracket} />Log Out</div></Link>
        </div>
    )
}

export default MyPageDropDown;