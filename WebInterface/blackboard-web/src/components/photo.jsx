import React, { Component } from "react";
import FotoInfo from "./fotoInfo";

class Photo extends Component {
  state = {
    fotolink:
      "http://brabo2.ddns.net:555/photo/getphoto/" +
      this.props.camera +
      "/" +
      this.props.photo,
    fotonaam: this.props.photo,
    show: false,
    id: this.props.id,
    vak: this.props.vak,
    klas: this.props.klas,
    richting: this.props.richting,
    lokaal: this.props.lokaal,
    gebouw: this.props.gebouw,
    prof: this.props.prof,
    begintijd: this.props.begintijd,
    eindtijd: this.props.eindtijd,
    camera: this.props.camera
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
        {}
        <FotoInfo
          id={this.state.id}
          show={this.state.show}
          onClose={this.showModal}
          fotoNaam={this.state.fotonaam}
          vak={this.state.vak}
          klas={this.state.klas}
          richting={this.state.richting}
          lokaal={this.state.lokaal}
          gebouw={this.state.gebouw}
          prof={this.state.prof}
          begintijd={this.state.begintijd}
          eindtijd={this.state.eindtijd}
          camera={this.state.camera}
        />
      </div>
    );
  }
}

export default Photo;
