package com.DataClima.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "encuestas")
public class Encuesta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    
    @Size(max = 900, message = "El campo admite máximo 900 caracteres")
    @Column(name = "temas_propuestos")
    private String temasPropuestos;

   
    @Column(name = "recomendaciones")
    private String recomendaciones;

    @NotNull
    @Min(1)
    @Max(5)
    @Column(name = "nivel_satisfaccion")
    private Integer nivelSatisfaccion;

    @NotNull
    @Min(1)
    @Max(5)
    @Column(name = "nivel_estructuracion")
    private Integer nivelEstructuracion;
    

    @Size(max = 900, message = "El campo admite máximo 900 caracteres")
    @Column(name = "sugerencias")
    private String sugerencias;

    
    @Email
    @Column(name = "correo_alternativo")
    private String correoAlternativo;


    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    public Encuesta() {
    }

    public Encuesta(String temasPropuestos, String recomendaciones, Integer nivelSatisfaccion, Integer nivelEstructuracion, String sugerencias, String correoAlternativo, Usuario usuario) {
        this.temasPropuestos = temasPropuestos;
        this.recomendaciones = recomendaciones;
        this.nivelSatisfaccion = nivelSatisfaccion;
        this.nivelEstructuracion = nivelEstructuracion;
        this.sugerencias = sugerencias;
        this.correoAlternativo = correoAlternativo;
       
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTemasPropuestos() {
        return temasPropuestos;
    }

    public void setTemasPropuestos(String temasPropuestos) {
        this.temasPropuestos = temasPropuestos;
    }

    public String getRecomendaciones() {
        return recomendaciones;
    }

    public void setRecomendaciones(String recomendaciones) {
        this.recomendaciones = recomendaciones;
    }

    public Integer getNivelSatisfaccion() {
        return nivelSatisfaccion;
    }

    public void setNivelSatisfaccion(Integer nivelSatisfaccion) {
        this.nivelSatisfaccion = nivelSatisfaccion;
    }

    public Integer getNivelEstructuracion() {
        return nivelEstructuracion;
    }

    public void setNivelEstructuracion(Integer nivelEstructuracion) {
        this.nivelEstructuracion = nivelEstructuracion;
    }

    public String getSugerencias() {
        return sugerencias;
    }

    public void setSugerencias(String sugerencias) {
        this.sugerencias = sugerencias;
    }

    public String getCorreoAlternativo() {
        return correoAlternativo;
    }

    public void setCorreoAlternativo(String correoAlternativo) {
        this.correoAlternativo = correoAlternativo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}