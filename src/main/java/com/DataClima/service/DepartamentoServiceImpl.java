package com.DataClima.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.DataClima.model.dao.IDepartamentoDao;
import com.DataClima.model.entity.Departamento;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class DepartamentoServiceImpl implements IDepartamentoService {

    @Autowired
    private IDepartamentoDao departamentoDao;

    @Override
    public List<Departamento> findAll() {
        return (List<Departamento>) departamentoDao.findAll();
    }

    @Override
    public Departamento saveDepartamento(Departamento departamento) {
        return departamentoDao.save(departamento);
    }

    @Override
    @Transactional(readOnly = true)
    public void deleteDepartamento(Long id) {
        departamentoDao.deleteById(id);
    }

    @Override
    public Departamento findByNombre(String nombre) {
        Optional<Departamento> optionalDepartamento = departamentoDao.findByNombre(nombre);
        return optionalDepartamento.orElse(null);
    }

    @Override
    public List<Departamento> getAllDepartamentos() {
        return (List<Departamento>) departamentoDao.findAll();
    }

    @Override
    public Departamento findById(Long departamentoId) {
        return departamentoDao.findById(departamentoId)
                .orElseThrow(() -> new NoSuchElementException("Departamento not found with id " + departamentoId));
    }

}