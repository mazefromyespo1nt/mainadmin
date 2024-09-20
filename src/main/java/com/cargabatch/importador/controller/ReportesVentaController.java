package com.cargabatch.importador.controller;

import com.cargabatch.importador.dto.ReportesVentaDTO;
import com.cargabatch.importador.entitys.ReportesVenta;
import com.cargabatch.importador.services.ReportesVentaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/reportes-ventas")
public class ReportesVentaController {

    @Autowired
    private ReportesVentaService reportesVentaService;

    @GetMapping
    public List<ReportesVenta> getAllReportesVentas() {
        return reportesVentaService.getAllReportesVentas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReportesVenta> getReportesVentaById(@PathVariable Integer id) {
        Optional<ReportesVenta> reportesVenta = reportesVentaService.getReportesVentaById(id);
        return reportesVenta.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ReportesVenta> createReportesVenta(@Valid @RequestBody ReportesVentaDTO dto) {
        ReportesVenta created = reportesVentaService.createReportesVenta(dto);
        return ResponseEntity.ok(created);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReportesVenta(@PathVariable Integer id) {
        reportesVentaService.deleteReportesVenta(id);
        return ResponseEntity.noContent().build();
    }
}
