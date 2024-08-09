package com.cargabatch.importador.services;

import com.cargabatch.importador.DTO.SucursalDTO;
import com.cargabatch.importador.entitys.Sucursal;
import com.cargabatch.importador.repositorys.SucursalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SucursalService {

    @Autowired
    private SucursalRepository sucursalRepository;

    private Sucursal dtoToEntity(SucursalDTO dto) {
        Sucursal entity = new Sucursal();
        entity.setIdSucursal(dto.getIdSucursal());
        entity.setNombre(dto.getNombre());
        entity.setDireccion(dto.getDireccion());
        entity.setFechaRegistro(dto.getFechaRegistro());
        entity.setFechaModificacion(dto.getFechaModificacion());
        entity.setCapacidad(dto.getCapacidad());
        entity.setDimensiones(dto.getDimensiones());
        entity.setStatus(dto.isStatus());
        entity.setModificables(dto.getModificables());
        return entity;
    }

    private SucursalDTO entityToDto(Sucursal entity) {
        SucursalDTO dto = new SucursalDTO();
        dto.setIdSucursal(entity.getIdSucursal());
        dto.setNombre(entity.getNombre());
        dto.setDireccion(entity.getDireccion());
        dto.setFechaRegistro(entity.getFechaRegistro());
        dto.setFechaModificacion(entity.getFechaModificacion());
        dto.setCapacidad(entity.getCapacidad());
        dto.setDimensiones(entity.getDimensiones());
        dto.setStatus(entity.isStatus());
        dto.setModificables(entity.getModificables());
        return dto;
    }

    public SucursalDTO saveSucursal(SucursalDTO sucursalDTO) {
        Sucursal entity = dtoToEntity(sucursalDTO);
        Sucursal savedEntity = sucursalRepository.save(entity);
        return entityToDto(savedEntity);
    }

    public List<SucursalDTO> getAllSucursales() {
        return sucursalRepository.findAll().stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }

    public SucursalDTO getSucursalById(int id) {
        Optional<Sucursal> optionalEntity = sucursalRepository.findById(id);
        return optionalEntity.map(this::entityToDto).orElse(null);
    }

    public SucursalDTO updateSucursal(int id, SucursalDTO sucursalDTO) {
        if (sucursalRepository.existsById(id)) {
            Sucursal entity = dtoToEntity(sucursalDTO);
            entity.setIdSucursal(id); // Asegúrate de que el ID no cambie
            Sucursal updatedEntity = sucursalRepository.save(entity);
            return entityToDto(updatedEntity);
        } else {
            throw new RuntimeException("Sucursal no encontrada con id: " + id);
        }
    }

    public void deleteSucursal(int id) {
        if (sucursalRepository.existsById(id)) {
            sucursalRepository.deleteById(id);
        } else {
            throw new RuntimeException("Sucursal no encontrada con id: " + id);
        }
    }
}
