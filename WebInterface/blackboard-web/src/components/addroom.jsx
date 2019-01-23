import React, { Component } from "react";

async function postRoom(cameraid, lokaalid) {
  console.log("post naar server");
  fetch("http://brabo2.ddns.net:555/couple", {
    method: "POST",
    headers: {
      Accept: "application/json",
      "Content-Type": "application/json"
    },
    body: JSON.stringify({
      cameraId: cameraid,
      lokaalId: lokaalid
    })
  });
}

class Addroom extends Component {
  state = {
    lokalen: [],
    inputvalue: "keuze",
    output: "",
    heeftAlLokaal: "loading..."
  };

  componentDidMount() {
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
    this.getCameraNu();
  }

  render() {
    const { lokalen } = this.state;
    return (
      <React.Fragment>
        <h2>Camera {this.props.camera.id}</h2>
        <p>
          <b>
            Het lokaal dat deze camera al heeft is: {this.state.heeftAlLokaal}
          </b>
        </p>
        <p>Geef het nieuwe lokaal voor deze camera op in de dropdown</p>
        <div className="dropdown">
          <select
            className="btn btn-secondary dropdown-toggle dropdowns"
            //defaultValue={this.state.klas}
            onChange={this.getInputValue}
          >
            <option value="keuze" key="keuze">
              maak een keuze
            </option>
            {lokalen.map(lokaal => (
              <option
                value={lokaal.id}
                key={lokaal.id}
                select={this.props.camera.lokaal ? true : false}
              >
                {lokaal.naam}
              </option>
            ))}
          </select>
        </div>
        <button onClick={this.safe}>bewaar wijzigingen</button>
        <p>{this.state.output}</p>
        <br />
        <br />
        <br />
        <br />
        <br />
        <br />
      </React.Fragment>
    );
  }

  getInputValue = e => {
    this.setState({
      inputvalue: e.target.value
    });
  };

  safe = e => {
    if (this.state.inputvalue === "keuze") {
      this.setState({
        output: "geef een geldige waarde"
      });
    } else {
      postRoom(this.props.camera.id, this.state.inputvalue);
      this.setState({
        output: "changes saved"
      });
    }
  };

  getCameraNu = e => {
    if (this.props.camera.lokaal) {
      this.setState({
        heeftAlLokaal: this.props.camera.lokaal.naam
      });
    } else {
      this.setState({
        heeftAlLokaal: "nog geen lokaal"
      });
    }
  };
}

export default Addroom;
