package com.example.producto.repository;


import com.example.producto.entity.ProductoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends JpaRepository<ProductoEntity, Integer> {
    // comenta tus funciones
}
