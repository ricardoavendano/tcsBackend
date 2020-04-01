package com.tcs.controller.test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import com.tcs.controller.LoginController;
import com.tcs.datatransfer.Error;
import com.tcs.datatransfer.UsuarioDTO;
import com.tcs.exception.UsuarioNoExisteException;
import com.tcs.service.ErrorService;
import com.tcs.service.UsuarioService;

import fj.data.Either;

@RunWith(MockitoJUnitRunner.Silent.class)
public class LoginControllerTest {
	
	@InjectMocks
	private LoginController loginController;
	
	@Mock
	private UsuarioService usuarioService;
	
	@Mock
	private ErrorService errorService;
	
	@Test
	public void validLogin() {
		
		UsuarioDTO usuarioDTO = new UsuarioDTO();
		usuarioDTO.setIdUsuario("usuario1");
		usuarioDTO.setPassword("dXN1YXJpbzE=");
		
		Either<Exception, UsuarioDTO> resultEither = Either.right(usuarioDTO);
		Mockito.when(usuarioService.validarAutenticacionUsuario(usuarioDTO)).thenReturn(resultEither);
		
		ResponseEntity<?> res = loginController.validarLogin(usuarioDTO);
		
		assertEquals(200, res.getStatusCodeValue());
	}
	
	@Test
	public void noValidLogin() {
		
		Exception e = new UsuarioNoExisteException();
		Error error = errorService.getError(e);
		
		Either<Exception, UsuarioDTO> resultEither = Either.left(new UsuarioNoExisteException());
		
		Mockito.when(usuarioService.validarAutenticacionUsuario(Mockito.any())).thenReturn(resultEither);
		Mockito.when(errorService.getError(e)).thenReturn(error);
		
		ResponseEntity<?> res = loginController.validarLogin(Mockito.any());
		
		assertEquals(500, res.getStatusCodeValue());
	}
	
	@Test
	public void getUsuarioList() {
		List<UsuarioDTO> usuarioDTOList = new ArrayList<>();
		UsuarioDTO usuarioDTO = new UsuarioDTO();
		usuarioDTO.setIdUsuario("usuario1");
		usuarioDTO.setPassword("dXN1YXJpbzE=");
		usuarioDTOList.add(usuarioDTO);
		
		Either<Exception, List<UsuarioDTO>> resultEither = Either.right(usuarioDTOList);
		
		Mockito.when(usuarioService.listarUsuario()).thenReturn(resultEither);
				
		ResponseEntity<?> res = loginController.getUsuarioList();
		
		assertEquals(200, res.getStatusCodeValue());
	}
	
	@Test
	public void noGetUsuarioList() {
		
		Exception e = new UsuarioNoExisteException();
		Error error = errorService.getError(e);
		
		Either<Exception, List<UsuarioDTO>> resultEither = Either.left(new UsuarioNoExisteException());
		
		Mockito.when(usuarioService.listarUsuario()).thenReturn(resultEither);
		Mockito.when(errorService.getError(e)).thenReturn(error);
				
		ResponseEntity<?> res = loginController.getUsuarioList();
		
		assertEquals(500, res.getStatusCodeValue());
	}
}
