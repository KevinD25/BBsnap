import React, { Component } from "react";
import FotoInfo from "./fotoInfo";

class Photo extends Component {
  state = {
    fotolink:
      "http://brabo2.ddns.net:555/photo/getphoto/10/" + this.props.photo,
    fotonaam: this.props.photo,
    show: false
  };

  showModal = () => {
    this.setState({
      ...this.state,
      show: !this.state.show
    });
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
        <input type="button" onClick={this.showModal} value="Show Info" />
        <FotoInfo show={this.state.show} onClose={this.showModal}>
          <p>klas: 3EA1</p> <p>les: InfoSec</p> <p>prof: TDAMS</p>
        </FotoInfo>
      </div>
    );
  }
}

export default Photo;
