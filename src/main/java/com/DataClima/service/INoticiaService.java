package com.DataClima.service;

import java.util.List;
import com.DataClima.model.entity.Noticia;

public interface INoticiaService {

    List<Noticia> findNoticia();
    
    void saveNoticia(Noticia noticia);

    public Noticia editarNoti(Long id);

    public void eliminarNoti(Long id);

}
