package com.DataClima.service;

import java.util.List;

import com.DataClima.model.entity.DepartamentoFenomeno;
import com.DataClima.model.entity.Enf_dep_fen;

public interface IEnfDepFenService {

    List<Enf_dep_fen> findByDepartamentoFenomeno(DepartamentoFenomeno depFen);

}
