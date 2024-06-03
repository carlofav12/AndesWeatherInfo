package com.DataClima.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Prevenciones")
public class Prevenciones {

    @Id
    @Column(name = "ID_PREVENCION")
    private Long IdPrevencion;

    @ManyToOne
    @JoinColumn(name = "COD_DEP_FEN")
    private DepartamentoFenomeno departamentoFenomeno;

    @Column(name = "DESCRIPCION_PRE", length = 1000)
    private String descripcionPre;

    //getter y s
    public Long getIdPrevencion() {
        return IdPrevencion;
    }

    public void setIdPrevencion(Long idPrevencion) {
        IdPrevencion = idPrevencion;
    }

    public DepartamentoFenomeno getDepartamentoFenomeno() {
        return departamentoFenomeno;
    }

    public void setDepartamentoFenomeno(DepartamentoFenomeno departamentoFenomeno) {
        this.departamentoFenomeno = departamentoFenomeno;
    }

    public String getDescripcionPre() {
        return descripcionPre;
    }

    public void setDescripcionPre(String descripcionPre) {
        this.descripcionPre = descripcionPre;
    }    
}