package com.example.producto.service;

import com.example.producto.entity.ProveedorEquipoEntity;
import com.example.producto.repository.ProveedorEquipoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProveedorEquipoService {

    @Autowired
    private ProveedorEquipoRepository proveedorEquipoRepository;

    public List<ProveedorEquipoEntity> getAllProveedorEquipos() {
        return proveedorEquipoRepository.findAll();
    }

    public ResponseEntity<ProveedorEquipoEntity> getProveedorEquipoById(int id) {
        Optional<ProveedorEquipoEntity> proveedorEquipo = proveedorEquipoRepository.findById(id);
        return proveedorEquipo.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    public ResponseEntity<ProveedorEquipoEntity> createOrUpdateProveedorEquipo(ProveedorEquipoEntity proveedorEquipo) {
        return ResponseEntity.ok(proveedorEquipoRepository.save(proveedorEquipo));
    }

    public ResponseEntity<Void> deleteProveedorEquipo(int id) {
        if (proveedorEquipoRepository.existsById(id)) {
            proveedorEquipoRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
