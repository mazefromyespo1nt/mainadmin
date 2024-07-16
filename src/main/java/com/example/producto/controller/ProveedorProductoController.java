package com.example.producto.controller;

import com.example.producto.entity.ProveedorProductoEntity;
import com.example.producto.service.ProveedorProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/proveedorProductos")
public class ProveedorProductoController {

    @Autowired
    private ProveedorProductoService proveedorProductoService;

    @GetMapping
    public List<ProveedorProductoEntity> getAllProveedorProductos() {
        return proveedorProductoService.getAllProveedorProductos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProveedorProductoEntity> getProveedorProductoById(@PathVariable int id) {
        return proveedorProductoService.getProveedorProductoById(id);
    }

    @PostMapping
    public ResponseEntity<ProveedorProductoEntity> createOrUpdateProveedorProducto(@RequestBody ProveedorProductoEntity proveedorProducto) {
        return proveedorProductoService.createOrUpdateProveedorProducto(proveedorProducto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProveedorProducto(@PathVariable int id) {
        return proveedorProductoService.deleteProveedorProducto(id);
    }
}
