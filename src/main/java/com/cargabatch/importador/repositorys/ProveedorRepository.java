package com.cargabatch.importador.repositorys;

import com.cargabatch.importador.entitys.Proveedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProveedorRepository extends JpaRepository<Proveedor, Integer> {
    // Obtener solo proveedores activos
    List<Proveedor> findAllByStatusTrue();
}
