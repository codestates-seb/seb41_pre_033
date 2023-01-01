import React, { useState, useRef, useEffect } from 'react';
import { Link } from 'react-router-dom';
import './header.css';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faBars } from "@fortawesome/free-solid-svg-icons";
import logoImg from "./logo-stackoverflow.png";
import SearchDropDown from "./Dropdown";
import MyPageDropDown from "./MypageDropdown";

function Header({isLogin, setLogin}) {
  //헤더
  const dropdownRef = useRef(null);
  const mypageRef = useRef(null);
  const [isActive, setIsActive] = useState(false);
  const [isMyPage, setIsMyPage] = useState(false);
  const openDrop = () => setIsActive(!isActive);
  const openMyPage = () => setIsMyPage(!isMyPage);

  useEffect(() => {
    const pageClickEvent = (e) => {
        if (dropdownRef.current !== null && !dropdownRef.current.contains(e.target)) {
            setIsActive(!isActive);
        }
    };
    if (isActive) {
        window.addEventListener('click', pageClickEvent);
    }
    return () => {
        window.removeEventListener('click', pageClickEvent);
    }
  }, [isActive]);

  useEffect(() => {
    const anotherClickEvent = (e) => {
        if (mypageRef.current !== null && !mypageRef.current.contains(e.target)){
            setIsMyPage(!isMyPage);
        }
    };
    if (isMyPage) {
        window.addEventListener('click', anotherClickEvent);
    }
    return () => {
        window.removeEventListener('click', anotherClickEvent);
    }
  }, [isMyPage]);

  return (
    <div id="header">
    <div id="header-body">
      <span id="logo">
        <Link to="/"><img src={logoImg} alt="stack Overflow" className='logo-img'/></Link>
      </span>
      <div id="search-bar">
        <input className='search-bar-body' type="text" placeholder='Search...' name="search-bar" onClick={openDrop} ref={dropdownRef}/>
        <div id="drop-down">
        {isActive && <SearchDropDown />}
        </div>
        </div>
        <div id="profile-picture">A</div>
    {isLogin ? 
    <div id="my">
      <div id="mypage" onClick={openMyPage} ref={mypageRef}>
        <FontAwesomeIcon className="three-bars" icon={faBars} />
      </div>
      <div id="mypage-dropdown">
        {isMyPage && <MyPageDropDown setLogin={setLogin}/>}
      </div>
    </div> :
    <div id="not-login-body">
        <Link to="/users/login"><button id="login-button">Log in</button></Link>
        <Link to="/users/sign-up" className='link'><button id="signup-button">Sign up</button></Link>
    </div>}
    </div>
    </div>
  );
}

export default Header;
