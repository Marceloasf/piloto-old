import React from 'react';
import { Routes } from './routes';
import { BrowserRouter as Router, Link } from 'react-router-dom';

const App = () => {
  return (
    <Router>
      <ul>
        <li>
          <Link to="/">Home</Link>
        </li>
        <li>
          <Link to="/users">Users</Link>
        </li>
      </ul>
      <hr />
      <Routes />
    </Router>
  );
}

export default App;
