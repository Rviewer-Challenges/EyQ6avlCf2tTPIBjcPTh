import React from 'react';
import ReactDOM from 'react-dom/client';
import { BrowserRouter, Routes, Route } from "react-router-dom";
import { ThemeProvider, CssBaseline } from '@mui/material';

import mytheme from './utils/mytheme';
import './index.css';

import '@fontsource/roboto/300.css';
import '@fontsource/roboto/400.css';
import '@fontsource/roboto/500.css';
import '@fontsource/roboto/700.css';

import App from './App';
import Login from './components/login.component';
import Register from './components/register.component';

import reportWebVitals from './reportWebVitals';
import Home from './pages/home.page';
import Bookmark from './pages/bookmark.page';
import About from './pages/about.page';
import CodeDisplay from './pages/codedisplay.page';

const root = ReactDOM.createRoot(
  document.getElementById('root') as HTMLElement
);
root.render(
  <React.StrictMode>
    <CssBaseline />
    <ThemeProvider theme={mytheme}>
      <BrowserRouter>
        <Routes>
          <Route index element={<Login />} />
          <Route path="register" element={<Register />} />
          <Route path="app" element={<App />}>
            <Route index element={<Home />} />
            <Route path='bookmark' element={<Bookmark />} />
            <Route path='about' element={<About />} />
            <Route path='code/:codeId' element={<CodeDisplay />} />
          </Route>
        </Routes>
      </BrowserRouter>
    </ThemeProvider>
  </React.StrictMode>
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
