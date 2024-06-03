package com.DataClima.service;

import java.util.Optional;

import com.DataClima.model.entity.Fenomeno_cli;

public interface IFenomenoCliService {
        Optional<Fenomeno_cli> findById(Long id);

}
