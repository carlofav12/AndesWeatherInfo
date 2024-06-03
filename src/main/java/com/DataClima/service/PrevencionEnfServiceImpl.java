package com.DataClima.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.DataClima.model.dao.IPrevencionEnfDao;
import com.DataClima.model.entity.Enfermedades;
import com.DataClima.model.entity.Prevencion_Enf;

@Service
public class PrevencionEnfServiceImpl implements IPrevencionEnfService {

    @Autowired
    private IPrevencionEnfDao prevencionEnfDao;

    @Override
    public List<Prevencion_Enf> findByEnfermedad(Enfermedades enfermedad) {
        // TODO Auto-generated method stub
        return prevencionEnfDao.findByEnfermedad(enfermedad);
    }
}
