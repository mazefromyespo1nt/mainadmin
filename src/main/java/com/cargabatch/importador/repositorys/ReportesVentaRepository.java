package com.cargabatch.importador.repositorys;

import com.cargabatch.importador.entitys.ReportesVenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ReportesVentaRepository extends JpaRepository<ReportesVenta, Integer> {
    // Método para obtener reportes de ventas dentro de un rango de fechas
    List<ReportesVenta> findByFechaBetween(LocalDateTime startDate, LocalDateTime endDate);

}