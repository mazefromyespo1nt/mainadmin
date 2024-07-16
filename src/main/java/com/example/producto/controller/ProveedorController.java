package com.example.producto.controller;

import com.example.producto.entity.ProveedorEntity;
import com.example.producto.service.ProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/proveedores")
public class ProveedorController {

    @Autowired
    private ProveedorService proveedorService;

    @GetMapping
    public List<ProveedorEntity> getAllProveedores() {
        return proveedorService.getAllProveedores();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProveedorEntity> getProveedorById(@PathVariable int id) {
        return proveedorService.getProveedorById(id);
    }

    @PostMapping
    public ResponseEntity<ProveedorEntity> createOrUpdateProveedor(@RequestBody ProveedorEntity proveedor) {
        return proveedorService.createOrUpdateProveedor(proveedor);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProveedor(@PathVariable int id) {
        return proveedorService.deleteProveedor(id);
    }
}
