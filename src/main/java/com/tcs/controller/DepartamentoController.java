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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tcs.datatransfer.DepartamentoEmpleadoDTO;
import com.tcs.datatransfer.Error;
import com.tcs.service.DepartamentoService;
import com.tcs.service.ErrorService;

import fj.data.Either;

@EnableAutoConfiguration
@CrossOrigin(origins = "*")
@RequestMapping
@RestController
@Controller
public class DepartamentoController {
	
	@Autowired
	private DepartamentoService departamentoService;
	
	@Autowired
	private ErrorService errorService;

	@GetMapping(value = "/departamento/cargar")
	public ResponseEntity<?> cargarDepartamento(){
		
		Either<Exception, List<DepartamentoEmpleadoDTO>> resultEither = departamentoService.cargarDepartamentos();

		if (resultEither.isRight()) {
			return new ResponseEntity<>(resultEither.right().value(), HttpStatus.OK);
		}

		Error error = errorService.getError(resultEither.left().value());

		return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@GetMapping(value = "/departamento/pago/promedio/{idDepartamento}")
	public ResponseEntity<?> consultarPagoPromedio(@PathVariable Long idDepartamento) {
		
		Either<Exception, String> resultEither = departamentoService.consultarPagoPromedioDep(idDepartamento);

		if (resultEither.isRight()) {
			return new ResponseEntity<>(resultEither.right().value(), HttpStatus.OK);
		}

		Error error = errorService.getError(resultEither.left().value());

		return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
