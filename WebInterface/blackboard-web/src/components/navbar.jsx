import React, { Component } from "react";

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

class NavBar extends Component {
  state = {
    enabled: true,
    lokalen: [],
    camera: "10"
  };

  componentDidMount() {
    this.getStatusCamera();
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
          console.log("error gebeurd gvdqdsfqeflqskjfmqze");
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
    console.log("gvd werk is kut react ik ga u ophangen met u kanker taal");
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
                type="submit"
              >
                Sign out
              </button>
            </a>
          </li>
        </ul>
      </nav>
    );
  }
}

export default NavBar;
