package com.cargabatch.importador.services;

import com.cargabatch.importador.DTO.ProductoDTO;
import com.cargabatch.importador.entitys.Productos;
import com.cargabatch.importador.entitys.Proveedor;
import com.cargabatch.importador.entitys.TipoProducto;
import com.cargabatch.importador.repositorys.ProductoRepository;
import com.cargabatch.importador.repositorys.ProveedorRepository;
import com.cargabatch.importador.repositorys.TipoProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository repository;

    @Autowired
    private ProveedorRepository proveedorRepository;

    @Autowired
    private TipoProductoRepository tipoProductoRepository;

    private Productos dtoToEntity(ProductoDTO dto) {
        if (dto == null) {
            throw new IllegalArgumentException("DTO no puede ser nulo");
        }
        Productos entity = new Productos();
        entity.setIdProducto(dto.getIdProducto());
        entity.setNombre(dto.getNombre());
        entity.setDescripcion(dto.getDescripcion());
        entity.setCodigo(dto.getCodigo());
        entity.setFechaRegistro(dto.getFechaRegistro());
        entity.setFechaModificacion(dto.getFechaModificacion() != null ? dto.getFechaModificacion() : new java.util.Date());
        entity.setCantidadTotal(dto.getCantidadTotal());
        entity.setPrecioVenta(dto.getPrecioVenta());
        entity.setStatus(dto.getStatus() != null ? dto.getStatus() : true); // Default to true if null

        // Buscar y establecer los proveedores y tipoProducto usando su ID
        if (dto.getProveedorId() != null) {
            Proveedor proveedor = proveedorRepository.findById(dto.getProveedorId())
                    .orElseThrow(() -> new RuntimeException("Proveedor no encontrado con id: " + dto.getProveedorId()));
            entity.setProveedor(proveedor);
        }

        if (dto.getTipoProductoId() != null) {
            TipoProducto tipoProducto = tipoProductoRepository.findById(dto.getTipoProductoId())
                    .orElseThrow(() -> new RuntimeException("Tipo de producto no encontrado con id: " + dto.getTipoProductoId()));
            entity.setTipoProducto(tipoProducto);
        }

        return entity;
    }

    private ProductoDTO entityToDto(Productos entity) {
        if (entity == null) {
            return null;
        }
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

        // Extraer los IDs de proveedor y tipoProducto
        dto.setTipoProductoId(entity.getTipoProducto() != null ? entity.getTipoProducto().getIdTipoProducto() : null);
        dto.setProveedorId(entity.getProveedor() != null ? entity.getProveedor().getIdProveedor() : null);

        return dto;
    }

    public ProductoDTO saveProduct(ProductoDTO productoDTO) {
        if (productoDTO == null) {
            throw new IllegalArgumentException("ProductoDTO no puede ser nulo");
        }
        Productos entity = dtoToEntity(productoDTO);
        Productos savedEntity = repository.save(entity);
        return entityToDto(savedEntity);
    }

    public List<ProductoDTO> getAllProducts() {
        return repository.findAllByStatusTrue().stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }

    public ProductoDTO getProductById(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("ID no puede ser nulo");
        }
        Optional<Productos> optionalEntity = repository.findById(id);
        return optionalEntity.filter(Productos::getStatus) // Filtrar por estado activo
                .map(this::entityToDto)
                .orElse(null);
    }

    public ProductoDTO updateProduct(Integer id, ProductoDTO productoDTO) {
        if (id == null || productoDTO == null) {
            throw new IllegalArgumentException("ID y ProductoDTO no pueden ser nulos");
        }
        if (repository.existsById(id)) {
            Productos entity = dtoToEntity(productoDTO);
            entity.setIdProducto(id); // Asegúrate de que el ID no cambie
            Productos updatedEntity = repository.save(entity);
            return entityToDto(updatedEntity);
        } else {
            throw new RuntimeException("Producto no encontrado con id: " + id);
        }
    }

    public void deleteProduct(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("ID no puede ser nulo");
        }
        if (repository.existsById(id)) {
            Productos entity = repository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado con id: " + id));
            entity.setStatus(false); // Marcamos como eliminado
            repository.save(entity); // Guardamos los cambios
        } else {
            throw new RuntimeException("Producto no encontrado con id: " + id);
        }
    }
}
