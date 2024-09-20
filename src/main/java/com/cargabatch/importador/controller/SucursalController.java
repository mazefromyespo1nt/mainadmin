package com.cargabatch.importador.controller;

import com.cargabatch.importador.dto.SucursalDTO;
import com.cargabatch.importador.services.SucursalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sucursales")
public class SucursalController {

    @Autowired
    private SucursalService sucursalService;

    @PostMapping
    public ResponseEntity<SucursalDTO> createSucursal(@RequestBody SucursalDTO sucursalDTO) {
        SucursalDTO savedSucursal = sucursalService.saveSucursal(sucursalDTO);
        return new ResponseEntity<>(savedSucursal, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<SucursalDTO>> getAllSucursales() {
        List<SucursalDTO> sucursales = sucursalService.getAllSucursales();
        return new ResponseEntity<>(sucursales, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SucursalDTO> getSucursalById(@PathVariable int id) {
        SucursalDTO sucursal = sucursalService.getSucursalById(id);
        return sucursal != null ? new ResponseEntity<>(sucursal, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SucursalDTO> updateSucursal(@PathVariable int id, @RequestBody SucursalDTO sucursalDTO) {
        try {
            SucursalDTO updatedSucursal = sucursalService.updateSucursal(id, sucursalDTO);
            return new ResponseEntity<>(updatedSucursal, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSucursal(@PathVariable int id) {
        try {
            sucursalService.deleteSucursal(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
