import React, { useState } from 'react';
import { Button, Modal, ModalHeader, ModalBody, ModalFooter, Form, FormGroup, Label, Input, Alert } from 'reactstrap';

const AddClientModal = () => {
    const [modal, setModal] = useState(false);
    const [client, setClient] = useState({
        nombre: '',
        email: '',
        password: '',
        telefono: '',
        direccion: '',
        encargado: '',
        nombreEncargado: '',
        fechaRegistro: new Date().toISOString().slice(0, 10),
        status: true
    });
    const [error, setError] = useState('');
    const [successMessage, setSuccessMessage] = useState('');

    const toggle = () => setModal(!modal);

    const handleChange = (e) => {
        const { name, value } = e.target;
        setClient(prevState => ({
            ...prevState,
            [name]: value
        }));
    };

    const handleSubmit = async (e) => {
        e.preventDefault();

        if (client.password.length < 6 || !/^[a-zA-Z0-9]+$/.test(client.password)) {
            setError('La contraseña debe tener al menos 6 caracteres y no debe contener caracteres especiales.');
            return;
        }

        try {
            // const response = await fetch(`/api/usuario`);
            // const data = await response.json();
            // if (data.length > 0) {
            //     setError('Ya existe un cliente con el mismo nombre y correo.');
            //     return;
            // }
        } catch (error) {
            console.error('Error al verificar la existencia del cliente:', error);
            setError('Error al verificar la existencia del cliente.');
            return;
        }

        try {
            const response = await fetch('http://localhost:8080/api/usuario/login', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(client)
            });

            if (!response.ok) {
                throw new Error('Error al agregar el cliente');
            }
console.log(response);
            const responseData = await response.json();
            console.log('Cliente agregado:', responseData);

            setSuccessMessage('Cliente agregado exitosamente.');
            toggle();
            setClient({
                nombre: '',
                email: '',
                password: '',
                telefono: '',
                direccion: '',
                encargado: '',
                nombreEncargado: '',
                fechaRegistro: new Date().toISOString().slice(0, 10),
                status: true
            });
            setError('');
        } catch (error) {
            console.error('Error al agregar el cliente:', error);
            setError('Error al agregar el cliente.');
        }
    };

    const handleDismissError = () => {
        setError('');
    };

    const handleDismissSuccess = () => {
        setSuccessMessage('');
    };

    return (
        <div>
            <Button color="success" onClick={toggle}>Add Client</Button>
            <Modal isOpen={modal} toggle={toggle}>
                <ModalHeader toggle={toggle}>Add Client</ModalHeader>
                <ModalBody>
                    <Form onSubmit={handleSubmit}>
                        <FormGroup>
                            <Label for="nombre">Nombre</Label>
                            <Input type="text" name="nombre" id="nombre" value={client.nombre} onChange={handleChange} required />
                        </FormGroup>
                        <FormGroup>
                            <Label for="email">Email</Label>
                            <Input type="email" name="email" id="email" value={client.email} onChange={handleChange} required />
                        </FormGroup>
                        <FormGroup>
                            <Label for="password">Password</Label>
                            <Input type="password" name="password" id="password" value={client.password} onChange={handleChange} required pattern="^[a-zA-Z0-9]{6,}$" title="La contraseña debe tener al menos 6 caracteres y no debe contener caracteres especiales." />
                        </FormGroup>
                        <FormGroup>
                            <Label for="telefono">Teléfono</Label>
                            <Input type="text" name="telefono" id="telefono" value={client.telefono} onChange={handleChange} required />
                        </FormGroup>
                        <FormGroup>
                            <Label for="direccion">Dirección</Label>
                            <Input type="text" name="direccion" id="direccion" value={client.direccion} onChange={handleChange} required />
                        </FormGroup>
                        <FormGroup>
                            <Label for="encargado">Encargado</Label>
                            <Input type="text" name="encargado" id="encargado" value={client.encargado} onChange={handleChange} required />
                        </FormGroup>
                        <FormGroup>
                            <Label for="nombreEncargado">Nombre del Encargado</Label>
                            <Input type="text" name="nombreEncargado" id="nombreEncargado" value={client.nombreEncargado} onChange={handleChange} required />
                        </FormGroup>
                        {error && <Alert color="danger" isOpen={error !== ''} toggle={handleDismissError}>{error}</Alert>}
                        {successMessage && <Alert color="success" isOpen={successMessage !== ''} toggle={handleDismissSuccess}>{successMessage}</Alert>}
                        <ModalFooter>
                            <Button color="primary" type="submit">Save</Button>{' '}
                            <Button color="secondary" onClick={toggle}>Cancel</Button>
                        </ModalFooter>
                    </Form>
                </ModalBody>
            </Modal>
        </div>
    );
};

export default AddClientModal;
