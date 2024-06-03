package com.DataClima.model.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "DEPARTAMENTOS")
public class Departamento {
    @Id
    @Column(name = "ID_DEP")
    private Long idDep;

    @Column(name = "NOMBRE")
    private String nombre;

    @Column(name = "EXTENSION_KM2")
    private Double extensionKm2;

    @Column(name = "POBLACION")
    private Long poblacion;

    @Column(name = "FOTO")
    private String foto;

    @Column(name = "ALTITUD")
    private String altitud;

    @Column(name = "DESCRIPCION", length = 1000)
    private String des;

    @ManyToOne
    @JoinColumn(name = "COD_ECOREGION")
    private Ecoregion ecoregion;

    @OneToMany(mappedBy = "departamento", fetch = FetchType.LAZY)
    private List<Provincia> provincias;

    @OneToMany(mappedBy = "departamento", fetch = FetchType.LAZY)
    private List<TemporadaDepartamento> temporadas;

    // Getters y Setters
    public Long getIdDep() {
        return idDep;
    }

    public void setIdDep(Long idDep) {
        this.idDep = idDep;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getExtensionKm2() {
        return extensionKm2;
    }

    public void setExtensionKm2(Double extensionKm2) {
        this.extensionKm2 = extensionKm2;
    }

    public Long getPoblacion() {
        return poblacion;
    }

    public void setPoblacion(Long poblacion) {
        this.poblacion = poblacion;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getAltitud() {
        return altitud;
    }

    public void setAltitud(String altitud) {
        this.altitud = altitud;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public List<Provincia> getProvincias() {
        return provincias;
    }

    public void setProvincias(List<Provincia> provincias) {
        this.provincias = provincias;
    }

    public Ecoregion getEcoregion() {
        return ecoregion;
    }

    public void setEcoregion(Ecoregion ecoregion) {
        this.ecoregion = ecoregion;
    }

    public List<TemporadaDepartamento> getTemporadas() {
        return temporadas;
    }

    public void setTemporadas(List<TemporadaDepartamento> temporadas) {
        this.temporadas = temporadas;
    }
    

}