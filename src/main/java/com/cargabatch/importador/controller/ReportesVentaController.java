package com.cargabatch.importador.controller;

import com.cargabatch.importador.dto.ReportesVentaDTO;
import com.cargabatch.importador.entitys.ReportesVenta;
import com.cargabatch.importador.services.ReportesVentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/reportes-ventas")
public class ReportesVentaController {

    @Autowired
    private ReportesVentaService reportesVentaService;
    // Crear un nuevo reporte de venta
    @PostMapping
    public ResponseEntity<ReportesVenta> createReportesVenta(@RequestBody ReportesVentaDTO dto) {
        ReportesVenta nuevoReporte = reportesVentaService.createReportesVenta(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoReporte);
    }

    // Eliminar un reporte de venta por su ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReportesVenta(@PathVariable Integer id) {
        reportesVentaService.deleteReportesVenta(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/diario")
    public List<ReportesVenta> getReportesVentasDiarias() {
        return reportesVentaService.getReportesVentasDiarias();
    }

    @GetMapping("/semanal")
    public List<ReportesVenta> getReportesVentasSemanales() {
        return reportesVentaService.getReportesVentasSemanales();
    }

    @GetMapping("/mensual")
    public List<ReportesVenta> getReportesVentasMensuales() {
        return reportesVentaService.getReportesVentasMensuales();
    }

    @GetMapping("/anual")
    public List<ReportesVenta> getReportesVentasAnuales() {
        return reportesVentaService.getReportesVentasAnuales();
    }
}
