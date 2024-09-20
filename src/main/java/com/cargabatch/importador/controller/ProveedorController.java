package com.cargabatch.importador.controller;

import com.cargabatch.importador.dto.ProveedorDTO;
import com.cargabatch.importador.services.ProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/proveedores")
public class ProveedorController {

    @Autowired
    private ProveedorService proveedorService;

    @PostMapping
    public ResponseEntity<ProveedorDTO> saveOrUpdateProveedor(@RequestBody ProveedorDTO proveedorDTO) {
        try {
            ProveedorDTO savedProveedor = proveedorService.saveProveedor(proveedorDTO);
            return new ResponseEntity<>(savedProveedor, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<ProveedorDTO>> getAllProveedores() {
        List<ProveedorDTO> proveedores = proveedorService.getAllProveedores();
        return new ResponseEntity<>(proveedores, HttpStatus.OK);
    }

    @GetMapping("/page")
    public ResponseEntity<Page<ProveedorDTO>> getProveedores(Pageable pageable) {
        Page<ProveedorDTO> proveedoresPage = proveedorService.getAllProveedores(pageable);
        return new ResponseEntity<>(proveedoresPage, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProveedorDTO> getProveedorById(@PathVariable int id) {
        ProveedorDTO proveedor = proveedorService.getProveedorById(id);
        return proveedor != null ? new ResponseEntity<>(proveedor, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
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

    @GetMapping("/search")
    public ResponseEntity<List<ProveedorDTO>> searchProveedoresByName(@RequestParam String nombre) {
        try {
            List<ProveedorDTO> proveedores = proveedorService.searchProveedoresByName(nombre);
            return new ResponseEntity<>(proveedores, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/active-since")
    public ResponseEntity<List<ProveedorDTO>> getActiveProveedoresSince(@RequestParam Date fecha) {
        try {
            List<ProveedorDTO> proveedores = proveedorService.getActiveProveedoresSince(fecha);
            return new ResponseEntity<>(proveedores, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/by-tipo-producto")
    public ResponseEntity<List<ProveedorDTO>> getProveedoresByTipoProducto(@RequestParam Integer tipoProductoId) {
        try {
            List<ProveedorDTO> proveedores = proveedorService.getProveedoresByTipoProducto(tipoProductoId);
            return new ResponseEntity<>(proveedores, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/by-sucursal")
    public ResponseEntity<List<ProveedorDTO>> getProveedoresBySucursal(@RequestParam Integer sucursalId) {
        try {
            List<ProveedorDTO> proveedores = proveedorService.getProveedoresBySucursal(sucursalId);
            return new ResponseEntity<>(proveedores, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
