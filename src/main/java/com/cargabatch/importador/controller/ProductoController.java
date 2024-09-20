package com.cargabatch.importador.controller;

import com.cargabatch.importador.dto.ProductoDTO;
import com.cargabatch.importador.services.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    private final ProductoService productoService;

    @Autowired
    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @PostMapping("/add")
    public ResponseEntity<ProductoDTO> addProduct(@RequestBody ProductoDTO productoDTO) {
        ProductoDTO createdProduct = productoService.saveProduct(productoDTO);
        return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<ProductoDTO> updateProduct(@PathVariable Integer id, @RequestBody ProductoDTO productoDTO) {
        ProductoDTO updatedProduct = productoService.updateProduct(id, productoDTO);
        return updatedProduct != null ? new ResponseEntity<>(updatedProduct, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping
    public ResponseEntity<List<ProductoDTO>> getAllProducts() {
        List<ProductoDTO> productos = productoService.getAllProducts();
        return new ResponseEntity<>(productos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductoDTO> getProductById(@PathVariable Integer id) {
        ProductoDTO producto = productoService.getProductById(id);
        return producto != null ? new ResponseEntity<>(producto, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Integer id) {
        boolean isDeleted = productoService.deleteProduct(id);
        return isDeleted ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/search")
    public ResponseEntity<List<ProductoDTO>> searchProductsByName(@RequestParam("nombre") String nombre) {
        List<ProductoDTO> productos = productoService.buscarPorNombreConteniendo(nombre);
        return productos.isEmpty() ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(productos, HttpStatus.OK);
    }

    @GetMapping("/filter")
    public ResponseEntity<List<ProductoDTO>> getProductosByDates(@RequestParam LocalDateTime fechaRegistro, @RequestParam LocalDateTime fechaModificacion) {
        List<ProductoDTO> productos = productoService.findByFechaRegistroAndFechaModificacionAndStatusTrue(fechaRegistro, fechaModificacion);
        return productos.isEmpty() ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(productos, HttpStatus.OK);
    }
}
