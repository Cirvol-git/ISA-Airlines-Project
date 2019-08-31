import React, {Component} from 'react';
import {connect} from 'react-redux';
import {getLet} from '../../store/actions/LetActions';
import {createReservations} from '../../store/actions/RezervacijaActions';
import {getPrijatelji} from '../../store/actions/PrijateljActions';



class dodeliPrijateljaLetu extends Component {
  state = {
    rezervacije : this.props.rezp,//.filter((m,i) => {return i !== 0}), // izbacivanje prvog je je od korisnika
    dodeljeni: [],
    potencijalni: [],
    pasosi: [...Array(this.props.rezp.length)].map(x => {""}), //Array.apply(null, Array(this.props.rezp.length)).map(()=>{ return ''})
    imena : [...Array(this.props.rezp.length)].map(x => {return {ime : "", prezime : ""}})
  }
  componentDidMount() {
    this.props.getPrijatelji(this.props.korisnik.id)
  }

  componentWillReceiveProps(newProps) {
    const oldProps = this.props
    if(oldProps.prijatelji !== newProps.prijatelji) {
      this.setState({ 
        ...this.state,
        potencijalni : [newProps.korisnik ,...[...newProps.prijatelji].map(x => x.ret)]
      })
    }
  }

  onClickData(pri) {
    let found= [];
    found = this.state.dodeljeni.filter(r => {return r.id === pri.id})
    if(this.state.dodeljeni.length < this.state.rezervacije.length) {
      if(found.length === 0) {
        this.setState({
          ...this.state,
          dodeljeni: [...this.state.dodeljeni, pri],
          potencijalni: this.state.potencijalni.filter(r => {return r.id !== pri.id}),
        })
      } else {
        let ide = 0;
        let noviDod = this.state.dodeljeni.filter((r,i) => {if(r.id !== pri.id) {return true} else { ide=i; return false}});
        let noviImena = this.state.imena;
        noviImena[ide] = {ime : "", prezime : ""}
        this.setState({
          ...this.state,
          dodeljeni: noviDod,
          potencijalni: [...this.state.potencijalni, pri],
          imena : noviImena
        })
      }
    }else {
      alert("Cant add more friends, not enough seats")
    }
  }
  handleImena = (e) => {
    console.log(e.target);
    let imena = this.state.imena;
    if(e.target.id < this.state.rezervacije.length) {
      imena[e.target.id].ime = e.target.value;
    }else {
      imena[e.target.id-this.state.rezervacije.length].prezime = e.target.value;
    }
    this.setState({
      ...this.state,
      imena
    })
  }
  handlePassport = (e) => {
    let pas = this.state.pasosi
    pas[e.target.id] = e.target.value
    this.setState({
      ...this.state,
      pasosi : pas
    })
  } 
  handleChange = (e) => {
    this.setState({
        ...this.state,
        [e.target.id] : e.target.value
    })
}
  
  handleClickMake = (e) => {
    if(this.state.pasosi.filter(x=>x === "").length) {
      alert("You must enter all passports");
      return;
    }
    if(this.state.imena.slice(this.state.dodeljeni.length).some(x => { return (x.ime === "" || x.prezime === "")})) {
      alert("You must enter all names and surnames");
      return;
    }
    this.props.createReservations(this.state.rezervacije, this.state.dodeljeni, this.state.imena, this.state.pasosi,this.props.korisnik.id);
    this.props.history.push('/avios')
  }

  render() {
    return (
      <div className="row">
        <div className="col s5">
            <h5>Friends (click on one to add him to a reservation)</h5>
            <ul>
                {this.state.potencijalni && this.state.potencijalni.map(pri => {
                    return (
                        <li onClick={()=>this.onClickData(pri)}>
                            {pri.ime} {pri.prezime}
                        </li>
                    )})
                
                }
            </ul>
        </div>
        <div className="col s3">
        <h5>Marked Seats </h5>
            <ul>
                {this.state.rezervacije && this.state.rezervacije.map(pri => {
                    return (
                        <li>
                            row: {pri.red+1}, column: {pri.kolona+1}, class: { pri.tip === 1 ? "First" : (pri.tip === 2 ? "Business" : "Economy") }
                        </li>
                )})}
            </ul>
        </div>
        <div className="col s4">
            <h5>Add to (click on a friend to remove him from the spot)</h5>
            <ul>
                { this.state.dodeljeni && this.state.dodeljeni.map(pri => {
                    return (
                        <li onClick={()=>this.onClickData(pri)}>
                            {pri.ime} {pri.prezime}
                        </li>
                    )
                })}
            </ul>
        </div>
        <div className="col s12">
          <table>
            <thead>
              <tr>
                <th>Seat class</th>
                <th>Row</th>
                <th>Column</th>
                <th>Name</th>
                <th>Surname</th>
                <th>Passport number</th>
              </tr>
              
            </thead>
            <tbody>
              {this.state.rezervacije.map((pri,i) => {
                return (
                  <tr>
                    <td>{ pri.tip === 1 ? "First" : (pri.tip === 2 ? "Business" : "Economy") }</td>
                    <td>{pri.red+1}</td>
                    <td>{pri.kolona+1}</td>
                    <td> 
                      {this.state.dodeljeni.length>i ? (
                        this.state.dodeljeni[i].ime
                      ) : (
                        <input type="text" id={i} onChange={this.handleImena} />
                      )}
                    </td>
                    <td> 
                      {this.state.dodeljeni.length>i ? (
                        this.state.dodeljeni[i].prezime
                      ) : (
                        <input type="text" id={i+this.state.rezervacije.length} onChange={this.handleImena} />
                      )}
                    </td>
                    <td><input type="text" id={i}  onChange={this.handlePassport} /></td>
                  </tr>
                )
              })}
              
            </tbody>
          </table>
        </div>
        <div className="col s12">
          <button onClick={this.handleClickMake} className="btn grey size-4 centar">Complete Creation</button>
        </div>
        
      </div>
    )
  }
}

const mapStateToProps = (state) => {
  return {
      rezp: state.rezp.seats,
      korisnik: state.log.log,
      prijatelji: state.prijatelji.prijatelji,
      letp: state.letp.letp
  }
}

const mapDispatchToProps = (dispatch) => {
  return {
    getLet : (id) => dispatch(getLet(id)),
    createReservations : (seats,dodeljeni,imena,pasos,id) => dispatch(createReservations(seats,dodeljeni,imena,pasos,id)),
    getPrijatelji: (id) => dispatch(getPrijatelji(id))
  }
}

export default connect(mapStateToProps,mapDispatchToProps)(dodeliPrijateljaLetu);