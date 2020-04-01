package com.tcs.service;

import org.springframework.stereotype.Service;

import com.tcs.request.mapping.FuncionEmpleadoRqMapping;

import fj.data.Either;

@Service
public interface FuncionService {

	public Either<Exception, String> guardarFuncionEmpleado(FuncionEmpleadoRqMapping funcionEmpleadoRqMapping);
}
