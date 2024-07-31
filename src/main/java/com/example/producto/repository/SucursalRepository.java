package com.example.producto.repository;

import com.example.producto.entity.SucursalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SucursalRepository extends JpaRepository<SucursalEntity, Integer> {
}
