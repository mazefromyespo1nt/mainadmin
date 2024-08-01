// src/main/java/com/example/producto/service/TipoProductoService.java
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
    private TipoProductoRepository tipoProductoRepository;

    private TipoProducto dtoToEntity(TipoProductoDTO dto) {
        TipoProducto entity = new TipoProducto();
        entity.setIdTipoProducto(dto.getIdTipoProducto());
        entity.setNombre(dto.getNombre());
        entity.setDescripcion(dto.getDescripcion());
        entity.setFechaRegistro(dto.getFechaRegistro());
        entity.setFechaModificacion(dto.getFechaModificacion());
        entity.setStatus(dto.isStatus());
        return entity;
    }

    private TipoProductoDTO entityToDto(TipoProducto entity) {
        TipoProductoDTO dto = new TipoProductoDTO();
        dto.setIdTipoProducto(entity.getIdTipoProducto());
        dto.setNombre(entity.getNombre());
        dto.setDescripcion(entity.getDescripcion());
        dto.setFechaRegistro(entity.getFechaRegistro());
        dto.setFechaModificacion(entity.getFechaModificacion());
        dto.setStatus(entity.isStatus());
        return dto;
    }

    public TipoProductoDTO saveTipoProducto(TipoProductoDTO tipoProductoDTO) {
        TipoProducto entity = dtoToEntity(tipoProductoDTO);
        TipoProducto savedEntity = tipoProductoRepository.save(entity);
        return entityToDto(savedEntity);
    }

    public List<TipoProductoDTO> getAllTipoProductos() {
        return tipoProductoRepository.findAll().stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }

    public TipoProductoDTO getTipoProductoById(int id) {
        Optional<TipoProducto> optionalEntity = tipoProductoRepository.findById(id);
        return optionalEntity.map(this::entityToDto).orElse(null);
    }

    public TipoProductoDTO updateTipoProducto(int id, TipoProductoDTO tipoProductoDTO) {
        if (tipoProductoRepository.existsById(id)) {
            TipoProducto entity = dtoToEntity(tipoProductoDTO);
            entity.setIdTipoProducto(id); // Asegúrate de que el ID no cambie
            TipoProducto updatedEntity = tipoProductoRepository.save(entity);
            return entityToDto(updatedEntity);
        } else {
            throw new RuntimeException("TipoProducto no encontrado con id: " + id);
        }
    }

    public void deleteTipoProducto(int id) {
        if (tipoProductoRepository.existsById(id)) {
            tipoProductoRepository.deleteById(id);
        } else {
            throw new RuntimeException("TipoProducto no encontrado con id: " + id);
        }
    }
}
