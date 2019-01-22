import React, { Component } from "react";
import Addroom from "./addroom";

class Addpage extends Component {
  state = {
    cameras: []
  };

  componentDidMount() {
    fetch("http://brabo2.ddns.net:555/camera")
      .then(res => res.json())
      .then(
        result => {
          this.setState({
            isLoaded: true,
            cameras: result.cameras
          });
          console.log("de data is: " + this.state.cameras);
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
    const { cameras } = this.state;
    return (
      <div className="container">
        <div className="row" />
        <div className="row">
          <div className="col-sm">
            <h1>Camera's aanpassen en toevoegen</h1>
            {cameras.map(camera => (
              <Addroom key={camera.id} camera={camera} />
            ))}
          </div>
          <div className="row" />
        </div>
      </div>
    );
  }
}

export default Addpage;
