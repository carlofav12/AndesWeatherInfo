package com.DataClima.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.DataClima.model.dao.ITemporadaDao;
import com.DataClima.model.entity.Departamento;
import com.DataClima.model.entity.TemporadaDepartamento;

@Service
public class TemporadaDepartamentoServiceImpl implements ITemporadaDepartamentoService{

    @Autowired
    private ITemporadaDao temporadaDao;

    @Override
    public List<TemporadaDepartamento> findByDepartamento(Departamento departamento) {
        return temporadaDao.findByDepartamento(departamento);
    }

}