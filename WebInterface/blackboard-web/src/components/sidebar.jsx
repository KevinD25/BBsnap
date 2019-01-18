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
  constructor(props) {
    super(props);
    this.state = {
      error: null,
      isLoaded: false,
      klassen: [],
      proffen: [],
      vakken: [],
      lokalen: []
    };
  }

  handleKlas = e => {
    this.props.onSelectKlas(e.target.value);
  };

  handleProf = e => {
    this.props.onSelectProf(e.target.value);
  };

  handleLes = e => {
    this.props.onSelectLes(e.target.value);
  };

  handleLokaal = e => {
    this.props.onSelectLokaal(e.target.value);
  };

  componentDidMount() {
    fetch("http://brabo2.ddns.net:555/klas")
      .then(res => res.json())
      .then(
        result => {
          this.setState({
            isLoaded: true,
            klassen: result.klassen
          });
          console.log("de data is: " + this.state.klassen);
        },
        error => {
          this.setState({
            isLoaded: true,
            error
          });
          console.log("error gebeurd gvdqdsfqeflqskjfmqze");
        }
      );
    fetch("http://brabo2.ddns.net:555/prof")
      .then(res => res.json())
      .then(
        result => {
          this.setState({
            isLoaded: true,
            proffen: result.proffen
          });
          console.log("de data is: " + this.state.proffen);
        },
        error => {
          this.setState({
            isLoaded: true,
            error
          });
          console.log("error gebeurd gvdqdsfqeflqskjfmqze");
        }
      );
    fetch("http://brabo2.ddns.net:555/vak")
      .then(res => res.json())
      .then(
        result => {
          this.setState({
            isLoaded: true,
            vakken: result.vakken
          });
          console.log("de data is: " + this.state.vakken);
        },
        error => {
          this.setState({
            isLoaded: true,
            error
          });
          console.log("error gebeurd gvdqdsfqeflqskjfmqze");
        }
      );
    fetch("http://brabo2.ddns.net:555/lokaal")
      .then(res => res.json())
      .then(
        result => {
          this.setState({
            isLoaded: true,
            lokalen: result.lokalen
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

  render() {
    const { klassen, proffen, vakken, lokalen } = this.state;

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
                  //value={this.state.klas}
                  onChange={this.handleKlas}
                >
                  <option value="keuze" key="keuze">
                    maak een keuze
                  </option>
                  {klassen.map(klas => (
                    <option value={klas.naam} key={klas.id}>
                      {klas.naam}
                    </option>
                  ))}
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
                  <option value="volvo" key="volvo">
                    Volvo
                  </option>
                  <option value="saab" key="saab">
                    Saab
                  </option>
                  <option value="mercedes" key="mercedes">
                    Mercedes
                  </option>
                  <option value="audi" key="audi">
                    Audi
                  </option>
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
                  //defaultValue={this.state.klas}
                  onChange={this.handleProf}
                >
                  <option value="keuze" key="keuze">
                    maak een keuze
                  </option>
                  {proffen.map(prof => (
                    <option value={prof.naam} key={prof.id}>
                      {prof.naam}
                    </option>
                  ))}
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
                  //defaultValue={this.state.klas}
                  onChange={this.handleLes}
                >
                  <option value="keuze" key="keuze">
                    maak een keuze
                  </option>
                  {vakken.map(vak => (
                    <option value={vak.naam} key={vak.id}>
                      {vak.naam}
                    </option>
                  ))}
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
                  //defaultValue={this.state.klas}
                  onChange={this.handleLokaal}
                >
                  <option value="keuze" key="keuze">
                    maak een keuze
                  </option>
                  {lokalen.map(lokaal => (
                    <option value={lokaal.naam} key={lokaal.id}>
                      {lokaal.naam}
                    </option>
                  ))}
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
