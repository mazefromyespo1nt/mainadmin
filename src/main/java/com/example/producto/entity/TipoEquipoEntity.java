package com.example.producto.entity;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tipoEquipos")
public class TipoEquipoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo_equipo")
    private int idTipoEquipo;

    @Column(name = "nombre", length = 25)
    private String nombre;

    @Column(name = "descripcion", length = 60)
    private String descripcion;

    /*@ManyToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "id_usuario")
    private UsuarioEntity usuario;*/

    @Column(name = "fecha_registro")
    private Date fechaRegistro;

    @Column(name = "fecha_modificacion")
    private Date fechaModificacion;

    @Column(name = "consideraciones_cuidado", length = 255)
    private String consideracionesCuidado;

    @Column(name = "status")
    private boolean status;

    // Getters and Setters
    public int getIdTipoEquipo() {
        return idTipoEquipo;
    }

    public void setIdTipoEquipo(int idTipoEquipo) {
        this.idTipoEquipo = idTipoEquipo;
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

    public String getConsideracionesCuidado() {
        return consideracionesCuidado;
    }

    public void setConsideracionesCuidado(String consideracionesCuidado) {
        this.consideracionesCuidado = consideracionesCuidado;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}