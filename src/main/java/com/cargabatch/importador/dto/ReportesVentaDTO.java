package com.cargabatch.importador.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDateTime;

public class ReportesVentaDTO {

    private Integer idVenta;

    @NotNull(message = "La fecha no puede ser nula")
    private LocalDateTime fecha;

    @NotNull(message = "El producto no puede ser nulo")
    private Integer producto;  // Aquí usas el ID del producto

    @Min(value = 1, message = "La cantidad debe ser al menos 1")
    private Float cantidad;

    @Positive(message = "El precio de venta debe ser positivo")
    private Float precioVenta;

    @Positive(message = "El total debe ser positivo")
    private Float total;

    // Constructor vacío
    public ReportesVentaDTO() {}

    // Constructor con parámetros
    public ReportesVentaDTO(LocalDateTime fecha, Float cantidad, Float precioVenta, Float total, Integer producto) {
        this.fecha = fecha;
        this.cantidad = cantidad;
        this.precioVenta = precioVenta;
        this.total = total;
        this.producto = producto;  // Pasamos el ID del producto
    }

    // Getters y Setters
    public Integer getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(Integer idVenta) {
        this.idVenta = idVenta;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public Float getCantidad() {
        return cantidad;
    }

    public void setCantidad(Float cantidad) {
        this.cantidad = cantidad;
    }

    public Float getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(Float precioVenta) {
        this.precioVenta = precioVenta;
    }

    public Integer getProducto() {
        return producto;
    }

    public void setProducto(Integer producto) {
        this.producto = producto;
    }

    public Float getTotal() {
        return total;
    }

    public void setTotal(Float total) {
        this.total = total;
    }
}
