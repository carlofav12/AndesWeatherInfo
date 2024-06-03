package com.DataClima.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.DataClima.model.dao.IPrevencionesDao;
import com.DataClima.model.entity.DepartamentoFenomeno;
import com.DataClima.model.entity.Prevenciones;

@Service
public class PrenvencionesServiceImpl implements IPrevencionesService{

    @Autowired
    private IPrevencionesDao prevencionesDao;

    @Override
    public List<Prevenciones> findByDepartamentoFenomeno(DepartamentoFenomeno depFen) {
        return prevencionesDao.findByDepartamentoFenomeno(depFen);

    }

}
