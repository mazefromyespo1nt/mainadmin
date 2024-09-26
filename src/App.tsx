import React, { useState } from 'react';
import { Routes, Route } from 'react-router-dom';
import './App.css';
import Home from './Component/Home';
import ProductForm from './Component/ProductoForm';
import ProductList from './Component/ProducList';
import Cashier from './Cashier';
import ReportesVenta from './Component/ReportesVenta';
import Compra from './Component/CompraOnline'; 
import CompraOnline from './Component/CompraOnline';

function App() {
  const [ventas, setVentas] = useState<{ fecha: Date, total: number }[]>([]);

  const registrarVenta = (venta: { fecha: Date, total: number }) => {
    setVentas((prevVentas) => [...prevVentas, venta]);
  };

  return (
    <div className="App">
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/add-product" element={<ProductForm />} />
        <Route path="/product-list" element={<ProductList />} />
        <Route path="/cashier" element={<Cashier registrarVenta={registrarVenta} />} />
        <Route path="/reportesventa" element={<ReportesVenta />} />
        <Route path="/compra" element={<CompraOnline />} /> 
      </Routes>
    </div>
  );
}

export default App;
