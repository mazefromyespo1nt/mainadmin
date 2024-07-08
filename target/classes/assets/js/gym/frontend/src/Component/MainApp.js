import React from 'react';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import AppNavbar from './AppNavbar';
import ClientList from './ClientList';
import App from './App';

function MainApp() {
    return (
        <Router>
            <div>
                <AppNavbar />
                <Switch>
                    <Route path="/" exact component={App} />
                    <Route path="/clients" exact component={ClientList} />
                    {/* Agrega otras rutas aquí según sea necesario */}
                </Switch>
            </div>
        </Router>
    );
}

export default MainApp;