package com.example.producto.entity;

import jakarta.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "sucursales")
public class SucursalEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sucursal")
    private int idSucursal;

//    @OneToMany(mappedBy = "sucursal")
//    private List<EquipoEntity> equipos;

    @Column(name = "nombre", length = 50)
    private String nombre;

    @Column(name = "direccion", length = 50)
    private String direccion;

    @Column(name = "fecha_registro")
    private Date fechaRegistro;

    @Column(name = "fecha_modificacion")
    private Date fechaModificacion;

    @Column(name = "capacidad")
    private int capacidad;

    @Column(name = "dimensiones", length = 20)
    private String dimensiones;

    @Column(name = "status")
    private boolean status;

    @Column(name = "modificables", length = 255)
    private String modificables;

    // Getters and Setters
    public int getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(int idSucursal) {
        this.idSucursal = idSucursal;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
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

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public String getDimensiones() {
        return dimensiones;
    }

    public void setDimensiones(String dimensiones) {
        this.dimensiones = dimensiones;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getModificables() {
        return modificables;
    }

   public void setModificables(String modificables) {
        this.modificables = modificables;
    }
   /*  public List<EquipoEntity> getEquipos() {
        return equipos;
    }

    public void setEquipos(List<EquipoEntity> equipos) {
        this.equipos = equipos;
    }*/
}
