package com.example.producto.entity;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "equipos")
public class EquipoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_equipo")
    private int idEquipo;

    @Column(name = "nombre", length = 25)
    private String nombre;

    @Column(name = "descripcion", length = 80)
    private String descripcion;

    /*@ManyToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "id_usuario")
    private UsuarioEntity usuario;*/

    @Column(name = "fecha_registro")
    private Date fechaRegistro;

    @Column(name = "fecha_modificacion")
    private Date fechaModificacion;

    @ManyToOne
    @JoinColumn(name = "tipo_equipo_id", referencedColumnName = "id_tipo_equipo")
    private TipoEquipoEntity tipoEquipo;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado")
    private Estado estado;

    @ManyToOne
    @JoinColumn(name = "sucursal_id", referencedColumnName = "id_sucursal")
    private SucursalEntity sucursal;

    @Column(name = "status")
    private boolean status;

    // Getters and Setters
    public int getIdEquipo() {
        return idEquipo;
    }

    public void setIdEquipo(int idEquipo) {
        this.idEquipo = idEquipo;
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

    /*public UsuarioEntity getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioEntity usuario) {
        this.usuario = usuario;
    }*/

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

    public TipoEquipoEntity getTipoEquipo() {
        return tipoEquipo;
    }

    public void setTipoEquipo(TipoEquipoEntity tipoEquipo) {
        this.tipoEquipo = tipoEquipo;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public SucursalEntity getSucursal() {
        return sucursal;
    }

    public void setSucursal(SucursalEntity sucursal) {
        this.sucursal = sucursal;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public enum Estado {
        ACTIVO, INACTIVO
    }
}