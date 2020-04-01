package com.tcs.adapter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.tcs.datatransfer.DepartamentoEmpleadoDTO;
import com.tcs.datatransfer.EmpleadoBasicoDTO;
import com.tcs.datatransfer.FuncionDTO;
import com.tcs.domain.Departamento;
import com.tcs.domain.Empleado;
import com.tcs.domain.Funcion;

@Component
public class DepartamentoAdapter {

	public List<DepartamentoEmpleadoDTO> listarDepartamentos(List<Departamento> listaDepartamento) {

		List<DepartamentoEmpleadoDTO> lista = new ArrayList<>();
		for (Departamento departamento : listaDepartamento) {
			DepartamentoEmpleadoDTO departamentoDTO = new DepartamentoEmpleadoDTO();
			
			departamentoDTO.setCodigo(departamento.getCodigo());
			departamentoDTO.setDescripcion(departamento.getDescripcion());
			departamentoDTO.setIdDepartamento(departamento.getIdDepartamento());
			departamentoDTO.setNombre(departamento.getNombre());

			List<FuncionDTO> funcionList = new ArrayList<>();
			for (Funcion funcionDep : departamento.getFuncionList()) {
				FuncionDTO funcion = new FuncionDTO();
				funcion.setDescripcion(funcionDep.getDescripcion());
				funcion.setIdFuncion(funcionDep.getIdFuncion());
				funcion.setNombre(funcionDep.getNombre());
				funcionList.add(funcion);
			}
			departamentoDTO.setFuncionDTO(funcionList);
			
			Set<String> nameSet = new HashSet<>();
			List<Empleado> empleadoFiltro = departamento.getEmpleadoList().stream()
			            .filter(e -> nameSet.add(e.getNumeroDocumento()))
			            .collect(Collectors.toList());
			
			List<EmpleadoBasicoDTO> empleadoList = new ArrayList<>();
			for (Empleado empleadoDep : empleadoFiltro) {
				EmpleadoBasicoDTO empleadoBasico = new EmpleadoBasicoDTO();
				empleadoBasico.setActivo(empleadoDep.getActivo());
				empleadoBasico.setApellidos(empleadoDep.getApellidos());
				empleadoBasico.setCorreo(empleadoDep.getCorreo());
				empleadoBasico.setIdEmpleado(empleadoDep.getIdEmpleado());
				empleadoBasico.setNombre(empleadoDep.getNombre());
				empleadoBasico.setNumeroDocumento(empleadoDep.getNumeroDocumento());
				empleadoBasico.setSalario(empleadoDep.getSalario());
				empleadoBasico.setTelefono(empleadoDep.getTelefono());
				empleadoList.add(empleadoBasico);
			}
			departamentoDTO.setEmpleadoBasicoDTO(empleadoList);
			lista.add(departamentoDTO);
		}

		return lista;
	}
}
