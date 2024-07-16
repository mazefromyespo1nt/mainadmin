package com.example.producto.entity;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "proveedoresEquipos")
public class ProveedorEquipoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_proveedor_equipo")
    private int idProveedorEquipo;

    @ManyToOne
    @JoinColumn(name = "id_proveedor", referencedColumnName = "id_proveedor")
    private ProveedorEntity proveedor;

    @ManyToOne
    @JoinColumn(name = "tipo_equipo_id", referencedColumnName = "id_tipo_equipo")
    private TipoEquipoEntity tipoEquipo;

    @Column(name = "nombre", length = 25)
    private String nombre;

    @Column(name = "descripcion", length = 60)
    private String descripcion;

    @Column(name = "fecha_registro")
    private Date fechaRegistro;

    @Column(name = "fecha_modificacion")
    private Date fechaModificacion;

    @Column(name = "cantidad_equipo_id")
    private int cantidadEquipoId;

    // Getters and Setters
    public int getIdProveedorEquipo() {
        return idProveedorEquipo;
    }

    public void setIdProveedorEquipo(int idProveedorEquipo) {
        this.idProveedorEquipo = idProveedorEquipo;
    }

    public ProveedorEntity getProveedor() {
        return proveedor;
    }

    public void setProveedor(ProveedorEntity proveedor) {
        this.proveedor = proveedor;
    }

    public TipoEquipoEntity getTipoEquipo() {
        return tipoEquipo;
    }

    public void setTipoEquipo(TipoEquipoEntity tipoEquipo) {
        this.tipoEquipo = tipoEquipo;
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

    public int getCantidadEquipoId() {
        return cantidadEquipoId;
    }

    public void setCantidadEquipoId(int cantidadEquipoId) {
        this.cantidadEquipoId = cantidadEquipoId;
    }
}