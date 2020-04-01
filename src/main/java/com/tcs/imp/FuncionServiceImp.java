package com.tcs.imp;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tcs.adapter.EmpleadoAdapter;
import com.tcs.domain.Empleado;
import com.tcs.exception.FuncionYaAsignadaEmpleadoException;
import com.tcs.repository.EmpleadoRepository;
import com.tcs.request.mapping.FuncionEmpleadoRqMapping;
import com.tcs.service.FuncionService;

import fj.data.Either;

@Service
public class FuncionServiceImp implements FuncionService {

	private static final Logger LOGGER = LoggerFactory.getLogger(FuncionServiceImp.class);

	@Autowired
	private EmpleadoRepository empleadoRepository;
	
	@Autowired
	private EmpleadoAdapter empleadoAdapter;

	public Either<Exception, String> guardarFuncionEmpleado(FuncionEmpleadoRqMapping funcionEmpleadoRqMapping) {

		try {

			List<Empleado> optionlEmpleado = empleadoRepository
					.listaEmpleadoXDocuemnto(funcionEmpleadoRqMapping.getNumeroDocumento());

			if (!optionlEmpleado.isEmpty()) {
				try {
					for (Empleado empleado : optionlEmpleado) {
						if (empleado.getIdfuncionfk().getIdFuncion().equals(funcionEmpleadoRqMapping.getIdFuncion())) {
							throw new FuncionYaAsignadaEmpleadoException();
						}
					}

					Empleado empleado = empleadoAdapter.setNuevoEmpleadoFuncion(optionlEmpleado.get(0), funcionEmpleadoRqMapping);

					empleadoRepository.save(empleado);

				} catch (FuncionYaAsignadaEmpleadoException e) {
					LOGGER.error("CompraServiceImp:guardarCompra", e);
					return Either.left(e);
				}

				return Either.right("{\"code\":\"Funcion nueva asignada actualizado con exito\"}");
			} else {
				return Either.right("{\"code\":\"No se encontro empleado\"}");
			}

		} catch (Exception e) {
			LOGGER.error("CompraServiceImp:guardarCompra", e);
			return Either.left(e);
		}
	}

}
