package com.cargabatch.importador.repositorys;

import com.cargabatch.importador.entitys.ReportesVenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportesVentaRepository extends JpaRepository<ReportesVenta, Integer> {
    // Métodos de consulta personalizados, si es necesario
}
