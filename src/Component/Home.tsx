import React from 'react';
import { Link } from 'react-router-dom';

const Home: React.FC = () => {
  return (
    <div className="container mt-4">
      <h1>Bienvenido a la Gestión de Productos</h1>
      <div className="mt-4">
        <Link to="/add-product" className="btn btn-primary me-2">Agregar Producto</Link>
        <Link to="/product-list" className="btn btn-primary me-2">Ver Productos</Link>
        <Link to="/Cashier" className='btn btn-primary me-2'>venta</Link>
        <Link to="/ReportesVenta" className='btn btn-primary me-2'>Reportes</Link>
      </div>
    </div>
  );
};

export default Home;
