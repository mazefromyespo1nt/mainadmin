package com.cargabatch.importador.repositorys;

import com.cargabatch.importador.entitys.ProveedorProducto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProveedorProductoRepository extends JpaRepository<ProveedorProducto, Integer> {
}
