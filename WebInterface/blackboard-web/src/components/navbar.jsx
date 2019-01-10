import React, { Component } from "react";

async function disableCamera() {
  console.log("post naar server");
  fetch("http://brabo2.ddns.net:555/disablephoto/10", {
    method: "POST",
    headers: {
      Accept: "application/json",
      "Content-Type": "application/json"
    }
  });
}

class NavBar extends Component {
  state = {
    enabled: true
  };

  componentDidMount() {
    fetch("http://brabo2.ddns.net:555/camera/10/enabled")
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
  }

  render() {
    return (
      <nav className="navbar navbar-dark fixed-top bg-dark flex-md-nowrap p-0 shadow">
        <a className="navbar-brand col-sm-3 col-md-2 mr-0" href="#">
          Blackboard Snapshot
        </a>

        <ul className="navbar-nav px-3">
          <li className="nav-item text-nowrap">
            <a className="nav-link" href="#">
              <button
                onClick={disableCamera}
                class="btn btn-outline-success my-2 my-sm-0"
                type="submit"
              >
                {this.state.enabled === true && "Disable"}
                {this.state.enabled === false && "Enable"}
              </button>
              <button
                class="btn btn-outline-success my-2 my-sm-0"
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
