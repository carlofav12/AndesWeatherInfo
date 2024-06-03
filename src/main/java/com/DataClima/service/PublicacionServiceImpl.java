package com.DataClima.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.DataClima.model.dao.IPublicacionDao;
import com.DataClima.model.entity.Publicacion;
import com.DataClima.model.entity.Usuario;

@Service
public class PublicacionServiceImpl implements IPublicacionService{


	@Autowired
	private IPublicacionDao publicacionDao;
	
	@Override
	@Transactional(readOnly=true)
	public List<Publicacion> findPublicacion() {
		return (List<Publicacion>) publicacionDao.findAll();
	}

	@Override
	public void savePublicacion(Publicacion publicacion) {
		publicacionDao.save(publicacion);		
	}
	
	@Override
	@Transactional(readOnly=true)
	public Publicacion editarPublicacion(Long id) {
		return publicacionDao.findById(id).orElse(null);
	}

	@Override
	public void eliminarPublicacion(Long id) {
		publicacionDao.deleteById(id);
		
	}
    
	@Override
    @Transactional(readOnly=true)
    public void eliminarPublicacionesPorUsuario(Usuario usuario) {
        publicacionDao.deletePublicacionesByUsuario(usuario);
    }
	
}
