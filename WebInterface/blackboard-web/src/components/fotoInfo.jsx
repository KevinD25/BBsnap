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
    fotonaam: this.props.photo
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
          {this.props.children}

          <div style={footerStyle}>
            <button
              onClick={e => {
                this.onClose(e);
              }}
            >
              close
            </button>
          </div>
        </div>
      </div>
    );
  }
}

export default FotoInfo;
