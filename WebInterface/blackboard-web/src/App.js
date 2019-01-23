import React, { Component } from "react";
import "./App.css";
import NavBar from "./components/navbar";
import SideBar from "./components/sidebar";
import Dashboard from "./components/dashboard";
import Auth from "./Auth";
import Home from "./components/home";
import { BrowserRouter, Route, Switch } from "react-router-dom";
import Addpage from "./components/addpage";
import NotFound from "./components/notfound";

const auth = new Auth();

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

  componentDidMount() {
    auth.handleAuthentication();
  }

  render() {
    auth.isAuthenticated();
    return (
      <BrowserRouter>
        <Switch>
          <Route path="/" component={Home} exact />
          <Route path="/add" component={Addpage} />
          <Route component={NotFound} />
        </Switch>
      </BrowserRouter>
    );
  }
}

export default App;
