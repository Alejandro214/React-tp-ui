import React from 'react';
import './Search/Search.css'
import './App/App.css';
import Login from './login'
import Home from './Home/Home'
import './Home/Home.css'
import { BrowserRouter as Router, Switch, Route } from 'react-router-dom';
import Register from './register'
import Search from './Search/Search'
import Movie from './Movie/Movie'
import './Movie/Movie.css'
import Serie from './Serie/Serie'
import './Serie/Serie.css'
import Player from './Reproductor/Player'

const App = () => {

  return (

    <Router>
      <Switch>
        <Route path="/login" component={Login} />
        <Route path="/register" component={Register} />
        <Route path="/home" component={Home} />
        <Route path="/search" component={Search} />
        <Route path="/movie" component={Movie} />
        <Route path="/serie" component={Serie} />
        <Route path="/player" component={Player} />
        <Route path="/" component = {Login}/>
        <Route path="*" render={() => <div className="NotFound">NotFound</div>} />
      </Switch>
    </Router>

  );
}

export default App;