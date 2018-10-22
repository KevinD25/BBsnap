import React, { Component } from "react";
import logo from "./logo.svg";
import "./App.css";
import NavBar from "./components/navbar";
import Photo from "./components/photo";
import SideBar from "./components/sidebar";
import Dashboard from "./components/dashboard";

class App extends Component {
  render() {
    return (
      <body>
        <NavBar />

        <div class="container-fluid">
          <div class="row">
            <SideBar />
            <Dashboard />
          </div>
        </div>
      </body>
    );
  }
}

export default App;
