import React, { Component } from 'react';
import {BrowserRouter, Switch, Route} from 'react-router-dom';
import NavigacioniBar from './komponente/layout/NavigacioniBar';
import Aviokompanije from './komponente/avio/Aviokompanije';
import Aviokompanija from './komponente/avio/Aviokompanija';
import AvioIzmeni from './komponente/avio/AvioIzmeni';
import createDest from './komponente/dest/createDest';
import updateDest from './komponente/dest/updateDest';
import createLet from './komponente/let/createLet';
import DrawSedista from './komponente/let/DrawSedista';
import Prijatelji from './komponente/korisnik/Prijatelji';
import Login from './komponente/layout/Login';
import DodajPrijatelja from './komponente/korisnik/DodajPrijatelja';
import PretragaLeta from './komponente/let/PregragaLeta'
import DrawSedistaRez from './komponente/rezervacije/DrawSedistaRez'
import Home from './komponente/layout/Home';
import IzmeniKorisnika from './komponente/korisnik/IzmeniKorisnika';
import ListaBrzihRez from './komponente/rezervacije/ListaBrzihRez';
import dodeliPrijteljaLetu from './komponente/rezervacije/dodeliPrijteljaLetu';
import RezervacijeList from './komponente/rezervacije/RezervacijeList';


class App extends Component {
  render() {
    return (
      <BrowserRouter>
        <div className="App">
          <NavigacioniBar />
          <Switch>
            <Route exact path='/' component={Login}></Route>
            <Route exact path='/home' component={Home}></Route>
            <Route exact path='/avios' component={Aviokompanije}></Route>
            <Route exact path ='/avio' component={Aviokompanija} ></Route>
            <Route exact path ='/avio/:id' component={AvioIzmeni} ></Route>
            <Route exact path ='/dest/:id' component={updateDest} ></Route>
            <Route exact path='/createDest' component={createDest}></Route>
            <Route exact path='/createLet' component={createLet}></Route>
            <Route exact path='/createLet2' component={DrawSedista}></Route>
            <Route exact path='/prijatelji' component={Prijatelji}></Route>
            <Route exact path='/pretragaKorisnika' component={DodajPrijatelja}></Route>
            <Route exact path='/pretragaLetova' component={PretragaLeta}></Route>
            <Route exact path='/rezervacija/:id' component={DrawSedistaRez}></Route>
            <Route exact path='/rezList' component={RezervacijeList}></Route>
            <Route exact path='/brzeRezervacije/:id' component={ListaBrzihRez}></Route>
            <Route exact path='/azuriranje' component={IzmeniKorisnika}></Route>
            <Route exact path='/dodeliPrijatelja' component={dodeliPrijteljaLetu}></Route>

          </Switch>
        </div>
      </BrowserRouter>
    );
  }
}

export default App;
