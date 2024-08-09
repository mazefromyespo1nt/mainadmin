package com.cargabatch.importador.repositorys;

import com.cargabatch.importador.entitys.TipoProducto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TipoProductoRepository extends JpaRepository<TipoProducto, Integer> {
    List<TipoProducto> findAllByStatus(int status); // 1 for active, 0 for deleted
}
