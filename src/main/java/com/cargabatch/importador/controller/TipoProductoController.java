package com.cargabatch.importador.controller;

import com.cargabatch.importador.DTO.TipoProductoDTO;
import com.cargabatch.importador.services.TipoProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tipo-productos")
public class TipoProductoController {

    @Autowired
    private TipoProductoService tipoProductoService;

    @PostMapping
    public ResponseEntity<TipoProductoDTO> createTipoProducto(@RequestBody TipoProductoDTO tipoProductoDTO) {
        TipoProductoDTO savedTipoProducto = tipoProductoService.saveTipoProducto(tipoProductoDTO);
        return new ResponseEntity<>(savedTipoProducto, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<TipoProductoDTO>> getAllTipoProductos() {
        List<TipoProductoDTO> tipoProductos = tipoProductoService.getAllTipoProductos();
        return new ResponseEntity<>(tipoProductos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoProductoDTO> getTipoProductoById(@PathVariable int id) {
        TipoProductoDTO tipoProducto = tipoProductoService.getTipoProductoById(id);
        return tipoProducto != null ? new ResponseEntity<>(tipoProducto, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TipoProductoDTO> updateTipoProducto(@PathVariable int id, @RequestBody TipoProductoDTO tipoProductoDTO) {
        try {
            TipoProductoDTO updatedTipoProducto = tipoProductoService.updateTipoProducto(id, tipoProductoDTO);
            return new ResponseEntity<>(updatedTipoProducto, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTipoProducto(@PathVariable int id) {
        try {
            tipoProductoService.deleteTipoProducto(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
