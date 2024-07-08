import React, { Component } from 'react';
import { Button, Form, FormGroup, Label, Input, Modal, ModalHeader, ModalBody, ModalFooter } from 'reactstrap';
import { withRouter } from 'react-router-dom';
import AppNavbar from './AppNavbar';
import DeleteIcon from '@mui/icons-material/Delete';
import SendIcon from '@mui/icons-material/Send';

class ApiButton extends Component {
    constructor(props) {
        super(props);
        this.state = {
            response: null,
            error: null,
            modal: false,
            nombre: '',
            razonSocial: ''
        };

        this.toggle = this.toggle.bind(this);
        this.handleClick = this.handleClick.bind(this);
        this.handleSave = this.handleSave.bind(this);
        this.handleChange = this.handleChange.bind(this);
    }

    toggle() {
        this.setState(prevState => ({
            modal: !prevState.modal
        }));
    }

    handleClick() {
        fetch('https://schema.getpostman.com/json/collection/v2.1.0/collection.json')
            .then(res => {
                if (!res.ok) {
                    throw new Error('Network response was not ok');
                }
                return res.json();
            })
            .then(data => this.setState({ response: data }))
            .catch(err => this.setState({ error: err.message }));
    }

    handleSave() {
        const { nombre, razonSocial } = this.state;

        fetch('http://localhost:8080/api/usuario', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({
                nombre,
                razonSocial,
            }),
        })
            .then(response => {
                if (!response.ok) {
                    throw new Error('Error al agregar los datos');
                }
                return response.json();
            })
            .then(data => {
                console.log('Datos agregados correctamente:', data);
                this.setState({ modal: false });
            })
            .catch(error => {
                console.error('Error al agregar los datos:', error);
            });
    }

    handleChange(event) {
        const { name, value } = event.target;
        this.setState({ [name]: value });
    }

    render() {
        const { response, error, modal, nombre, razonSocial } = this.state;

        return (
            <div>
                <AppNavbar />
                <Button color="primary" endIcon={<SendIcon />} onClick={this.handleClick}>
                    Agregar
                </Button>
                <Button color="secondary" startIcon={<DeleteIcon />} onClick={this.toggle}>
                    Guardar
                </Button>
                {response && (
                    <div>
                        <h3>Respuesta de la API:</h3>
                        <pre>{JSON.stringify(response, null, 2)}</pre>
                    </div>
                )}
                {error && (
                    <div>
                        <h3>Error:</h3>
                        <p>{error}</p>
                    </div>
                )}
                <Modal isOpen={modal} toggle={this.toggle}>
                    <ModalHeader toggle={this.toggle}>Agregar Datos</ModalHeader>
                    <ModalBody>
                        <Form>
                            <FormGroup>
                                <Label for="nombre">Nombre</Label>
                                <Input type="text" name="nombre" id="nombre" value={nombre} onChange={this.handleChange} />
                            </FormGroup>
                            <FormGroup>
                                <Label for="razonSocial">Razón Social</Label>
                                <Input type="text" name="razonSocial" id="razonSocial" value={razonSocial} onChange={this.handleChange} />
                            </FormGroup>
                        </Form>
                    </ModalBody>
                    <ModalFooter>
                        <Button color="primary" onClick={this.handleSave}>Guardar</Button>{' '}
                        <Button color="secondary" onClick={this.toggle}>Cancelar</Button>
                    </ModalFooter>
                </Modal>
            </div>
        );
    }
}

export default withRouter(ApiButton);