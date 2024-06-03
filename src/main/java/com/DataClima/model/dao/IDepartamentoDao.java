package com.DataClima.model.dao;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.DataClima.model.entity.Departamento;

public interface IDepartamentoDao extends CrudRepository<Departamento, Long> {

    Optional<Departamento> findByNombre(String nombre); 
}
