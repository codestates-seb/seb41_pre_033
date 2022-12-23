//다수의 유저 보여주는 페이지
import './users.css'
import React from 'react';
import { UserItem } from "./UserItem";

const Users = ({users}) => {
    return (
        <div className='user-list-wrapper'>
            <ul className='user-list-container'>
                {users.map((e) => {
                    return <UserItem key={e.id} user={e}/>
                })}
            </ul>
        </div>
    )
}

export default Users;