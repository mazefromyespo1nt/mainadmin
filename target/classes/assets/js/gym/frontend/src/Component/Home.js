import React, { Component } from 'react';
import '../App.css';  // Ajusta según la ubicación real de App.css
import { Link } from 'react-router-dom';
import { Button, Container } from 'reactstrap';


class Home extends Component {
    render() {
        return (
            <div>
                <Container fluid>
                    <h1 className="display-4">Bienvenido </h1>
                    <p className="lead">This is a simple client management system.</p>
                    <Button color="primary" tag={Link} to="/clients/">Ver Clientes</Button>
                </Container>
            </div>
        );
    }
}

export default Home;