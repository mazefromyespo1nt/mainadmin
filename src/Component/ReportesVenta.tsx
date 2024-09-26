import React, { useState, useEffect } from 'react';
import { LineChart, Line, CartesianGrid, XAxis, YAxis, Tooltip, Legend, ResponsiveContainer } from 'recharts';
import { Row, Col } from 'react-bootstrap'; // Usamos react-bootstrap para la cuadrícula

interface Venta {
  idVenta: number;
  fecha: string;
  cantidad: number;
  precioVenta: number;
  total: number;
}

const ReportesVenta: React.FC = () => {
  const [ventasDiarias, setVentasDiarias] = useState<Venta[]>([]);
  const [ventasSemanales, setVentasSemanales] = useState<Venta[]>([]);
  const [ventasMensuales, setVentasMensuales] = useState<Venta[]>([]);
  const [ventasAnuales, setVentasAnuales] = useState<Venta[]>([]);
  const [loading, setLoading] = useState<boolean>(true);
  const [error, setError] = useState<string | null>(null);

  useEffect(() => {
    const loadVentas = async () => {
      setLoading(true);
      try {
        await fetchVentasPorPeriodo('diario');
        await fetchVentasPorPeriodo('semanal');
        await fetchVentasPorPeriodo('mensual');
        await fetchVentasPorPeriodo('anual');
      } catch (error) {
        if (error instanceof Error) {
          setError(error.message);
        } else {
          setError('Error desconocido');
        }
      } finally {
        setLoading(false);
      }
    };

    loadVentas();
  }, []);

  const fetchVentasPorPeriodo = async (periodo: string) => {
    try {
      const response = await fetch(`http://localhost:8080/api/reportes-ventas/${periodo}`);
      if (!response.ok) {
        throw new Error('Error al obtener las ventas ' + periodo);
      }
      const ventas = await response.json();

      const ventasConTotal = ventas.map((venta: Venta) => ({
        ...venta,
        total: venta.cantidad * venta.precioVenta,
      }));

      if (periodo === 'diario') {
        setVentasDiarias(ventasConTotal);
      } else if (periodo === 'semanal') {
        setVentasSemanales(ventasConTotal);
      } else if (periodo === 'mensual') {
        setVentasMensuales(ventasConTotal);
      } else if (periodo === 'anual') {
        setVentasAnuales(ventasConTotal);
      }
    } catch (error) {
      if (error instanceof Error) {
        setError(error.message);
      } else {
        setError('Error desconocido');
      }
    } finally {
      setLoading(false);
    }
  };

  if (loading) {
    return <p>Cargando reportes...</p>;
  }

  if (error) {
    return <p>{error}</p>;
  }

  const renderChart = (data: Venta[], color: string) => (
    <ResponsiveContainer width="100%" height={300}>
      <LineChart data={data}>
        <CartesianGrid strokeDasharray="5 5" stroke="#ccc" />
        <XAxis dataKey="fecha" />
        <YAxis />
        <Tooltip contentStyle={{ backgroundColor: '#f5f5f5', border: 'none', borderRadius: '8px' }} />
        <Legend wrapperStyle={{ top: 0 }} />
        <Line type="monotone" dataKey="total" stroke={color} strokeWidth={3} />
      </LineChart>
    </ResponsiveContainer>
  );

  return (
    <div style={{ padding: '20px' }}>
      <h2>Reportes de Ventas</h2>
      <Row>
        <Col md={6}>
          <h4>Ventas Diarias</h4>
          {renderChart(ventasDiarias, "#8884d8")}
        </Col>
        <Col md={6}>
          <h4>Ventas Semanales</h4>
          {renderChart(ventasSemanales, "#82ca9d")}
        </Col>
      </Row>

      <Row style={{ marginTop: '20px' }}>
        <Col md={6}>
          <h4>Ventas Mensuales</h4>
          {renderChart(ventasMensuales, "#cc1117")}
        </Col>
        <Col md={6}>
          <h4>Ventas Anuales</h4>
          {renderChart(ventasAnuales, "#ff7300")}
        </Col>
      </Row>
    </div>
  );
};

export default ReportesVenta;
