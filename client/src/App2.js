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

function App2() {
  const tempDomain = "http://localhost:3001";
  const [isLogin, setIsLogin] = useState(false);
  const setLogin = () => setIsLogin(!isLogin);

  return (
    <div>
    <Router>
      <Header isLogin={isLogin} setLogin={setLogin}/>
      <Navbar />
      <Routes>
        <Route path="/" element={<Questions />} />
        <Route path="/questions" element={<Questions />} />
        <Route path="/tags" element={<Tags />} />
        <Route path="/users" element={<Users />} />
        <Route path="/users/:userId/:nickname" element={<User />}/>
        <Route path="/users/login" element={<Login setLogin={setLogin} domain={tempDomain}/>}/>
        <Route path="/users/sign-up" element={<Signup setLogin={setLogin} />}/>
      </Routes>
    </Router>
    </div>
  );
}

export default App2;