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
    private ProductoRepository repository;

    public ProductoEntity saveProduct(ProductoEntity producto) {
        return repository.save(producto);
    }

    public List<ProductoEntity> getAllProducts() {
        return repository.findAll();
    }

    public ProductoEntity getProductById(Integer id) {
        Optional<ProductoEntity> producto = repository.findById(id);
        return producto.orElse(null);
    }

    public ProductoEntity updateProduct(Integer id, ProductoEntity productoActualizado) {
        Optional<ProductoEntity> optionalProducto = repository.findById(id);
        if (optionalProducto.isPresent()) {
            ProductoEntity producto = optionalProducto.get();

            producto.setNombre(productoActualizado.getNombre());
            producto.setDescripcion(productoActualizado.getDescripcion());
            producto.setCodigo(productoActualizado.getCodigo());
            //producto.setUsuario(productoActualizado.getUsuario());
            producto.setFechaRegistro(productoActualizado.getFechaRegistro());
            producto.setFechaModificacion(productoActualizado.getFechaModificacion());
            producto.setCantidadTotal(productoActualizado.getCantidadTotal());
            producto.setPrecioVenta(productoActualizado.getPrecioVenta());
            producto.setProveedor(productoActualizado.getProveedor());
            //producto.setTipoProducto(productoActualizado.getTipoProducto());
            producto.setStatus(productoActualizado.isStatus());
            // comenta tus funciones

            return repository.save(producto);
        } else {
            throw new RuntimeException("Producto no encontrado con id: " + id);
        }
    }

    public void deleteUser(Integer id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new RuntimeException("Producto no encontrado con id: " + id);
        }
    }
}