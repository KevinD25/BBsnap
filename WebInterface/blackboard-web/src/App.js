import React, { Component } from "react";
import logo from "./logo.svg";
import "./App.css";

class App extends Component {
  render() {
    return (
      <body>
        <nav class="navbar navbar-dark fixed-top bg-dark flex-md-nowrap p-0 shadow">
          <a class="navbar-brand col-sm-3 col-md-2 mr-0" href="#">
            Blackboard Snapshot
          </a>

          <ul class="navbar-nav px-3">
            <li class="nav-item text-nowrap">
              <a class="nav-link" href="#">
                Sign out
              </a>
            </li>
          </ul>
        </nav>

        <div class="container-fluid">
          <div class="row">
            <nav class="col-md-2 d-none d-md-block bg-light sidebar">
              <div class="sidebar-sticky">
                <ul class="nav flex-column">
                  <li class="nav-item">
                    <a class="nav-link active" href="#">
                      <span data-feather="home" />
                      Klas <span class="sr-only">(current)</span>
                    </a>
                  </li>
                  <li class="nav-item">
                    <a class="nav-link" href="#">
                      <span data-feather="file" />
                      Datum
                    </a>
                  </li>
                  <li class="nav-item">
                    <a class="nav-link" href="#">
                      <span data-feather="shopping-cart" />
                      Prof
                    </a>
                  </li>
                  <li class="nav-item">
                    <a class="nav-link" href="#">
                      <span data-feather="users" />
                      Les
                    </a>
                  </li>
                  <li class="nav-item">
                    <a class="nav-link" href="#">
                      <span data-feather="bar-chart-2" />
                      Lokaal
                    </a>
                  </li>
                </ul>
              </div>
            </nav>

            <main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-4">
              <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
                <h1 class="h2">Dashboard</h1>
                <div class="btn-toolbar mb-2 mb-md-0">
                  <div class="btn-group mr-2">
                    <button class="btn btn-sm btn-outline-secondary">
                      Share
                    </button>
                    <button class="btn btn-sm btn-outline-secondary">
                      Export
                    </button>
                  </div>
                  <button class="btn btn-sm btn-outline-secondary dropdown-toggle">
                    <span data-feather="calendar" />
                    This week
                  </button>
                </div>
              </div>
              <canvas
                class="my-4 w-100"
                id="myChart"
                width="900"
                height="380"
              />
            </main>
          </div>
        </div>
      </body>
    );
  }
}

export default App;
