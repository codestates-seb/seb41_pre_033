//다수의 유저 보여주는 페이지
import './users.css'
import { UserItem } from "./UserItem";
import Pagination from "./pages/pagination";
import React, { useEffect, useState } from 'react';
import Navbar from '../components/navbar/Navbar';
import { useQuery } from "react-query";
import axios from "axios";

const Users = () => {
    const domain = "http://ec2-15-164-87-251.ap-northeast-2.compute.amazonaws.com:8080/"
  const [users, setUsers] = useState([]);
  const [page, setPage] = useState(1);
  const [tab, setTab] = useState("abc");
  const repTab = () => setTab("reputation");
  const abcTab = () => setTab("abc");

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
  }, [page, tab])
  const getUser = () => {
    return fetch(domain+`users?page=${page}&tab=${tab}`)
    .then((res) => res.json())
    .then((data) => {
      setUsers(data.data);
    })
  };
    return (
      <div>
        <div className='user-list-wrapper'>
          <Navbar />
          <div>
          <div id="tab-change">
            <div className='users-title'>Users</div>
            <div id="buttons-container">
              <button className='user-list-tab rep-tab' onClick={repTab}>Reputation</button>
              <button className='user-list-tab abc-tab' onClick={abcTab}>Basic (ABC)</button>
            </div>
          </div>
            <ul className='user-list-container'>
                {users.map((e) => {
                    return <UserItem key={e.userId} user={e}/>
                })}
            </ul>
            </div>
        </div>
        <div id="pagination-container">
          <Pagination page={page} setPage={setPage} />
        </div>
      </div>
    )
}

export default Users;