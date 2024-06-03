package com.DataClima.model.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.DataClima.model.entity.Departamento;
import com.DataClima.model.entity.DepartamentoFenomeno;
import com.DataClima.model.entity.Fenomeno_cli;

public interface IDepFenDao extends CrudRepository<DepartamentoFenomeno,Long>{

    List<DepartamentoFenomeno> findByDepartamento(Departamento departamento);

    List<DepartamentoFenomeno> findByFenomeno(Fenomeno_cli fenomeno);

    List<DepartamentoFenomeno> findByFenomenoAndDepartamento(Fenomeno_cli fenomeno, Departamento departamento);


}