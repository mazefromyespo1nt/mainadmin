package com.cargabatch.importador.services;

import com.cargabatch.importador.dto.ReportesVentaDTO;
import com.cargabatch.importador.entitys.ReportesVenta;
import com.cargabatch.importador.repositorys.ReportesVentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReportesVentaService {

    @Autowired
    private ReportesVentaRepository reportesVentaRepository;

    public List<ReportesVenta> getAllReportesVentas() {
        return reportesVentaRepository.findAll();
    }

    public Optional<ReportesVenta> getReportesVentaById(Integer id) {
        return reportesVentaRepository.findById(id);
    }

    public ReportesVenta createReportesVenta(ReportesVentaDTO dto) {
        ReportesVenta reportesVenta = new ReportesVenta();
        // Mapear dto a entity
        // ...
        return reportesVentaRepository.save(reportesVenta);
    }

    public void deleteReportesVenta(Integer id) {
        reportesVentaRepository.deleteById(id);
    }
}
