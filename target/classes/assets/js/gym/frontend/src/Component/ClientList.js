import React, { Component } from 'react';
import { Button, ButtonGroup, Container, Table } from 'reactstrap';
import { Link } from 'react-router-dom';

class ClientList extends Component {
    constructor(props) {
        super(props);
        this.state = { clients: [], isLoading: true };
        this.remove = this.remove.bind(this);
    }

    componentDidMount() {
        this.fetchClients();
    }

    async fetchClients() {
        try {
            const response = await fetch('/api/usuario');
            const data = await response.json();
            this.setState({ clients: data, isLoading: false });
        } catch (error) {
            console.error('Error fetching clients:', error);
            this.setState({ isLoading: false });
        }
    }

    async remove(id) {
        try {
            await fetch(`/api/usuario/${id}`, {
                method: 'DELETE',
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                }
            });

            // Remove the client from the state
            const updatedClients = this.state.clients.filter(client => client.id !== id);
            this.setState({ clients: updatedClients });

        } catch (error) {
            console.error('Error removing client:', error);
        }
    }

    render() {
        const { clients, isLoading } = this.state;

        if (isLoading) {
            return <p>Loading...</p>;
        }

        const clientList = clients.map(client => (
            <tr key={client.id}>
                <td style={{ whiteSpace: 'nowrap' }}>{client.name}</td>
                <td>{client.email}</td>
                <td>
                    <ButtonGroup>
                        <Button size="sm" color="primary" tag={Link} to={`/clients/${client.id}`}>Edit</Button>
                        <Button size="sm" color="danger" onClick={() => this.remove(client.id)}>Delete</Button>
                    </ButtonGroup>
                </td>
            </tr>
        ));

        return (
            <Container fluid>
                <div className="float-right">
                    <Button color="success" tag={Link} to="/clients/new">Add Client</Button>
                </div>
                <h3>Clients</h3>
                <Table className="mt-4">
                    <thead>
                    <tr>
                        <th width="30%">Name</th>
                        <th width="30%">Email</th>
                        <th width="40%">Actions</th>
                    </tr>
                    </thead>
                    <tbody>
                    {clientList}
                    </tbody>
                </Table>
            </Container>
        );
    }
}

export default ClientList;