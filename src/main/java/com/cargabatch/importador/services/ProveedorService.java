package com.cargabatch.importador.services;

import com.cargabatch.importador.DTO.ProveedorDTO;
import com.cargabatch.importador.entitys.Proveedor;
import com.cargabatch.importador.entitys.TipoProducto;
import com.cargabatch.importador.entitys.Sucursal;
import com.cargabatch.importador.repositorys.ProveedorRepository;
import com.cargabatch.importador.repositorys.TipoProductoRepository;
import com.cargabatch.importador.repositorys.SucursalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProveedorService {

    @Autowired
    private ProveedorRepository proveedorRepository;

    @Autowired
    private TipoProductoRepository tipoProductoRepository;

    @Autowired
    private SucursalRepository sucursalRepository;

    private Proveedor dtoToEntity(ProveedorDTO dto) {
        Proveedor entity = new Proveedor();
        entity.setIdProveedor(dto.getIdProveedor());
        entity.setNombre(dto.getNombre());
        entity.setDescripcion(dto.getDescripcion());
        entity.setContacto(dto.getContacto());
        entity.setTelefonoContacto(dto.getTelefonoContacto());
        entity.setEmailContacto(dto.getEmailContacto());
        entity.setFechaRegistro(dto.getFechaRegistro());
        entity.setFechaModificacion(dto.getFechaModificacion());
        entity.setStatus(dto.isStatus());
        entity.setCantidadProveedor(dto.getCantidadProveedor());
        entity.setPrecioProductos(dto.getPrecioProductos());

        // Buscar y asignar tipoProducto
        if (dto.getTipoProductoId() != 0) {
            Optional<TipoProducto> tipoProducto = tipoProductoRepository.findById(dto.getTipoProductoId());
            tipoProducto.ifPresent(entity::setTipoProducto);
        }

        // Buscar y asignar sucursal
        if (dto.getSucursalId() != 0) {
            Optional<Sucursal> sucursal = sucursalRepository.findById(dto.getSucursalId());
            sucursal.ifPresent(entity::setSucursal);
        }

        return entity;
    }

    private ProveedorDTO entityToDto(Proveedor entity) {
        ProveedorDTO dto = new ProveedorDTO();
        dto.setIdProveedor(entity.getIdProveedor());
        dto.setNombre(entity.getNombre());
        dto.setDescripcion(entity.getDescripcion());
        dto.setContacto(entity.getContacto());
        dto.setTelefonoContacto(entity.getTelefonoContacto());
        dto.setEmailContacto(entity.getEmailContacto());
        dto.setFechaRegistro(entity.getFechaRegistro());
        dto.setFechaModificacion(entity.getFechaModificacion());
        dto.setStatus(entity.isStatus());
        dto.setCantidadProveedor(entity.getCantidadProveedor());
        dto.setPrecioProductos(entity.getPrecioProductos());

        // Extraer IDs de tipoProducto y sucursal
        if (entity.getTipoProducto() != null) {
            dto.setTipoProductoId(entity.getTipoProducto().getIdTipoProducto());
        }
        if (entity.getSucursal() != null) {
            dto.setSucursalId(entity.getSucursal().getIdSucursal());
        }

        return dto;
    }

    public ProveedorDTO saveProveedor(ProveedorDTO proveedorDTO) {
        Proveedor entity = dtoToEntity(proveedorDTO);
        Proveedor savedEntity = proveedorRepository.save(entity);
        return entityToDto(savedEntity);
    }

    public List<ProveedorDTO> getAllProveedores() {
        return proveedorRepository.findAll().stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }

    public ProveedorDTO getProveedorById(int id) {
        Optional<Proveedor> optionalEntity = proveedorRepository.findById(id);
        return optionalEntity.map(this::entityToDto).orElse(null);
    }

    public ProveedorDTO updateProveedor(int id, ProveedorDTO proveedorDTO) {
        if (proveedorRepository.existsById(id)) {
            Proveedor entity = dtoToEntity(proveedorDTO);
            entity.setIdProveedor(id); // Asegúrate de que el ID no cambie
            Proveedor updatedEntity = proveedorRepository.save(entity);
            return entityToDto(updatedEntity);
        } else {
            throw new RuntimeException("Proveedor no encontrado con id: " + id);
        }
    }

    public void deleteProveedor(int id) {
        if (proveedorRepository.existsById(id)) {
            proveedorRepository.deleteById(id);
        } else {
            throw new RuntimeException("Proveedor no encontrado con id: " + id);
        }
    }
}
