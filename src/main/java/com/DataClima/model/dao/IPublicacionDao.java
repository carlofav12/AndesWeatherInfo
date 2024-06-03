package com.DataClima.model.dao;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.DataClima.model.entity.Publicacion;
import com.DataClima.model.entity.Usuario;

import jakarta.transaction.Transactional;

public interface IPublicacionDao extends CrudRepository<Publicacion, Long>{
    @Modifying
    @Transactional
    @Query("delete from Publicacion p where p.usuario = ?1")
    void deletePublicacionesByUsuario(Usuario usuario);
}
