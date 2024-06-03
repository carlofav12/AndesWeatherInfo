package com.DataClima.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "ENF_DEP_FEN")
public class Enf_dep_fen {
    @Id
    @Column(name = "COD_EDF")
    private long codEDF;

    @ManyToOne
    @JoinColumn(name = "COD_ENF")
    private Enfermedades enfermedad;

    @ManyToOne
    @JoinColumn(name = "COD_DEP_ENF")
    private DepartamentoFenomeno departamentoFenomeno;

    /* @Column(name = "DESCRIPCION")
    private String descripcion; */

    // Getters y Setters
    public long getCodEDF() {
        return codEDF;
    }

    public void setCodEDF(long codEDF) {
        this.codEDF = codEDF;
    }

    public Enfermedades getEnfermedad() {
        return enfermedad;
    }

    public void setEnfermedad(Enfermedades enfermedad) {
        this.enfermedad = enfermedad;
    }

    public DepartamentoFenomeno getDepartamentoFenomeno() {
        return departamentoFenomeno;
    }

    public void setDepartamentoFenomeno(DepartamentoFenomeno departamentoFenomeno) {
        this.departamentoFenomeno = departamentoFenomeno;
    }

   /*  public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    } */
}