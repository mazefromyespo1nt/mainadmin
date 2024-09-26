package com.cargabatch.importador.services;

import com.cargabatch.importador.dto.ReportesVentaDTO;
import com.cargabatch.importador.entitys.ReportesVenta;
import com.cargabatch.importador.repositorys.ReportesVentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ReportesVentaService {

    @Autowired
    private ReportesVentaRepository reportesVentaRepository;

    // Obtener un reporte de venta por su ID
    public Optional<ReportesVenta> getReportesVentaById(Integer id) {
        return reportesVentaRepository.findById(id);
    }

    // Crear un nuevo reporte de venta
    public ReportesVenta createReportesVenta(ReportesVentaDTO dto) {
        ReportesVenta nuevoReporte = new ReportesVenta();
        // Asignar valores desde el DTO a la entidad
        nuevoReporte.setTotal(dto.getTotal());
        nuevoReporte.setFecha(dto.getFecha()); // Asegúrate de que la fecha esté en el formato correcto
        // Otros campos según el DTO
        return reportesVentaRepository.save(nuevoReporte);
    }

    // Eliminar un reporte de venta por su ID
    public void deleteReportesVenta(Integer id) {
        reportesVentaRepository.deleteById(id);
    }

    // Obtener reportes de ventas diarias
    public List<ReportesVenta> getReportesVentasDiarias() {
        LocalDateTime todayStart = LocalDateTime.now().withHour(0).withMinute(0).withSecond(0).withNano(0);
        LocalDateTime todayEnd = LocalDateTime.now().withHour(23).withMinute(59).withSecond(59).withNano(999999999);
        return reportesVentaRepository.findByFechaBetween(todayStart, todayEnd);
    }

    // Obtener reportes de ventas semanales
    public List<ReportesVenta> getReportesVentasSemanales() {
        LocalDateTime weekStart = LocalDateTime.now().minusDays(LocalDateTime.now().getDayOfWeek().getValue() - 1).withHour(0).withMinute(0).withSecond(0).withNano(0);
        LocalDateTime weekEnd = weekStart.plusDays(7).withHour(23).withMinute(59).withSecond(59).withNano(999999999);
        return reportesVentaRepository.findByFechaBetween(weekStart, weekEnd);
    }

    // Obtener reportes de ventas mensuales
    public List<ReportesVenta> getReportesVentasMensuales() {
        LocalDateTime monthStart = LocalDateTime.now().withDayOfMonth(1).withHour(0).withMinute(0).withSecond(0).withNano(0);
        LocalDateTime monthEnd = monthStart.plusMonths(1).withHour(23).withMinute(59).withSecond(59).withNano(999999999);
        return reportesVentaRepository.findByFechaBetween(monthStart, monthEnd);
    }

    // Obtener reportes de ventas anuales
    public List<ReportesVenta> getReportesVentasAnuales() {
        LocalDateTime yearStart = LocalDateTime.now().withDayOfYear(1).withHour(0).withMinute(0).withSecond(0).withNano(0);
        LocalDateTime yearEnd = yearStart.plusYears(1).withHour(23).withMinute(59).withSecond(59).withNano(999999999);
        return reportesVentaRepository.findByFechaBetween(yearStart, yearEnd);
    }
}
