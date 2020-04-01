package com.tcs.service.test;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import com.tcs.datatransfer.Error;
import com.tcs.exception.AutenticacionFallidaException;
import com.tcs.exception.FuncionYaAsignadaEmpleadoException;
import com.tcs.exception.UsuarioNoEncontradoException;
import com.tcs.exception.UsuarioNoExisteException;
import com.tcs.service.ErrorService;

@RunWith(MockitoJUnitRunner.class)
public class ErrorServiceTest {

	@InjectMocks
	private ErrorService errorService;

	@Test
	public void UsuarioNoEncontradoException() {
		Error error = errorService.getError(new UsuarioNoEncontradoException());
		assertTrue(error.getCode().equals("UsuarioNoEncontradoException001"));
	}
	
	@Test
	public void FuncionYaAsignadaEmpleadoException() {
		Error error = errorService.getError(new FuncionYaAsignadaEmpleadoException());
		assertTrue(error.getCode().equals("FuncionYaAsignadaEmpleadoException001"));
	}
	

	@Test
	public void usuarioNoExisteException() {
		Error error = errorService.getError(new UsuarioNoExisteException());
		assertTrue(error.getCode().equals("UsuarioNoExisteException001"));
	}

	@Test
	public void autenticacionFallidaException() {
		Error error = errorService.getError(new AutenticacionFallidaException());
		assertTrue(error.getCode().equals("AutenticacionFallidaException001"));
	}

	@Test
	public void errorDesconocido() {
		Error error = errorService.getError(new Exception());

		assertTrue(error.getCode().equals("Libro001"));
		assertTrue(error.getDescription().equals("Unknown Error"));
	}

}
