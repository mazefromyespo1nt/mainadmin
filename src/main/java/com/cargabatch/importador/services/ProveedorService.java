package com.cargabatch.importador.services;

import com.cargabatch.importador.DTO.ProveedorDTO;
import com.cargabatch.importador.entitys.Proveedor;
import com.cargabatch.importador.entitys.TipoProducto;
import com.cargabatch.importador.entitys.Sucursal;
import com.cargabatch.importador.repositorys.ProveedorRepository;
import com.cargabatch.importador.repositorys.TipoProductoRepository;
import com.cargabatch.importador.repositorys.SucursalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProveedorService {

    @Autowired
    private ProveedorRepository repository;

    @Autowired
    private TipoProductoRepository tipoProductoRepository;

    @Autowired
    private SucursalRepository sucursalRepository;

    private Proveedor dtoToEntity(ProveedorDTO dto) {
        if (dto == null) {
            throw new IllegalArgumentException("ProveedorDTO no puede ser nulo");
        }
        Proveedor entity = new Proveedor();
        entity.setIdProveedor(dto.getIdProveedor());
        entity.setNombre(dto.getNombre());
        entity.setDescripcion(dto.getDescripcion());
        entity.setContacto(dto.getContacto());
        entity.setTelefonoContacto(dto.getTelefonoContacto());
        entity.setEmailContacto(dto.getEmailContacto());
        entity.setFechaRegistro(dto.getFechaRegistro());
        entity.setFechaModificacion(dto.getFechaModificacion() != null ? dto.getFechaModificacion() : new Date());
        entity.setCantidadProveedor(dto.getCantidadProveedor());
        entity.setPrecioProductos(dto.getPrecioProductos());
        entity.setStatus(dto.isStatus()); // Usamos el valor del DTO para status

        // Buscar y establecer tipoProducto y sucursal usando sus ID
        if (dto.getTipoProductoId() != 0) {
            TipoProducto tipoProducto = tipoProductoRepository.findById(dto.getTipoProductoId())
                    .orElseThrow(() -> new RuntimeException("Tipo de producto no encontrado con id: " + dto.getTipoProductoId()));
            entity.setTipoProducto(tipoProducto);
        }

        if (dto.getSucursalId() != 0) {
            Sucursal sucursal = sucursalRepository.findById(dto.getSucursalId())
                    .orElseThrow(() -> new RuntimeException("Sucursal no encontrada con id: " + dto.getSucursalId()));
            entity.setSucursal(sucursal);
        }

        return entity;
    }

    private ProveedorDTO entityToDto(Proveedor entity) {
        if (entity == null) {
            return null;
        }
        ProveedorDTO dto = new ProveedorDTO();
        dto.setIdProveedor(entity.getIdProveedor());
        dto.setNombre(entity.getNombre());
        dto.setDescripcion(entity.getDescripcion());
        dto.setContacto(entity.getContacto());
        dto.setTelefonoContacto(entity.getTelefonoContacto());
        dto.setEmailContacto(entity.getEmailContacto());
        dto.setFechaRegistro(entity.getFechaRegistro());
        dto.setFechaModificacion(entity.getFechaModificacion());
        dto.setCantidadProveedor(entity.getCantidadProveedor());
        dto.setPrecioProductos(entity.getPrecioProductos());
        dto.setStatus(entity.isStatus());

        // Extraer los IDs de tipoProducto y sucursal
        dto.setTipoProductoId(entity.getTipoProducto() != null ? entity.getTipoProducto().getIdTipoProducto() : 0);
        dto.setSucursalId(entity.getSucursal() != null ? entity.getSucursal().getIdSucursal() : 0);

        return dto;
    }

    public ProveedorDTO saveProveedor(ProveedorDTO proveedorDTO) {
        if (proveedorDTO == null) {
            throw new IllegalArgumentException("ProveedorDTO no puede ser nulo");
        }
        Proveedor entity = dtoToEntity(proveedorDTO);
        Proveedor savedEntity = repository.save(entity);
        return entityToDto(savedEntity);
    }

    public List<ProveedorDTO> getAllProveedores() {
        return repository.findAllByStatusTrue().stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }

    public Page<ProveedorDTO> getAllProveedores(Pageable pageable) {
        Page<Proveedor> proveedoresPage = repository.findAllByStatusTrue(PageRequest.of(pageable.getPageNumber(), pageable.getPageSize()));
        return proveedoresPage.map(this::entityToDto);
    }

    public ProveedorDTO getProveedorById(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("ID no puede ser nulo");
        }
        Optional<Proveedor> optionalEntity = repository.findById(id);
        return optionalEntity.filter(Proveedor::isStatus) // Filtrar por estado activo
                .map(this::entityToDto)
                .orElse(null);
    }

    public ProveedorDTO updateProveedor(Integer id, ProveedorDTO proveedorDTO) {
        if (id == null || proveedorDTO == null) {
            throw new IllegalArgumentException("ID y ProveedorDTO no pueden ser nulos");
        }
        if (repository.existsById(id)) {
            Proveedor entity = dtoToEntity(proveedorDTO);
            entity.setIdProveedor(id); // Asegúrate de que el ID no cambie
            Proveedor updatedEntity = repository.save(entity);
            return entityToDto(updatedEntity);
        } else {
            throw new RuntimeException("Proveedor no encontrado con id: " + id);
        }
    }

    public void deleteProveedor(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("ID no puede ser nulo");
        }
        if (repository.existsById(id)) {
            Proveedor entity = repository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Proveedor no encontrado con id: " + id));
            entity.setStatus(false); // Marcamos como eliminado
            repository.save(entity); // Guardamos los cambios
        } else {
            throw new RuntimeException("Proveedor no encontrado con id: " + id);
        }
    }

    // Método para buscar proveedores por nombre
    public List<ProveedorDTO> searchProveedoresByName(String nombre) {
        if (nombre == null || nombre.isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede ser nulo o vacío");
        }
        return repository.findByNombreContainingIgnoreCase(nombre).stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }

    // Método para buscar proveedores activos desde una fecha específica
    public List<ProveedorDTO> getActiveProveedoresSince(Date fecha) {
        if (fecha == null) {
            throw new IllegalArgumentException("La fecha no puede ser nula");
        }
        return repository.findActiveProveedoresSince(fecha).stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }

    // Método para obtener proveedores activos por tipo de producto
    public List<ProveedorDTO> getProveedoresByTipoProducto(Integer tipoProductoId) {
        if (tipoProductoId == null) {
            throw new IllegalArgumentException("El ID del tipo de producto no puede ser nulo");
        }
        return repository.findByStatusTrueAndTipoProductoId(tipoProductoId).stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }

    // Método para obtener proveedores activos por sucursal
    public List<ProveedorDTO> getProveedoresBySucursal(Integer sucursalId) {
        if (sucursalId == null) {
            throw new IllegalArgumentException("El ID de la sucursal no puede ser nulo");
        }
        return repository.findByStatusTrueAndSucursalId(sucursalId).stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }
}
