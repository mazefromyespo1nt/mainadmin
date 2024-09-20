package com.cargabatch.importador.repositorys;

import com.cargabatch.importador.entitys.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {

    List<Producto> findAllByStatusTrue();

    List<Producto> findByNombreAndStatusTrue(String nombre);

    Optional<Producto> findByIdProductoAndStatusTrue(Integer idProducto);

    List<Producto> findByFechaRegistroAndFechaModificacionAndStatusTrue(LocalDateTime fechaRegistro, LocalDateTime fechaModificacion);

    List<Producto> findByCodigoAndStatusTrue(String codigo);

    List<Producto> findByNombreContainingAndStatusTrue(String nombre);

}
