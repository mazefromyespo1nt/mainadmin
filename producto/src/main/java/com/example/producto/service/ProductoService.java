package com.example.producto.service;

import com.example.producto.entity.ProductoEntity;
import com.example.producto.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    public List<ProductoEntity> obtenerTodosLosProductos() {
        return productoRepository.findAll();
    }

    public ProductoEntity obtenerProductoPorId(int id) {
        Optional<ProductoEntity> producto = productoRepository.findById(id);
        if (producto.isPresent()) {
            return producto.get();
        } else {
            throw new RuntimeException("Producto no encontrado");
        }
    }

    public ProductoEntity agregarProducto(ProductoEntity nuevoProducto) {
        return productoRepository.save(nuevoProducto);
    }

    public ProductoEntity actualizarProducto(int id, ProductoEntity productoActualizado) {
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
            throw new RuntimeException("Producto no encontrado");
        }
    }

    public void eliminarProducto(int id) {
        productoRepository.deleteById(id);
    }
}
