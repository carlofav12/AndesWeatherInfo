package com.DataClima.service;
import java.util.List;

import com.DataClima.model.entity.Persona;
import com.DataClima.model.entity.Usuario;


public interface IPersonaService {
	
	public List<Persona> findPersona();

	public void savePersona(Persona persona);

	public Persona editarPersona(Long id);

	public void eliminarPersona(Long id);

	public Persona findById(Long id);
	
    public void eliminarPersonaPorUsuario(Long id);

}
