import './user.css';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faPen, faLocationDot } from "@fortawesome/free-solid-svg-icons";
import { faGithub } from "@fortawesome/free-brands-svg-icons";
import { useParams, Link } from 'react-router-dom';
import React, { useEffect, useState } from 'react';
import ProfileTab from './ProfileTab';
import ActivityTab from './ActivityTab';
import axios from 'axios';

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
            fetch(`/users/${id}`)
            .then((res) => {
                return res.json()
            })
            .then((data) => {
                setOneUsers(data.data);
            })
        };
        getOneUser();
    }, [])

    return (
    <div id="user-body">
        <div id="user-header">
            <div id="user-pfp">{oneUser.nickname.slice(0,1).toUpperCase()}</div>
            <div id="user-text">
                <div id="user-name">{oneUser.nickname}</div>
                <div id="user-info">
                    <div className='user-info-item'><a href={oneUser.link} target="_blank" className='link'><FontAwesomeIcon className="user-icon" icon={faGithub} /></a></div>
                    <div className='user-info-item'><FontAwesomeIcon className="user-icon" icon={faLocationDot} /> From {oneUser.country}</div>
                </div>
                <div className='user-info-item'>{oneUser.title}</div>
            </div>
            <div id="edit-button-container">
                <Link to={`/users/edit/${id}`} className='link'><button id="edit-profile"><FontAwesomeIcon className="user-icon" icon={faPen} />Edit profile</button></Link>
            </div>
        </div>
        <div id="user-menu">
            <div className='user-menu-item' onClick={profileTab}>Profile</div>
            <div className='user-menu-item' onClick={activityTab}>Activity</div>
        </div>
        <div id="user-content">
            {tab==="profile" ? <ProfileTab rep={oneUser.reputation} about={oneUser.introduction} tags={oneUser.userTags} ans={oneUser.answers.length} qs={oneUser.questions.length}/>:<ActivityTab ans={oneUser.answers} qs={oneUser.questions}/>}
        </div>
    </div>
    )

}

export default User;