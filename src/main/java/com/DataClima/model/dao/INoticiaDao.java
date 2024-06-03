package com.DataClima.model.dao;

import org.springframework.data.repository.CrudRepository;

import com.DataClima.model.entity.Noticia;

public interface INoticiaDao extends CrudRepository<Noticia,Long>{
    
}
