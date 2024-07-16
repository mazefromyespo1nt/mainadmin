package com.example.producto.service;

import com.example.producto.entity.SucursalEntity;
import com.example.producto.repository.SucursalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SucursalService {

    @Autowired
    private SucursalRepository sucursalRepository;

    public List<SucursalEntity> getAllSucursales() {
        return sucursalRepository.findAll();
    }

    public ResponseEntity<SucursalEntity> getSucursalById(int id) {
        Optional<SucursalEntity> sucursal = sucursalRepository.findById(id);
        return sucursal.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    public ResponseEntity<SucursalEntity> createOrUpdateSucursal(SucursalEntity sucursal) {
        return ResponseEntity.ok(sucursalRepository.save(sucursal));
    }

    public ResponseEntity<Void> deleteSucursal(int id) {
        if (sucursalRepository.existsById(id)) {
            sucursalRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
