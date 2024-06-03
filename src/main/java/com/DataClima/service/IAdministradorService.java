package com.DataClima.service;

import com.DataClima.model.entity.Administrador;

public interface IAdministradorService {

    public boolean validarAdmin(String usuario, String contrasena);

    public Administrador findByAdministrador(String nombre);

}