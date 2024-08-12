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
    public ResponseEntity<ProveedorProductoDTO> createOrUpdateProveedorProducto(@RequestBody ProveedorProductoDTO proveedorProductoDTO) {
        try {
            ProveedorProductoDTO savedProveedorProducto = proveedorProductoService.saveProveedorProducto(proveedorProductoDTO);
            return new ResponseEntity<>(savedProveedorProducto, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
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
