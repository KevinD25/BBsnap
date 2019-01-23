import React, { Component } from "react";
import Auth from "../Auth";

const auth = new Auth();

async function disableCamera(cameraid) {
  console.log("post naar server");
  fetch("http://brabo2.ddns.net:555/disablephoto/" + cameraid, {
    method: "POST",
    headers: {
      Accept: "application/json",
      "Content-Type": "application/json"
    }
  });
}

function checkLog() {
  auth.logout();
  auth.login();
}

class NavBar extends Component {
  state = {
    enabled: true,
    loggedin: false,
    lokalen: [],
    camera: "10"
  };

  componentDidMount() {
    const auth = new Auth();
    auth.handleAuthentication();
    this.getStatusCamera();
    //this.getLoggedIn();
    fetch("http://brabo2.ddns.net:555/camera")
      .then(res => res.json())
      .then(
        result => {
          this.setState({
            isLoaded: true,
            lokalen: result.cameras
          });
          console.log("de data is: " + this.state.lokalen);
        },
        error => {
          this.setState({
            isLoaded: true,
            error
          });
          console.log("error gebeurd gvdqdsfqeflqskjfmqze");
        }
      );
  }

  getLoggedIn = () => {
    if (true) {
      this.setState({
        loggedin: true
      });
    } else {
      this.setState({
        loggedin: false
      });
    }
    console.log(this.state.loggedin);
  };

  Login = () => {
    console.log("LOGIN BOOLEAN");
    console.log(this.state.loggedin);
    if (this.state.loggedin) {
      auth.logout();
      this.getLoggedIn();
    } else {
      auth.login();
      this.getLoggedIn();
    }
  };

  getStatusCamera = () => {
    fetch("http://brabo2.ddns.net:555/camera/" + this.state.camera + "/enabled")
      .then(res => res.json())
      .then(
        result => {
          this.setState({
            enabled: result.enabled
          });
          console.log("de status van de camera is: " + this.state.enabled);
        },
        error => {
          this.setState({
            error
          });
          console.log("error gebeurd");
        }
      );
  };

  disableCamera2 = () => {
    disableCamera(this.state.camera);
    this.getStatusCamera();
  };

  handleLokaal = e => {
    this.setState({
      camera: e.target.value
    });
    console.log(this.state.camera);
    this.getStatusCamera();
  };

  render() {
    const { lokalen } = this.state;
    return (
      <nav className="navbar navbar-dark fixed-top bg-dark flex-md-nowrap p-0 shadow">
        <a className="navbar-brand col-sm-3 col-md-2 mr-0" href="#">
          Blackboard Snapshot
        </a>
        <select
          className="btn btn-secondary dropdown-toggle dropdowns"
          //value={this.state.klas}
          onChange={this.handleLokaal}
        >
          {lokalen
            .filter(lokaal => lokaal.lokaal)
            .map(lokaal => (
              <option value={lokaal.id} key={lokaal.id}>
                {lokaal.lokaal.naam}
              </option>
            ))}
        </select>
        )
        <ul className="navbar-nav px-3">
          <li className="nav-item text-nowrap">
            <a className="nav-link" href="#">
              <button
                onClick={this.disableCamera2}
                className="btn btn-outline-success my-2 my-sm-0"
                type="submit"
              >
                {this.state.enabled === true && "Disabled"}
                {this.state.enabled === false && "Enabled"}
              </button>
              <button
                className="btn btn-outline-success my-2 my-sm-0"
                id="loginbutton"
                type="submit"
                onClick={this.Login}
              >
                {this.state.loggedin === true && "Log out"}
                {this.state.loggedin === false && "Log In"}
              </button>
            </a>
          </li>
        </ul>
      </nav>
    );
  }
}

export default NavBar;
