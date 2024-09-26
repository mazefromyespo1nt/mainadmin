import React, { useEffect, useState } from 'react';
import { ListGroup, Button, Form } from 'react-bootstrap';
import { Icon } from '@iconify/react';

interface Producto {
  idProducto: number;
  idRol:number;
  nombre: string;
  precioVenta: number | null;
  cantidadTotal: number;
  codigo: number
}

const CompraOnline: React.FC = () => {
  const [carrito, setCarrito] = useState<Producto[]>([]);
  const [total, setTotal] = useState<number>(0);

  useEffect(() => {
    const storedCarrito = localStorage.getItem('carrito');
    const storedTotal = localStorage.getItem('total');

    if (storedCarrito) {
      setCarrito(JSON.parse(storedCarrito));
    }

    if (storedTotal) {
      setTotal(JSON.parse(storedTotal));
    }
  }, []);

  const handleCompra = async () => {
    const compraData = {
      fecha: new Date().toISOString(),
      cantidad: carrito[0].cantidadTotal,
      precioVenta: carrito[0].precioVenta,
      rolesId: carrito[0].idRol,
      productoId: carrito[0].idProducto,
      total: total
    };

    try {
      const response = await fetch('http://localhost:8080/api/reportes-ventas', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(compraData),
      });
      console.log('Petición enviada:', compraData);
      console.log('Respuesta:', response);
      
      if (!response.ok) {
        throw new Error('Error al registrar la compra');
      }

      // Guardar la compra en localStorage
      const storedVentas = localStorage.getItem('ventas') ? JSON.parse(localStorage.getItem('ventas')!) : [];
      storedVentas.push(compraData);
      localStorage.setItem('ventas', JSON.stringify(storedVentas));

      alert('Compra realizada con éxito');
      localStorage.removeItem('carrito');
      localStorage.removeItem('total');
    } catch (error: any) {
      console.log('Error:', Error);
      alert(error.message);
    }
  };

  return (
    <div className="container">
      <h2>Resumen de la Compra</h2>
      <ListGroup>
        {carrito.map(producto => (
          <ListGroup.Item key={producto.idProducto}>
            <h5>{producto.nombre}</h5>
            <p>Precio: ${producto.precioVenta?.toFixed(2)}</p>
            <p>Cantidad: {producto.cantidadTotal}</p>
          </ListGroup.Item>
        ))}
      </ListGroup>
      <h3>Total: ${total.toFixed(2)}</h3>

      <Form>
        <Form.Group controlId="formCardNumber">
          <Form.Label>Número de Tarjeta</Form.Label>
          <Form.Control type="text" placeholder="1234 5678 9012 3456" />
        </Form.Group>
        <Form.Group controlId="formExpiryDate">
          <Form.Label>Fecha de Expiración</Form.Label>
          <Form.Control type="text" placeholder="MM/AA" />
        </Form.Group>
        <Form.Group controlId="formCVC">
          <Form.Label>CVC</Form.Label>
          <Form.Control type="text" placeholder="123" />
        </Form.Group>
        <Button variant="success" onClick={handleCompra}>
          Finalizar Compra
          <Icon icon="mdi:check-circle" style={{ fontSize: '24px', marginLeft: '5px' }} />
        </Button>
      </Form>
    </div>
  );
};

export default CompraOnline;
