package com.DataClima.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.DataClima.model.dao.INoticiaDao;
import com.DataClima.model.entity.Noticia;

@Service
public class NoticiaServiceImpl implements INoticiaService{
    
    @Autowired
    private INoticiaDao noticiaDao;

    @Override
    @Transactional(readOnly = true)
    public List<Noticia>findNoticia(){

        return (List<Noticia>) noticiaDao.findAll();
    }

    @Override
    @Transactional
    public void saveNoticia(Noticia noticia){

        noticiaDao.save(noticia);
    }

    @Override
    @Transactional(readOnly = true)
    public Noticia editarNoti(Long id) {
        // TODO Auto-generated method stub
        return noticiaDao.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void eliminarNoti(Long id) {
        // TODO Auto-generated method stub
        noticiaDao.deleteById(id);
    }
}
