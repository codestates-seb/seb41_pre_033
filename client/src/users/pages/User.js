import './user.css';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faPen, faLocationDot } from "@fortawesome/free-solid-svg-icons";
import { faGithub } from "@fortawesome/free-brands-svg-icons";
import { useParams, Link } from 'react-router-dom';
import React, { useEffect, useState } from 'react';
import ProfileTab from './ProfileTab';
import ActivityTab from './ActivityTab';
import axios from 'axios';
import Navbar from '../../components/navbar/Navbar';

//개별 유저의 페이지
const User = () => {
    const { id } = useParams();
    const [oneUser, setOneUsers] = useState({
        "userId": 1,
        "nickname": "loading...",
        "email": "loading...",
        "country": null,
        "reputation": 0,
        "title": null,
        "introduction": null,
        "link": null,
        "userTags": [],
        "questions": [],
        "answers": []
      });
    const [tab, setTab] = useState("profile");
    const profileTab = () => setTab("profile");
    const activityTab = () => setTab("activity");

    useEffect(() => {
        const getOneUser = () => {
            fetch(`http://ec2-15-164-87-251.ap-northeast-2.compute.amazonaws.com:8080/users/${id}`)
            .then((res) => res.json())
            .then((data) => {
                setOneUsers(data.data);
            })
        };
        getOneUser();
    }, [])

    return (
    <div id="user-body">
        <Navbar />
        <div id="user-wrapper">
        <div id="user-header">
            <div id="user-pfp">{oneUser.nickname.slice(0,1).toUpperCase()}</div>
            <div id="user-text">
                <div id="user-name">{oneUser.nickname}</div>
                <div id="user-info">
                    <div className='user-info-item github'><a href={oneUser.link} target="_blank" className='link'><FontAwesomeIcon className="user-icon-git" icon={faGithub} /></a></div>
                    {oneUser.country===null ? "":<div className='user-info-item'><FontAwesomeIcon className="user-icon" icon={faLocationDot} /> From {oneUser.country}</div>}
                </div>
                <div className='user-info-item user-title'>{oneUser.title}</div>
            </div>
            <div id="edit-button-container">
                <button id="edit-profile-button"><Link to={`/users/edit/${id}`} className='link'><FontAwesomeIcon className="user-icon" icon={faPen} /> Edit profile</Link></button>
            </div>
        </div>
        <div id="user-menu">
            <div className={tab==="profile" ? "selected":"user-menu-item"} onClick={profileTab}>Profile</div>
            <div className={tab==="activity" ? "selected":"user-menu-item"} onClick={activityTab}>Activity</div>
        </div>
        <div id="user-content">
            {tab==="profile" ? <ProfileTab rep={oneUser.reputation} about={oneUser.introduction} tags={oneUser.userTags} ans={oneUser.answers.length} qs={oneUser.questions.length}/>:<ActivityTab ans={oneUser.answers} qs={oneUser.questions}/>}
        </div>
        </div>
    </div>
    )

}

export default User;