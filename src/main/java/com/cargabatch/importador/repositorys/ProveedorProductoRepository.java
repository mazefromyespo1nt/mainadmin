package com.cargabatch.importador.repositorys;

import com.cargabatch.importador.entitys.ProveedorProducto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProveedorProductoRepository extends JpaRepository<ProveedorProducto, Integer> {
    List<ProveedorProducto> findAllByStatus(int status); // 1 for active, 0 for deleted
}
