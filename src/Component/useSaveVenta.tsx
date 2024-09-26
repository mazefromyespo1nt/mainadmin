import { useState } from 'react';

interface Venta {
  idVenta: number | null;
  cantidad: number;
  fecha: Date;
  precioVenta: number;
  total: number;
  productoId: number[];
}

export const useSaveVenta = (url: string) => {
  const [loading, setLoading] = useState<boolean>(false);
  const [error, setError] = useState<string | null>(null);

  const saveVenta = async (venta: Venta) => {
    setLoading(true);
    setError(null);

    try {
      const response = await fetch(url, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(venta),
      });

      if (!response.ok) {
        throw new Error('Error al guardar la venta');
      }
      
      // Aquí podrías manejar la respuesta si es necesario
      return await response.json(); // o simplemente `response.ok` si no necesitas la respuesta
      
    } catch (error) {
      setError('No se pudo guardar la venta.');
    } finally {
      setLoading(false);
    }
  };

  return { saveVenta, loading, error };
};
