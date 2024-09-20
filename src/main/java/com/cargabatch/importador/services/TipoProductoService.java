package com.cargabatch.importador.services;

import com.cargabatch.importador.dto.TipoProductoDTO;
import com.cargabatch.importador.entitys.TipoProducto;
import com.cargabatch.importador.repositorys.TipoProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TipoProductoService {

    @Autowired
    private TipoProductoRepository repository;

    // Conversión de DTO a entidad
    private TipoProducto dtoToEntity(TipoProductoDTO dto) {
        if (dto == null) {
            throw new IllegalArgumentException("DTO no puede ser nulo");
        }
        TipoProducto entity = new TipoProducto();
        entity.setIdTipoProducto(dto.getIdTipoProducto());
        entity.setNombre(dto.getNombre());
        entity.setDescripcion(dto.getDescripcion());
        entity.setFechaRegistro(dto.getFechaRegistro() != null ? dto.getFechaRegistro() : new Date());
        entity.setFechaModificacion(dto.getFechaModificacion() != null ? dto.getFechaModificacion() : new Date());
        entity.setStatus(dto.getStatus());

        return entity;
    }

    // Conversión de entidad a DTO
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

    // Guardar o actualizar un TipoProducto
    public TipoProductoDTO saveOrUpdateTipoProducto(TipoProductoDTO tipoProductoDTO) {
        if (tipoProductoDTO == null) {
            throw new IllegalArgumentException("TipoProductoDTO no puede ser nulo");
        }

        TipoProducto entity = dtoToEntity(tipoProductoDTO);

        if (tipoProductoDTO.getIdTipoProducto() > 0) {
            // Actualizar un TipoProducto existente
            Optional<TipoProducto> existingEntity = repository.findById(tipoProductoDTO.getIdTipoProducto());
            if (existingEntity.isPresent()) {
                TipoProducto currentEntity = existingEntity.get();
                currentEntity.setNombre(entity.getNombre());
                currentEntity.setDescripcion(entity.getDescripcion());
                currentEntity.setFechaModificacion(new Date());
                currentEntity.setStatus(entity.getStatus());
                repository.save(currentEntity);
                return entityToDto(currentEntity);
            } else {
                throw new RuntimeException("TipoProducto no encontrado con id: " + tipoProductoDTO.getIdTipoProducto());
            }
        } else {
            // Crear un nuevo TipoProducto
            entity.setStatus(1); // Marcar como activo por defecto
            TipoProducto savedEntity = repository.save(entity);
            return entityToDto(savedEntity);
        }
    }

    // Obtener todos los TipoProducto activos
    public List<TipoProductoDTO> getAllTipoProductos() {
        return repository.findAllByStatus(1).stream() // Solo activos
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }

    // Obtener TipoProducto por ID
    public TipoProductoDTO getTipoProductoById(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("ID no puede ser nulo");
        }
        Optional<TipoProducto> optionalEntity = repository.findById(id);
        return optionalEntity.filter(entity -> entity.getStatus() == 1) // Filtrar por estado activo
                .map(this::entityToDto)
                .orElse(null);
    }

    // Eliminar un TipoProducto (marcar como eliminado en lugar de borrar)
    @Transactional
    public void deleteTipoProducto(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("ID no puede ser nulo");
        }
        TipoProducto entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("TipoProducto no encontrado con id: " + id));
        entity.setStatus(0); // Marcamos como eliminado
        repository.save(entity); // Guardamos los cambios
    }

    // Buscar por nombre
    public List<TipoProductoDTO> getTipoProductosByNombre(String nombre) {
        if (nombre == null || nombre.isEmpty()) {
            throw new IllegalArgumentException("Nombre no puede ser nulo o vacío");
        }
        return repository.findByNombreContainingIgnoreCaseAndStatus(nombre, 1).stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }

    // Buscar por fecha de registro
    public List<TipoProductoDTO> getTipoProductosByFechaRegistro(Date startDate, Date endDate) {
        if (startDate == null || endDate == null) {
            throw new IllegalArgumentException("Fechas no pueden ser nulas");
        }
        return repository.findByFechaRegistroBetweenAndStatus(startDate, endDate, 1).stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }

    // Contar por estado
    public long countTipoProductosByStatus(int status) {
        return repository.countByStatus(status);
    }

    // Obtener el TipoProducto más reciente
    public TipoProductoDTO getMostRecentTipoProducto() {
        TipoProducto entity = repository.findTopByOrderByFechaRegistroDesc();
        return entity != null ? entityToDto(entity) : null;
    }
}
