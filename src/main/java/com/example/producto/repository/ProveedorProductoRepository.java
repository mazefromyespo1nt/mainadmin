package com.example.producto.repository;

import com.example.producto.entity.ProveedorProductoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProveedorProductoRepository extends JpaRepository<ProveedorProductoEntity, Integer> {
}
