package com.cargabatch.importador.dto;

import java.util.Date;

public class ProveedorDTO {

    private int idProveedor;
    private String nombre;
    private String descripcion;
    private String contacto;
    private String telefonoContacto;
    private String emailContacto;
    private Date fechaRegistro;
    private Date fechaModificacion;
    private boolean status;
    private int cantidadProveedor;
    private int tipoProductoId;
    private int sucursalId;
    private int precioProductos;

    // Getters y Setters

    public int getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(int idProveedor) {
        this.idProveedor = idProveedor;
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

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public String getTelefonoContacto() {
        return telefonoContacto;
    }

    public void setTelefonoContacto(String telefonoContacto) {
        this.telefonoContacto = telefonoContacto;
    }

    public String getEmailContacto() {
        return emailContacto;
    }

    public void setEmailContacto(String emailContacto) {
        this.emailContacto = emailContacto;
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

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getCantidadProveedor() {
        return cantidadProveedor;
    }

    public void setCantidadProveedor(int cantidadProveedor) {
        this.cantidadProveedor = cantidadProveedor;
    }

    public int getTipoProductoId() {
        return tipoProductoId;
    }

    public void setTipoProductoId(int tipoProductoId) {
        this.tipoProductoId = tipoProductoId;
    }

    public int getSucursalId() {
        return sucursalId;
    }

    public void setSucursalId(int sucursalId) {
        this.sucursalId = sucursalId;
    }

    public int getPrecioProductos() {
        return precioProductos;
    }

    public void setPrecioProductos(int precioProductos) {
        this.precioProductos = precioProductos;
    }

    @Override
    public String toString() {
        return "ProveedorDTO{" +
                "idProveedor=" + idProveedor +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", contacto='" + contacto + '\'' +
                ", telefonoContacto='" + telefonoContacto + '\'' +
                ", emailContacto='" + emailContacto + '\'' +
                ", fechaRegistro=" + fechaRegistro +
                ", fechaModificacion=" + fechaModificacion +
                ", status=" + status +
                ", cantidadProveedor=" + cantidadProveedor +
                ", tipoProductoId=" + tipoProductoId +
                ", sucursalId=" + sucursalId +
                ", precioProductos=" + precioProductos +
                '}';
    }
}
