//다수의 유저 보여주는 페이지
import './users.css'
import { UserItem } from "./UserItem";
import { Pagination } from './pages/pagination';
import { Link } from 'react-router-dom';
import React, { useEffect, useState } from 'react';

const Users = ({domain}) => {
  const [users, setUsers] = useState([]);
  const [page, setPage] = useState(1);

  useEffect(() => {
    getUser();
  }, [])
  const getUser = () => {
    return fetch(domain+`/users?page=${page}`)
    .then((res) => res.json())
    .then((data) => {
      setUsers(data)
    })
  };
  const firstPage = () => {
    setPage(1);
    console.log(page);
  }
  const nextPage = () => {
    setPage(2);
    console.log(page);
  }
    return (
        <div className='user-list-wrapper'>
            <ul className='user-list-container'>
                {users.map((e) => {
                    return <UserItem key={e.id} user={e}/>
                })}
            </ul>
            <div>
                <Link to={`/users?page=${page}`}><button onClick={firstPage}>1</button></Link>
                <Link to={`/users?page=${page}`}><button onClick={nextPage}>2</button></Link>
            </div>
        </div>
    )
}

export default Users;