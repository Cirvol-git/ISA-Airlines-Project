import React, {Component} from 'react';
import {connect} from 'react-redux';
import DrawGrid from './DrawGrid';
import {createLet} from '../../store/actions/LetActions';


class DrawSedista extends Component {
  state = {
    seat : {id: '',
            red: '',
          kolona: '',
          zauzeto: '',
          tip : '',
          odobreno: ''},
    seats: [...this.props.letp.rows],
    letp: this.props.letp
  }
  
  handleChange = (e) => {
    let ret = this.state.seat;
    ret[e.target.id] = e.target.value;
    console.log(ret);
    this.setState({
        ...this.state,
        seat: ret
    })
  }
  handleSubmit = (e) => {
    e.preventDefault();
    let i =this.state.seat.red;
    let j =this.state.seat.kolona;
    let ret = this.state.seats;
    ret[i][j] = this.state.seat; 
    this.setState({
      ...this.state,
      seats: [...ret]
    })
  }
  
  onClickData = (seat) => {
    let ret ={
      id: seat.id,
      red: seat.red,
      kolona: seat.kolona,
      zauzeto: seat.zauzeto,
      tip : seat.tip,
      odobreno: seat.odobreno
    }
    this.setState({
      ...this.state,
      seat: ret
    })
  }

  handleClickMake = () => {
    let ret = this.state.letp;
    ret.rows = this.state.seats;
    console.log(ret);
    this.props.createLet(ret);
    this.props.history.push('/avios');
  }
  
  render() {
    console.log('letpppp',this.props.letp);
    return (
      <div>
        <h1>Seat Reservation System</h1>
        <div className="col s12 m6 container centar">
          <DrawGrid
            rows = {this.state.seats}
            onClickData = { this.onClickData.bind(this) }
          />
        </div>
        <div className="col s12 m5 offset-m1 container">
          <form className="white" onSubmit={this.handleSubmit}>
              <h5 className="grey-text text-darken-3">Update Seat</h5>
              <h6>Row: {this.state.seat.red}</h6>
              <h6 >Column: {this.state.seat.kolona}</h6>
              <div className="input-field">
                  <label htmlFor="tip">City</label>
                  <select defaultValue={this.state.seat.tip} className="input-field browser-default" id='tip' onChange={this.handleChange}>
                    <option value="1">First class</option>
                    <option value="2">Business class</option>
                    <option value="3">Economy class</option>
                    <option value="4">Seat for fast Reservation</option>
                  </select>
              </div>
              <div className="input-field">
                  <select defaultValue={this.state.seat.odobreno} className="input-field browser-default" id='odobreno' onChange={this.handleChange}>
                    <option value="true">Odobreno</option>
                    <option value="false">Nije odobreno</option>
                  </select>
              </div>
              <div className="input-field">
                  <button className="btn grey">Update</button>
              </div>
          </form>
        </div>
        <div>
          <button onClick={this.handleClickMake} className="btn grey size-4 centar">Complete Creation</button>
        </div>
      </div>
    )
  }
}

const mapStateToProps = (state) => {
  return {
      letp: state.letp.letp
  }
}

const mapDispatchToProps = (dispatch) => {
  return {
    createLet : (letp) => dispatch(createLet(letp))
  }
}

export default connect(mapStateToProps,mapDispatchToProps)(DrawSedista);