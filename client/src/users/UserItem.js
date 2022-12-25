import React from 'react';
import { Link } from 'react-router-dom';

export const UserItem = ({user}) => {
    const { id, user_nickname, user_country, user_tags } = user;

    return (
        <li className="user-item-container">
            <div className="user-item-wrapper">
                <Link to={`/users/${user.id}/${user.user_nickname}`}><div className="user-pfp">{`${(user_nickname).slice(0,1).toUpperCase()}`}</div></Link>
                <div className="users-info">
                    <Link to={`/users/${user.id}/${user.user_nickname}`}><div className="user-name">{user_nickname}</div></Link>
                    <div className="user-location">{user_country}</div>
                    <div className="user-tags">{`${(user_tags)[0]}, ${(user_tags)[1]}`}</div>
                </div>
            </div>
        </li>
    )
}