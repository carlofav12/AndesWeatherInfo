package com.DataClima.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "TEMPORADA_DEPARTAMENTO")
public class TemporadaDepartamento {

    @Id
    @Column(name = "COD_CLIMA")
    private Long codClima;

    @ManyToOne
    @JoinColumn(name = "ID_DEP")
    private Departamento departamento;

    @Column(name = "NOM_ESTACION",length = 20)
    private String nomEstacion;

    @Column(name = "INICIO_E",length = 15)
    private String inicioE;

    @Column(name = "FIN_E",length = 15)
    private String finE;

    @Column(name = "CLIMA",length = 23)
    private String clima;

    @Column(name = "TEMP_MAX")
    private Double tempMax;

    @Column(name = "TEMP_MIN")
    private Double tempMin;

    //getters
    public Long getCodClima() {
        return codClima;
    }

    public void setCodClima(Long codClima) {
        this.codClima = codClima;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public String getNomEstacion() {
        return nomEstacion;
    }

    public void setNomEstacion(String nomEstacion) {
        this.nomEstacion = nomEstacion;
    }

    public String getInicioE() {
        return inicioE;
    }

    public void setInicioE(String inicioE) {
        this.inicioE = inicioE;
    }

    public String getFinE() {
        return finE;
    }

    public void setFinE(String finE) {
        this.finE = finE;
    }

    public String getClima() {
        return clima;
    }

    public void setClima(String clima) {
        this.clima = clima;
    }

    public Double getTempMax() {
        return tempMax;
    }

    public void setTempMax(Double tempMax) {
        this.tempMax = tempMax;
    }

    public Double getTempMin() {
        return tempMin;
    }

    public void setTempMin(Double tempMin) {
        this.tempMin = tempMin;
    }    
}