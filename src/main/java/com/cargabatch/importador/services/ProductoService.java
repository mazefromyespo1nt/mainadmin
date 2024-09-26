package com.cargabatch.importador.services;

import com.cargabatch.importador.dto.ProductoDTO;
import com.cargabatch.importador.entitys.Producto;
import com.cargabatch.importador.entitys.Proveedor;
import com.cargabatch.importador.entitys.TipoProducto;
import com.cargabatch.importador.repositorys.ProductoRepository;
import com.cargabatch.importador.repositorys.ProveedorRepository;
import com.cargabatch.importador.repositorys.TipoProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductoService {

    private final ProductoRepository repository;
    private final ProveedorRepository proveedorRepository;
    private final TipoProductoRepository tipoProductoRepository;

    @Autowired
    public ProductoService(ProductoRepository repository, ProveedorRepository proveedorRepository, TipoProductoRepository tipoProductoRepository) {
        this.repository = repository;
        this.proveedorRepository = proveedorRepository;
        this.tipoProductoRepository = tipoProductoRepository;
    }

    private Producto dtoToEntity(ProductoDTO dto) {
        if (dto == null) {
            throw new IllegalArgumentException("DTO no puede ser nulo");
        }
        Producto entity = new Producto();
        entity.setIdProducto(dto.getIdProducto());
        entity.setNombre(dto.getNombre());
        entity.setDescripcion(dto.getDescripcion());
        entity.setCodigo(dto.getCodigo());
        entity.setCantidadTotal(dto.getCantidadTotal());
        entity.setPrecioVenta(dto.getPrecioVenta());
        entity.setImagenBase64(dto.getImagenBase64());

        // Inicializamos fechaRegistro y status al registrar un nuevo producto
        if (dto.getIdProducto() == null) {
            entity.setFechaRegistro(LocalDateTime.now());
            entity.setStatus(true); // 1 representa activo
        }

        // Si no se proporciona fechaModificacion, se actualiza automáticamente
        entity.setFechaModificacion(LocalDateTime.now());

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

    private ProductoDTO entityToDto(Producto entity) {
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
        dto.setImagenBase64(entity.getImagenBase64());
        dto.setStatus(entity.getStatus());
        dto.setTipoProductoId(entity.getTipoProducto() != null ? entity.getTipoProducto().getIdTipoProducto() : null);
        dto.setProveedorId(entity.getProveedor() != null ? entity.getProveedor().getIdProveedor() : null);

        return dto;
    }

    @Transactional
    public ProductoDTO saveProduct(ProductoDTO productoDTO) {
        if (productoDTO == null) {
            throw new IllegalArgumentException("ProductoDTO no puede ser nulo");
        }

        // Buscar si existe un producto activo con el mismo nombre o código de barras
        List<Producto> productosExistentes = repository.findByCodigoAndStatusTrue(productoDTO.getCodigo());

        if (!productosExistentes.isEmpty()) {
            Producto productoExistente = productosExistentes.get(0);
            // Actualizar la cantidad total y la fecha de modificación
            productoExistente.setCantidadTotal(productoExistente.getCantidadTotal() + productoDTO.getCantidadTotal());
            productoExistente.setFechaModificacion(LocalDateTime.now());
            Producto updatedEntity = repository.save(productoExistente);
            return entityToDto(updatedEntity);
        } else {
            Producto entity = dtoToEntity(productoDTO);
            Producto savedEntity = repository.save(entity);
            return entityToDto(savedEntity);
        }
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
        Optional<Producto> optionalEntity = repository.findByIdProductoAndStatusTrue(id);
        return optionalEntity.map(this::entityToDto).orElse(null);
    }

    @Transactional
    public ProductoDTO updateProduct(Integer id, ProductoDTO productoDTO) {
        if (id == null || productoDTO == null) {
            throw new IllegalArgumentException("ID y ProductoDTO no pueden ser nulos");
        }
        Optional<Producto> optionalEntity = repository.findByIdProductoAndStatusTrue(id);
        if (optionalEntity.isPresent()) {
            Producto entity = optionalEntity.get();
            entity.setNombre(productoDTO.getNombre());
            entity.setDescripcion(productoDTO.getDescripcion());
            entity.setCodigo(productoDTO.getCodigo());
            entity.setCantidadTotal(productoDTO.getCantidadTotal());
            entity.setPrecioVenta(productoDTO.getPrecioVenta());
            entity.setImagenBase64(productoDTO.getImagenBase64());
            entity.setFechaModificacion(LocalDateTime.now());
            Producto updatedEntity = repository.save(entity);
            return entityToDto(updatedEntity);
        } else {
            return null;
        }
    }

    @Transactional
    public boolean deleteProduct(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("ID no puede ser nulo");
        }
        Optional<Producto> optionalEntity = repository.findByIdProductoAndStatusTrue(id);
        if (optionalEntity.isPresent()) {
            Producto entity = optionalEntity.get();
            entity.setStatus(false); // 0 representa inactivo
            repository.save(entity);
            return true;
        } else {
            return false;
        }
    }

    public List<ProductoDTO> buscarPorNombreConteniendo(String nombre) {
        return repository.findByNombreContainingAndStatusTrue(nombre).stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }

    public List<ProductoDTO> findByFechaRegistroAndFechaModificacionAndStatusTrue(LocalDateTime fechaRegistro, LocalDateTime fechaModificacion) {
        return repository.findByFechaRegistroAndFechaModificacionAndStatusTrue(fechaRegistro, fechaModificacion).stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }
}
