package com.DataClima.service;

import java.util.List;

import com.DataClima.model.entity.DepartamentoFenomeno;
import com.DataClima.model.entity.Prevenciones;

public interface IPrevencionesService{

    List<Prevenciones> findByDepartamentoFenomeno(DepartamentoFenomeno depFen);

}
