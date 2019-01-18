import React, { Component } from "react";
import "./App.css";
import NavBar from "./components/navbar";
import SideBar from "./components/sidebar";
import Dashboard from "./components/dashboard";

class App extends Component {
  state = {
    klas: "keuze",
    prof: "keuze",
    les: "keuze",
    lokaal: "keuze"
  };

  selectKlas = waarde => {
    this.setState({ klas: waarde });
  };

  selectProf = waarde => {
    this.setState({ prof: waarde });
  };

  selectLes = waarde => {
    this.setState({ les: waarde });
  };

  selectLokaal = waarde => {
    this.setState({ lokaal: waarde });
  };

  render() {
    return (
      <React.Fragment>
        <NavBar />

        <div className="container-fluid">
          <div className="row">
            <SideBar
              onSelectKlas={this.selectKlas}
              onSelectProf={this.selectProf}
              onSelectLes={this.selectLes}
              onSelectLokaal={this.selectLokaal}
            />
            <Dashboard
              klas={this.state.klas}
              prof={this.state.prof}
              les={this.state.les}
              lokaal={this.state.lokaal}
            />
          </div>
        </div>
      </React.Fragment>
    );
  }
}

export default App;
