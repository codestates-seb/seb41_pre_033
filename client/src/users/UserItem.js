import React from 'react';
import { Link } from 'react-router-dom';

export const UserItem = ({user}) => {
    const { userId, nickname, country, tags } = user;

    return (
        <li className="user-item-container">
            <div className="user-item-wrapper">
                <Link to={`/users/${(userId)}`}><div className="user-pfp">{`${(nickname).slice(0,1).toUpperCase()}`}</div></Link>
                <div className="users-info">
                    <Link to={`/users/${(userId)}`} className="link"><div className="user-name">{nickname}</div></Link>
                    <div className="user-location">{country}</div>
                    <div className="user-tags">{`${(tags)}`}</div>
                </div>
            </div>
        </li>
    )
}