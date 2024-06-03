package com.DataClima.service;

import com.DataClima.model.entity.Galeria;
import java.util.List;

public interface IGaleriaService {
    List<Galeria> findAllGaleria();
    void saveGaleria(Galeria galeria);
    Galeria findById(Long id); // Agrega este mÃ©todo
    Galeria editarGaleria(Long id);
    public void eliminarGaleria(Long id);
}