import React, { Component } from "react";

async function takePhoto() {
  console.log("post naar server");
  fetch("http://brabo2.ddns.net:555/takephoto/", {
    method: "POST",
    headers: {
      Accept: "application/json",
      "Content-Type": "application/json"
    },
    body: JSON.stringify({
      studnr: "0945786"
    })
  });
}

class SideBar extends Component {
  state = {
    klas: "keuze",
    prof: "keuze",
    les: "keuze",
    lokaal: "keuze"
  };

  handleKlas = e => {
    this.setState({ klas: e.target.value });
    console.log(this.state.klas);
  };

  handleProf = e => {
    this.setState({ prof: e.target.value });
    console.log(this.state.prof);
  };

  handleLes = e => {
    this.setState({ les: e.target.value });
    console.log(this.state.les);
  };

  handleLokaal = e => {
    this.setState({ lokaal: e.target.value });
    console.log(this.state.lokaal);
  };

  render() {
    return (
      <nav className="col-md-2 d-none d-md-block bg-light sidebar">
        <div className="sidebar-sticky">
          <ul className="nav flex-column">
            <li className="nav-item">
              <a className="nav-link" href="#">
                Klas
              </a>
            </li>
            <li>
              <div className="dropdown">
                <select
                  className="btn btn-secondary dropdown-toggle dropdowns"
                  value={this.state.klas}
                  onChange={this.handleKlas}
                >
                  <option value="keuze">maak een keuze</option>
                  <option value="3EA1">3EA1</option>
                  <option value="3GDM1">3GDM1</option>
                </select>
              </div>
            </li>
            <li className="nav-item">
              <a className="nav-link" href="#">
                Datum
              </a>
            </li>
            <li>
              <div className="dropdown">
                <select className="btn btn-secondary dropdown-toggle dropdowns">
                  <option value="volvo">Volvo</option>
                  <option value="saab">Saab</option>
                  <option value="mercedes">Mercedes</option>
                  <option value="audi">Audi</option>
                </select>
              </div>
            </li>
            <li className="nav-item">
              <a className="nav-link" href="#">
                Prof
              </a>
            </li>
            <li>
              <div className="dropdown">
                <select
                  className="btn btn-secondary dropdown-toggle dropdowns"
                  defaultValue={this.state.klas}
                  onChange={this.handleProf}
                >
                  <option value="keuze">maak een keuze</option>
                  <option value="Tim Dams">Tim Dams</option>
                  <option value="Rick and Morty">Rick & Morty</option>
                </select>
              </div>
            </li>
            <li className="nav-item">
              <a className="nav-link" href="#">
                Les
              </a>
            </li>
            <li>
              <div className="dropdown">
                <select
                  className="btn btn-secondary dropdown-toggle dropdowns"
                  defaultValue={this.state.klas}
                  onChange={this.handleLes}
                >
                  <option value="keuze">maak een keuze</option>
                  <option value="Information Security">
                    Information Security
                  </option>
                  <option value="Tekenshit">Tekenshit</option>
                </select>
              </div>
            </li>
            <li className="nav-item">
              <a className="nav-link" href="#">
                Lokaal
              </a>
            </li>
            <li className="dropdownList">
              <div className="dropdown">
                <select
                  className="btn btn-secondary dropdown-toggle dropdowns"
                  defaultValue={this.state.klas}
                  onChange={this.handleLokaal}
                >
                  <option value="keuze">maak een keuze</option>
                  <option value="00.02">00.02</option>
                  <option value="01.02">01.02</option>
                </select>
              </div>
            </li>
          </ul>
          <div className="button" onClick={takePhoto}>
            <button className="snapbutton btn-secondary btn">SNAP</button>
          </div>
          <p>you selected {this.state.klas}</p>
        </div>
      </nav>
    );
  }
}
export default SideBar;
