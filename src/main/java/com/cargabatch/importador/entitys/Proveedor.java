package com.cargabatch.importador.entitys;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
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
    @NotBlank(message = "El nombre del proveedor no puede estar vacío")
    @Size(max = 25, message = "El nombre del proveedor no puede exceder los 25 caracteres")
    private String nombre;

    @Column(name = "descripcion", length = 60)
    @Size(max = 60, message = "La descripción no puede exceder los 60 caracteres")
    private String descripcion;

    @Column(name = "contacto", length = 30)
    @Size(max = 30, message = "El contacto no puede exceder los 30 caracteres")
    private String contacto;

    @Column(name = "telefono_contacto")
    @Pattern(regexp = "^\\+?\\d*$", message = "El teléfono de contacto debe ser un número válido")
    private String telefonoContacto;

    @Column(name = "email_contacto", length = 50)
    @Email(message = "El email de contacto debe ser una dirección de correo válida")
    private String emailContacto;

    @Column(name = "fecha_registro")
    @Temporal(TemporalType.TIMESTAMP)
    @PastOrPresent(message = "La fecha de registro no puede estar en el futuro")
    private Date fechaRegistro;

    @Column(name = "fecha_modificacion")
    @Temporal(TemporalType.TIMESTAMP)
    @PastOrPresent(message = "La fecha de modificación no puede estar en el futuro")
    private Date fechaModificacion;

    @Column(name = "status")
    private boolean status = true; // Valor por defecto para nuevos registros

    @Column(name = "cantidad_proveedor")
    @Min(value = 0, message = "La cantidad del proveedor debe ser mayor o igual a 0")
    private int cantidadProveedor = 0;

    @ManyToOne
    @JoinColumn(name = "tipo_productos_id", referencedColumnName = "id_tipo_producto")
    private TipoProducto tipoProducto;

    @ManyToOne
    @JoinColumn(name = "sucursal_id", referencedColumnName = "id_sucursal")
    private Sucursal sucursal;

    @Column(name = "precio_productos")
    @Min(value = 0, message = "El precio de los productos debe ser mayor o igual a 0")
    private int precioProductos;

    @OneToMany(mappedBy = "proveedor")
    private List<Producto> productos;

    // Campo TipoEquipoEntity comentado
    /*
    @ManyToOne
    @JoinColumn(name = "tipo_equipo_id", referencedColumnName = "id_tipo_equipo")
    private TipoEquipoEntity tipoEquipo;
    */

    // Getters y setters

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

    public String getTelefonoContacto() {
        return telefonoContacto;
    }

    public void setTelefonoContacto(String telefonoContacto) {
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
        if (cantidadProveedor < 0) {
            throw new IllegalArgumentException("La cantidad debe ser mayor o igual a 0");
        }
        this.cantidadProveedor = cantidadProveedor;
    }

    public TipoProducto getTipoProducto() {
        return tipoProducto;
    }

    public void setTipoProducto(TipoProducto tipoProducto) {
        this.tipoProducto = tipoProducto;
    }

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
        if (precioProductos < 0) {
            throw new IllegalArgumentException("El precio debe ser mayor o igual a 0");
        }
        this.precioProductos = precioProductos;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    /*
    public TipoEquipoEntity getTipoEquipo() {
        return tipoEquipo;
    }

    public void setTipoEquipo(TipoEquipoEntity tipoEquipo) {
        this.tipoEquipo = tipoEquipo;
    }
    */

    @Override
    public String toString() {
        return "Proveedor{" +
                "idProveedor=" + idProveedor +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", contacto='" + contacto + '\'' +
                ", telefonoContacto='" + telefonoContacto + '\'' +
                ", emailContacto='" + emailContacto + '\'' +
                ", fechaRegistro=" + fechaRegistro +
                ", fechaModificacion=" + fechaModificacion +
                ", status=" + status +
                ", cantidadProveedor=" + cantidadProveedor +
                ", tipoProducto=" + (tipoProducto != null ? tipoProducto.getIdTipoProducto() : null) +
                ", sucursal=" + (sucursal != null ? sucursal.getIdSucursal() : null) +
                ", precioProductos=" + precioProductos +
                '}';
    }
}
