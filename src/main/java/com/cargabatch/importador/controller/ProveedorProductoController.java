package com.cargabatch.importador.controller;

import com.cargabatch.importador.DTO.ProveedorProductoDTO;
import com.cargabatch.importador.services.ProveedorProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/proveedores-productos")
public class ProveedorProductoController {

    @Autowired
    private ProveedorProductoService proveedorProductoService;

    @PostMapping
    public ResponseEntity<ProveedorProductoDTO> createProveedorProducto(@RequestBody ProveedorProductoDTO proveedorProductoDTO) {
        ProveedorProductoDTO savedProveedorProducto = proveedorProductoService.saveProveedorProducto(proveedorProductoDTO);
        return new ResponseEntity<>(savedProveedorProducto, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ProveedorProductoDTO>> getAllProveedorProductos() {
        List<ProveedorProductoDTO> proveedoresProductos = proveedorProductoService.getAllProveedorProductos();
        return new ResponseEntity<>(proveedoresProductos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProveedorProductoDTO> getProveedorProductoById(@PathVariable int id) {
        ProveedorProductoDTO proveedorProducto = proveedorProductoService.getProveedorProductoById(id);
        return proveedorProducto != null ? new ResponseEntity<>(proveedorProducto, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProveedorProductoDTO> updateProveedorProducto(@PathVariable int id, @RequestBody ProveedorProductoDTO proveedorProductoDTO) {
        try {
            ProveedorProductoDTO updatedProveedorProducto = proveedorProductoService.updateProveedorProducto(id, proveedorProductoDTO);
            return new ResponseEntity<>(updatedProveedorProducto, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProveedorProducto(@PathVariable int id) {
        try {
            proveedorProductoService.deleteProveedorProducto(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
