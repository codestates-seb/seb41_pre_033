//다수의 유저 보여주는 페이지
import './users.css';
import { UserItem } from './userItem';
import { Pagination } from './pages/pagination';
import React, { useEffect, useState } from 'react';
import { useQuery } from 'react-query';
import axios from 'axios';

const Users = () => {
  const domain = 'http://localhost:3001';
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
  }, [page]);
  const getUser = () => {
    return fetch(domain + `/users?page=${page}`)
      .then((res) => res.json())
      .then((data) => {
        setUsers(data);
      });
  };
  return (
    <div className="user-list-wrapper">
      <ul className="user-list-container">
        {users.map((e) => {
          return <UserItem key={e.userId} user={e} />;
        })}
      </ul>
      <div>
        <Pagination page={page} setPage={setPage} />
      </div>
    </div>
  );
};

export default Users;

// 네스티드 라우팅 구현해주세요
{
  /*
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';

  import Login from './users/pages/login/Login';
import Signup from './users/pages/login/Signup';
import User from './users/pages/user';
import EditUser from './users/pages/EditUser';
import AccountRecovery from './users/pages/login/AccountRecovery';


<Router>
  <Routes>
    <Route path="/users/login" element={<Login setLogin={setLogin} />} />
    <Route path="/users/sign-up" element={<Signup setLogin={setLogin} />} />
    <Route path="/users/:id" element={<User />} />
    <Route path="/users/edit/:id" element={<EditUser />} />
    <Route path="/users/account-recovery" element={<AccountRecovery />} />
  </Routes>
</Router>;
*/
}
