import React, { Component } from "react";

class Addroom extends Component {
  state = {
    lokalen: [],
    inputvalue: "keuze",
    output: ""
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
  }

  render() {
    const { lokalen } = this.state;
    return (
      <React.Fragment>
        <h1>{this.props.ip}</h1>
        <p>Geef het juiste lokaal voor deze camera op in de dropdown</p>
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
              <option value={lokaal.id} key={lokaal.id}>
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
    if (this.state.inputvalue == "keuze") {
      this.setState({
        output: "geef een geldige waarde"
      });
    } else {
      this.setState({
        output: "changes saved"
      });
    }
  };
}

export default Addroom;
