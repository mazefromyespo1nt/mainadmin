package com.cargabatch.importador.services;

import com.cargabatch.importador.DTO.ProveedorProductoDTO;
import com.cargabatch.importador.entitys.ProveedorProducto;
import com.cargabatch.importador.entitys.Proveedor;
import com.cargabatch.importador.entitys.TipoProducto;
import com.cargabatch.importador.repositorys.ProveedorProductoRepository;
import com.cargabatch.importador.repositorys.ProveedorRepository;
import com.cargabatch.importador.repositorys.TipoProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProveedorProductoService {

    @Autowired
    private ProveedorProductoRepository repository;

    @Autowired
    private ProveedorRepository proveedorRepository;

    @Autowired
    private TipoProductoRepository tipoProductoRepository;

    private ProveedorProducto dtoToEntity(ProveedorProductoDTO dto) {
        if (dto == null) {
            throw new IllegalArgumentException("DTO no puede ser nulo");
        }
        ProveedorProducto entity = new ProveedorProducto();
        entity.setIdProveedorProducto(dto.getIdProveedorProducto());
        entity.setNombre(dto.getNombre());
        entity.setDescripcion(dto.getDescripcion());
        entity.setFechaRegistro(dto.getFechaRegistro());
        entity.setFechaModificacion(dto.getFechaModificacion() != null ? dto.getFechaModificacion() : new java.util.Date());
        entity.setCantidadProductoId(dto.getCantidadProductoId());
        entity.setStatus(dto.getStatus());

        // Buscar y establecer el proveedor y tipoProducto usando su ID
        if (dto.getProveedorId() != 0) {
            Proveedor proveedor = proveedorRepository.findById(dto.getProveedorId())
                    .orElseThrow(() -> new RuntimeException("Proveedor no encontrado con id: " + dto.getProveedorId()));
            entity.setProveedor(proveedor);
        }

        if (dto.getTipoProductoId() != 0) {
            TipoProducto tipoProducto = tipoProductoRepository.findById(dto.getTipoProductoId())
                    .orElseThrow(() -> new RuntimeException("Tipo de producto no encontrado con id: " + dto.getTipoProductoId()));
            entity.setTipoProducto(tipoProducto);
        }

        return entity;
    }

    private ProveedorProductoDTO entityToDto(ProveedorProducto entity) {
        if (entity == null) {
            return null;
        }
        ProveedorProductoDTO dto = new ProveedorProductoDTO();
        dto.setIdProveedorProducto(entity.getIdProveedorProducto());
        dto.setNombre(entity.getNombre());
        dto.setDescripcion(entity.getDescripcion());
        dto.setFechaRegistro(entity.getFechaRegistro());
        dto.setFechaModificacion(entity.getFechaModificacion());
        dto.setCantidadProductoId(entity.getCantidadProductoId());
        dto.setStatus(entity.getStatus());

        // Extraer los IDs de proveedor y tipoProducto
        dto.setTipoProductoId(entity.getTipoProducto() != null ? entity.getTipoProducto().getIdTipoProducto() : 0);
        dto.setProveedorId(entity.getProveedor() != null ? entity.getProveedor().getIdProveedor() : 0);

        return dto;
    }

    public ProveedorProductoDTO saveProveedorProducto(ProveedorProductoDTO proveedorProductoDTO) {
        if (proveedorProductoDTO == null) {
            throw new IllegalArgumentException("ProveedorProductoDTO no puede ser nulo");
        }
        ProveedorProducto entity = dtoToEntity(proveedorProductoDTO);
        entity.setStatus(1); // Marcar como activo por defecto
        ProveedorProducto savedEntity = repository.save(entity);
        return entityToDto(savedEntity);
    }

    public List<ProveedorProductoDTO> getAllProveedorProductos() {
        return repository.findAllByStatus(1).stream() // Solo activos
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }

    public ProveedorProductoDTO getProveedorProductoById(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("ID no puede ser nulo");
        }
        Optional<ProveedorProducto> optionalEntity = repository.findById(id);
        return optionalEntity.filter(entity -> entity.getStatus() == 1) // Filtrar por estado activo
                .map(this::entityToDto)
                .orElse(null);
    }

    public ProveedorProductoDTO updateProveedorProducto(Integer id, ProveedorProductoDTO proveedorProductoDTO) {
        if (id == null || proveedorProductoDTO == null) {
            throw new IllegalArgumentException("ID y ProveedorProductoDTO no pueden ser nulos");
        }
        if (repository.existsById(id)) {
            ProveedorProducto entity = dtoToEntity(proveedorProductoDTO);
            entity.setIdProveedorProducto(id); // Asegúrate de que el ID no cambie
            ProveedorProducto updatedEntity = repository.save(entity);
            return entityToDto(updatedEntity);
        } else {
            throw new RuntimeException("ProveedorProducto no encontrado con id: " + id);
        }
    }

    public void deleteProveedorProducto(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("ID no puede ser nulo");
        }
        if (repository.existsById(id)) {
            ProveedorProducto entity = repository.findById(id)
                    .orElseThrow(() -> new RuntimeException("ProveedorProducto no encontrado con id: " + id));
            entity.setStatus(0); // Marcamos como eliminado
            repository.save(entity); // Guardamos los cambios
        } else {
            throw new RuntimeException("ProveedorProducto no encontrado con id: " + id);
        }
    }
}
