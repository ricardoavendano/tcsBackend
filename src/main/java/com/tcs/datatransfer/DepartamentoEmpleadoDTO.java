package com.tcs.datatransfer;

import java.util.List;

public class DepartamentoEmpleadoDTO {

	private Long idDepartamento;
	private String nombre;
	private String descripcion;
	private Long codigo;
	private List<EmpleadoBasicoDTO> empleadoBasicoDTO;
	private List<FuncionDTO> funcionDTO;

	public Long getIdDepartamento() {
		return idDepartamento;
	}

	public void setIdDepartamento(Long idDepartamento) {
		this.idDepartamento = idDepartamento;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public List<EmpleadoBasicoDTO> getEmpleadoBasicoDTO() {
		return empleadoBasicoDTO;
	}

	public void setEmpleadoBasicoDTO(List<EmpleadoBasicoDTO> empleadoBasicoDTO) {
		this.empleadoBasicoDTO = empleadoBasicoDTO;
	}

	public List<FuncionDTO> getFuncionDTO() {
		return funcionDTO;
	}

	public void setFuncionDTO(List<FuncionDTO> funcionDTO) {
		this.funcionDTO = funcionDTO;
	}

}
