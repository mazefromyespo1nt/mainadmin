package com.cargabatch.importador.repositorys;

import com.cargabatch.importador.entitys.TipoProducto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface TipoProductoRepository extends JpaRepository<TipoProducto, Integer> {

    // Encontrar todos los TipoProducto por estado
    List<TipoProducto> findAllByStatus(int status); // 1 for active, 0 for deleted

    // Encontrar TipoProducto por nombre
    List<TipoProducto> findByNombreContainingIgnoreCaseAndStatus(String nombre, int status);

    // Encontrar TipoProducto por fecha de registro
    List<TipoProducto> findByFechaRegistroBetweenAndStatus(Date startDate, Date endDate, int status);

    // Contar TipoProducto por estado
    long countByStatus(int status);

    // Encontrar el TipoProducto más reciente por fecha de registro
    @Query("SELECT t FROM TipoProducto t ORDER BY t.fechaRegistro DESC")
    TipoProducto findTopByOrderByFechaRegistroDesc();

    // Actualizar estado de un TipoProducto
    @Modifying
    @Query("UPDATE TipoProducto t SET t.status = :status WHERE t.idTipoProducto = :id")
    void updateStatus(@Param("id") int id, @Param("status") int status);

    // Eliminar TipoProducto por ID (marcar como eliminado)
    @Modifying
    @Query("UPDATE TipoProducto t SET t.status = 0 WHERE t.idTipoProducto = :id")
    void softDeleteById(@Param("id") int id);
}
