package com.DataClima.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.DataClima.model.dao.ILoginDao;
import com.DataClima.model.entity.Usuario;

import java.util.Optional;

@Service
public class UsuarioServiceImpl implements IUsuarioService {

	@Autowired
	private ILoginDao loginDao;

	@Override
	@Transactional(readOnly = true)
	public List<Usuario> findUsuario() {
		// TODO Auto-generated method stub
		return (List<Usuario>) loginDao.findAll();
	}

	@Override
	public void saveUsuario(Usuario usuario) {
		// TODO Auto-generated method stub
		loginDao.save(usuario);
	}

	@Override
	@Transactional(readOnly = true)
	public Usuario editarUsuario(Long id) {
		// TODO Auto-generated method stub
		return loginDao.findById(id).orElse(null);
	}

	@Override
	public void eliminarUsuario(Long id) {
		// TODO Auto-generated method stub
		loginDao.deleteUsuarioById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public boolean validarUsuario(String usuario, String contrasena) {
		Usuario usu = loginDao.findByUsu(usuario);

		if (usu != null && usu.getContra().equals(contrasena)) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public Usuario findByUsuario(String usu) {
		return loginDao.findByUsu(usu);
	}

	@Override
    public Usuario findById(Long id) {
        Optional<Usuario> optionalUsuario = loginDao.findById(id);
        return optionalUsuario.orElse(null);
    }

}
