package com.cargabatch.importador.dto;

import jakarta.validation.constraints.*;
import java.util.Date;

public class ProveedorProductoDTO {

    private int idProveedorProducto;

    private int proveedorId;

    private int tipoProductoId;

    @NotBlank(message = "El nombre no puede estar vacío")
    @Size(max = 25, message = "El nombre no puede tener más de 25 caracteres")
    private String nombre;

    @Size(max = 60, message = "La descripción no puede tener más de 60 caracteres")
    private String descripcion;

    @NotNull(message = "La fecha de registro no puede ser nula")
    private Date fechaRegistro;

    private Date fechaModificacion;

    @Min(value = 0, message = "La cantidad del producto debe ser al menos 0")
    private int cantidadProductoId;

    @Min(value = 0, message = "El estado debe ser 0 o 1")
    @Max(value = 1, message = "El estado debe ser 0 o 1")
    private int status; // 1 for active, 0 for deleted

    // Getters y Setters

    public int getIdProveedorProducto() {
        return idProveedorProducto;
    }

    public void setIdProveedorProducto(int idProveedorProducto) {
        this.idProveedorProducto = idProveedorProducto;
    }

    public int getProveedorId() {
        return proveedorId;
    }

    public void setProveedorId(int proveedorId) {
        this.proveedorId = proveedorId;
    }

    public int getTipoProductoId() {
        return tipoProductoId;
    }

    public void setTipoProductoId(int tipoProductoId) {
        this.tipoProductoId = tipoProductoId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Date getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public int getCantidadProductoId() {
        return cantidadProductoId;
    }

    public void setCantidadProductoId(int cantidadProductoId) {
        this.cantidadProductoId = cantidadProductoId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        if (status < 0 || status > 1) {
            throw new IllegalArgumentException("El estado debe ser 0 o 1");
        }
        this.status = status;
    }

    @Override
    public String toString() {
        return "ProveedorProductoDTO{" +
                "idProveedorProducto=" + idProveedorProducto +
                ", proveedorId=" + proveedorId +
                ", tipoProductoId=" + tipoProductoId +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", fechaRegistro=" + fechaRegistro +
                ", fechaModificacion=" + fechaModificacion +
                ", cantidadProductoId=" + cantidadProductoId +
                ", status=" + status +
                '}';
    }
}
