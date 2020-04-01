package com.tcs.service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.tcs.datatransfer.Error;
import com.tcs.exception.AutenticacionFallidaException;
import com.tcs.exception.FuncionYaAsignadaEmpleadoException;
import com.tcs.exception.UsuarioNoEncontradoException;
import com.tcs.exception.UsuarioNoExisteException;

@Service
public class ErrorService {

	public Error getError(Exception e) {

		if (e instanceof UsuarioNoEncontradoException) {

			return new Error("UsuarioNoEncontradoException001", "No hay usuarios registrados");

		}
		if (e instanceof UsuarioNoExisteException) {

			return new Error("UsuarioNoExisteException001", "Usuario no encontrado");

		}
		if (e instanceof AutenticacionFallidaException) {

			return new Error("AutenticacionFallidaException001", "Autenticacion fallida, vuelva a intentarlo");

		}
		if (e instanceof FuncionYaAsignadaEmpleadoException) {

			return new Error("FuncionYaAsignadaEmpleadoException001", "El usuario ya tiene esta funcion asignada");

		}
				
		Map<String, String> params = new HashMap<>();
		params.put("Exception", e.getClass() + "-" + e.getMessage());
		params.put("Time", LocalDateTime.now().toString());

		return new Error("Libro001", "Unknown Error");
	}
}
