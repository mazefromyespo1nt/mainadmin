package com.cargabatch.importador.entitys;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "tipo_producto")
public class TipoProducto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo_producto")
    private int idTipoProducto;

    @Column(name = "nombre", length = 25, nullable = false)
    @NotBlank(message = "El nombre no puede estar vacío")
    @Size(max = 25, message = "El nombre no puede exceder los 25 caracteres")
    private String nombre;

    @Column(name = "descripcion", length = 100)
    @Size(max = 100, message = "La descripción no puede exceder los 100 caracteres")
    private String descripcion;

    @Column(name = "fecha_registro")
    @PastOrPresent(message = "La fecha de registro no puede estar en el futuro")
    private Date fechaRegistro;

    @Column(name = "fecha_modificacion")
    @PastOrPresent(message = "La fecha de modificación no puede estar en el futuro")
    private Date fechaModificacion;

    @Column(name = "status")
    @Min(value = 0, message = "El status debe ser 0 (inactivo) o 1 (activo)")
    @Max(value = 1, message = "El status debe ser 0 (inactivo) o 1 (activo)")
    private int status; // 1 for active, 0 for deleted

    @OneToMany(mappedBy = "tipoProducto")
    private List<Producto> productos;

    // Getters y Setters

    public int getIdTipoProducto() {
        return idTipoProducto;
    }

    public void setIdTipoProducto(int idTipoProducto) {
        this.idTipoProducto = idTipoProducto;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    @Override
    public String toString() {
        return "TipoProducto{" +
                "idTipoProducto=" + idTipoProducto +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", fechaRegistro=" + fechaRegistro +
                ", fechaModificacion=" + fechaModificacion +
                ", status=" + status +
                '}';
    }
}
