import React, {Component} from 'react';
import {connect} from 'react-redux';
import { saveLetState } from '../../store/actions/LetActions'
import { getDestinations } from '../../store/actions/DestActions'

class createLet extends Component {
    state = {
      od: '',
      do: '',
      presedanja: '',
      tipleta: "0",
      polece: '',
      slece: '',
      vreme: '',
      duzina: '',
      ceconomy: '',
      cbusiness: '',
      cfirst: '',
      redova: '',
      idavio: this.props.korisnik.adminOd,
      rows: [],
      prtljag : ''
    }

    componentDidMount() {
      this.props.getDestinations()
    }

    handleChange = (e) => {
        this.setState({
          [e.target.id] : e.target.value
        })
        
        console.log(this.state);
    }
    handleSubmit = (e) => {
        e.preventDefault();
        this.props.saveLetState(this.state);
        this.props.history.push('/createLet2');
    }
    render() {
      var destinacije = this.props.destinacije;
      return(
        <div className="container">
          <form className="white" onSubmit={this.handleSubmit}>
            <div>
              <select defaultValue="" className="input-field browser-default" id='od' onChange={this.handleChange}>
                <option value="" disabled >Taking off from</option>
                {destinacije && destinacije.map(dest => {
                  return(
                    <option value={dest.id}>{dest.grad}, {dest.ime}</option>
                  )
                })}
              </select>
            </div>

            <div >
              <select defaultValue="" className="input-field browser-default" id='do' onChange={this.handleChange}>
                <option value="" disabled>Landing to</option>
                {destinacije && destinacije.map(dest => {
                  return(
                    <option value={dest.id}>{dest.grad}, {dest.ime}</option>
                  )
                })}
              </select>
            </div>

            <div className="input-field">
              <input type="number" id='presedanja' onChange={this.handleChange} />
              <label htmlFor="presedanja">Transits</label>
            </div>

            <div >
              <select defaultValue="0" className="input-field browser-default" id='tipleta' onChange={this.handleChange}>
                <option value="0" disabled>Type of flight</option>
                <option value="1">Round-trip</option>
                <option value="2">One-way</option>
                <option value="3">Multi-city</option>
              </select>
            </div>

            <div className="input-field">
              Takes off:
              <input type="datetime-local" id='polece' onChange={this.handleChange} />
            </div>

            <div className="input-field">
              Lands:
              <input type="datetime-local" id='slece' onChange={this.handleChange} />
            </div>
              
            <div className="input-field">
              <input type="number" id='vreme' onChange={this.handleChange} />
              <label htmlFor="vreme">Duration of travel(in minutes)</label>
            </div>
            
            <div className="input-field">
              <input type="number" id='duzina' onChange={this.handleChange} />
              <label htmlFor="duzina">Distance traveled (in Kilometars)</label>
            </div>

            <div className="input-field">
              <input type="number" id='ceconomy' onChange={this.handleChange} />
              <label htmlFor="ceconomy">Price of economy class card</label>
            </div>
            <div className="input-field">
              <input type="number" id='cbusiness' onChange={this.handleChange} />
              <label htmlFor="cbusiness">Price of business class ticket</label>
            </div>
            <div className="input-field">
              <input type="number" id='cfirst' onChange={this.handleChange} />
              <label htmlFor="cfirst">Price of first class ticket</label>
            </div>

            <div className="input-field">
              <input type="number" id='redova' onChange={this.handleChange} />
              <label htmlFor="redova">Seat rows in a plain</label>
            </div>

            <div className="input-field">
              <input type="number" id='prtljag' onChange={this.handleChange} />
              <label htmlFor="prtljag">Allowed baggage</label>
            </div>

            <div className="input-field">
                <button className="btn grey">Create</button>
            </div>
          </form>
        </div>
      )
    }
}

const mapStateToProps = (state) => {
  console.log(state);
  return {
      destinacije: state.dests.dests,
      korisnik: state.log.log
  }
}

const mapDispatchToProps = (dispatch) => {
  return {
      saveLetState : (letp) => dispatch(saveLetState(letp)),
      getDestinations : () => dispatch(getDestinations())
  }
}

export default connect(mapStateToProps,mapDispatchToProps)(createLet);