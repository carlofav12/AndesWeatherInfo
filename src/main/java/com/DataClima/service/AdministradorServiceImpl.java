package com.DataClima.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.DataClima.model.dao.IAdministradorDao;
import com.DataClima.model.entity.Administrador;

@Service
public class AdministradorServiceImpl implements IAdministradorService{

    @Autowired
    private IAdministradorDao administradorDao;

    @Override
    @Transactional(readOnly = true)
    public boolean validarAdmin(String nombre, String contrasena) {
        Administrador administrador = administradorDao.findByNombre(nombre);
        if (administrador != null) {
            if (administrador.getContra().equals(contrasena)) {
                return true;
            } else {
                System.out.println("Password does not match for admin: " + nombre);
            }
        } else {
            System.out.println("No admin found with name: " + nombre);
        }
        return false;
    }

    @Override
    public Administrador findByAdministrador(String nombre) {

        return administradorDao.findByNombre(nombre);
    }



}