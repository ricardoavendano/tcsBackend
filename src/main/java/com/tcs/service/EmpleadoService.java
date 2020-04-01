package com.tcs.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tcs.datatransfer.EmpleadoDTO;
import com.tcs.request.mapping.EmpleadoDepartamentoRqMapping;
import com.tcs.request.mapping.EmpleadoRqMapping;

import fj.data.Either;

@Service
public interface EmpleadoService {

	public Either<Exception, List<EmpleadoDTO>> cargarEmpleados();
	public Either<Exception, String> guardarEmpleado(EmpleadoRqMapping empleadoRqMapping);
	public Either<Exception, String> guardarEmpleadoDepartamento(EmpleadoDepartamentoRqMapping empleadoRqMapping);
	public Either<Exception, String> activarInactivarEmpleado(String idempleado, Long estado);
}
