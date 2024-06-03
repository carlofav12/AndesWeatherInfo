package com.DataClima.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "PROVINCIAS")
public class Provincia {
    @Id
    @Column(name = "COD_PROVINCIA")
    private Long codProvincia;

    @Column(name = "NOMBRE_PRO")
    private String nombreProvincia;

    @ManyToOne
    @JoinColumn(name = "COD_DEP")
    private Departamento departamento;

    // Getters y Setters
    public Long getCodProvincia() {
        return codProvincia;
    }

    public void setCodProvincia(Long codProvincia) {
        this.codProvincia = codProvincia;
    }

    public String getNombreProvincia() {
        return nombreProvincia;
    }

    public void setNombreProvincia(String nombreProvincia) {
        this.nombreProvincia = nombreProvincia;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }
}