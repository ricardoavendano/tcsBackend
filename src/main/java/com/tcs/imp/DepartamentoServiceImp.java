package com.tcs.imp;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tcs.adapter.DepartamentoAdapter;
import com.tcs.datatransfer.DepartamentoEmpleadoDTO;
import com.tcs.domain.Departamento;
import com.tcs.domain.Empleado;
import com.tcs.repository.DepartamentoRepository;
import com.tcs.service.DepartamentoService;

import fj.data.Either;

@Service
public class DepartamentoServiceImp implements DepartamentoService {

	private static final Logger LOGGER = LoggerFactory.getLogger(DepartamentoServiceImp.class);

	@Autowired
	private DepartamentoRepository departamentoRepository;
	
	@Autowired
	private DepartamentoAdapter departamentoAdapter;

	public Either<Exception, List<DepartamentoEmpleadoDTO>> cargarDepartamentos() {

		List<Departamento> listaDepartamento = (List<Departamento>) departamentoRepository.findAll();

		return Either.right(departamentoAdapter.listarDepartamentos(listaDepartamento));
	}

	public Either<Exception, String> consultarPagoPromedioDep(Long idDepartamento) {
		try {
			Double promedio = 0D;
			Optional<Departamento> departamento = departamentoRepository.findById(idDepartamento);

			if (departamento.isPresent()) {

				Set<String> nameSet = new HashSet<>();
				List<Empleado> empleadoFiltro = departamento.get().getEmpleadoList().stream()
						.filter(e -> nameSet.add(e.getNumeroDocumento())).collect(Collectors.toList());

				for (Empleado empleado : empleadoFiltro) {
					promedio += empleado.getSalario();
				}
				promedio = promedio / empleadoFiltro.size();
			}

			return Either.right("{\"code\":\"Pago promedio del departamento: " + promedio + "\"}");
		} catch (Exception e) {
			LOGGER.error("DepartamentoServiceImp:guardarFuncionesEmpleado", e);
			return Either.left(e);
		}
	}
}
