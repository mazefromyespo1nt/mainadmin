package com.cargabatch.importador.entitys;

import jakarta.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "proveedores")
public class Proveedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_proveedor")
    private int idProveedor;

    @Column(name = "nombre", length = 25, nullable = false)
    private String nombre;

    @Column(name = "descripcion", length = 60)
    private String descripcion;

    @Column(name = "contacto", length = 30)
    private String contacto;

    @Column(name = "telefono_contacto")
    private int telefonoContacto;

    @Column(name = "email_contacto", length = 50)
    private String emailContacto;

    @Column(name = "fecha_registro")
    private Date fechaRegistro;

    @Column(name = "fecha_modificacion")
    private Date fechaModificacion;

    @Column(name = "status")
    private boolean status;

    @Column(name = "cantidad_proveedor")
    private int cantidadProveedor;

    @ManyToOne
    @JoinColumn(name = "tipo_productos_id", referencedColumnName = "id_tipo_producto")
    private TipoProducto tipoProducto;

/*    @ManyToOne
    @JoinColumn(name = "tipo_equipos_id", referencedColumnName = "id_tipo_equipo")
    private TipoEquipoEntity tipoEquipo;*/

    @ManyToOne
    @JoinColumn(name = "sucursal_id", referencedColumnName = "id_sucursal")
    private Sucursal sucursal;

    // comenta tus funciones
    @Column(name = "precio_productos")
    private int precioProductos;

    @OneToMany(mappedBy = "proveedor")
    private List<Productos> productos;


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

    public int getTelefonoContacto() {
        return telefonoContacto;
    }

    public void setTelefonoContacto(int telefonoContacto) {
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

    public TipoProducto getTipoProducto() {
        return tipoProducto;
    }

    public void setTipoProducto(TipoProducto tipoProducto) {
        this.tipoProducto = tipoProducto;
    }

   /* public TipoEquipoEntity getTipoEquipo() {
        return tipoEquipo;
    }

    public void setTipoEquipo(TipoEquipoEntity tipoEquipo) {
        this.tipoEquipo = tipoEquipo;
    }*/

    public Sucursal getSucursal() {
        return sucursal;
    }

    public void setSucursal(Sucursal sucursal) {
        this.sucursal = sucursal;
    }

    public int getPrecioProductos() {
        return precioProductos;
    }

    public void setPrecioProductos(int precioProductos) {
        this.precioProductos = precioProductos;
    }

    public List<Productos> getProductos() {
        return productos;
    }

    public void setProductos(List<Productos> productos) {
        this.productos = productos;
    }
}