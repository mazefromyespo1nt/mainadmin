package com.example.producto.service;

import com.example.producto.dto.ProveedorProductoDTO;
import com.example.producto.entity.ProveedorProductoEntity;
import com.example.producto.entity.ProveedorEntity;
import com.example.producto.entity.TipoProductoEntity;
import com.example.producto.repository.ProveedorProductoRepository;
import com.example.producto.repository.ProveedorRepository;
import com.example.producto.repository.TipoProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProveedorProductoService {

    @Autowired
    private ProveedorProductoRepository proveedorProductoRepository;

    @Autowired
    private ProveedorRepository proveedorRepository;

    @Autowired
    private TipoProductoRepository tipoProductoRepository;

    private ProveedorProductoEntity dtoToEntity(ProveedorProductoDTO dto) {
        ProveedorProductoEntity entity = new ProveedorProductoEntity();
        entity.setIdProveedorProducto(dto.getIdProveedorProducto());
        entity.setNombre(dto.getNombre());
        entity.setDescripcion(dto.getDescripcion());
        entity.setFechaRegistro(dto.getFechaRegistro());
        entity.setFechaModificacion(dto.getFechaModificacion());
        entity.setCantidadProductoId(dto.getCantidadProductoId());

        // Buscar y asignar proveedor
        if (dto.getProveedorId() != 0) {
            Optional<ProveedorEntity> proveedor = proveedorRepository.findById(dto.getProveedorId());
            proveedor.ifPresent(entity::setProveedor);
        }

        // Buscar y asignar tipoProducto
        if (dto.getTipoProductoId() != 0) {
            Optional<TipoProductoEntity> tipoProducto = tipoProductoRepository.findById(dto.getTipoProductoId());
            tipoProducto.ifPresent(entity::setTipoProducto);
        }

        return entity;
    }

    private ProveedorProductoDTO entityToDto(ProveedorProductoEntity entity) {
        ProveedorProductoDTO dto = new ProveedorProductoDTO();
        dto.setIdProveedorProducto(entity.getIdProveedorProducto());
        dto.setNombre(entity.getNombre());
        dto.setDescripcion(entity.getDescripcion());
        dto.setFechaRegistro(entity.getFechaRegistro());
        dto.setFechaModificacion(entity.getFechaModificacion());
        dto.setCantidadProductoId(entity.getCantidadProductoId());

        // Extraer IDs de proveedor y tipoProducto
        if (entity.getProveedor() != null) {
            dto.setProveedorId(entity.getProveedor().getIdProveedor());
        }
        if (entity.getTipoProducto() != null) {
            dto.setTipoProductoId(entity.getTipoProducto().getIdTipoProducto());
        }

        return dto;
    }

    public ProveedorProductoDTO saveProveedorProducto(ProveedorProductoDTO proveedorProductoDTO) {
        ProveedorProductoEntity entity = dtoToEntity(proveedorProductoDTO);
        ProveedorProductoEntity savedEntity = proveedorProductoRepository.save(entity);
        return entityToDto(savedEntity);
    }

    public List<ProveedorProductoDTO> getAllProveedorProductos() {
        return proveedorProductoRepository.findAll().stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }

    public ProveedorProductoDTO getProveedorProductoById(int id) {
        Optional<ProveedorProductoEntity> optionalEntity = proveedorProductoRepository.findById(id);
        return optionalEntity.map(this::entityToDto).orElse(null);
    }

    public ProveedorProductoDTO updateProveedorProducto(int id, ProveedorProductoDTO proveedorProductoDTO) {
        if (proveedorProductoRepository.existsById(id)) {
            ProveedorProductoEntity entity = dtoToEntity(proveedorProductoDTO);
            entity.setIdProveedorProducto(id); // Asegúrate de que el ID no cambie
            ProveedorProductoEntity updatedEntity = proveedorProductoRepository.save(entity);
            return entityToDto(updatedEntity);
        } else {
            throw new RuntimeException("ProveedorProducto no encontrado con id: " + id);
        }
    }

    public void deleteProveedorProducto(int id) {
        if (proveedorProductoRepository.existsById(id)) {
            proveedorProductoRepository.deleteById(id);
        } else {
            throw new RuntimeException("ProveedorProducto no encontrado con id: " + id);
        }
    }
}
