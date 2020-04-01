package com.tcs.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tcs.datatransfer.DepartamentoEmpleadoDTO;

import fj.data.Either;

@Service
public interface DepartamentoService {

	Either<Exception, List<DepartamentoEmpleadoDTO>> cargarDepartamentos();
	public Either<Exception, String> consultarPagoPromedioDep(Long iddepartamento);
}
