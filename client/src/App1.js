import React, { useEffect, useState } from 'react';
import Header from "./header/header";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Ask from "./questions/pages/ask/Ask";
import Navbar from "./components/navbar/navbar";
import Questions from "./questions/Questions";
import Tags from "./tags/Tags";
import Users from "./users/Users";
// import User from "./users/pages/User";
// import Login from "./users/pages/login/Login"
// import Signup from "./users/pages/login/Signup";

function App() {
  const tempDomain = "http://localhost:3001";
  const [users, setUsers] = useState([]);
  const [isLogin, setIsLogin] = useState(false);
  const setLogin = () => setIsLogin(!isLogin);

  useEffect(() => {
    getUser();
  }, []);
  const getUser = () => {
    return fetch(tempDomain + `/users`)
      .then((res) => res.json())
      .then((data) => {
        setUsers(data);
      });
  };

  return (
    <div>
      <Router>
        <Header isLogin={isLogin} setLogin={setLogin} />
        <Navbar />
        <Routes>
          <Route path="/" element={<Questions />} />
          <Route path="/questions" element={<Questions />} />
          <Route path="/questions/ask" element={<Ask />} />
          <Route path="/tags" element={<Tags />} />
          <Route path="/users" element={<Users domain={tempDomain} />} />
          {/* <Route path="/users/:id/:user_nickname" element={<User users={users}/>}/> */}
          {/* <Route path="/users/login" element={<Login setLogin={setLogin} domain={tempDomain}/>}/> */}
          {/* <Route path="/users/sign-up" element={<Signup setLogin={setLogin} />}/> */}
          <Route path="/dev1" /> {/* 코드 리뷰용 임시 루트1 */}
          <Route path="/dev2" /> {/* 코드 리뷰용 임시 루트2 */}
          <Route path="/dev3" /> {/* 코드 리뷰용 임시 루트3 */}
        </Routes>
      </Router>
    </div>
  );
}

export default App;