package com.cargabatch.importador.controller;

import com.cargabatch.importador.DTO.ProductoDTO;
import com.cargabatch.importador.services.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;


    @GetMapping
    public List<ProductoDTO> obtenerProductos() {
        return productoService.getAllProducts();
    }

    @GetMapping("/{id}")
    public ProductoDTO obtenerProductoPorId(@PathVariable Integer id) {
        return productoService.getProductById(id);
    }

    @PostMapping
    public ProductoDTO agregarOActualizarProducto(@RequestBody ProductoDTO producto) {
        if (producto.getIdProducto() != null) {
            return productoService.updateProduct(producto.getIdProducto(), producto);
        } else {
            return productoService.saveProduct(producto);
        }
    }

    @DeleteMapping("/{id}")
    public void eliminarProducto(@PathVariable Integer id) {
        productoService.deleteProduct(id);
    }
}
