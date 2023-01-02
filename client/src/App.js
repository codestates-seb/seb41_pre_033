import React, { useState } from 'react';
import Header from "./header/header";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";

import Questions from "./questions/Questions";
import Users from "./users/Users";
import Login from "./users/pages/login/login";
import Signup from "./users/pages/login/Signup";
import User from "./users/pages/User";
import EditUser from "./users/pages/EditUser";
import { AccountRecovery } from "./users/pages/AccountRecovery";
import Ask from "./questions/pages/ask/Ask";
import Question from "./questions/pages/Question";
// import EditQuestion from "./questions/pages/edit/EditQuestion";
import Tags from "./tags/Tags";

function App() {
  const [isLogin, setIsLogin] = useState(false);
  const [myUserId, setMyUserId] = useState(null);
  const setLogin = () => setIsLogin(!isLogin);

  return (
    <div>
      <Router>
        <Header isLogin={isLogin} setLogin={setLogin} />
        <Routes>
          <Route path="/" element={<Questions />} />
          <Route path="/users" element={<Users />} />
          <Route
            path="/users/login"
            element={<Login setLogin={setLogin} setMyUserId={setMyUserId} />}
          />
          <Route
            path="/users/sign-up"
            element={<Signup setLogin={setLogin} setMyUserId={setMyUserId} />}
          />
          <Route path="/users/:id" element={<User />} />
          <Route path="/users/edit/:id" element={<EditUser />} />
          <Route path="/users/account-recovery" element={<AccountRecovery />} />
          <Route path="/questions" element={<Questions />} />
          <Route path="/questions/ask" element={<Ask />} />
          <Route path="/questions/:question_id" element={<Question />} />
          {/* <Route
            path="/questions/edit/:question_id"
            element={<EditQuestion />}
          /> */}
          <Route path="/tags" element={<Tags />} />
        </Routes>
      </Router>
    </div>
  );
}



export default App;