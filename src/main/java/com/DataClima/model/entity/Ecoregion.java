package com.DataClima.model.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "ECOREGIONES")
public class Ecoregion {

    @Id
    @Column(name = "COD_ECOREGION")
    private Long CodEcoRegion;

    @Column(name = "NOMBRE",length = 24)
    private String nombre;

    @OneToMany(mappedBy = "ecoregion", fetch = FetchType.LAZY)
    private List<Departamento> departamentos;

    //getters
    public Long getCodEcoRegion() {
        return CodEcoRegion;
    }

    public void setCodEcoRegion(Long codEcoRegion) {
        CodEcoRegion = codEcoRegion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Departamento> getDepartamentos() {
        return departamentos;
    }

    public void setDepartamentos(List<Departamento> departamentos) {
        this.departamentos = departamentos;
    }

    
}