import React, { Component } from "react";
import Popup from "reactjs-popup";

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
  top: 0,
  margin: "0 auto",
  padding: 30,
  position: "relative"
};

class FotoInfo extends Component {
  state = {
    fotonaam: this.props.fotoNaam,
    fotolink:
      "http://brabo2.ddns.net:555/photo/getphoto/" +
      this.props.camera +
      "/" +
      this.props.fotoNaam
  };
  onClose = e => {
    this.props.onClose && this.props.onClose(e);
  };

  onDelete() {
    const requestOptions = {
      method: "DELETE"
    };
    console.log("begin");
    fetch("http://brabo2.ddns.net:555/photo/" + this.props.id, requestOptions)
      .then(response => {
        console.log(this.props.key);
        return response.json();
      })
      .then(result => {
        // do what you want with the response here
      });
  }

  render() {
    if (!this.props.show) {
      return null;
    }

    return (
      <div style={backdropStyle}>
        <div style={modalStyle}>
          <img
            className="mr-3"
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
            close
          </button>
          <Popup trigger={<button> delete</button>} position="right center">
            <div>Are you sure you want to delete this picture?</div>
            <div>
              <button
                onClick={e => {
                  this.onDelete();
                  this.props.loadFoto();
                }}
              >
                yes
              </button>
            </div>
          </Popup>
        </div>
      </div>
    );
  }
}

export default FotoInfo;
