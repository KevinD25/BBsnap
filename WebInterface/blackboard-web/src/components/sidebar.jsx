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
                Les
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
                Lokaal
              </a>
            </li>
            <li className="dropdownList">
              <div className="dropdown">
                <select className="btn btn-secondary dropdown-toggle dropdowns">
                  <option value="volvo">Volvo</option>
                  <option value="saab">Saab</option>
                  <option value="mercedes">Tesla Model S</option>
                  <option value="audi">Audi</option>
                </select>
              </div>
            </li>
          </ul>
          <div className="button" onClick={takePhoto}>
            <button className="snapbutton btn-secondary btn">SNAP</button>
          </div>
        </div>
      </nav>
    );
  }
}
export default SideBar;
