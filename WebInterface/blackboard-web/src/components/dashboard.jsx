import React, { Component } from "react";
import Photo from "./photo";

class Dashboard extends Component {
  constructor(props) {
    super(props);
    this.state = {
      error: null,
      isLoaded: false,
      items: []
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
        <main role="main" className="col-md-9 ml-sm-auto col-lg-10 px-4">
          <div className="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
            <h1 className="h2">Dashboard</h1>
          </div>
          <div className="photos">
            {items
              .filter(item => {
                var klasBool = true;
                var profBool = true;
                var vakBool = true;
                var lokaalBool = true;

                if (this.props.klas !== "keuze") {
                  klasBool = item.les.klas.naam === this.props.klas;
                }
                if (this.props.prof !== "keuze") {
                  profBool = item.les.vak.prof.naam === this.props.prof;
                }
                if (this.props.les !== "keuze") {
                  vakBool = item.les.vak.naam === this.props.les;
                }
                if (this.props.lokaal !== "keuze") {
                  lokaalBool = item.les.lokaal.naam === this.props.lokaal;
                }

                if (klasBool && profBool && vakBool && lokaalBool) {
                  return true;
                } else {
                  return false;
                }
              })
              .map(item => (
                <div className="photo" key={item.id}>
                  <Photo
                    id={item.id}
                    photo={item.naam}
                    vak={item.les.vak.naam}
                    klas={item.les.klas.naam}
                    richting={item.les.klas.richting.naam}
                    lokaal={item.les.lokaal.naam}
                    gebouw={item.les.lokaal.gebouw}
                    prof={item.les.vak.prof.naam}
                    begintijd={item.les.starttijd}
                    eindtijd={item.les.eindtijd}
                    camera={item.camera.id}
                    key={item.id}
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
