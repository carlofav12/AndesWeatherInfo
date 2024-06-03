package com.DataClima.service;

import java.util.List;

import com.DataClima.model.entity.Enfermedades;
import com.DataClima.model.entity.Prevencion_Enf;

public interface IPrevencionEnfService {

    List<Prevencion_Enf> findByEnfermedad(Enfermedades enfermedad);

}
