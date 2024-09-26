import React, { useReducer, useState } from 'react';
import { Card, Button, ListGroup, Form, Offcanvas, InputGroup } from 'react-bootstrap';
import Cart from './Cart';
import { useFetchProductos } from './useFetchProductos';
import { Icon } from '@iconify/react';
import Notification from './Notificacion';

interface Producto {
  idProducto: number;
  nombre: string;
  descripcion: string;
  precioVenta: number | null;
  imagenBase64: string;
  cantidadTotal: number;
  tipoProductoId: number;
  codigo: string;
  proveedorId: number;
}

const initialState: Producto[] = [];

const comprasReducer = (state: Producto[], action: any) => {
  switch (action.type) {
    case '[CARRITO] Agregar Compra':
      const existingProducto = state.find(item => item.idProducto === action.payload.idProducto);
      if (existingProducto) {
        return state.map(item =>
          item.idProducto === action.payload.idProducto
            ? { ...item, cantidadTotal: item.cantidadTotal + 1 }
            : item
        );
      }
      return [...state, { ...action.payload, cantidadTotal: 1 }];
    case '[CARRITO] Aumentar Cantidad Compra':
      return state.map(item =>
        item.idProducto === action.payload
          ? { ...item, cantidadTotal: item.cantidadTotal + 1 }
          : item
      );
    case '[CARRITO] Disminuir Cantidad Compra':
      return state.map(item =>
        item.idProducto === action.payload && item.cantidadTotal > 1
          ? { ...item, cantidadTotal: item.cantidadTotal - 1 }
          : item
      );
    case '[CARRITO] Eliminar Compra':
      return state.filter(compra => compra.idProducto !== action.payload);
    default:
      return state;
  }
};

const ProductList: React.FC = () => {
  const { productos, loading, error } = useFetchProductos('http://localhost:8080/api/productos');
  const [busqueda, setBusqueda] = useState<string>('');
  const [showCart, setShowCart] = useState<boolean>(false);
  const [carrito, dispatch] = useReducer(comprasReducer, initialState);
  const [notification, setNotification] = useState<{ message: string; type: 'success' | 'error' } | null>(null);

  const productosFiltrados = productos.filter(producto =>
    producto.nombre.toLowerCase().includes(busqueda.toLowerCase())
  );

  const agregarCompra = (producto: Producto) => {
    dispatch({ type: '[CARRITO] Agregar Compra', payload: producto });
    setNotification({ message: `Producto "${producto.nombre}" agregado al carrito`, type: 'success' });

    setTimeout(() => {
      setNotification(null);
    }, 3000); 
  };

  const total = carrito.reduce(
    (total, producto) => total + (producto.precioVenta || 0) * producto.cantidadTotal,
    0
  );

  if (loading) return <p>Cargando productos...</p>;
  if (error) return <p>{error}</p>;

  return (
    <div className="container">
      <h1 className="text-center mb-4">Tienda de Productos</h1>

      {notification && (
        <Notification message={notification.message} type={notification.type} />
      )}

      <Form.Group controlId="search" className="mb-3">
        <InputGroup>
          <InputGroup.Text>
            <Icon icon="mdi:store-search" style={{ fontSize: '30px' }} />
          </InputGroup.Text>
          <Form.Control
            type="text"
            placeholder="Buscar productos..."
            value={busqueda}
            onChange={(e) => setBusqueda(e.target.value)}
            className="form-control-lg"
            style={{ width: '800px' }}
          />
          <Button
            variant="info"
            onClick={() => setShowCart(true)}
            style={{ margin: '1rem' }}
          >
            Ver Carrito
            <Icon icon="mdi:cart-variant" style={{ fontSize: '25px' }} />
          </Button>
        </InputGroup>
      </Form.Group>

      <div className="d-flex flex-wrap justify-content-center">
        {productosFiltrados.length > 0 ? (
          productosFiltrados.map(producto => (
            <Card key={producto.idProducto} style={{ maxWidth: "300px", minWidth: "200px", margin: '1rem', maxHeight: "600px" }}>
              <Card.Img
                variant="top"
                src={`data:image/jpeg;base64,${producto.imagenBase64}`}
                alt="Imagen del producto"
                style={{ width: '300px', height: '200px', objectFit: 'cover' }}
              />
              <Card.Body>
                <Card.Title>{producto.nombre}</Card.Title>
                <Card.Text>{producto.descripcion}</Card.Text>
              </Card.Body>
              <ListGroup className="list-group-flush">
                <ListGroup.Item>
                  Precio: ${producto.precioVenta !== null ? producto.precioVenta.toFixed(2) : '0.00'}
                </ListGroup.Item>
              </ListGroup>
              <Card.Body>
                <Button variant="primary" onClick={() => agregarCompra(producto)}>
                  Agregar al carrito
                  <Icon icon="mdi:cart-plus" style={{ fontSize: '20px' }} />
                </Button>
              </Card.Body>
            </Card>
          ))
        ) : (
          <p>No se encontraron productos.</p>
        )}
      </div>

      <Offcanvas show={showCart} onHide={() => setShowCart(false)} placement="end">
        <Offcanvas.Header closeButton>
          <Offcanvas.Title>Carrito de Compras</Offcanvas.Title>
        </Offcanvas.Header>

        <Offcanvas.Body>
          <Cart
            carrito={carrito.map(producto => ({ ...producto, cantidad: producto.cantidadTotal }))}
            dispatch={dispatch}
            total={total}
          />
        </Offcanvas.Body>
      </Offcanvas>
    </div>
  );
};

export default ProductList;
