package com.DataClima.model.dao;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.DataClima.model.entity.Persona;
import com.DataClima.model.entity.Usuario;

import jakarta.transaction.Transactional;


public interface IPersonaDao extends CrudRepository<Persona, Long>{
	@Modifying
    @Transactional
    @Query("delete from Persona p where p.id = ?1")
    void deletePersonaByUsuario(Long id);
}
