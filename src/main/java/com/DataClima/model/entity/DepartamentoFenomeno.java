package com.DataClima.model.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "DEPARTAMENTO_FENOMENO")
public class DepartamentoFenomeno {
    @Id
    @Column(name="COD_DEP_FEN")
    private long codDepFen;

    @ManyToOne
    @JoinColumn(name = "ID_DEP")
    private Departamento departamento;

    @ManyToOne
    @JoinColumn(name = "COD_FENOMENO")
    private Fenomeno_cli fenomeno;

    @Column(name = "DESCRIPCION_FE1",length = 1000)
    private String descripcion1;

    @Column(name = "DESCRIPCION_FE2",length = 1000)
    private String descripcion2;

    @OneToMany(mappedBy = "departamentoFenomeno")
    private List<Prevenciones> prevenciones;

    @OneToMany(mappedBy = "departamentoFenomeno")
    private List<Enf_dep_fen> enfDepFenList;

    // Getters y Setters
    public long getCodDepFen() {
        return codDepFen;
    }

    public void setCodDepFen(long codDepFen) {
        this.codDepFen = codDepFen;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public Fenomeno_cli getFenomeno() {
        return fenomeno;
    }

    public void setFenomeno(Fenomeno_cli fenomeno) {
        this.fenomeno = fenomeno;
    }

    public String getDescripcion1() {
        return descripcion1;
    }

    public void setDescripcion1(String descripcion1) {
        this.descripcion1 = descripcion1;
    }

    public String getDescripcion2() {
        return descripcion2;
    }

    public void setDescripcion2(String descripcion2) {
        this.descripcion2 = descripcion2;
    }

    public List<Prevenciones> getPrevenciones() {
        return prevenciones;
    }

    public void setPrevenciones(List<Prevenciones> prevenciones) {
        this.prevenciones = prevenciones;
    }

    public List<Enf_dep_fen> getEnfDepFenList() {
        return enfDepFenList;
    }

    public void setEnfDepFenList(List<Enf_dep_fen> enfDepFenList) {
        this.enfDepFenList = enfDepFenList;
    }

    public List<Enfermedades> getEnfermedades() {
        List<Enfermedades> enfermedades = new ArrayList<>();
        for (Enf_dep_fen enfDepFen : enfDepFenList) {
            enfermedades.add(enfDepFen.getEnfermedad());
        }
        return enfermedades;
    }
}