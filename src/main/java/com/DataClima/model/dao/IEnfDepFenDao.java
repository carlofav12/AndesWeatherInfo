package com.DataClima.model.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.DataClima.model.entity.DepartamentoFenomeno;
import com.DataClima.model.entity.Enf_dep_fen;

public interface IEnfDepFenDao extends CrudRepository<Enf_dep_fen,Long>{

    List<Enf_dep_fen> findByDepartamentoFenomeno(DepartamentoFenomeno depFen);

}