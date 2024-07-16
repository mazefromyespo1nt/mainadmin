package com.example.producto.controller;

import com.example.producto.entity.SucursalEntity;
import com.example.producto.service.SucursalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sucursales")
public class SucursalController {

    @Autowired
    private SucursalService sucursalService;

    @GetMapping
    public List<SucursalEntity> getAllSucursales() {
        return sucursalService.getAllSucursales();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SucursalEntity> getSucursalById(@PathVariable int id) {
        return sucursalService.getSucursalById(id);
    }

    @PostMapping
    public ResponseEntity<SucursalEntity> createOrUpdateSucursal(@RequestBody SucursalEntity sucursal) {
        return sucursalService.createOrUpdateSucursal(sucursal);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSucursal(@PathVariable int id) {
        return sucursalService.deleteSucursal(id);
    }
}
