package com.tcs.imp;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tcs.adapter.EmpleadoAdapter;
import com.tcs.datatransfer.EmpleadoDTO;
import com.tcs.domain.Empleado;
import com.tcs.repository.EmpleadoRepository;
import com.tcs.request.mapping.EmpleadoDepartamentoRqMapping;
import com.tcs.request.mapping.EmpleadoRqMapping;
import com.tcs.service.EmpleadoService;

import fj.data.Either;

@Service
public class EmpleadoServiceImp implements EmpleadoService {

	private static final Logger LOGGER = LoggerFactory.getLogger(EmpleadoServiceImp.class);

	@Autowired
	private EmpleadoRepository empleadoRepository;

	@Autowired
	private EmpleadoAdapter empleadoAdapter;
	
	public Either<Exception, List<EmpleadoDTO>> cargarEmpleados(){
		
		List<Empleado> empleadoList = (List<Empleado>) empleadoRepository.findAll();
		
		Set<String> nameSet = new HashSet<>();
		List<Empleado> empleadoFiltro = empleadoList.stream()
		            .filter(e -> nameSet.add(e.getNumeroDocumento()))
		            .collect(Collectors.toList());
		
		
		return Either.right(empleadoAdapter.listarEmpleados(empleadoFiltro));
	}

	public Either<Exception, String> guardarEmpleado(EmpleadoRqMapping empleadoRqMapping) {
		try {

			List<Empleado> optionlEmpleado = empleadoRepository
					.listaEmpleadoXDocuemnto(empleadoRqMapping.getNumeroDocumento());

			if (optionlEmpleado.isEmpty()) {

				Empleado empleadoDTO = empleadoAdapter.setNuevoEmpleado(empleadoRqMapping);
				empleadoRepository.save(empleadoDTO);

				return Either.right("{\"code\":\"Empleado guardado con exito\"}");
			} else {
				return Either.right("{\"code\":\"Numero de documento ya se encuentra registrado\"}");
			}

		} catch (Exception e) {
			LOGGER.error("EmpleadoServiceImp:guardarEmpleado", e);
			return Either.left(e);
		}
	}

	public Either<Exception, String> guardarEmpleadoDepartamento(EmpleadoDepartamentoRqMapping empleadoRqMapping) {
		try {

			List<Empleado> optionlEmpleado = empleadoRepository
					.listaEmpleadoXDocuemnto(empleadoRqMapping.getNumeroDocumento());

			if (!optionlEmpleado.isEmpty()) {

				empleadoRepository.empleadoADepartamento(empleadoRqMapping.getIdDepartamento(),
						empleadoRqMapping.getNumeroDocumento());
				return Either.right("{\"code\":\"Empleado actualizado con exito\"}");
			} else {
				return Either.right("{\"code\":\"No se encontro empleado\"}");
			}

		} catch (Exception e) {
			LOGGER.error("EmpleadoServiceImp:guardarEmpleadoDepartamento", e);
			return Either.left(e);
		}
	}
	
	public Either<Exception, String> activarInactivarEmpleado(String idEmpleado, Long estado) {
		try {

			List<Empleado> optionlEmpleado = empleadoRepository
					.listaEmpleadoXDocuemnto(idEmpleado);

			if (!optionlEmpleado.isEmpty()) {
				boolean estadoEmpleado = (estado.equals(Long.valueOf("1"))? true: false);
				for(Empleado empleado: optionlEmpleado) {
					empleado.setActivo(estadoEmpleado);
					empleadoRepository.save(empleado);
				}
				
				return Either.right("{\"code\":\"Empleado actualizado con exito\"}");
			} else {
				return Either.right("{\"code\":\"No se encontro empleado\"}");
			}

		} catch (Exception e) {
			LOGGER.error("EmpleadoServiceImp:activarInactivarEmpleado", e);
			return Either.left(e);
		}
	}
	

}
