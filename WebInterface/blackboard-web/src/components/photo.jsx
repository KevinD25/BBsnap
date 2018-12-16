import React, { Component } from "react";

class Photo extends Component {
  state = {
    fotolink:
      "http://brabo2.ddns.net:555/photo/getphoto/10/" + this.props.photo,
    fotonaam: this.props.photo
  };

  render() {
    return (
      <div>
        <img
          class="mr-3"
          src={this.state.fotolink}
          alt="lelijk heufd"
          height="240px"
        />
        <p>{this.state.fotonaam}</p>
      </div>
    );
  }
}

export default Photo;
