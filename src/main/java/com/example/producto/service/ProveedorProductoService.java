package com.example.producto.service;

import com.example.producto.entity.ProveedorProductoEntity;
import com.example.producto.repository.ProveedorProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProveedorProductoService {

    @Autowired
    private ProveedorProductoRepository proveedorProductoRepository;

    public List<ProveedorProductoEntity> getAllProveedorProductos() {
        return proveedorProductoRepository.findAll();
    }

    public ResponseEntity<ProveedorProductoEntity> getProveedorProductoById(int id) {
        Optional<ProveedorProductoEntity> proveedorProducto = proveedorProductoRepository.findById(id);
        return proveedorProducto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    public ResponseEntity<ProveedorProductoEntity> createOrUpdateProveedorProducto(ProveedorProductoEntity proveedorProducto) {
        return ResponseEntity.ok(proveedorProductoRepository.save(proveedorProducto));
    }

    public ResponseEntity<Void> deleteProveedorProducto(int id) {
        if (proveedorProductoRepository.existsById(id)) {
            proveedorProductoRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
