import React, { useState } from 'react';
import Header from './header/Header';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Navbar from "./components/navbar/Navbar";
import Questions from "./questions/Questions";
import Tags from "./tags/Tags";
import Users from "./users/Users";
import User from "./users/pages/User";
import Login from "./users/pages/login/Login"
import Signup from "./users/pages/login/Signup"
import { EditUser } from './users/pages/EditUser';
import {AccountRecovery} from './users/pages/AccountRecovery';
import Ask from './questions/pages/ask/Ask';

function App2() {
  const [isLogin, setIsLogin] = useState(false);
  const [myUserId, setMyUserId] = useState(null);
  const setLogin = () => setIsLogin(!isLogin);

  return (
    <div>
    <Router>
      <Header isLogin={isLogin} setLogin={setLogin}/>
      <Navbar />
      <Routes>
        <Route path="/" element={<Questions />} />
        <Route path="/users" element={<Users />} />
        <Route path="/users/login" element={<Login setLogin={setLogin} setMyUserId={setMyUserId}/>}/>
        <Route path="/users/sign-up" element={<Signup setLogin={setLogin} setMyUserId={setMyUserId}/>}/>
        <Route path="/users/:id" element={<User />}/>
        <Route path="/users/edit/:id" element={<EditUser />}/>
        <Route path="/users/account-recovery" element={<AccountRecovery />}/>
        <Route path="/questions" element={<Questions />} />
        <Route path="/questions/ask" element={<Ask />} />
        <Route path="/tags" element={<Tags />} />
      </Routes>
    </Router>
    </div>
  );
}

export default App2;