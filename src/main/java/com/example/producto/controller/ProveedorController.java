package com.example.producto.controller;

import com.example.producto.dto.ProveedorDTO;
import com.example.producto.service.ProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/proveedores")
public class ProveedorController {

    @Autowired
    private ProveedorService proveedorService;

    @PostMapping
    public ResponseEntity<ProveedorDTO> createProveedor(@RequestBody ProveedorDTO proveedorDTO) {
        ProveedorDTO savedProveedor = proveedorService.saveProveedor(proveedorDTO);
        return new ResponseEntity<>(savedProveedor, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ProveedorDTO>> getAllProveedores() {
        List<ProveedorDTO> proveedores = proveedorService.getAllProveedores();
        return new ResponseEntity<>(proveedores, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProveedorDTO> getProveedorById(@PathVariable int id) {
        ProveedorDTO proveedor = proveedorService.getProveedorById(id);
        return proveedor != null ? new ResponseEntity<>(proveedor, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProveedorDTO> updateProveedor(@PathVariable int id, @RequestBody ProveedorDTO proveedorDTO) {
        try {
            ProveedorDTO updatedProveedor = proveedorService.updateProveedor(id, proveedorDTO);
            return new ResponseEntity<>(updatedProveedor, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProveedor(@PathVariable int id) {
        try {
            proveedorService.deleteProveedor(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
