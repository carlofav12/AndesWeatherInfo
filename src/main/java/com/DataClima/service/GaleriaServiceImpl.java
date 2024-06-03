package com.DataClima.service;

import java.util.List;
import java.util.Optional;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.DataClima.model.dao.IGaleriaDao;
import com.DataClima.model.entity.Galeria;
import com.DataClima.model.entity.Noticia;

@Service
public class GaleriaServiceImpl implements IGaleriaService{

    @Autowired
    private IGaleriaDao galeriaDao;

    @Override
    @Transactional(readOnly=true)
    public List<Galeria> findAllGaleria() {

        return (List<Galeria>) galeriaDao.findAll();
    }

    @Override
    public void saveGaleria(Galeria galeria) {
        galeriaDao.save(galeria);
    }
    
    @Override
    public Galeria findById(Long id) { 
        Optional<Galeria> optionalGaleria = galeriaDao.findById(id);
        return optionalGaleria.orElse(null);
    }
    
    @Override
    @Transactional(readOnly = true)
    public Galeria editarGaleria(Long id) {
        return galeriaDao.findById(id).orElse(null);
    }
    @Override
    @Transactional
    public void eliminarGaleria(Long id) {
        // TODO Auto-generated method stub
        galeriaDao.deleteById(id);
    }

}