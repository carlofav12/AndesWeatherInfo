package com.DataClima.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.DataClima.model.dao.IProvinciaDao;
import com.DataClima.model.entity.Departamento;
import com.DataClima.model.entity.Provincia;

@Service
public class ProvinciaServiceImpl implements IProvinciaService{

    @Autowired
    private IProvinciaDao provinciaDao;

    @Override
    public List<Provincia> findByDepartamento(Departamento departamento) {
        return provinciaDao.findByDepartamento(departamento);

    }
}