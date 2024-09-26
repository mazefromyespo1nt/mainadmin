import { useState, useEffect } from 'react';

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

export const useFetchProductos = (url: string) => {
  const [productos, setProductos] = useState<Producto[]>([]);
  const [loading, setLoading] = useState<boolean>(true);
  const [error, setError] = useState<string | null>(null);

  useEffect(() => {
    fetch(url)
      .then(response => {
        if (!response.ok) {
          throw new Error('Network response was not ok');
        }
        return response.json();
      })
      .then((data: Producto[]) => {
        setProductos(data);
        setLoading(false);
      })
      .catch((error: Error) => {
        setError('Error al encontrar productos');
        setLoading(false);
      });
  }, [url]);

  return { productos, loading, error };
};
