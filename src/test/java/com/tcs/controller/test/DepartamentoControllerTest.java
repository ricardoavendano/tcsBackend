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

import com.tcs.controller.DepartamentoController;
import com.tcs.datatransfer.DepartamentoEmpleadoDTO;
import com.tcs.datatransfer.Error;
import com.tcs.service.DepartamentoService;
import com.tcs.service.ErrorService;

import fj.data.Either;

@RunWith(MockitoJUnitRunner.Silent.class)
public class DepartamentoControllerTest {

	@InjectMocks
	private DepartamentoController departamentoController;
	
	@Mock
	private DepartamentoService departamentoService;
	
	@Mock
	private ErrorService errorService;
	
	@Test
	public void cargarDepartamento() {
		List<DepartamentoEmpleadoDTO> listDep = new ArrayList<>();
		
		Either<Exception, List<DepartamentoEmpleadoDTO>> resultEither = Either.right(listDep);
		Mockito.when(departamentoService.cargarDepartamentos()).thenReturn(resultEither);
		
		ResponseEntity<?> res = departamentoController.cargarDepartamento();
		
		assertEquals(200, res.getStatusCodeValue());
	}
	
	@Test
	public void cargarDepartamentoException() {
		
		Exception e = new Exception();
		Error error = errorService.getError(e);
		
		Either<Exception, List<DepartamentoEmpleadoDTO>> resultEither = Either.left(new Exception());
		
		Mockito.when(departamentoService.cargarDepartamentos()).thenReturn(resultEither);
		Mockito.when(errorService.getError(e)).thenReturn(error);
		
		ResponseEntity<?> res = departamentoController.cargarDepartamento();
		
		assertEquals(500, res.getStatusCodeValue());
	}
	
	@Test
	public void consultarPormedio() {
		
		Either<Exception, String> resultEither = Either.right("");
		Mockito.when(departamentoService.consultarPagoPromedioDep(Mockito.any())).thenReturn(resultEither);
		
		ResponseEntity<?> res = departamentoController.consultarPagoPromedio(Mockito.any());
		
		assertEquals(200, res.getStatusCodeValue());
	}
	
	@Test
	public void consultarPormedioException() {
		
		Exception e = new Exception();
		Error error = errorService.getError(e);
		
		Either<Exception, String> resultEither = Either.left(new Exception());
		
		Mockito.when(departamentoService.consultarPagoPromedioDep(Mockito.any())).thenReturn(resultEither);
		Mockito.when(errorService.getError(e)).thenReturn(error);
		
		ResponseEntity<?> res = departamentoController.consultarPagoPromedio(Mockito.any());
		
		assertEquals(500, res.getStatusCodeValue());
	}
}
