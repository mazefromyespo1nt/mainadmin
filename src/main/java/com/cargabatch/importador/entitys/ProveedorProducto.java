package com.cargabatch.importador.entitys;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "proveedoresProductos")
public class ProveedorProducto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_proveedor_producto")
    private int idProveedorProducto;

    @ManyToOne
    @JoinColumn(name = "id_proveedor", referencedColumnName = "id_proveedor", nullable = false)
    private Proveedor proveedor;

    @ManyToOne
    @JoinColumn(name = "id_tipo_producto", referencedColumnName = "id_tipo_producto", nullable = false)
    private TipoProducto tipoProducto;

    @Column(name = "nombre", length = 25, nullable = false)
    @NotBlank(message = "El nombre no puede estar vacío")
    private String nombre;

    @Column(name = "descripcion", length = 60)
    private String descripcion;

    @Column(name = "fecha_registro", nullable = false)
    @NotNull(message = "La fecha de registro no puede ser nula")
    private Date fechaRegistro;

    @Column(name = "fecha_modificacion")
    private Date fechaModificacion;

    @Column(name = "cantidad_producto_id")
    @Min(value = 0, message = "La cantidad del producto debe ser al menos 0")
    private int cantidadProductoId;

    @Column(name = "status", nullable = false)
    @Min(value = 0, message = "El estado debe ser 0 o 1")
    private int status; // 1 for active, 0 for deleted

    // Getters y Setters

    public int getIdProveedorProducto() {
        return idProveedorProducto;
    }

    public void setIdProveedorProducto(int idProveedorProducto) {
        this.idProveedorProducto = idProveedorProducto;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public TipoProducto getTipoProducto() {
        return tipoProducto;
    }

    public void setTipoProducto(TipoProducto tipoProducto) {
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        if (status < 0 || status > 1) {
            throw new IllegalArgumentException("El estado debe ser 0 o 1");
        }
        this.status = status;
    }

    @Override
    public String toString() {
        return "ProveedorProducto{" +
                "idProveedorProducto=" + idProveedorProducto +
                ", proveedor=" + (proveedor != null ? proveedor.getIdProveedor() : "null") +
                ", tipoProducto=" + (tipoProducto != null ? tipoProducto.getIdTipoProducto() : "null") +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", fechaRegistro=" + fechaRegistro +
                ", fechaModificacion=" + fechaModificacion +
                ", cantidadProductoId=" + cantidadProductoId +
                ", status=" + status +
                '}';
    }
}
