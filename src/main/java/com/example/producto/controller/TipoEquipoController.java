package com.example.producto.controller;

import com.example.producto.entity.TipoEquipoEntity;
import com.example.producto.service.TipoEquipoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tipoEquipos")
public class TipoEquipoController {

    @Autowired
    private TipoEquipoService tipoEquipoService;

    @GetMapping
    public List<TipoEquipoEntity> getAllTipoEquipos() {
        return tipoEquipoService.getAllTipoEquipos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoEquipoEntity> getTipoEquipoById(@PathVariable int id) {
        return tipoEquipoService.getTipoEquipoById(id);
    }

    @PostMapping
    public ResponseEntity<TipoEquipoEntity> createOrUpdateTipoEquipo(@RequestBody TipoEquipoEntity tipoEquipo) {
        return tipoEquipoService.createOrUpdateTipoEquipo(tipoEquipo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTipoEquipo(@PathVariable int id) {
        return tipoEquipoService.deleteTipoEquipo(id);
    }
}
