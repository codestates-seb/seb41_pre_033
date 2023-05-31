import React from 'react';
import { Link } from 'react-router-dom';
import './users.css';

export const UserItem = ({user}) => {
    const { userId, nickname, country, userTags, reputation } = user;

    return (
        <li className="user-item-container">
            <div className="user-item-wrapper">
                <Link to={`/users/${(userId)}`}><div className="user-pfp">{`${(nickname).slice(0,1).toUpperCase()}`}</div></Link>
                <div className="users-info">
                    <Link to={`/users/${(userId)}`} className="link"><div className="user-name">{nickname}</div></Link>
                    <div className="user-location items">{country}</div>
                    <div className='reputation-num items'>{reputation}</div>
                    <div className="user-tags items">{ userTags.length>=2 ? `${(userTags)[0].tagName}, ${(userTags)[1].tagName}`: `${userTags.length<1 ? "" : `${(userTags)[0].tagName}`}`}</div>
                </div>
            </div>
        </li>
    )
}