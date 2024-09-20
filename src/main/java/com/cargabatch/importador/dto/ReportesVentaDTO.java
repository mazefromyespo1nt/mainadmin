package com.cargabatch.importador.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDateTime;

public class ReportesVentaDTO {

    private Integer idVenta;

    @NotNull(message = "La fecha no puede ser nula")
    private LocalDateTime fecha;

    @Min(value = 1, message = "La cantidad debe ser al menos 1")
    private Integer cantidad;

    @Positive(message = "El precio de venta debe ser positivo")
    private Float precioVenta;

    @NotNull(message = "El rol no puede ser nulo")
    private Integer rolesId;

    @NotNull(message = "El producto no puede ser nulo")
    private Integer productoId;

    @NotNull(message = "El usuario no puede ser nulo")
    private Integer usuarioId;

    @Positive(message = "El total debe ser positivo")
    private Float total;

    // Getters and Setters

    public Integer getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(Integer idVenta) {
        this.idVenta = idVenta;
    }

    public @NotNull(message = "La fecha no puede ser nula") LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(@NotNull(message = "La fecha no puede ser nula") LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public @Min(value = 1, message = "La cantidad debe ser al menos 1") Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(@Min(value = 1, message = "La cantidad debe ser al menos 1") Integer cantidad) {
        this.cantidad = cantidad;
    }

    public @Positive(message = "El precio de venta debe ser positivo") Float getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(@Positive(message = "El precio de venta debe ser positivo") Float precioVenta) {
        this.precioVenta = precioVenta;
    }

    public @NotNull(message = "El rol no puede ser nulo") Integer getRolesId() {
        return rolesId;
    }

    public void setRolesId(@NotNull(message = "El rol no puede ser nulo") Integer rolesId) {
        this.rolesId = rolesId;
    }

    public @NotNull(message = "El producto no puede ser nulo") Integer getProductoId() {
        return productoId;
    }

    public void setProductoId(@NotNull(message = "El producto no puede ser nulo") Integer productoId) {
        this.productoId = productoId;
    }

    public @NotNull(message = "El usuario no puede ser nulo") Integer getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(@NotNull(message = "El usuario no puede ser nulo") Integer usuarioId) {
        this.usuarioId = usuarioId;
    }

    public @Positive(message = "El total debe ser positivo") Float getTotal() {
        return total;
    }

    public void setTotal(@Positive(message = "El total debe ser positivo") Float total) {
        this.total = total;
    }
}
