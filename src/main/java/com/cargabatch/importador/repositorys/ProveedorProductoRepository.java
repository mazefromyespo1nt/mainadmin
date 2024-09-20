package com.cargabatch.importador.repositorys;

import com.cargabatch.importador.entitys.ProveedorProducto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProveedorProductoRepository extends JpaRepository<ProveedorProducto, Integer> {

    /**
     * Encuentra todos los productos de proveedor por estado.
     *
     * @param status El estado del producto (1 para activo, 0 para eliminado)
     * @return Una lista de productos de proveedor con el estado especificado.
     */
    List<ProveedorProducto> findAllByStatus(int status);

    /**
     * Encuentra todos los productos de proveedor por proveedor.
     *
     * @param proveedorId El ID del proveedor.
     * @return Una lista de productos de proveedor asociados con el ID del proveedor.
     */
    List<ProveedorProducto> findAllByProveedorIdProveedor(int idProveedor);

    /**
     * Encuentra todos los productos de proveedor por tipo de producto.
     *
     * @param tipoProductoId El ID del tipo de producto.
     * @return Una lista de productos de proveedor asociados con el ID del tipo de producto.
     */
    List<ProveedorProducto> findAllByTipoProductoIdTipoProducto(int idTipoProducto);
}
