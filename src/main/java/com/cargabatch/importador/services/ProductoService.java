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
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
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

    /**
     * Convierte un DTO a una entidad de Producto.
     * @param dto El DTO del producto.
     * @return La entidad Producto correspondiente.
     */
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
        entity.setFechaModificacion(dto.getFechaModificacion() != null ? dto.getFechaModificacion() : LocalDateTime.now());
        entity.setCantidadTotal(dto.getCantidadTotal());
        entity.setPrecioVenta(dto.getPrecioVenta());
        entity.setStatus(dto.getStatus() != null ? dto.getStatus() : true);

        // Buscar y establecer el proveedor si existe
        if (dto.getProveedorId() != null) {
            Proveedor proveedor = proveedorRepository.findById(dto.getProveedorId())
                    .orElseThrow(() -> new RuntimeException("Proveedor no encontrado con id: " + dto.getProveedorId()));
            entity.setProveedor(proveedor);
        }

        // Buscar y establecer el tipo de producto si existe
        if (dto.getTipoProductoId() != null) {
            TipoProducto tipoProducto = tipoProductoRepository.findById(dto.getTipoProductoId())
                    .orElseThrow(() -> new RuntimeException("Tipo de producto no encontrado con id: " + dto.getTipoProductoId()));
            entity.setTipoProducto(tipoProducto);
        }

        return entity;
    }

    /**
     * Convierte una entidad de Producto a un DTO.
     * @param entity La entidad Producto.
     * @return El DTO correspondiente.
     */
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
        dto.setTipoProductoId(entity.getTipoProducto() != null ? entity.getTipoProducto().getIdTipoProducto() : null);
        dto.setProveedorId(entity.getProveedor() != null ? entity.getProveedor().getIdProveedor() : null);

        return dto;
    }

    /**
     * Guarda un producto en la base de datos. Si el nombre del producto ya existe, suma la cantidad.
     * @param productoDTO El DTO del producto a guardar.
     * @return El DTO del producto guardado.
     */
    @Transactional
    public ProductoDTO saveProduct(ProductoDTO productoDTO) {
        if (productoDTO == null) {
            throw new IllegalArgumentException("ProductoDTO no puede ser nulo");
        }

        // Verifica si ya existe un producto con el mismo nombre y estado activo
        List<Productos> productosExistentes = repository.findByNombreAndStatusTrue(productoDTO.getNombre());

        if (!productosExistentes.isEmpty()) {
            // Si existe, actualiza la cantidad del producto existente
            Productos productoExistente = productosExistentes.get(0);
            productoExistente.setCantidadTotal(productoExistente.getCantidadTotal() + productoDTO.getCantidadTotal());
            Productos updatedEntity = repository.save(productoExistente);
            return entityToDto(updatedEntity);
        } else {
            // Si no existe, crea un nuevo producto
            Productos entity = dtoToEntity(productoDTO);
            Productos savedEntity = repository.save(entity);
            return entityToDto(savedEntity);
        }
    }

    /**
     * Obtiene todos los productos activos.
     * @return Una lista de DTOs de productos activos.
     */
    public List<ProductoDTO> getAllProducts() {
        return repository.findAllByStatusTrue().stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }

    /**
     * Obtiene un producto por su ID si está activo.
     * @param id El ID del producto.
     * @return El DTO del producto si existe y está activo, o null en caso contrario.
     */
    public ProductoDTO getProductById(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("ID no puede ser nulo");
        }
        Optional<Productos> optionalEntity = repository.findByIdAndStatusTrue(id);
        return optionalEntity.map(this::entityToDto).orElse(null);
    }

    /**
     * Actualiza un producto existente.
     * @param id El ID del producto a actualizar.
     * @param productoDTO El DTO con los datos actualizados.
     * @return El DTO del producto actualizado.
     */
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

    /**
     * Elimina (desactiva) un producto por su ID.
     */
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
