package com.tcs.adapter.test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import com.tcs.adapter.EmpleadoAdapter;
import com.tcs.datatransfer.EmpleadoDTO;
import com.tcs.domain.Departamento;
import com.tcs.domain.Empleado;
import com.tcs.domain.Funcion;
import com.tcs.request.mapping.EmpleadoRqMapping;
import com.tcs.request.mapping.FuncionEmpleadoRqMapping;

@RunWith(MockitoJUnitRunner.class)
public class EmpleadoAdapterTest {

	@InjectMocks
	private EmpleadoAdapter empleadoAdapter;

	@Test
	public void nuevoEmpleado() {

		EmpleadoRqMapping empleadoRqMapping = new EmpleadoRqMapping();
		empleadoRqMapping.setActivo(true);
		empleadoRqMapping.setApellidos("");
		empleadoRqMapping.setCorreo("");
		empleadoRqMapping.setNombre("");
		empleadoRqMapping.setNumeroDocumento("");
		empleadoRqMapping.setSalario(0D);
		empleadoRqMapping.setTelefono("");
		
		empleadoAdapter.setNuevoEmpleado(empleadoRqMapping);
	}

	@Test
	public void nuevoEmpleadoFuncion() {
		Empleado empleadoFuncion = getEmpleado();

		FuncionEmpleadoRqMapping funcionEmpleadoRqMapping = new FuncionEmpleadoRqMapping();
		funcionEmpleadoRqMapping.setIdDepartamento(Long.valueOf("1"));
		funcionEmpleadoRqMapping.setIdFuncion(Long.valueOf("1"));
		funcionEmpleadoRqMapping.setNumeroDocumento("");

		empleadoAdapter.setNuevoEmpleadoFuncion(empleadoFuncion, funcionEmpleadoRqMapping);
	}

	@Test
	public void listarEmpleados() {

		List<Empleado> listaEmpleado = new ArrayList<>();
		Empleado empleadoFuncion = getEmpleado();
		empleadoFuncion.setIddepartamentofk(getDepartamento());
		empleadoFuncion.getIddepartamentofk().setFuncionList(getFuncionList());
		listaEmpleado.add(empleadoFuncion);

		List<EmpleadoDTO> listEmpleado = empleadoAdapter.listarEmpleados(listaEmpleado);
		assertEquals("", listEmpleado.get(0).getApellidos());
		assertEquals(true, listEmpleado.get(0).getActivo());
		assertEquals("", listEmpleado.get(0).getCorreo());
		assertEquals(Long.valueOf("1"), listEmpleado.get(0).getDepartamento().getCodigo());
		assertEquals("", listEmpleado.get(0).getDepartamento().getDescripcion());
		assertEquals(Long.valueOf("1"), listEmpleado.get(0).getDepartamento().getIdDepartamento());
		assertEquals("", listEmpleado.get(0).getDepartamento().getNombre());
		assertEquals(Long.valueOf("1"), listEmpleado.get(0).getIdEmpleado());
		assertEquals("", listEmpleado.get(0).getNombre());
		assertEquals("", listEmpleado.get(0).getNumeroDocumento());
		assertEquals(Double.valueOf(0D), listEmpleado.get(0).getSalario());
		assertEquals("", listEmpleado.get(0).getTelefono());
		
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

}
