package com.DataClima.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.DataClima.model.dao.IEnfDepFenDao;
import com.DataClima.model.entity.DepartamentoFenomeno;
import com.DataClima.model.entity.Enf_dep_fen;

@Service
public class EnfDepFenServiceImpl implements IEnfDepFenService{

    @Autowired
    private IEnfDepFenDao enfDepFenDao;

    @Override
    public List<Enf_dep_fen> findByDepartamentoFenomeno(DepartamentoFenomeno depFen) {
        return enfDepFenDao.findByDepartamentoFenomeno(depFen);
    }

}
