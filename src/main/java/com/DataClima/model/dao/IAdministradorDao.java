package com.DataClima.model.dao;

import org.springframework.data.repository.CrudRepository;

import com.DataClima.model.entity.Administrador;

public interface IAdministradorDao extends CrudRepository<Administrador,Long>{

    Administrador findByNombre(String nombre);

}