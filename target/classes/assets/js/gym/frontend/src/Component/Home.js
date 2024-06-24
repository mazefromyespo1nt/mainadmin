import React, { Component } from 'react';
import '../App.css';  // Ajusta según la ubicación real de App.css
import { Link } from 'react-router-dom';
import { Button, Container } from 'reactstrap';
import AppNavbar from "./AppNavbar";

class Home extends Component {
    render() {
        return (
            <div>
                <AppNavbar/>
                <Container fluid>
                    <h1 className="display-4">Welcome to Your App</h1>
                    <p className="lead">This is a simple client management system.</p>
                    <Button color="primary" tag={Link} to="/clients">View Clients</Button>
                </Container>
            </div>
        );
    }
}

export default Home;