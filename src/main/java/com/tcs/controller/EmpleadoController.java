package com.tcs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tcs.datatransfer.EmpleadoDTO;
import com.tcs.datatransfer.Error;
import com.tcs.request.mapping.EmpleadoDepartamentoRqMapping;
import com.tcs.request.mapping.EmpleadoRqMapping;
import com.tcs.service.EmpleadoService;
import com.tcs.service.ErrorService;

import fj.data.Either;

@EnableAutoConfiguration
@CrossOrigin(origins = "*")
@RequestMapping
@RestController
@Controller
public class EmpleadoController {
	
	@Autowired
	private EmpleadoService empleadoService;
	
	@Autowired
	private ErrorService errorService;
	
	@GetMapping(value = "/empleado/cargar")
	public ResponseEntity<?> cargarEmpleado(){
		
		Either<Exception, List<EmpleadoDTO>> resultEither = empleadoService.cargarEmpleados();

		if (resultEither.isRight()) {
			return new ResponseEntity<>(resultEither.right().value(), HttpStatus.OK);
		}

		Error error = errorService.getError(resultEither.left().value());

		return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@PostMapping(value = "/empleado/registro")
	public ResponseEntity<?> registroEmpleado(@RequestBody EmpleadoRqMapping empleadoRqMapping) {

		Either<Exception, String> resultEither = empleadoService.guardarEmpleado(empleadoRqMapping);

		if (resultEither.isRight()) {
			return new ResponseEntity<>(resultEither.right().value(), HttpStatus.OK);
		}

		Error error = errorService.getError(resultEither.left().value());
		return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@PostMapping(value = "/empleado/registro/departamento")
	public ResponseEntity<?> asociarEmpleadoDepartamento(@RequestBody EmpleadoDepartamentoRqMapping empleadoRqMapping) {

		Either<Exception, String> resultEither = empleadoService.guardarEmpleadoDepartamento(empleadoRqMapping);

		if (resultEither.isRight()) {
			return new ResponseEntity<>(resultEither.right().value(), HttpStatus.OK);
		}

		Error error = errorService.getError(resultEither.left().value());
		return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@GetMapping(value = "/empleado/activar-inactivar/{idEmpleado}/{estado}")
	public ResponseEntity<?> activarInactivarEmpleado(@PathVariable String idEmpleado, @PathVariable Long estado) {
		
		Either<Exception, String> resultEither = empleadoService.activarInactivarEmpleado(idEmpleado, estado);

		if (resultEither.isRight()) {
			return new ResponseEntity<>(resultEither.right().value(), HttpStatus.OK);
		}

		Error error = errorService.getError(resultEither.left().value());

		return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
