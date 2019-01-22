import React, { Component } from "react";
import Addroom from "./addroom";

class Addpage extends Component {
  state = {};

  render() {
    return (
      <div class="container">
        <div class="row" />
        <div class="row">
          <div class="col-sm">
            <Addroom ip="173.168.0.3" cameraid="1" />
            <Addroom ip="173.194.1.3" cameraid="2" />
          </div>
          <div class="row" />
        </div>
      </div>
    );
  }
}

export default Addpage;
