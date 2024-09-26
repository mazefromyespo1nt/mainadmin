import React, { useState } from 'react';
import { Form, Button, Alert, ListGroup, InputGroup, Row, Col } from 'react-bootstrap';
import { useFetchProductos } from './Component/useFetchProductos'; 
import { useSaveVenta } from './Component/useSaveVenta';  
import { Icon } from '@iconify/react';

interface Producto {
  idProducto: number;
  nombre: string;
  precioVenta: number | null;
  cantidad: number;
  codigo: string; 
}

interface Venta {
  idVenta: number | null;
  cantidad: number;
  fecha: Date;
  precioVenta: number;
  total: number;
  productoId: number[];
}

interface CashierProps {
  registrarVenta: (venta: Venta) => void;
}

const Cashier: React.FC<CashierProps> = ({ registrarVenta }) => {
  const { productos: productosBase, loading, error } = useFetchProductos('http://localhost:8080/api/productos');
  const { saveVenta, loading: savingLoading, error: savingError } = useSaveVenta('http://localhost:8080/api/ventas');
  
  const [productos, setProductos] = useState<Producto[]>([]);
  const [total, setTotal] = useState<number>(0);
  const [cashReceived, setCashReceived] = useState<number>(0);
  const [change, setChange] = useState<number | null>(null);
  const [errorMsg, setErrorMsg] = useState<string | null>(null);
  const [codigoBarras, setCodigoBarras] = useState<string>('');

  // Función para agregar un producto basado en el código de barras
  const agregarProducto = () => {
    const codigoNumero = parseInt(codigoBarras.trim(), 10);

    if (isNaN(codigoNumero)) {
      setErrorMsg('Código de barras inválido.');
      return;
    }

    const productoEncontrado = productosBase.find(
      (item) => item.codigo === codigoBarras.trim()
    );

    if (productoEncontrado) {
      if (productoEncontrado.idProducto === undefined) {
        setErrorMsg('El producto no tiene un ID válido.');
        return;
      }

      const productoExistente = productos.find(
        (item) => item.codigo === productoEncontrado.codigo
      );

      if (productoExistente) {
        const productosActualizados = productos.map((item) =>
          item.codigo === productoExistente.codigo
            ? { ...item, cantidad: item.cantidad + 1 }
            : item
        );
        setProductos(productosActualizados);
      } else {
        setProductos([...productos, { ...productoEncontrado, cantidad: 1 }]);
      }

      setTotal((prevTotal) => prevTotal + Number(productoEncontrado.precioVenta || 0)); // Ajuste de total
      setCodigoBarras('');
    } else {
      setErrorMsg('Producto no encontrado.');
    }
  };

  // Validación de la cantidad recibida y cálculo de cambio
  const handleCalculateChange = async () => {
    if (isNaN(cashReceived) || cashReceived <= 0) {
      setErrorMsg('Ingrese un monto válido de efectivo.');
      return;
    }

    if (cashReceived < total) {
      setErrorMsg('La cantidad recibida es menor que el total.');
      setChange(null);
    } else {
      const cambio = cashReceived - total;
      setChange(cambio);
      setErrorMsg(null);
  
      const cantidadTotal = productos.reduce((acc, producto) => acc + producto.cantidad, 0);
      const productoIds = productos.map((producto) => producto.idProducto);
  
      const venta: Venta = {
        idVenta: null,
        cantidad: cantidadTotal,
        fecha: new Date(),
        precioVenta: total,
        total,
        productoId: productoIds,
      };
  
      try {
        // Guardar la venta en la API de ventas
        await saveVenta(venta);
  
        // Guardar la venta en la API de reportes de ventas
        const reporteVentaData = {
          fecha: new Date().toISOString(),
          cantidad: cantidadTotal,
          precioVenta: total,
          total,
          productoId: productoIds,
        };
  
        const response = await fetch('http://localhost:8080/api/reportes-ventas', {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json',
          },
          body: JSON.stringify(reporteVentaData),
        });
  
        if (!response.ok) {
          throw new Error('Error al registrar el reporte de la venta');
        }
  
        // Guardar la venta en localStorage
        const storedVentas = localStorage.getItem('ventas') ? JSON.parse(localStorage.getItem('ventas')!) : [];
        storedVentas.push(reporteVentaData);
        localStorage.setItem('ventas', JSON.stringify(storedVentas));
  
        registrarVenta(venta); // Llama a la función que registra la venta
      } catch (error) {
        console.error('Error al registrar la venta:', error); // Agregar logging para depuración
        setErrorMsg('No se pudo guardar la venta o el reporte de la venta.');
      }
    }
  };

  if (loading) {
    return <p>Cargando productos...</p>;
  }

  if (error) {
    return <p>{error}</p>;
  }

  return (
    <div style={{ padding: '20px' }}>
      <h2>Pago en Efectivo</h2>
      <Row> 
        <Col md={6}>
          <Form>
            <Form.Group controlId="codigoBarras">
              <Form.Label>Código de Barras</Form.Label>
              <InputGroup>
                <InputGroup.Text>
                  <Icon icon="streamline:money-cashier-bar-code-codes-tags-upc-barcode" style={{ fontSize: '25px' }} />
                </InputGroup.Text>
                <Form.Control
                  type="text"
                  placeholder="Escanea o ingresa el código de barras"
                  value={codigoBarras}
                  onChange={(e) => setCodigoBarras(e.target.value)}
                  onKeyDown={(e) => {
                    if (e.key === 'Enter') {
                      e.preventDefault();
                      agregarProducto();
                    }
                  }}
                />
              </InputGroup>
            </Form.Group>

            <Form.Group controlId="cashReceived" style={{ marginTop: '20px' }}>
              <Form.Label>Efectivo Recibido</Form.Label>
              <InputGroup>
                <InputGroup.Text>
                  <Icon icon="bi:cash-coin" style={{ fontSize: '25px' }} />
                </InputGroup.Text>
                <Form.Control
                  type="number"
                  placeholder="Ingrese el efectivo recibido"
                  value={cashReceived}
                  onChange={(e) => setCashReceived(parseFloat(e.target.value))}
                  onKeyDown={(e) => {
                    if (e.key === 'Enter') {
                      e.preventDefault();
                      handleCalculateChange();
                    }
                  }}
                />
              </InputGroup>
            </Form.Group>

            <Button
              variant="success"
              style={{ marginTop: '20px' }}
              onClick={handleCalculateChange}
            >
              Calcular Cambio
              <Icon icon="fluent:money-hand-16-regular" style={{ fontSize: '28px' }} />
            </Button>

            {errorMsg && <Alert variant="danger" style={{ marginTop: '20px' }}>{errorMsg}</Alert>}

            {change !== null && (
              <Alert variant="success" style={{ marginTop: '20px' }}>
                Cambio a devolver: ${change.toFixed(2)}
              </Alert>
            )}
          </Form>
        </Col>

        <Col md={6}>
          <h3>Productos Escaneados</h3>
          <ListGroup>
            {productos.map((producto) => (
              <ListGroup.Item key={producto.idProducto}>
                <div style={{ display: 'flex', justifyContent: 'space-between' }}>
                  <span>{producto.nombre} x {producto.cantidad}</span>
                  <span>${((producto.precioVenta || 0) * producto.cantidad).toFixed(2)}</span>
                </div>
              </ListGroup.Item>
            ))}
          </ListGroup>
          <h4 style={{ marginTop: '20px' }}>Total: ${total.toFixed(2)}</h4>
        </Col>
      </Row>
    </div>
  );
};

export default Cashier;
