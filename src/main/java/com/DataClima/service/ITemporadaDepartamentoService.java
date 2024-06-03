package com.DataClima.service;

import java.util.List;

import com.DataClima.model.entity.Departamento;
import com.DataClima.model.entity.TemporadaDepartamento;

public interface ITemporadaDepartamentoService {

    List<TemporadaDepartamento> findByDepartamento(Departamento departamento);

}