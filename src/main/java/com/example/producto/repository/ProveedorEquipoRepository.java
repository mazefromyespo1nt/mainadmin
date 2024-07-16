package com.example.producto.repository;

import com.example.producto.entity.ProveedorEquipoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProveedorEquipoRepository extends JpaRepository<ProveedorEquipoEntity, Integer> {
}
