package com.cargabatch.importador.DTO;

import jakarta.validation.constraints.*;
import java.time.LocalDateTime;

public class ProductoDTO {

    private Integer idProducto;

    @NotBlank(message = "El nombre del producto no puede estar vacío")
    @Size(max = 30, message = "El nombre del producto no puede exceder los 30 caracteres")
    private String nombre;

    @Size(max = 120, message = "La descripción no puede exceder los 120 caracteres")
    private String descripcion;

    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "El código debe contener solo caracteres alfanuméricos")
    private String codigo;

    @PastOrPresent(message = "La fecha de registro no puede estar en el futuro")
    private LocalDateTime fechaRegistro;

    @PastOrPresent(message = "La fecha de modificación no puede estar en el futuro")
    private LocalDateTime fechaModificacion;

    @Min(value = 0, message = "La cantidad total debe ser un valor positivo")
    private Integer cantidadTotal;

    @DecimalMin(value = "0.0", inclusive = false, message = "El precio de venta debe ser un valor positivo")
    private Float precioVenta;

    private Boolean status;

    private Integer proveedorId;

    private Integer tipoProductoId;

    // Getters y Setters

    public Integer getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
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

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDateTime fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public LocalDateTime getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(LocalDateTime fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public Integer getCantidadTotal() {
        return cantidadTotal;
    }

    public void setCantidadTotal(Integer cantidadTotal) {
        this.cantidadTotal = cantidadTotal;
    }

    public Float getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(Float precioVenta) {
        this.precioVenta = precioVenta;
    }

    public Integer getProveedorId() {
        return proveedorId;
    }

    public void setProveedorId(Integer proveedorId) {
        this.proveedorId = proveedorId;
    }

    public Integer getTipoProductoId() {
        return tipoProductoId;
    }

    public void setTipoProductoId(Integer tipoProductoId) {
        this.tipoProductoId = tipoProductoId;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ProductoDTO{" +
                "idProducto=" + idProducto +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", codigo='" + codigo + '\'' +
                ", fechaRegistro=" + fechaRegistro +
                ", fechaModificacion=" + fechaModificacion +
                ", cantidadTotal=" + cantidadTotal +
                ", precioVenta=" + precioVenta +
                ", proveedorId=" + proveedorId +
                ", tipoProductoId=" + tipoProductoId +
                ", status=" + status +
                '}';
    }
}
