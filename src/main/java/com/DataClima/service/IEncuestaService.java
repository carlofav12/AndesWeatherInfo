package com.DataClima.service;

import com.DataClima.model.entity.Encuesta;
import com.DataClima.model.entity.Usuario;

public interface IEncuestaService {

    void save(Encuesta encuesta);

    boolean existeEncuestaPorUsuario(Usuario usuario);

}
