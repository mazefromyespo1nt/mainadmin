package com.cargabatch.importador.services;

import com.cargabatch.importador.DTO.TipoProductoDTO;
import com.cargabatch.importador.entitys.TipoProducto;
import com.cargabatch.importador.repositorys.TipoProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TipoProductoService {

    @Autowired
    private TipoProductoRepository repository;

    private TipoProducto dtoToEntity(TipoProductoDTO dto) {
        if (dto == null) {
            throw new IllegalArgumentException("DTO no puede ser nulo");
        }
        TipoProducto entity = new TipoProducto();
        entity.setIdTipoProducto(dto.getIdTipoProducto());
        entity.setNombre(dto.getNombre());
        entity.setDescripcion(dto.getDescripcion());
        entity.setFechaRegistro(dto.getFechaRegistro());
        entity.setFechaModificacion(dto.getFechaModificacion() != null ? dto.getFechaModificacion() : new java.util.Date());
        entity.setStatus(dto.getStatus());

        return entity;
    }

    private TipoProductoDTO entityToDto(TipoProducto entity) {
        if (entity == null) {
            return null;
        }
        TipoProductoDTO dto = new TipoProductoDTO();
        dto.setIdTipoProducto(entity.getIdTipoProducto());
        dto.setNombre(entity.getNombre());
        dto.setDescripcion(entity.getDescripcion());
        dto.setFechaRegistro(entity.getFechaRegistro());
        dto.setFechaModificacion(entity.getFechaModificacion());
        dto.setStatus(entity.getStatus());

        return dto;
    }

    public TipoProductoDTO saveTipoProducto(TipoProductoDTO tipoProductoDTO) {
        if (tipoProductoDTO == null) {
            throw new IllegalArgumentException("TipoProductoDTO no puede ser nulo");
        }
        TipoProducto entity = dtoToEntity(tipoProductoDTO);
        entity.setStatus(1); // Marcar como activo por defecto
        TipoProducto savedEntity = repository.save(entity);
        return entityToDto(savedEntity);
    }

    public List<TipoProductoDTO> getAllTipoProductos() {
        return repository.findAllByStatus(1).stream() // Solo activos
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }

    public TipoProductoDTO getTipoProductoById(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("ID no puede ser nulo");
        }
        Optional<TipoProducto> optionalEntity = repository.findById(id);
        return optionalEntity.filter(entity -> entity.getStatus() == 1) // Filtrar por estado activo
                .map(this::entityToDto)
                .orElse(null);
    }

    public TipoProductoDTO updateTipoProducto(Integer id, TipoProductoDTO tipoProductoDTO) {
        if (id == null || tipoProductoDTO == null) {
            throw new IllegalArgumentException("ID y TipoProductoDTO no pueden ser nulos");
        }
        if (repository.existsById(id)) {
            TipoProducto entity = dtoToEntity(tipoProductoDTO);
            entity.setIdTipoProducto(id); // Asegúrate de que el ID no cambie
            TipoProducto updatedEntity = repository.save(entity);
            return entityToDto(updatedEntity);
        } else {
            throw new RuntimeException("TipoProducto no encontrado con id: " + id);
        }
    }

    public void deleteTipoProducto(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("ID no puede ser nulo");
        }
        if (repository.existsById(id)) {
            TipoProducto entity = repository.findById(id)
                    .orElseThrow(() -> new RuntimeException("TipoProducto no encontrado con id: " + id));
            entity.setStatus(0); // Marcamos como eliminado
            repository.save(entity); // Guardamos los cambios
        } else {
            throw new RuntimeException("TipoProducto no encontrado con id: " + id);
        }
    }
}
