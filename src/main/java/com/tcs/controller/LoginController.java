package com.tcs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tcs.datatransfer.Error;
import com.tcs.datatransfer.UsuarioDTO;
import com.tcs.service.ErrorService;
import com.tcs.service.UsuarioService;

import fj.data.Either;

@EnableAutoConfiguration
@CrossOrigin(origins = "*")
@RequestMapping
@RestController
@Controller
public class LoginController {

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private ErrorService errorService;

	@PostMapping(value = "/login")
	public ResponseEntity<?> validarLogin(@RequestBody UsuarioDTO usuarioDTO) {

		Either<Exception, UsuarioDTO> resultEither = usuarioService.validarAutenticacionUsuario(usuarioDTO);

		if (resultEither.isRight()) {
			return new ResponseEntity<>(resultEither.right().value(), HttpStatus.OK);
		}

		Error error = errorService.getError(resultEither.left().value());

		return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@GetMapping(value = "/listar/usuario")
	public ResponseEntity<?> getUsuarioList() {

		Either<Exception, List<UsuarioDTO>> resultEither = usuarioService.listarUsuario();

		if (resultEither.isRight()) {
			return new ResponseEntity<>(resultEither.right().value(), HttpStatus.OK);
		}

		Error error = errorService.getError(resultEither.left().value());
		return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
