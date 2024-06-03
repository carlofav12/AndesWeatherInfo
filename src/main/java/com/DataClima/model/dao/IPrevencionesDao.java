package com.DataClima.model.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.DataClima.model.entity.DepartamentoFenomeno;
import com.DataClima.model.entity.Prevenciones;

public interface IPrevencionesDao extends CrudRepository<Prevenciones,Long>{

    List<Prevenciones> findByDepartamentoFenomeno(DepartamentoFenomeno depFen);

}