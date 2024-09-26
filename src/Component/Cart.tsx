import React, { useState } from 'react';
import { Icon } from '@iconify/react';
import { Button, ListGroup, Modal } from 'react-bootstrap';
import { useNavigate } from 'react-router-dom';

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

interface CartProps {
  carrito: Producto[];
  dispatch: React.Dispatch<any>;
  total: number;
}

const Cart: React.FC<CartProps> = ({ carrito, dispatch, total }) => {
  const [productoAEliminar, setProductoAEliminar] = useState<Producto | null>(null);
  const navigate = useNavigate();

  const formatoMoneda = (precio: number | null) => {
    return precio !== null
      ? `$${precio.toLocaleString('es-ES', { minimumFractionDigits: 2, maximumFractionDigits: 2 })}`
      : 'N/A';
  };

  const handleClose = () => setProductoAEliminar(null);

  const handleConfirmEliminar = () => {
    if (productoAEliminar) {
      dispatch({ type: '[CARRITO] Eliminar Compra', payload: productoAEliminar.idProducto });
      setProductoAEliminar(null);
    }
  };

  const handleCompra = () => {
    // Almacenar el carrito y el total en el local storage
    localStorage.setItem('carrito', JSON.stringify(carrito));
    localStorage.setItem('total', JSON.stringify(total));
    navigate('/compra'); // Redirige a la página de compra
  };
  

  return (
    <div>
      <h2>Carrito de Compras</h2>
      {carrito.length > 0 ? (
        <ListGroup>
          {carrito.map(producto => (
            <ListGroup.Item key={producto.idProducto}>
              <div>
                <h3>{producto.nombre}</h3>
                <p>{formatoMoneda(producto.precioVenta)}</p>
                <p>Cantidad: {producto.cantidadTotal}</p>

                <Button
                  variant="success"
                  onClick={() => dispatch({ type: '[CARRITO] Aumentar Cantidad Compra', payload: producto.idProducto })}
                >
                  Aumentar
                  <Icon icon="material-symbols:masked-transitions-add" style={{ fontSize: '24px', marginRight: '5px' }} />
                </Button>
                <Button
                  variant="warning"
                  onClick={() => dispatch({ type: '[CARRITO] Disminuir Cantidad Compra', payload: producto.idProducto })}
                >
                  Disminuir
                  <Icon icon="iconamoon:shopping-card-remove" style={{ fontSize: '24px', marginRight: '5px' }} />
                </Button>
                <Button
                  variant="danger"
                  onClick={() => setProductoAEliminar(producto)}
                >
                  Eliminar
                  <Icon icon="pepicons-print:trash-circle" style={{ fontSize: '24px', marginLeft: '5px' }} />
                </Button>
              </div>
            </ListGroup.Item>
          ))}
        </ListGroup>
      ) : (
        <p>El carrito está vacío.</p>
      )}

      <h4>Total: {formatoMoneda(total)}</h4>

      <Button variant="primary" onClick={handleCompra}>
        Proceder con la compra
        <Icon icon="mdi:cart-check" style={{ fontSize: '24px', marginLeft: '5px' }} />
      </Button>

      <Modal show={!!productoAEliminar} onHide={handleClose}>
        <Modal.Header closeButton>
          <Modal.Title>Confirmar Eliminación</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          ¿Estás seguro de que deseas eliminar {productoAEliminar?.nombre} del carrito?
        </Modal.Body>
        <Modal.Footer>
          <Button variant="secondary" onClick={handleClose}>
            Cancelar
          </Button>
          <Button variant="danger" onClick={handleConfirmEliminar}>
            Eliminar
          </Button>
        </Modal.Footer>
      </Modal>
    </div>
  );
};

export default Cart;
