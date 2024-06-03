package com.DataClima.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.DataClima.model.dao.IFenomenoCliDao;
import com.DataClima.model.entity.Fenomeno_cli;

@Service
public class FenomenoCliServiceImpl implements IFenomenoCliService{

    @Autowired 
    private IFenomenoCliDao fenomenoDao;

    @Override
    public Optional<Fenomeno_cli> findById(Long id) {
        return fenomenoDao.findById(id);
    }

}
