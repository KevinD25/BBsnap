import React, { Component } from "react";

class Photo extends Component {
  state = { foto: ".../images/joren.jpg", naam: "lelijk heufd" };
  render() {
    return (
      <div>
        <img
          class="mr-3"
          src="https://i1.sndcdn.com/avatars-000050933768-gqr14u-t500x500.jpg"
          alt="lelijk heufd"
          height="240px"
          width="240px"
        />
        <p>Joren in het dagelijkse leven</p>
      </div>
    );
  }
}

export default Photo;
