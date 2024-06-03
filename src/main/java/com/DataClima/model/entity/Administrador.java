package com.DataClima.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "ADMINISTRADORES")
public class Administrador {
    @Id
    @Column(name = "ID_ADMIN")
    private Long idAdmin;

    @Column(name = "NOMBRE")
    private String nombre;

    @Column(name = "FOTO")
    private String foto;

    @Column(name = "ROL")
    private String rol;

    @Column(name= "CONTRASEÑA")
    private String contra;

    // Getters y Setters
    public Long getIdAdmin() {
        return idAdmin;
    }

    public void setIdAdmin(Long idAdmin) {
        this.idAdmin = idAdmin;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getContra() {
        return contra;
    }

    public void setContra(String contra) {
        this.contra = contra;
    }

}