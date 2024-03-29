package org.javacoreuocx.alquilatusvehiculos.model;

import jakarta.persistence.*;

@Entity
@Table(name = "oficina")
public class Oficina {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String direccion;
    private String ciudad;
    private String telefono;
    private String codigoPostal;

    public Oficina() {
    }

    public Oficina(String direccion, String ciudad, String telefono, String codigo_postal) {
        this.direccion = direccion;
        this.ciudad = ciudad;
        this.telefono = telefono;
        this.codigoPostal = codigo_postal;
    }

    public Oficina(Long id, String direccion, String ciudad, String telefono, String codigo_postal) {
        this.id = id;
        this.direccion = direccion;
        this.ciudad = ciudad;
        this.telefono = telefono;
        this.codigoPostal = codigo_postal;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }
}
