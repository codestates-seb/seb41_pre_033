//다수의 유저 보여주는 페이지
import './users.css'
import { UserItem } from "./UserItem";
import { Pagination } from "./pages/Pagination";
import React, { useEffect, useState } from 'react';
import { useQuery } from "react-query";
import axios from "axios";

const Users = () => {
    const domain = "http://localhost:3001"
  const [users, setUsers] = useState([]);
  const [page, setPage] = useState(1);

//   const useUsers = (activePage) => {
//     return useQuery(
//       ["users", activePage],
//       async () => {
//         const { data } = await axios.get(
//           `https://localhost:3001/users?page=${activePage}`
//         );
   
//         return data;
//       },
//       { keepPreviousData: true }
//     );
//    };
//    const usersList = useUsers(page);
//    setUsers(usersList);

  useEffect(() => {
    getUser();
  }, [page])
  const getUser = () => {
    return fetch(domain+`/users?page=${page}`)
    .then((res) => res.json())
    .then((data) => {
      setUsers(data)
    })
  };
    return (
        <div className='user-list-wrapper'>
            <ul className='user-list-container'>
                {users.map((e) => {
                    return <UserItem key={e.userId} user={e}/>
                })}
            </ul>
            <div>
                <Pagination page={page} setPage={setPage} />
            </div>
        </div>
    )
}

export default Users;