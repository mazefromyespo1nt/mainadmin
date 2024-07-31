package com.example.producto.service;

import com.example.producto.dto.ProductoDTO;
import com.example.producto.entity.ProductoEntity;
import com.example.producto.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository repository;

    private ProductoEntity dtoToEntity(ProductoDTO dto) {
        ProductoEntity entity = new ProductoEntity();
        entity.setIdProducto(dto.getIdProducto());
        entity.setNombre(dto.getNombre());
        entity.setDescripcion(dto.getDescripcion());
        entity.setCodigo(dto.getCodigo());
        entity.setFechaRegistro(dto.getFechaRegistro());
        entity.setFechaModificacion(dto.getFechaModificacion());
        entity.setCantidadTotal(dto.getCantidadTotal());
        entity.setPrecioVenta(dto.getPrecioVenta());
        entity.setStatus(dto.getStatus());
        // Aquí deberías buscar y establecer los proveedores y tipoProducto usando su ID.
        return entity;
    }

    private ProductoDTO entityToDto(ProductoEntity entity) {
        ProductoDTO dto = new ProductoDTO();
        dto.setIdProducto(entity.getIdProducto());
        dto.setNombre(entity.getNombre());
        dto.setDescripcion(entity.getDescripcion());
        dto.setCodigo(entity.getCodigo());
        dto.setFechaRegistro(entity.getFechaRegistro());
        dto.setFechaModificacion(entity.getFechaModificacion());
        dto.setCantidadTotal(entity.getCantidadTotal());
        dto.setPrecioVenta(entity.getPrecioVenta());
        dto.setStatus(entity.getStatus());
        // Aquí deberías extraer los IDs de proveedor y tipoProducto.
        return dto;
    }

    public ProductoDTO saveProduct(ProductoDTO productoDTO) {
        ProductoEntity entity = dtoToEntity(productoDTO);
        ProductoEntity savedEntity = repository.save(entity);
        return entityToDto(savedEntity);
    }

    public List<ProductoDTO> getAllProducts() {
        return repository.findAll().stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }

    public ProductoDTO getProductById(Integer id) {
        Optional<ProductoEntity> optionalEntity = repository.findById(id);
        return optionalEntity.map(this::entityToDto).orElse(null);
    }

    public ProductoDTO updateProduct(Integer id, ProductoDTO productoDTO) {
        if (repository.existsById(id)) {
            ProductoEntity entity = dtoToEntity(productoDTO);
            entity.setIdProducto(id); // Asegúrate de que el ID no cambie
            ProductoEntity updatedEntity = repository.save(entity);
            return entityToDto(updatedEntity);
        } else {
            throw new RuntimeException("Producto no encontrado con id: " + id);
        }
    }

    public void deleteProduct(Integer id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new RuntimeException("Producto no encontrado con id: " + id);
        }
    }
}
