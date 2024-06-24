import React from 'react';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import AppNavbar from './AppNavbar';
import ClientList from './ClientList';
import ClientEdit from './ApiButton';  // Renombrado para mantener la consistencia con las rutas
import App from './App';

function MainApp() {
    return (
        <Router>
            <div>
                <AppNavbar />
                <Switch>
                    <Route path="/" exact component={App} />
                    <Route path="/clients" exact component={ClientList} />
                    <Route path="/clients/:id" component={ClientEdit} />
                    {/* Agrega otras rutas aquí según sea necesario */}
                </Switch>
            </div>
        </Router>
    );
}

export default MainApp;