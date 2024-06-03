package com.DataClima.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.DataClima.model.dao.IDepFenDao;
import com.DataClima.model.entity.Departamento;
import com.DataClima.model.entity.DepartamentoFenomeno;
import com.DataClima.model.entity.Fenomeno_cli;

@Service
public class DepFenServiceImpl implements IDepFenService {

    @Autowired
    private IDepFenDao depFenDao;

    @Override
    public List<DepartamentoFenomeno> findByDepartamento(Departamento departamento) {
        return depFenDao.findByDepartamento(departamento);
    }

    @Override
    public List<DepartamentoFenomeno> findByFenomeno(Fenomeno_cli fenomeno) {
        return depFenDao.findByFenomeno(fenomeno);
    }

    @Override
    public List<DepartamentoFenomeno> findByFenomenoAndDepartamento(Fenomeno_cli fenomeno, Departamento departamento) {
        return depFenDao.findByFenomenoAndDepartamento(fenomeno, departamento);
    }
    

}
