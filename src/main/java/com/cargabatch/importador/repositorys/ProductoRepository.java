package com.cargabatch.importador.repositorys;


import com.cargabatch.importador.entitys.Productos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends JpaRepository<Productos, Integer> {
    // comenta tus funciones
}
