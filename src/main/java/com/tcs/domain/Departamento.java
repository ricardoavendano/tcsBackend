package com.tcs.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "departamento")
public class Departamento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false, name = "iddepartamento")
	private Long idDepartamento;

	@Column(nullable = false, name = "nombre")
	private String nombre;

	@Column(nullable = false, name = "descripcion")
	private String descripcion;

	@Column(nullable = false, name = "codigo")
	private Long codigo;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "iddepartamentofk")
	private List<Empleado> empleadoList;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "iddepartamentopk")
	private List<Funcion> funcionList;

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

	public List<Empleado> getEmpleadoList() {
		return empleadoList;
	}

	public void setEmpleadoList(List<Empleado> empleadoList) {
		this.empleadoList = empleadoList;
	}

	public List<Funcion> getFuncionList() {
		return funcionList;
	}

	public void setFuncionList(List<Funcion> funcionList) {
		this.funcionList = funcionList;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof Departamento)) {
			return false;
		} else {
			Departamento other = (Departamento) object;
			if ((this.idDepartamento == null && other.idDepartamento != null)
					|| (this.idDepartamento != null && !this.idDepartamento.equals(other.idDepartamento))) {
				return false;
			}
			return true;
		}
	}

	@Override
	public String toString() {
		return "entity.Departamento[ idDepartamento=" + idDepartamento + " ]";
	}
}
