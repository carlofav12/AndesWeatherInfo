package com.DataClima.model.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table (name = "ENFERMEDADES")
public class Enfermedades {

    @Id
    @Column(name = "COD_ENF")
    private Long codEnf;

    @Column(name = "NOMBRE_ENF")
    private String nomEnfer;

    @Column(name = "FOTO", length = 40)
    private String foto;

    @Column(name = "DESCRIPCION", length = 500)
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "COD_ESCALA")
    private Escala escala;

    @OneToMany(mappedBy = "enfermedad")
    private List<Enf_dep_fen> enfDepFenList;

    @OneToMany(mappedBy = "enfermedad")
    private List<Prevencion_Enf> prevencionEnfList;
    
    //getters 
    public Long getCodEnf() {
        return codEnf;
    }

    public void setCodEnf(Long codEnf) {
        this.codEnf = codEnf;
    }

    public String getNomEnfer() {
        return nomEnfer;
    }

    public void setNomEnfer(String nomEnfer) {
        this.nomEnfer = nomEnfer;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public Escala getEscala() {
        return escala;
    }

    public void setEscala(Escala escala) {
        this.escala = escala;
    }

    public List<Enf_dep_fen> getEnfDepFenList() {
        return enfDepFenList;
    }

    public void setEnfDepFenList(List<Enf_dep_fen> enfDepFenList) {
        this.enfDepFenList = enfDepFenList;
    }

    public List<Prevencion_Enf> getPrevencionEnfList() {
        return prevencionEnfList;
    }

    public void setPrevencionEnfList(List<Prevencion_Enf> prevencionEnfList) {
        this.prevencionEnfList = prevencionEnfList;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
}