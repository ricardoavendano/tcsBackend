package com.tcs.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import com.tcs.domain.Usuario;

@Component
public interface UsuarioRepository extends CrudRepository<Usuario, String>{

}
