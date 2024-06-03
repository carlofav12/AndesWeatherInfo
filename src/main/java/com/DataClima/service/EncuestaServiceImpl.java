package com.DataClima.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.DataClima.model.dao.IEncuestaDao;
import com.DataClima.model.entity.Encuesta;
import com.DataClima.model.entity.Usuario;

@Service
public class EncuestaServiceImpl implements IEncuestaService{

    @Autowired
    private IEncuestaDao encuestaDao;

    @Override
    public void save(Encuesta encuesta) {
        encuestaDao.save(encuesta);
    }

    @Override
    public boolean existeEncuestaPorUsuario(Usuario usuario) {
        List<Encuesta> encuestas = encuestaDao.findByUsuario(usuario);

        // Si la lista de encuestas no está vacía, significa que ya existe al menos una encuesta para ese usuario
        return !encuestas.isEmpty();
    }
}