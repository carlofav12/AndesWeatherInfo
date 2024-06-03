package com.DataClima.model.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.DataClima.model.entity.Encuesta;
import com.DataClima.model.entity.Usuario;

public interface IEncuestaDao extends CrudRepository<Encuesta,Long>{

    List<Encuesta> findByUsuario(Usuario usuario);

}
