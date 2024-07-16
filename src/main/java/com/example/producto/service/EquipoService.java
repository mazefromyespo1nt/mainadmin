package com.example.producto.service;

import com.example.producto.entity.EquipoEntity;
import com.example.producto.repository.EquipoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EquipoService {

    @Autowired
    private EquipoRepository equipoRepository;

    public List<EquipoEntity> getAllEquipos() {
        return equipoRepository.findAll();
    }

    public ResponseEntity<EquipoEntity> getEquipoById(int id) {
        Optional<EquipoEntity> equipo = equipoRepository.findById(id);
        return equipo.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    public ResponseEntity<EquipoEntity> createOrUpdateEquipo(EquipoEntity equipo) {
        return ResponseEntity.ok(equipoRepository.save(equipo));
    }

    public ResponseEntity<Void> deleteEquipo(int id) {
        if (equipoRepository.existsById(id)) {
            equipoRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
