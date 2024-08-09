package com.cargabatch.importador.repositorys;

import com.cargabatch.importador.entitys.Productos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<Productos, Integer> {
    // Obtener solo los productos activos
    List<Productos> findAllByStatusTrue();
}
