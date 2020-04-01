package com.tcs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tcs.datatransfer.Error;
import com.tcs.request.mapping.FuncionEmpleadoRqMapping;
import com.tcs.service.ErrorService;
import com.tcs.service.FuncionService;

import fj.data.Either;

@EnableAutoConfiguration
@CrossOrigin(origins = "*")
@RequestMapping
@RestController
@Controller
public class FuncionController {
	
	@Autowired
	private FuncionService funcionService;
	
	@Autowired
	private ErrorService errorService;

	@PostMapping(value = "/funcion/registro/empleado")
	public ResponseEntity<?> asociarFuncionEmpleado(@RequestBody FuncionEmpleadoRqMapping funcionEmpleadoRqMapping) {

		Either<Exception, String> resultEither = funcionService.guardarFuncionEmpleado(funcionEmpleadoRqMapping);

		if (resultEither.isRight()) {
			return new ResponseEntity<>(resultEither.right().value(), HttpStatus.OK);
		}

		Error error = errorService.getError(resultEither.left().value());
		return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
