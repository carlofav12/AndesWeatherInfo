package com.DataClima.model.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.DataClima.model.entity.Enfermedades;
import com.DataClima.model.entity.Prevencion_Enf;

public interface IPrevencionEnfDao extends CrudRepository<Prevencion_Enf,Long>{

    List<Prevencion_Enf> findByEnfermedad(Enfermedades enfermedad);

}