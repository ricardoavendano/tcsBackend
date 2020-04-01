package com.tcs.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tcs.datatransfer.UsuarioDTO;

import fj.data.Either;

@Service
public interface UsuarioService {

	public Either<Exception, List<UsuarioDTO>> listarUsuario();
	
	public Either<Exception, UsuarioDTO> validarAutenticacionUsuario(UsuarioDTO usuarioDTO);
}
