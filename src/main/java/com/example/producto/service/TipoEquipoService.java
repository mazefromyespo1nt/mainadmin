package com.example.producto.service;

import com.example.producto.entity.TipoEquipoEntity;
import com.example.producto.repository.TipoEquipoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TipoEquipoService {

    @Autowired
    private TipoEquipoRepository tipoEquipoRepository;

    public List<TipoEquipoEntity> getAllTipoEquipos() {
        return tipoEquipoRepository.findAll();
    }

    public ResponseEntity<TipoEquipoEntity> getTipoEquipoById(int id) {
        Optional<TipoEquipoEntity> tipoEquipo = tipoEquipoRepository.findById(id);
        return tipoEquipo.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    public ResponseEntity<TipoEquipoEntity> createOrUpdateTipoEquipo(TipoEquipoEntity tipoEquipo) {
        return ResponseEntity.ok(tipoEquipoRepository.save(tipoEquipo));
    }

    public ResponseEntity<Void> deleteTipoEquipo(int id) {
        if (tipoEquipoRepository.existsById(id)) {
            tipoEquipoRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
