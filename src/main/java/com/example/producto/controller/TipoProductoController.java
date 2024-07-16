package com.example.producto.controller;

import com.example.producto.entity.TipoProductoEntity;
import com.example.producto.service.TipoProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tipoProductos")
public class TipoProductoController {

    @Autowired
    private TipoProductoService tipoProductoService;

    @GetMapping
    public List<TipoProductoEntity> getAllTipoProductos() {
        return tipoProductoService.getAllTipoProductos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoProductoEntity> getTipoProductoById(@PathVariable int id) {
        return tipoProductoService.getTipoProductoById(id);
    }

    @PostMapping
    public ResponseEntity<TipoProductoEntity> createOrUpdateTipoProducto(@RequestBody TipoProductoEntity tipoProducto) {
        return tipoProductoService.createOrUpdateTipoProducto(tipoProducto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTipoProducto(@PathVariable int id) {
        return tipoProductoService.deleteTipoProducto(id);
    }
}
