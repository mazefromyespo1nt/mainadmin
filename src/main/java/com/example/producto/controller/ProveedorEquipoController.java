package com.example.producto.controller;

import com.example.producto.entity.ProveedorEquipoEntity;
import com.example.producto.service.ProveedorEquipoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/proveedorEquipos")
public class ProveedorEquipoController {

    @Autowired
    private ProveedorEquipoService proveedorEquipoService;

    @GetMapping
    public List<ProveedorEquipoEntity> getAllProveedorEquipos() {
        return proveedorEquipoService.getAllProveedorEquipos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProveedorEquipoEntity> getProveedorEquipoById(@PathVariable int id) {
        return proveedorEquipoService.getProveedorEquipoById(id);
    }

    @PostMapping
    public ResponseEntity<ProveedorEquipoEntity> createOrUpdateProveedorEquipo(@RequestBody ProveedorEquipoEntity proveedorEquipo) {
        return proveedorEquipoService.createOrUpdateProveedorEquipo(proveedorEquipo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProveedorEquipo(@PathVariable int id) {
        return proveedorEquipoService.deleteProveedorEquipo(id);
    }
}
