import React, { Component } from "react";
import Photo from "./photo";

class Dashboard extends Component {
  render() {
    state = {
      fotos: [
        "https://i1.sndcdn.com/avatars-000050933768-gqr14u-t500x500.jpg",
        "https://i1.sndcdn.com/avatars-000050933768-gqr14u-t500x500.jpg",
        "https://i1.sndcdn.com/avatars-000050933768-gqr14u-t500x500.jpg"
      ]
    };
    return (
      <main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-4">
        <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
          <h1 class="h2">Dashboard</h1>
          <div class="btn-toolbar mb-2 mb-md-0">
            <div class="btn-group mr-2">
              <button class="btn btn-sm btn-outline-secondary">Share</button>
              <button class="btn btn-sm btn-outline-secondary">Export</button>
            </div>
            <button class="btn btn-sm btn-outline-secondary dropdown-toggle">
              <span data-feather="calendar" />
              This week
            </button>
          </div>
        </div>
        <div className="photos">
          <div className="photo">
            <Photo />
          </div>
          <div className="photo">
            <Photo />
          </div>
          <div className="photo">
            <Photo />
          </div>
          <div className="photo">
            <Photo />
          </div>
          <div className="photo">
            <Photo />
          </div>
          <div className="photo">
            <Photo />
          </div>
          <div className="photo">
            <Photo />
          </div>
          <div className="photo">
            <Photo />
          </div>
          {this.state.fotos.map(fotos => (
            <div>
              <img
                class="mr-3"
                src={fotos}
                alt="lelijk heufd"
                height="240px"
                width="240px"
              />
            </div>
          ))}
        </div>
        <canvas class="my-4 w-100" id="myChart" width="900" height="380" />
      </main>
    );
  }
}

export default Dashboard;
