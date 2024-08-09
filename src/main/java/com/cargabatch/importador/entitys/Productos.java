package com.cargabatch.importador.entitys;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "productos")
public class Productos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_producto")
    private Integer idProducto;

    @Column(name = "nombre", length = 30, nullable = false)
    private String nombre;

    @Column(name = "descripcion", length = 120)
    private String descripcion;

    @Column(name = "codigo")
    private String codigo;

    @Column(name = "fecha_registro")
    private Date fechaRegistro;

    @Column(name = "fecha_modificacion")
    private Date fechaModificacion;

    @Column(name = "cantidad_total")
    private Integer cantidadTotal;

    @Column(name = "precio_venta")
    private Float precioVenta;

    @ManyToOne
    @JoinColumn(name = "proveedor_id", referencedColumnName = "id_proveedor")
    private Proveedor proveedor;

    @ManyToOne
    @JoinColumn(name = "tipo_producto_id", referencedColumnName = "id_tipo_producto")
    private TipoProducto tipoProducto;

    @Column(name = "status")
    private Boolean status = true; // Valor por defecto para nuevos registros

    // Getters y Setters

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public TipoProducto getTipoProducto() {
        return tipoProducto;
    }

    public void setTipoProducto(TipoProducto tipoProducto) {
        this.tipoProducto = tipoProducto;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public Float getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(Float precioVenta) {
        this.precioVenta = precioVenta;
    }

    public Integer getCantidadTotal() {
        return cantidadTotal;
    }

    public void setCantidadTotal(Integer cantidadTotal) {
        this.cantidadTotal = cantidadTotal;
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

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
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

    public Integer getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }
}
