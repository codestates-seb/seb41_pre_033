//다수의 유저 보여주는 페이지
import './users.css'
import { UserItem } from "./UserItem";
import Pagination from "./pages/pagination";
import React, { useEffect, useState } from 'react';
import { useQuery } from "react-query";
import axios from "axios";

const Users = () => {
    const domain = "http://ec2-43-201-146-208.ap-northeast-2.compute.amazonaws.com:8080"
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
    return fetch(domain+`/users?page=${page}&tab=${tab}`)
    .then((res) => res.json())
    .then((data) => {
      setUsers(data.data);
    })
  };
    return (
        <div className='user-list-wrapper'>
          <div id="tab-chage">
            <div className='mini-title'>Sort By</div>
            <div id="buttons-container">
            <button onClick={repTab}>Reputation</button>
            <button onClick={abcTab}>Basic (ABC)</button>
            </div>
          </div>
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