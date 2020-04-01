package com.tcs.imp;

import java.util.Base64;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tcs.adapter.UsuarioAdapter;
import com.tcs.datatransfer.UsuarioDTO;
import com.tcs.domain.Usuario;
import com.tcs.exception.AutenticacionFallidaException;
import com.tcs.exception.UsuarioNoEncontradoException;
import com.tcs.exception.UsuarioNoExisteException;
import com.tcs.repository.UsuarioRepository;
import com.tcs.service.UsuarioService;

import fj.data.Either;

@Service
public class UsuarioServiceImp implements UsuarioService{

	private static final Logger LOGGER = LoggerFactory.getLogger(UsuarioServiceImp.class);
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private UsuarioAdapter usuarioAdapter;

	public Either<Exception, List<UsuarioDTO>> listarUsuario() {
		try {
			List<Usuario> listUsuario = (List<Usuario>) usuarioRepository.findAll();

			if (listUsuario.isEmpty()) {
				throw new UsuarioNoEncontradoException();
			} else {
				
				List<UsuarioDTO> listUsuarioDTO = usuarioAdapter.usuarioListAdapter(listUsuario);
				return Either.right(listUsuarioDTO);
			}

		} catch (UsuarioNoEncontradoException e) {
			LOGGER.error("UsuarioServiceImp:listarUsuario",e);
			return Either.left(e);
			
		} catch (Exception e) {

			return Either.left(e);
		}
	}
	
	public Either<Exception, UsuarioDTO> validarAutenticacionUsuario(UsuarioDTO usuarioDTO) {
		try {
			Optional<Usuario> usuario = usuarioRepository.findById(usuarioDTO.getIdUsuario());

			if (!usuario.isPresent()) {
				throw new UsuarioNoExisteException();
			} else {
				
				String encodedString = Base64.getEncoder().encodeToString(usuarioDTO.getPassword().getBytes());
				if(encodedString.equals(usuario.get().getPassword())) {
					
					usuarioDTO.setPassword(usuario.get().getPassword());
					return Either.right(usuarioDTO);
				}else {
					throw new AutenticacionFallidaException();
				}
				
			}

		} catch (AutenticacionFallidaException | UsuarioNoExisteException  e) {
			LOGGER.error("UsuarioServiceImp:validarAutenticacionUsuario",e);
			return Either.left(e);
			
		} catch (Exception e) {

			return Either.left(e);
		}
	}
}
