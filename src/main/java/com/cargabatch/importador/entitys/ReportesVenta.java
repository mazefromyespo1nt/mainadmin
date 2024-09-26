package com.cargabatch.importador.entitys;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "reportes_ventas")
public class ReportesVenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_venta")
    private Integer idVenta;

    @Column(name = "fecha", updatable = false)
    private LocalDateTime fecha;

    @Column(name = "cantidad")
    private Float cantidad;

    @Column(name = "precio_venta")
    private Float precioVenta;

    @ManyToOne
    @JoinColumn(name = "producto_id", referencedColumnName = "id_producto")
    private Producto producto;  // Aquí está la relación con Producto

    @Column(name = "total")
    private Float total;
//    @ManyToOne
//    @JoinColumn(name = "roles_id", referencedColumnName = "id_roles")
//    private CatalogoRoles roles;

//    @ManyToOne
//    @JoinColumn(name = "usuario_id", referencedColumnName = "id_usuario")
//    private Usuario usuario;


    // Getters and Setters
    public Integer getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(Integer idVenta) {
        this.idVenta = idVenta;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public Float getCantidad() {
        return cantidad;
    }

    public void setCantidad(Float cantidad) {
        this.cantidad = cantidad;
    }

    public Float getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(Float precioVenta) {
        this.precioVenta = precioVenta;
    }

//    public CatalogoRoles getRoles() {
//        return roles;
//    }
//
//    public void setRoles(CatalogoRoles roles) {
//        this.roles = roles;
//    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

//    public Usuario getUsuario() {
//        return usuario;
//    }
//
//    public void setUsuario(Usuario usuario) {
//        this.usuario = usuario;
//    }

    public Float getTotal() {
        return total;
    }

    public void setTotal(Float total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "ReportesVenta{" +
                "idVenta=" + idVenta +
                ", fecha=" + fecha +
                ", cantidad=" + cantidad +
                ", precioVenta=" + precioVenta +
                //", roles=" + roles +
                ", producto=" + producto +
                //", usuario=" + usuario +
                ", total=" + total +
                '}';
    }
}
