package com.cargabatch.importador.repositorys;

import com.cargabatch.importador.entitys.Productos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductoRepository extends JpaRepository<Productos, Integer> {

    // Obtener todos los productos que están activos
    List<Productos> findAllByStatusTrue();

    // Buscar productos por nombre y estado activo
    List<Productos> findByNombreAndStatusTrue(String nombre);

    // Buscar un producto por ID y estado activo
    Optional<Productos> findByIdAndStatusTrue(Integer id);
}
