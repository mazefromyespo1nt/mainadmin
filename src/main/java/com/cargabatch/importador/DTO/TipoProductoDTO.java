package com.cargabatch.importador.DTO;

import jakarta.validation.constraints.*;
import java.util.Date;

public class TipoProductoDTO {

    @PositiveOrZero(message = "El ID del tipo de producto debe ser cero o positivo")
    private int idTipoProducto;

    @NotBlank(message = "El nombre no puede estar vacío")
    @Size(max = 25, message = "El nombre no puede exceder los 25 caracteres")
    private String nombre;

    @Size(max = 100, message = "La descripción no puede exceder los 100 caracteres")
    private String descripcion;

    @PastOrPresent(message = "La fecha de registro no puede estar en el futuro")
    private Date fechaRegistro;

    @PastOrPresent(message = "La fecha de modificación no puede estar en el futuro")
    private Date fechaModificacion;

    @Min(value = 0, message = "El status debe ser 0 (inactivo) o 1 (activo)")
    @Max(value = 1, message = "El status debe ser 0 (inactivo) o 1 (activo)")
    private int status; // 1 for active, 0 for deleted

    // Getters y Setters

    public int getIdTipoProducto() {
        return idTipoProducto;
    }

    public void setIdTipoProducto(int idTipoProducto) {
        this.idTipoProducto = idTipoProducto;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "TipoProductoDTO{" +
                "idTipoProducto=" + idTipoProducto +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", fechaRegistro=" + fechaRegistro +
                ", fechaModificacion=" + fechaModificacion +
                ", status=" + status +
                '}';
    }
}
