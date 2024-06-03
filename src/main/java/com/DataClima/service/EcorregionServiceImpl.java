package com.DataClima.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.DataClima.model.dao.IEcorregionDao;
import com.DataClima.model.entity.Ecoregion;

@Service
public class EcorregionServiceImpl implements IEcorregionService{

    @Autowired
    private IEcorregionDao ecorregionDao;

    @Override
    public List<Ecoregion> getAllEcoregiones() {
        return (List<Ecoregion>) ecorregionDao.findAll();
    }

}