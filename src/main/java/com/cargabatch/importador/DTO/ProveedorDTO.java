package com.cargabatch.importador.DTO;

import java.util.Date;

public class ProveedorDTO {
    private int idProveedor;
    private String nombre;
    private String descripcion;
    private String contacto;
    private int telefonoContacto;
    private String emailContacto;
    private Date fechaRegistro;
    private Date fechaModificacion;
    private boolean status;
    private int cantidadProveedor;
    private int tipoProductoId;
    private int sucursalId;
    private int precioProductos;

    // Getters y Setters

    public int getPrecioProductos() {
        return precioProductos;
    }

    public void setPrecioProductos(int precioProductos) {
        this.precioProductos = precioProductos;
    }

    public int getSucursalId() {
        return sucursalId;
    }

    public void setSucursalId(int sucursalId) {
        this.sucursalId = sucursalId;
    }

    public int getTipoProductoId() {
        return tipoProductoId;
    }

    public void setTipoProductoId(int tipoProductoId) {
        this.tipoProductoId = tipoProductoId;
    }

    public int getCantidadProveedor() {
        return cantidadProveedor;
    }

    public void setCantidadProveedor(int cantidadProveedor) {
        this.cantidadProveedor = cantidadProveedor;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Date getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getEmailContacto() {
        return emailContacto;
    }

    public void setEmailContacto(String emailContacto) {
        this.emailContacto = emailContacto;
    }

    public int getTelefonoContacto() {
        return telefonoContacto;
    }

    public void setTelefonoContacto(int telefonoContacto) {
        this.telefonoContacto = telefonoContacto;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(int idProveedor) {
        this.idProveedor = idProveedor;
    }
}
