package com.example.producto.service;

import com.example.producto.entity.TipoProductoEntity;
import com.example.producto.repository.TipoProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TipoProductoService {

    @Autowired
    private TipoProductoRepository tipoProductoRepository;

    public List<TipoProductoEntity> getAllTipoProductos() {
        return tipoProductoRepository.findAll();
    }

    public ResponseEntity<TipoProductoEntity> getTipoProductoById(int id) {
        Optional<TipoProductoEntity> tipoProducto = tipoProductoRepository.findById(id);
        return tipoProducto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    public ResponseEntity<TipoProductoEntity> createOrUpdateTipoProducto(TipoProductoEntity tipoProducto) {
        return ResponseEntity.ok(tipoProductoRepository.save(tipoProducto));
    }

    public ResponseEntity<Void> deleteTipoProducto(int id) {
        if (tipoProductoRepository.existsById(id)) {
            tipoProductoRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
