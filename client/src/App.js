import React, { useState } from 'react';
import Header from './header/Header';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Ask from "./questions/pages/ask/Ask";
import Navbar from "./components/navbar/Navbar";

function App() {
  return (
    <Router>
      <Header />
      <Navbar />
      <Ask />
      <div className="test">테스트</div>;
    </Router>
  );
}

export default App;
