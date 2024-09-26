import React, { useState } from 'react';
import { Form, Button, Container, Row, Col } from 'react-bootstrap';
import Notification from './Notificacion'; 
import 'bootstrap/dist/css/bootstrap.min.css';

const ProductForm: React.FC = () => {
    const [nombre, setNombre] = useState<string>('');
    const [descripcion, setDescripcion] = useState<string>('');
    const [codigo, setCodigo] = useState<string>('');
    const [cantidadTotal, setCantidadTotal] = useState<number>(0);
    const [precioVenta, setPrecioVenta] = useState<number>(0);
    const [imagen, setImagen] = useState<File | null>(null);
    const [notification, setNotification] = useState<{ message: string; type: 'success' | 'error' } | null>(null);

    const handleImageChange = (event: React.ChangeEvent<HTMLInputElement>) => {
        if (event.target.files && event.target.files[0]) {
            setImagen(event.target.files[0]);
        }
    };

    const validateFields = () => {
        if (!nombre || !descripcion || !codigo || cantidadTotal <= 0 || precioVenta <= 0 || !imagen) {
            let errorMessage = 'Por favor, complete todos los campos obligatorios: ';
            if (!nombre) errorMessage += 'Nombre, ';
            if (!descripcion) errorMessage += 'Descripción, ';
            if (!codigo) errorMessage += 'Código, ';
            if (cantidadTotal <= 0) errorMessage += 'Cantidad Total (mayor a 0), ';
            if (precioVenta <= 0) errorMessage += 'Precio de Venta (mayor a 0), ';
            if (!imagen) errorMessage += 'Imagen. ';
            setNotification({ message: errorMessage.trimEnd().slice(0, -1) + '.', type: 'error' });
            return false;
        }
        return true;
    };

    const handleSubmit = async (event: React.FormEvent) => {
        event.preventDefault();

        if (!validateFields()) return;

        if (imagen) {
            const reader = new FileReader();
            
            reader.onloadend = async () => {
                const base64String = reader.result as string;
                const base64Data = base64String.split(',')[1]; // Eliminar el prefijo

                const data = {
                    nombre,
                    descripcion,
                    codigo,
                    cantidadTotal,
                    precioVenta,
                    imagenBase64: base64Data,
                };

                try {
                    const response = await fetch('http://localhost:8080/api/productos', {
                        method: 'POST',
                        headers: {
                            'Content-Type': 'application/json',
                        },
                        body: JSON.stringify(data),
                    });

                    if (response.ok) {
                        const responseData = await response.text();
                        setNotification({ message: responseData, type: 'success' });
                    } else {
                        const errorText = await response.text();
                        setNotification({ message: `Error: ${errorText}`, type: 'error' });
                    }
                } catch (error) {
                    setNotification({ message: 'Error en la carga del producto', type: 'error' });
                }
            };

            reader.readAsDataURL(imagen);
        }
    };

    return (
        <Container className="mt-5">
            <Row className="justify-content-center">
                <Col md={8} lg={6}>
                    <h2 className="mb-4 text-center">Agregar Producto</h2>
                    <Form onSubmit={handleSubmit}>
                        <Form.Group controlId="formNombre">
                            <Form.Label>Nombre</Form.Label>
                            <Form.Control
                                type="text"
                                value={nombre}
                                onChange={(e) => setNombre(e.target.value)}
                                placeholder="Ingrese el nombre del producto"
                            />
                        </Form.Group>
                        <Form.Group controlId="formDescripcion">
                            <Form.Label>Descripción</Form.Label>
                            <Form.Control
                                type="text"
                                value={descripcion}
                                onChange={(e) => setDescripcion(e.target.value)}
                                placeholder="Ingrese una descripción del producto"
                            />
                        </Form.Group>
                        <Form.Group controlId="formCodigo">
                            <Form.Label>Código</Form.Label>
                            <Form.Control
                                type="text"
                                value={codigo}
                                onChange={(e) => setCodigo(e.target.value)}
                                placeholder="Ingrese el código del producto"
                            />
                        </Form.Group>
                        <Form.Group controlId="formCantidadTotal">
                            <Form.Label>Cantidad Total</Form.Label>
                            <Form.Control
                                type="number"
                                value={cantidadTotal}
                                onChange={(e) => setCantidadTotal(Number(e.target.value))}
                                placeholder="Ingrese la cantidad total"
                            />
                        </Form.Group>
                        <Form.Group controlId="formPrecioVenta">
                            <Form.Label>Precio de Venta</Form.Label>
                            <Form.Control
                                type="number"
                                step="0.01"
                                value={precioVenta}
                                onChange={(e) => setPrecioVenta(Number(e.target.value))}
                                placeholder="Ingrese el precio de venta"
                            />
                        </Form.Group>
                        <Form.Group controlId="formImagen">
                            <Form.Label>Imagen</Form.Label>
                            <Form.Control
                                type="file"
                                accept="image/*"
                                onChange={handleImageChange}
                            />
                        </Form.Group>
                        <Button variant="primary" type="submit" className="w-100 mt-3">
                            Guardar Producto
                        </Button>
                    </Form>
                    {notification && (
                        <Notification message={notification.message} type={notification.type} />
                    )}
                </Col>
            </Row>
        </Container>
    );
};

export default ProductForm;
