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

import com.tcs.controller.EmpleadoController;
import com.tcs.datatransfer.EmpleadoDTO;
import com.tcs.datatransfer.Error;
import com.tcs.service.EmpleadoService;
import com.tcs.service.ErrorService;

import fj.data.Either;

@RunWith(MockitoJUnitRunner.Silent.class)
public class EmpleadoControllerTest {

	@InjectMocks
	private EmpleadoController empleadoController;

	@Mock
	private EmpleadoService empleadoService;

	@Mock
	private ErrorService errorService;

	@Test
	public void cargarEmpleado() {
		List<EmpleadoDTO> listDep = new ArrayList<>();

		Either<Exception, List<EmpleadoDTO>> resultEither = Either.right(listDep);
		Mockito.when(empleadoService.cargarEmpleados()).thenReturn(resultEither);

		ResponseEntity<?> res = empleadoController.cargarEmpleado();

		assertEquals(200, res.getStatusCodeValue());
	}
	
	@Test
	public void cargarEmpleadoException() {
		Exception e = new Exception();
		Error error = errorService.getError(e);
		
		Either<Exception, List<EmpleadoDTO>> resultEither = Either.left(new Exception());
		
		Mockito.when(empleadoService.cargarEmpleados()).thenReturn(resultEither);
		Mockito.when(errorService.getError(e)).thenReturn(error);
		
		ResponseEntity<?> res = empleadoController.cargarEmpleado();
		
		assertEquals(500, res.getStatusCodeValue());
	}

}
