package com.DataClima.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.DataClima.model.dao.ILoginDao;
import com.DataClima.model.dao.IPersonaDao;
import com.DataClima.model.entity.Persona;
import com.DataClima.model.entity.Usuario;

@Service
public class PersonaServiceImpl implements IPersonaService {

	@Autowired
	private IPersonaDao personaDao;
	
	
	@Override
	@Transactional(readOnly=true)
	public List<Persona> findPersona() {
		// TODO Auto-generated method stub
		return (List<Persona>) personaDao.findAll();
	}

	@Override
	public void savePersona(Persona persona) {
		// TODO Auto-generated method stub
			personaDao.save(persona);
	}

	@Override
	@Transactional(readOnly=true)
	public Persona editarPersona(Long id) {
		// TODO Auto-generated method stub
		return personaDao.findById(id).orElse(null);
	}

	@Override
	public void eliminarPersona(Long id) {
		// TODO Auto-generated method stub
		personaDao.deleteById(id);
	}

	@Override
	public Persona findById(Long id) {
		Optional<Persona> optionalUsuario = personaDao.findById(id);
        return optionalUsuario.orElse(null);
	}

	@Override
	public void eliminarPersonaPorUsuario(Long id) {
		personaDao.deletePersonaByUsuario(id);
	}

}
