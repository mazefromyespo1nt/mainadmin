package com.cargabatch.importador.controller;

import com.cargabatch.importador.DTO.ProductoDTO;
import com.cargabatch.importador.services.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @GetMapping
    public ResponseEntity<List<ProductoDTO>> obtenerProductos() {
        List<ProductoDTO> productos = productoService.getAllProducts();
        return ResponseEntity.ok(productos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductoDTO> obtenerProductoPorId(@PathVariable Integer id) {
        ProductoDTO productoDTO = productoService.getProductById(id);
        if (productoDTO != null) {
            return ResponseEntity.ok(productoDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<String> agregarOActualizarProducto(@RequestBody ProductoDTO productoDTO) {
        if (productoDTO == null) {
            return ResponseEntity.badRequest().body("El DTO del producto no puede ser nulo");
        }
        ProductoDTO savedProduct = productoService.saveProduct(productoDTO);
        return ResponseEntity.ok("Producto guardado con éxito con ID: " + savedProduct.getIdProducto());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarProducto(@PathVariable Integer id) {
        try {
            productoService.deleteProduct(id);
            return ResponseEntity.ok("Producto eliminado con éxito");
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }
}
