package com.example.producto.repository;

import com.example.producto.entity.TipoEquipoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoEquipoRepository extends JpaRepository<TipoEquipoEntity, Integer> {
}
