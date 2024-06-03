package com.DataClima.model.entity;

import java.io.Serializable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "noticias")
public class Noticia implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="Id_Noti")
    private Long id;

    @Column(name = "descrip_noticia")
    private String desNoticia;

    @Column(name = "imagen_noticia")
    private String imgNoti;

    @Column(name = "link")
    private String link;


    @ManyToOne
    @JoinColumn(name = "ID_ADMIN") 
    private Administrador administrador;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getDesNoticia() {
        return desNoticia;
    }

    public void setDesNoticia(String desNoticia) {
        this.desNoticia = desNoticia;
    }

    public String getImgNoti() {
        return imgNoti;
    }

    public void setImgNoti(String imgNoti) {
        this.imgNoti = imgNoti;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Administrador getAdministrador() {
        return administrador;
    }

    public void setAdministrador(Administrador administrador) {
        this.administrador = administrador;
    }
}
