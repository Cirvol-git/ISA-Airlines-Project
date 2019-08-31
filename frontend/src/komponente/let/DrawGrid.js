import React, {Component} from 'react';

class DrawGrid extends Component {
    onClickSeat(seat) {
        this.props.onClickData(seat);
      }
    render() {
      return (
         <div><div className="container">
          <table className="grid">
            <tbody>
              {this.props.rows.map(row => {
                return (<tr>
                  { row.map( seat =>
                    <td 
 //                     className={this.props.reserved.indexOf(row) > -1? 'reserved': 'available'}
                      key={seat.id} onClick = {() => this.onClickSeat(seat)}>id:{seat.id}, tip: {seat.tip} </td>) }
                </tr>
              )})}
            </tbody>
          </table>
         </div></div>
      )
    }
}

export default DrawGrid;