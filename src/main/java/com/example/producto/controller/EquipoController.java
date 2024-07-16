package com.example.producto.controller;

import com.example.producto.entity.EquipoEntity;
import com.example.producto.service.EquipoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/equipos")
public class EquipoController {

    @Autowired
    private EquipoService equipoService;

    @GetMapping
    public List<EquipoEntity> getAllEquipos() {
        return equipoService.getAllEquipos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EquipoEntity> getEquipoById(@PathVariable int id) {
        return equipoService.getEquipoById(id);
    }

    @PostMapping
    public ResponseEntity<EquipoEntity> createOrUpdateEquipo(@RequestBody EquipoEntity equipo) {
        return equipoService.createOrUpdateEquipo(equipo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEquipo(@PathVariable int id) {
        return equipoService.deleteEquipo(id);
    }
}
