package com.cargabatch.importador.DTO;

import java.util.Date;

public class ProveedorProductoDTO {
    private int idProveedorProducto;
    private int proveedorId;
    private int tipoProductoId;
    private String nombre;
    private String descripcion;
    private Date fechaRegistro;
    private Date fechaModificacion;
    private int cantidadProductoId;
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
        this.status = status;
    }
}
