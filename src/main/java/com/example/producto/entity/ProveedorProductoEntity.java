package com.example.producto.entity;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "proveedoresProductos")
public class ProveedorProductoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_proveedor_producto")
    private int idProveedorProducto;

    @ManyToOne
    @JoinColumn(name = "id_proveedor", referencedColumnName = "id_proveedor")
    private ProveedorEntity proveedor;

    @ManyToOne
    @JoinColumn(name = "id_tipo_producto", referencedColumnName = "id_tipo_producto")
    private TipoProductoEntity tipoProducto;

    @Column(name = "nombre", length = 25)
    private String nombre;

    @Column(name = "descripcion", length = 60)
    private String descripcion;

    @Column(name = "fecha_registro")
    private Date fechaRegistro;

    @Column(name = "fecha_modificacion")
    private Date fechaModificacion;

    @Column(name = "cantidad_producto_id")
    private int cantidadProductoId;

    public int getIdProveedorProducto() {
        return idProveedorProducto;
    }

    public void setIdProveedorProducto(int idProveedorProducto) {
        this.idProveedorProducto = idProveedorProducto;
    }

    public ProveedorEntity getProveedor() {
        return proveedor;
    }

    public void setProveedor(ProveedorEntity proveedor) {
        this.proveedor = proveedor;
    }

    public TipoProductoEntity getTipoProducto() {
        return tipoProducto;
    }

    public void setTipoProducto(TipoProductoEntity tipoProducto) {
        this.tipoProducto = tipoProducto;
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
}