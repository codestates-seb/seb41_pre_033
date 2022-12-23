import React, { useState } from 'react';
import Header from './header/Header';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Ask from "./questions/pages/ask/Ask";
import Navbar from "./components/navbar/Navbar";
import Questions from "./questions/Questions";
import Tags from "./tags/Tags";
import Users from "./users/Users";

function App() {
  return (
    <Router>
      <Header />
      <Navbar />
      <Routes>
        <Route path="/" element={<Questions />} />
        <Route path="/questions" element={<Questions />} />
        <Route path="/tags" element={<Tags />} />
        <Route path="/users" element={<Users />} />
        <Route path="/dev1" /> {/* 코드 리뷰용 임시 루트1 */}
        <Route path="/dev2" /> {/* 코드 리뷰용 임시 루트2 */}
        <Route path="/dev3" /> {/* 코드 리뷰용 임시 루트3 */}
      </Routes>
    </Router>
  );
}

export default App;
