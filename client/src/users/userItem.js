import React from 'react';
import { Link } from 'react-router-dom';

export const UserItem = ({user}) => {
    const { userId, nickname, country, tags } = user;
    const tagList0 = JSON.stringify(tags[0].tagName);
    let tagList1 = "";
    if (tags[1]){
        tagList1 = ", "+JSON.stringify(tags[1].tagName);
    }

    return (
        <li className="user-item-container">
            <div className="user-item-wrapper">
                <Link to={`/users/${(user.userId)}/${(user.nickname)}`}><div className="user-pfp">{`${(nickname).slice(0,1).toUpperCase()}`}</div></Link>
                <div className="users-info">
                    <Link to={`/users/${(user.userId)}/${(user.nickname)}`} className="link"><div className="user-name">{nickname}</div></Link>
                    <div className="user-location">{country}</div>
                    <div className="user-tags">{`${(tagList0)}, ${(tagList1)}`}</div>
                </div>
            </div>
        </li>
    )
}