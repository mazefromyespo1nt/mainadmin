package com.cargabatch.importador.repositorys;

import com.cargabatch.importador.entitys.Proveedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ProveedorRepository extends JpaRepository<Proveedor, Integer> {

    // Obtener solo proveedores activos
    List<Proveedor> findAllByStatusTrue();

    // Buscar proveedores por nombre (coincidencias parciales)
    List<Proveedor> findByNombreContainingIgnoreCase(String nombre);

    // Buscar proveedores activos por tipo de producto
    List<Proveedor> findByStatusTrueAndTipoProductoId(Integer tipoProductoId);

    // Buscar proveedores activos por sucursal
    List<Proveedor> findByStatusTrueAndSucursalId(Integer sucursalId);

    // Consulta personalizada para buscar proveedores activos desde una fecha específica
    @Query("SELECT p FROM Proveedor p WHERE p.status = true AND p.fechaRegistro >= :fecha")
    List<Proveedor> findActiveProveedoresSince(@Param("fecha") Date fecha);

    // Obtener proveedores activos con paginación
    Page<Proveedor> findAllByStatusTrue(Pageable pageable);

    // Obtener proveedores activos ordenados por fecha de registro ascendente
    List<Proveedor> findAllByStatusTrueOrderByFechaRegistroAsc();
}
