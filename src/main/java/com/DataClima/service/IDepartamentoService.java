package com.DataClima.service;

import java.util.List;

import com.DataClima.model.entity.Departamento;

public interface IDepartamentoService {
    List<Departamento> findAll();
    Departamento findByNombre(String departamento); 
    Departamento saveDepartamento(Departamento departamento);
    void deleteDepartamento(Long id);
    List<Departamento> getAllDepartamentos();
    Departamento findById(Long departamentoId);
}
