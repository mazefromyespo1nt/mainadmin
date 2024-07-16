package com.example.producto.controller;

import com.example.producto.entity.ProductoEntity;
import com.example.producto.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @GetMapping
    public List<ProductoEntity> obtenerProductos() {
        return productoService.getAllProducts();
    }

    @GetMapping("/{id}")
    public ProductoEntity obtenerProductoPorId(@PathVariable int id) {
        return productoService.getProductById(id);
    }
    // comenta tus funciones

    @PostMapping
    public ProductoEntity agregarOActualizarProducto(@RequestBody ProductoEntity producto) {
        ProductoEntity existingProduct = productoService.getProductById(producto.getIdProducto());
        if (existingProduct != null) {
            return productoService.updateProduct(producto.getIdProducto(), producto);
        } else {
            return productoService.saveProduct(producto);
        }
    }

    @DeleteMapping("/{id}")
    public void eliminarProducto(@PathVariable int id) {
        productoService.deleteUser(id);
    }
}