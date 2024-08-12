package com.cargabatch.importador.controller;

import com.cargabatch.importador.DTO.TipoProductoDTO;
import com.cargabatch.importador.services.TipoProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/tipo-productos")
public class TipoProductoController {

    @Autowired
    private TipoProductoService tipoProductoService;

    @PostMapping
    public ResponseEntity<TipoProductoDTO> createOrUpdateTipoProducto(@RequestBody TipoProductoDTO tipoProductoDTO) {
        // Guardar o actualizar un TipoProducto y devolver el DTO del producto guardado o actualizado
        TipoProductoDTO savedOrUpdatedTipoProducto = tipoProductoService.saveOrUpdateTipoProducto(tipoProductoDTO);
        return new ResponseEntity<>(savedOrUpdatedTipoProducto, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<TipoProductoDTO>> getAllTipoProductos() {
        // Obtener todos los TipoProducto activos
        List<TipoProductoDTO> tipoProductos = tipoProductoService.getAllTipoProductos();
        return new ResponseEntity<>(tipoProductos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoProductoDTO> getTipoProductoById(@PathVariable int id) {
        // Obtener un TipoProducto por ID
        TipoProductoDTO tipoProducto = tipoProductoService.getTipoProductoById(id);
        return tipoProducto != null ? new ResponseEntity<>(tipoProducto, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTipoProducto(@PathVariable int id) {
        try {
            // Eliminar un TipoProducto (marcar como eliminado)
            tipoProductoService.deleteTipoProducto(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/nombre")
    public ResponseEntity<List<TipoProductoDTO>> getTipoProductosByNombre(@RequestParam String nombre) {
        // Buscar TipoProducto por nombre
        List<TipoProductoDTO> tipoProductos = tipoProductoService.getTipoProductosByNombre(nombre);
        return new ResponseEntity<>(tipoProductos, HttpStatus.OK);
    }

    @GetMapping("/fechas")
    public ResponseEntity<List<TipoProductoDTO>> getTipoProductosByFechaRegistro(
            @RequestParam("start") Date startDate,
            @RequestParam("end") Date endDate) {
        // Buscar TipoProducto por rango de fechas de registro
        List<TipoProductoDTO> tipoProductos = tipoProductoService.getTipoProductosByFechaRegistro(startDate, endDate);
        return new ResponseEntity<>(tipoProductos, HttpStatus.OK);
    }

    @GetMapping("/count")
    public ResponseEntity<Long> countTipoProductosByStatus(@RequestParam int status) {
        // Contar TipoProducto por estado
        long count = tipoProductoService.countTipoProductosByStatus(status);
        return new ResponseEntity<>(count, HttpStatus.OK);
    }

    @GetMapping("/most-recent")
    public ResponseEntity<TipoProductoDTO> getMostRecentTipoProducto() {
        // Obtener el TipoProducto más reciente
        TipoProductoDTO tipoProducto = tipoProductoService.getMostRecentTipoProducto();
        return tipoProducto != null ? new ResponseEntity<>(tipoProducto, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
