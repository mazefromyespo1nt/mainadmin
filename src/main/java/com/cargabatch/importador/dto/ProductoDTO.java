package com.cargabatch.importador.dto;

import jakarta.validation.constraints.*;
import java.time.LocalDateTime;

public class ProductoDTO {

    @NotBlank(message = "El nombre del producto no puede estar vacío")
    @Size(max = 30, message = "El nombre del producto no puede exceder los 30 caracteres")
    private String nombre;

    @Size(max = 120, message = "La descripción no puede exceder los 120 caracteres")
    private String descripcion;

    @Pattern(regexp = "^[0-9]{1,12}$", message = "El código debe ser numérico y no exceder 12 dígitos")
    private String codigo;

    @Min(value = 0, message = "La cantidad total debe ser un valor positivo")
    private Integer cantidadTotal;

    @DecimalMin(value = "0.0", inclusive = false, message = "El precio de venta debe ser un valor positivo")
    private Float precioVenta;

    private String imagenBase64;

    private Boolean status;

    private Integer proveedorId;

    private Integer tipoProductoId;

    private LocalDateTime fechaRegistro;

    private Integer idProducto;

    private LocalDateTime fechaModificacion;

    // Getters y Setters


    public Integer getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    public @NotBlank(message = "El nombre del producto no puede estar vacío") @Size(max = 30, message = "El nombre del producto no puede exceder los 30 caracteres") String getNombre() {
        return nombre;
    }

    public void setNombre(@NotBlank(message = "El nombre del producto no puede estar vacío") @Size(max = 30, message = "El nombre del producto no puede exceder los 30 caracteres") String nombre) {
        this.nombre = nombre;
    }

    public @Size(max = 120, message = "La descripción no puede exceder los 120 caracteres") String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(@Size(max = 120, message = "La descripción no puede exceder los 120 caracteres") String descripcion) {
        this.descripcion = descripcion;
    }

    public @Pattern(regexp = "^[0-9]{1,12}$", message = "El código debe ser numérico y no exceder 12 dígitos") String getCodigo() {
        return codigo;
    }

    public void setCodigo(@Pattern(regexp = "^[0-9]{1,12}$", message = "El código debe ser numérico y no exceder 12 dígitos") String codigo) {
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

    public @Min(value = 0, message = "La cantidad total debe ser un valor positivo") Integer getCantidadTotal() {
        return cantidadTotal;
    }

    public void setCantidadTotal(@Min(value = 0, message = "La cantidad total debe ser un valor positivo") Integer cantidadTotal) {
        this.cantidadTotal = cantidadTotal;
    }

    public @DecimalMin(value = "0.0", inclusive = false, message = "El precio de venta debe ser un valor positivo") Float getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(@DecimalMin(value = "0.0", inclusive = false, message = "El precio de venta debe ser un valor positivo") Float precioVenta) {
        this.precioVenta = precioVenta;
    }

    public String getImagenBase64() {
        return imagenBase64;
    }

    public void setImagenBase64(String imagenBase64) {
        this.imagenBase64 = imagenBase64;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
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
                ", imagenBase64='" + imagenBase64 + '\'' +
                ", proveedorId=" + proveedorId +
                ", tipoProductoId=" + tipoProductoId +
                ", status=" + status +
                '}';
    }
}
