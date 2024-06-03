package com.DataClima.model.dao;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.DataClima.model.entity.Usuario;

import jakarta.transaction.Transactional;

public interface ILoginDao extends CrudRepository<Usuario, Long> {
	Usuario findByUsu(String usuario);
    @Modifying
    @Transactional
    @Query("delete from Usuario u where u.id = ?1")
    void deleteUsuarioById(Long id);
}
