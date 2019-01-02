import React, { Component } from "react";
import Photo from "./photo";
import FotoInfo from "./fotoInfo";

class Dashboard extends Component {
  constructor(props) {
    super(props);
    this.state = {
      error: null,
      isLoaded: false,
      items: [],
      klas: "3GDM1",
      prof: "keuze",
      lokaal: "keuze",
      vak: "keuze"
    };
  }

  componentDidMount() {
    fetch("http://brabo2.ddns.net:555/photo")
      .then(res => res.json())
      .then(
        result => {
          this.setState({
            isLoaded: true,
            items: result.fotos
          });
          console.log("de data is: " + this.state.items);
        },
        error => {
          this.setState({
            isLoaded: true,
            error
          });
          console.log("error gebeurd gvdqdsfqeflqskjfmqze");
        }
      );
  }

  render() {
    const { error, isLoaded, items } = this.state;

    if (error) {
      return <div>Error: {error.message}</div>;
    } else if (!isLoaded) {
      return <div>Loading...</div>;
    } else {
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
            {items
              .filter(item => {
                var klasBool = true;
                var profBool = true;
                var vakBool = true;
                var lokaalBool = true;

                if (this.state.klas != "keuze") {
                  klasBool = item.les.klas.naam == this.state.klas;
                }
                if (this.state.prof != "keuze") {
                  profBool = item.les.vak.prof.naam == this.state.prof;
                }
                if (this.state.vak != "keuze") {
                  vakBool = item.les.vak.naam == this.state.vak;
                }
                if (this.state.lokaal != "keuze") {
                  lokaalBool = item.les.lokaal.naam == this.state.lokaal;
                }

                if (klasBool && profBool && vakBool && lokaalBool) {
                  return true;
                } else {
                  return false;
                }
              })
              .map(item => (
                <div className="photo">
                  <Photo
                    key={item.id}
                    photo={item.naam}
                    vak={item.les.vak.naam}
                    klas={item.les.klas.naam}
                    richting={item.les.klas.richting.naam}
                    lokaal={item.les.lokaal.naam}
                    gebouw={item.les.lokaal.gebouw}
                    prof={item.les.vak.prof.naam}
                    begintijd={item.les.starttijd}
                    eindtijd={item.les.eindtijd}
                  />
                </div>
              ))}
          </div>
        </main>
      );
    }
  }
}

export default Dashboard;
