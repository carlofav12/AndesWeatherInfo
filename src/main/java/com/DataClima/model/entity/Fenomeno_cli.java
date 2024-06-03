package com.DataClima.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Fenomenos_cli")
public class Fenomeno_cli {

    @Id
    @Column(name = "cod_fen_cli")
    private Long codFenomeno;

    @Column(name = "nombre_fen_cli")
    private String nombreFenomeno;

    @Column(name = "foto",length = 40)
    private String foto;

    // Getters y Setters
    public Long getCodFenomeno() {
        return codFenomeno;
    }

    public void setCodFenomeno(Long codFenomeno) {
        this.codFenomeno = codFenomeno;
    }

    public String getNombreFenomeno() {
        return nombreFenomeno;
    }

    public void setNombreFenomeno(String nombreFenomeno) {
        this.nombreFenomeno = nombreFenomeno;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
}