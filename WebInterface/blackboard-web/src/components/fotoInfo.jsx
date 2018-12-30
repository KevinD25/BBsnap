import React, { Component } from "react";

const backdropStyle = {
  position: "fixed",
  top: 0,
  bottom: 0,
  left: 0,
  right: 0,
  backgroundClose: "rgba(0,0,0,0.3)",
  padding: 50
};

const modalStyle = {
  backgroundColor: "#D7D7D8",
  borderRadius: 5,
  maxWidth: 500,
  minHeight: 300,
  top: 40,
  margin: "0 auto",
  padding: 30,
  position: "relative"
};

const footerStyle = {
  position: "absolute",
  bottom: 20
};

class FotoInfo extends Component {
  state = {
    fotonaam: this.props.fotoNaam,
    fotolink:
      "http://brabo2.ddns.net:555/photo/getphoto/10/" + this.props.fotoNaam
  };
  onClose = e => {
    this.props.onClose && this.props.onClose(e);
  };

  render() {
    if (!this.props.show) {
      return null;
    }

    return (
      <div style={backdropStyle}>
        <div style={modalStyle}>
          <img
            class="mr-3"
            src={this.state.fotolink}
            alt="lelijk heufd"
            height="240px"
          />

          <p>naam: {this.props.fotoNaam}</p>
          <p>klas: {this.props.klas}</p>
          <p>richting: {this.props.richting}</p>
          <p>vak: {this.props.vak}</p>
          <p>begintijd: {this.props.begintijd}</p>
          <p>eindtijd: {this.props.eindtijd}</p>
          <p>prof: {this.props.prof}</p>
          <p>lokaal: {this.props.lokaal}</p>
          <p>gebouw: {this.props.gebouw}</p>

          <button
            onClick={e => {
              this.onClose(e);
            }}
          >
            download
          </button>
          <button
            onClick={e => {
              this.onClose(e);
            }}
          >
            close
          </button>
        </div>
      </div>
    );
  }
}

export default FotoInfo;
