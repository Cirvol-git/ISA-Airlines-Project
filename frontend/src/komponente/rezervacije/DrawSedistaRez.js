import React, {Component} from 'react';
import {connect} from 'react-redux';
import {getLet} from '../../store/actions/LetActions'
import {saveMarkedSeats, createReservations} from '../../store/actions/RezervacijaActions'
import DrawGridRez from './DrawGridRez';


class DrawSedistaRez extends Component {
  state = {
    rezervisano: [],
    pasos : '',
    checked : false
  }
  componentDidMount(){
    this.props.getLet(this.props.match.params.id);
  }

  handleChange = (e) => {
    this.setState({
        [e.target.id] : e.target.value
    })
  }

  onClickData(seat) {
    let found= [];
    found = this.state.rezervisano.filter(r => {
        return r.id === seat.id;
    })
    if(found.length === 0 && this.state.rezervisano.length < 5) {
      if(this.state.rezervisano.length+1 === 1) {
        this.setState({
          ...this.state,
          rezervisano: [...this.state.rezervisano, seat],
          checked : true
        })
      }else {
        this.setState({
          ...this.state,
          rezervisano: [...this.state.rezervisano, seat],
          checked : false
        })
      }
    } else {
      if(this.state.rezervisano.length-1 === 1) {
        this.setState({
          ...this.state,
          rezervisano: this.state.rezervisano.filter(r => {
              return r.id !== seat.id
          }),
          checked : true
        })
      }else {
        this.setState({
          ...this.state,
          rezervisano: this.state.rezervisano.filter(r => {
              return r.id !== seat.id
          }),
          checked : false
        })
      }
    }
  }  
  
  handleClickMake = () => {
    if(this.state.pasos === '' && this.state.length === 1) {
      alert("Enter passport number");
      return;
    }
    if(this.state.rezervisano.length === 0) {
      alert("There is no seat selected for reservation");
    }else if(this.state.rezervisano.length === 1){
      this.props.createReservations(this.state.rezervisano,[this.props.korisnik], [{ime: this.props.korisnik.ime, prezime: this.props.korisnik.prezime}], [this.state.pasos], this.props.korisnik.id)
    }else {
      this.props.saveMarkedSeats(this.state.rezervisano);
      this.props.history.push('/dodeliPrijatelja');
    }
  }

  render() {
    return (
      <div className="row">
        <div className="col s6">
            <h5>Price of Economy class seat: ${this.props.letp.cEconomy}</h5>
            <h5>Price of Business class seat: ${this.props.letp.cBusiness}</h5>
            <h5>Price of First class seat: ${this.props.letp.cFirst}</h5>
        </div>
        <div className="col s6">
            <h5>Pending reservations</h5>
            <ul>
                { this.state.rezervisano && this.state.rezervisano.map(pri => {
                    return(
                        <li>row: {pri.red+1}, column: {pri.kolona+1}, class: { pri.tip === 1 ? "First" : (pri.tip === 2 ? "Business" : "Economy") } </li>        
                    )
                })}
            </ul>
        </div>
        <div className="col s12">
          <h1>Seat Reservation System</h1>
            <DrawGridRez
              rows = {this.props.letp.rows}
              rezervisano = {this.state.rezervisano}
              onClickData = { this.onClickData.bind(this) }
            />
        </div>

        {this.state.checked ? (
          <div className="input-field col s12">
              <input type="number" id='pasos' value= {this.state.pasos} onChange={this.handleChange} />
              <label htmlFor="pasos">Input the passport number</label>
          </div>
        ) : (null)}  
         
        <div className="col s12">
          <button onClick={this.handleClickMake} className="btn grey size-4 centar">Complete Creation</button>
        </div>
      </div>
    )
  }
}

const mapStateToProps = (state) => {
  return {
      letp: state.letp.letp,
      korisnik: state.log.log
  }
}

const mapDispatchToProps = (dispatch) => {
  return {
    getLet : (id) => dispatch(getLet(id)),
    createReservations : (seats,dodeljeni,imena,pasos,id) => dispatch(createReservations(seats,dodeljeni,imena,pasos,id)),
    saveMarkedSeats: (seats) => dispatch(saveMarkedSeats(seats))
  }
}

export default connect(mapStateToProps,mapDispatchToProps)(DrawSedistaRez);