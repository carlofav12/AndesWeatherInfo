package com.DataClima.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "PREVENCION_ENF")
public class Prevencion_Enf {

    @Id
    @Column(name = "COD_PREV_ENF")
    private long codPrevEnf;

    @Column(name = "DESCRIPCION")
    private String DESCRIPCION;

    @ManyToOne
    @JoinColumn(name = "COD_ENF")
    private Enfermedades enfermedad;
    //getters
    public long getCodPrevEnf() {
        return codPrevEnf;
    }

    public void setCodPrevEnf(long codPrevEnf) {
        this.codPrevEnf = codPrevEnf;
    }

    public String getDESCRIPCION() {
        return DESCRIPCION;
    }

    public void setDESCRIPCION(String dESCRIPCION) {
        DESCRIPCION = dESCRIPCION;
    }

    public Enfermedades getEnfermedad() {
        return enfermedad;
    }

    public void setEnfermedad(Enfermedades enfermedad) {
        this.enfermedad = enfermedad;
    }

}