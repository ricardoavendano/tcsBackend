package com.tcs.adapter.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import com.tcs.adapter.DepartamentoAdapter;
import com.tcs.domain.Departamento;
import com.tcs.domain.Empleado;
import com.tcs.domain.Funcion;

@RunWith(MockitoJUnitRunner.class)
public class DepartamentoAdapterTest {

	@InjectMocks
	private DepartamentoAdapter departamentoAdapter;

	@Test
	public void listarDepartamentos() {
		
		List<Departamento> listaDepartamento = new ArrayList<>();
		Departamento departamento = getDepartamento();
		departamento.setFuncionList(getFuncionList());
		departamento.setEmpleadoList(getEmpleadoList());
		listaDepartamento.add(departamento);
		departamentoAdapter.listarDepartamentos(listaDepartamento);
	}
	
	private Departamento getDepartamento() {
		Departamento departamento = new Departamento();
		departamento.setCodigo(Long.valueOf("1"));
		departamento.setDescripcion("");
		departamento.setIdDepartamento(Long.valueOf("1"));
		departamento.setNombre("");
		return departamento;
	}
	
	private List<Funcion> getFuncionList() {
		List<Funcion> funcionList = new ArrayList<>();
		Funcion funcion = getFuncion();
		funcionList.add(funcion);
		return funcionList;
	}

	private Funcion getFuncion() {
		Funcion funcion = new Funcion();
		funcion.setDescripcion("");
		funcion.setIdFuncion(Long.valueOf("1"));
		funcion.setNombre("");
		return funcion;
	}
	
	private List<Empleado> getEmpleadoList() {
		List<Empleado> empleadoList = new ArrayList<>();
		Empleado empleadoFuncion = getEmpleado();
		empleadoList.add(empleadoFuncion);
		return empleadoList;
	}
	
	private Empleado getEmpleado() {
		Empleado empleadoFuncion = new Empleado();
		empleadoFuncion.setIdEmpleado(Long.valueOf("1"));
		empleadoFuncion.setActivo(true);
		empleadoFuncion.setApellidos("");
		empleadoFuncion.setCorreo("");
		empleadoFuncion.setNombre("");
		empleadoFuncion.setNumeroDocumento("");
		empleadoFuncion.setSalario(0D);
		empleadoFuncion.setTelefono("");
		return empleadoFuncion;
	}
}
