package com.DataClima.service;

import java.util.List;

import com.DataClima.model.entity.Publicacion;
import com.DataClima.model.entity.Usuario;

public interface IPublicacionService {
	public List<Publicacion> findPublicacion();

	public void savePublicacion(Publicacion publicacion);

	public Publicacion editarPublicacion(Long id);

	public void eliminarPublicacion(Long id);
	
    public void eliminarPublicacionesPorUsuario(Usuario usuario);

}
