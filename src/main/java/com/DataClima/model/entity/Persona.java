package com.DataClima.model.entity;

import java.io.Serializable;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="personas")
public class Persona implements Serializable{
	private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name="nombre")
    private String nombre;
    
    @Column(name="apellido_paterno")
    private String apePa;
    
    @Column(name="apellido_materno")
    private String apeMa;
    
    @Column(name="genero")
    private String genero;
    
    @Column(name="tipo_documento")
    private String tipDoc;
    
    @Column(name="numero_documento")
    private String numDoc;
    
    @Column(name="correo")
    private String correo;

	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_DEP")
    private Departamento departamento;
    
    @OneToOne(mappedBy = "persona", cascade = CascadeType.ALL)
    private Usuario usuario;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApePa() {
		return apePa;
	}

	public void setApePa(String apePa) {
		this.apePa = apePa;
	}

	public String getApeMa() {
		return apeMa;
	}

	public void setApeMa(String apeMa) {
		this.apeMa = apeMa;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getTipDoc() {
		return tipDoc;
	}

	public void setTipDoc(String tipDoc) {
		this.tipDoc = tipDoc;
	}

	public String getNumDoc() {
		return numDoc;
	}

	public void setNumDoc(String numDoc) {
		this.numDoc = numDoc;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }
    
	public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
}
