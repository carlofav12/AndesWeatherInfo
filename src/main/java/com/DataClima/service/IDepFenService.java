package com.DataClima.service;

import java.util.List;

import com.DataClima.model.entity.Departamento;
import com.DataClima.model.entity.DepartamentoFenomeno;
import com.DataClima.model.entity.Fenomeno_cli;

public interface IDepFenService {

    List<DepartamentoFenomeno> findByDepartamento(Departamento departamento);

    List<DepartamentoFenomeno> findByFenomeno(Fenomeno_cli fenomeno);

    List<DepartamentoFenomeno> findByFenomenoAndDepartamento(Fenomeno_cli fenomeno, Departamento departamento);
}
