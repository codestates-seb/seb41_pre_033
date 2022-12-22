import React, { useState } from 'react';
import Header from './header/Header';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';

function App() {
  return (
    <Router>
      <Header />
      <div className="test">
      </div>;
    </Router>
  )
}

export default App;
