package com.DataClima.service;

import java.util.List;

import com.DataClima.model.entity.Usuario;


public interface IUsuarioService {

	public List<Usuario> findUsuario();

	public void saveUsuario(Usuario usuario);

	public Usuario editarUsuario(Long id);

	public void eliminarUsuario(Long id);
	
    public boolean validarUsuario(String usuario, String contrasena);

    public Usuario findByUsuario(String usu);

	Usuario findById(Long id);
}
