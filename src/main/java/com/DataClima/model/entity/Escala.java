package com.DataClima.model.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "ESCALA")
public class Escala {

    @Id
    @Column(name = "COD_ESCALA")
    private long codEscala;

    @Column(name = "NOMBRE_ESCALA")
    private String NombreEscala;
    
    @OneToMany(mappedBy = "escala")
    private List<Enfermedades> enfermedades;

    //getters and 
    public long getCodEscala() {
        return codEscala;
    }

    public void setCodEscala(long codEscala) {
        this.codEscala = codEscala;
    }

    public String getNombreEscala() {
        return NombreEscala;
    }

    public void setNombreEscala(String nombreEscala) {
        NombreEscala = nombreEscala;
    }

    public List<Enfermedades> getEnfermedades() {
        return enfermedades;
    }

    public void setEnfermedades(List<Enfermedades> enfermedades) {
        this.enfermedades = enfermedades;
    }
    
}