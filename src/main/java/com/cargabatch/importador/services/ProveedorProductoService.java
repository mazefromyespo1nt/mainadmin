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
    private ProveedorProductoRepository proveedorProductoRepository;

    @Autowired
    private ProveedorRepository proveedorRepository;

    @Autowired
    private TipoProductoRepository tipoProductoRepository;

    private ProveedorProducto dtoToEntity(ProveedorProductoDTO dto) {
        ProveedorProducto entity = new ProveedorProducto();
        entity.setIdProveedorProducto(dto.getIdProveedorProducto());
        entity.setNombre(dto.getNombre());
        entity.setDescripcion(dto.getDescripcion());
        entity.setFechaRegistro(dto.getFechaRegistro());
        entity.setFechaModificacion(dto.getFechaModificacion());
        entity.setCantidadProductoId(dto.getCantidadProductoId());

        // Buscar y asignar proveedor
        if (dto.getProveedorId() != 0) {
            Optional<Proveedor> proveedor = proveedorRepository.findById(dto.getProveedorId());
            proveedor.ifPresent(entity::setProveedor);
        }

        // Buscar y asignar tipoProducto
        if (dto.getTipoProductoId() != 0) {
            Optional<TipoProducto> tipoProducto = tipoProductoRepository.findById(dto.getTipoProductoId());
            tipoProducto.ifPresent(entity::setTipoProducto);
        }

        return entity;
    }

    private ProveedorProductoDTO entityToDto(ProveedorProducto entity) {
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
        ProveedorProducto entity = dtoToEntity(proveedorProductoDTO);
        ProveedorProducto savedEntity = proveedorProductoRepository.save(entity);
        return entityToDto(savedEntity);
    }

    public List<ProveedorProductoDTO> getAllProveedorProductos() {
        return proveedorProductoRepository.findAll().stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }

    public ProveedorProductoDTO getProveedorProductoById(int id) {
        Optional<ProveedorProducto> optionalEntity = proveedorProductoRepository.findById(id);
        return optionalEntity.map(this::entityToDto).orElse(null);
    }

    public ProveedorProductoDTO updateProveedorProducto(int id, ProveedorProductoDTO proveedorProductoDTO) {
        if (proveedorProductoRepository.existsById(id)) {
            ProveedorProducto entity = dtoToEntity(proveedorProductoDTO);
            entity.setIdProveedorProducto(id); // Asegúrate de que el ID no cambie
            ProveedorProducto updatedEntity = proveedorProductoRepository.save(entity);
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
