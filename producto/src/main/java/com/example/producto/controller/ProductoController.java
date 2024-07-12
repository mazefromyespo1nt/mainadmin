package com.example.producto.controller;

import com.example.producto.entity.ProductoEntity;
import com.example.producto.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    @Autowired
    private ProductoRepository productoRepository;

    @GetMapping
    public List<ProductoEntity> obtenerProductos() {
        return productoRepository.findAll();
    }

    @GetMapping("/{id}")
    public ProductoEntity obtenerProductoPorId(@PathVariable int id) {
        Optional<ProductoEntity> producto = productoRepository.findById(id);
        if (producto.isPresent()) {
            return producto.get();
        } else {
            throw new RuntimeException("Producto no encontrado");
        }
    }

    @PostMapping("/{id}")
    public ProductoEntity agregarOActualizarProducto(@PathVariable int id, @RequestBody ProductoEntity productoActualizado) {
        Optional<ProductoEntity> optionalProducto = productoRepository.findById(id);
        if (optionalProducto.isPresent()) {
            ProductoEntity producto = optionalProducto.get();
            producto.setNombre(productoActualizado.getNombre());
            producto.setDescripcion(productoActualizado.getDescripcion());
            producto.setCodigo(productoActualizado.getCodigo());
            producto.setUsuarioId(productoActualizado.getUsuarioId());
            producto.setFechaRegistro(productoActualizado.getFechaRegistro());
            producto.setFechaModificacion(productoActualizado.getFechaModificacion());
            producto.setCantidadTotal(productoActualizado.getCantidadTotal());
            producto.setPrecioVenta(productoActualizado.getPrecioVenta());
            producto.setProveedorId(productoActualizado.getProveedorId());
            producto.setTipoProductoId(productoActualizado.getTipoProductoId());
            producto.setStatus(productoActualizado.isStatus());

            return productoRepository.save(producto);
        } else {
            // Si no existe el producto con el ID proporcionado, se crea uno nuevo con ese ID
            productoActualizado.setIdProducto(id);
            return productoRepository.save(productoActualizado);
        }
    }


    @DeleteMapping("/{id}")
    public void eliminarProducto(@PathVariable int id) {
        productoRepository.deleteById(id);
    }
}
