import React, { useState } from 'react';
import Header from './header/header';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import TopQuestions from './topQuestions';
import Questions from './questions/questions';
import Users from './users/users';
import Tags from './tags/tags';

function App() {
  const [isLogin, setIsLogin] = useState(false);
  const setLogin = () => setIsLogin(!isLogin);

  return (
    <div>
      <Header isLogin={isLogin} setLogin={setLogin} />
      <Router>
        <Routes>
          <Route path="/" element={<TopQuestions />} />
          <Route path="/users" element={<Users />} />
          <Route path="/questions" element={<Questions />} />
          <Route path="/tags" element={<Tags />} />
        </Routes>
      </Router>
    </div>
  );
}

export default App;
