package com.DataClima.model.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.DataClima.model.entity.Departamento;
import com.DataClima.model.entity.Provincia;


public interface IProvinciaDao extends CrudRepository<Provincia,Long>{

    List<Provincia> findByDepartamento(Departamento departamento);
}