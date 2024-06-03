package com.DataClima.model.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.DataClima.model.entity.Departamento;
import com.DataClima.model.entity.TemporadaDepartamento;

public interface ITemporadaDao extends CrudRepository<TemporadaDepartamento,Long>{

    List<TemporadaDepartamento> findByDepartamento(Departamento departamento);

}