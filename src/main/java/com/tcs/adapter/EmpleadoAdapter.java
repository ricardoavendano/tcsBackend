package com.tcs.adapter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.tcs.datatransfer.DepartamentoDTO;
import com.tcs.datatransfer.EmpleadoDTO;
import com.tcs.datatransfer.FuncionDTO;
import com.tcs.domain.Departamento;
import com.tcs.domain.Empleado;
import com.tcs.domain.Funcion;
import com.tcs.request.mapping.EmpleadoRqMapping;
import com.tcs.request.mapping.FuncionEmpleadoRqMapping;

@Component
public class EmpleadoAdapter {

	public Empleado setNuevoEmpleado(EmpleadoRqMapping empleadoRqMapping) {

		Empleado empleado = new Empleado();
		empleado.setIdEmpleado(Long.valueOf("0"));
		empleado.setActivo(empleadoRqMapping.getActivo());
		empleado.setApellidos(empleadoRqMapping.getApellidos());
		empleado.setCorreo(empleadoRqMapping.getCorreo());
		empleado.setNombre(empleadoRqMapping.getNombre());
		empleado.setNumeroDocumento(empleadoRqMapping.getNumeroDocumento());
		empleado.setSalario(empleadoRqMapping.getSalario());
		empleado.setTelefono(empleadoRqMapping.getTelefono());

		Departamento departamento = new Departamento();
		departamento.setIdDepartamento(Long.valueOf("1"));

		Funcion funcion = new Funcion();
		funcion.setIdFuncion(Long.valueOf("1"));

		empleado.setIddepartamentofk(departamento);
		empleado.setIdfuncionfk(funcion);

		return empleado;

	}

	public Empleado setNuevoEmpleadoFuncion(Empleado empleadoFuncion,
			FuncionEmpleadoRqMapping funcionEmpleadoRqMapping) {

		Empleado empleado = new Empleado();
		empleado.setIdEmpleado(Long.valueOf("0"));
		empleado.setActivo(empleadoFuncion.getActivo());
		empleado.setApellidos(empleadoFuncion.getApellidos());
		empleado.setCorreo(empleadoFuncion.getCorreo());
		empleado.setNombre(empleadoFuncion.getNombre());
		empleado.setNumeroDocumento(empleadoFuncion.getNumeroDocumento());
		empleado.setSalario(empleadoFuncion.getSalario());
		empleado.setTelefono(empleadoFuncion.getTelefono());

		Departamento departamento = new Departamento();
		departamento.setIdDepartamento(funcionEmpleadoRqMapping.getIdDepartamento());

		Funcion funcion = new Funcion();
		funcion.setIdFuncion(funcionEmpleadoRqMapping.getIdFuncion());

		empleado.setIddepartamentofk(departamento);
		empleado.setIdfuncionfk(funcion);

		return empleado;

	}
	
	public List<EmpleadoDTO> listarEmpleados(List<Empleado> listaEmpleado){
		
		List<EmpleadoDTO> lista = new ArrayList<>();
		for(Empleado empleado: listaEmpleado) {
			EmpleadoDTO empleadoDTO = new EmpleadoDTO();
			empleadoDTO.setActivo(empleado.getActivo());
			empleadoDTO.setApellidos(empleado.getApellidos());
			empleadoDTO.setCorreo(empleado.getCorreo());
			empleadoDTO.setIdEmpleado(empleado.getIdEmpleado());
			empleadoDTO.setNombre(empleado.getNombre());
			empleadoDTO.setNumeroDocumento(empleado.getNumeroDocumento());
			empleadoDTO.setSalario(empleado.getSalario());
			empleadoDTO.setTelefono(empleado.getTelefono());
			
			DepartamentoDTO departamento = new DepartamentoDTO();
			departamento.setCodigo(empleado.getIddepartamentofk().getCodigo());
			departamento.setDescripcion(empleado.getIddepartamentofk().getDescripcion());
			departamento.setIdDepartamento(empleado.getIddepartamentofk().getIdDepartamento());
			departamento.setNombre(empleado.getIddepartamentofk().getNombre());
			empleadoDTO.setDepartamento(departamento);
			
			List<FuncionDTO> funcionList = new ArrayList<>();
			for(Funcion funcionDep: empleado.getIddepartamentofk().getFuncionList()) {
				FuncionDTO funcion = new FuncionDTO();
				funcion.setDescripcion(funcionDep.getDescripcion());
				funcion.setIdFuncion(funcionDep.getIdFuncion());
				funcion.setNombre(funcionDep.getNombre());
				funcionList.add(funcion);
			}
			empleadoDTO.setFuncionList(funcionList);
			lista.add(empleadoDTO);
		}
		
		return lista;
	}
}
