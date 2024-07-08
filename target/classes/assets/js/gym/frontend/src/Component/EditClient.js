import React, { Component } from 'react';

class EditClient extends Component {
    constructor(props) {
        super(props);
        this.state = { client: null, isLoading: true };
        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    async componentDidMount() {
        const { id } = this.props.match.params;
        try {
            const response = await fetch(`/api/usuario/${id}`);
            const client = await response.json();
            this.setState({ client, isLoading: false });
        } catch (error) {
            console.error('Error fetching client:', error);
            this.setState({ isLoading: false });
        }
    }

    handleChange(event) {
        const { name, value } = event.target;
        this.setState(prevState => ({
            client: { ...prevState.client, [name]: value }
        }));
    }

    async handleSubmit(event) {
        event.preventDefault();
        const { id } = this.props.match.params;
        try {
            await fetch(`/api/usuario/${id}`, {
                method: 'POST',
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(this.state.client)
            });
            this.props.history.push('/clients');
        } catch (error) {
            console.error('Error updating client:', error);
        }
    }

    render() {
        const { client, isLoading } = this.state;

        if (isLoading) {
            return <p>Loading...</p>;
        }

        if (!client) {
            return <p>Error: client not found</p>;
        }

        return (
            <form onSubmit={this.handleSubmit}>
                <div>
                    <label htmlFor="nombre">Nombre:</label>
                    <input type="text" name="nombre" value={client.nombre} onChange={this.handleChange} />
                </div>
                <div>
                    <label htmlFor="email">Email:</label>
                    <input type="email" name="email" value={client.email} onChange={this.handleChange} />
                </div>
                <div>
                    <label htmlFor="direccion">Direccion:</label>
                    <input type="text" name="direccion" value={client.direccion} onChange={this.handleChange} />
                </div>
                <button type="submit">Save</button>
            </form>
        );
    }
}

export default EditClient;
