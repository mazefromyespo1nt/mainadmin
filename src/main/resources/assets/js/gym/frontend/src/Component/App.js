import React, { Component } from 'react';
import '../App.css';  // Ajusta según la ubicación real de App.css
import Home from './Home';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import ClientList from './ClientList';
import EditClient from './EditClient'; // Importa el componente de edición

class App extends Component {
    render() {
        return (
            <Router>
                <Switch>
                    <Route path='/' exact={true} component={Home} />
                    <Route path='/clients/' exact={true} component={ClientList} />
                    <Route path='/clients/:id' component={EditClient} />
                </Switch>
            </Router>
        );
    }
}

export default App;
