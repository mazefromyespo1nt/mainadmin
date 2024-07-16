package com.example.producto.service;

import com.example.producto.entity.ProveedorEntity;
import com.example.producto.repository.ProveedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProveedorService {

    @Autowired
    private ProveedorRepository proveedorRepository;

    public List<ProveedorEntity> getAllProveedores() {
        return proveedorRepository.findAll();
    }

    public ResponseEntity<ProveedorEntity> getProveedorById(int id) {
        Optional<ProveedorEntity> proveedor = proveedorRepository.findById(id);
        return proveedor.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    public ResponseEntity<ProveedorEntity> createOrUpdateProveedor(ProveedorEntity proveedor) {
        return ResponseEntity.ok(proveedorRepository.save(proveedor));
    }

    public ResponseEntity<Void> deleteProveedor(int id) {
        if (proveedorRepository.existsById(id)) {
            proveedorRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
