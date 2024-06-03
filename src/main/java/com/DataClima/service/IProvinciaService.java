package com.DataClima.service;

import java.util.List;

import com.DataClima.model.entity.Departamento;
import com.DataClima.model.entity.Provincia;



public interface IProvinciaService {
    List<Provincia> findByDepartamento(Departamento departamento);
}