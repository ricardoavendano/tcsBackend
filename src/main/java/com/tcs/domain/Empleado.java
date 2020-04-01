package com.tcs.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

//@Transactional
@Entity
@Table(name = "empleado")
public class Empleado {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false, name = "idempleado")
	private Long idEmpleado;

	@Column(nullable = false, name = "nombre")
	private String nombre;

	@Column(nullable = false, name = "apellidos")
	private String apellidos;

	@Column(nullable = false, name = "numerodocumento")
	private String numeroDocumento;

	@Column(nullable = false, name = "correo")
	private String correo;

	@Column(nullable = false, name = "telefono")
	private String telefono;

	@Column(nullable = false, name = "activo")
	private Boolean activo;

	@Column(nullable = false, name = "salario")
	private Double salario;

	@JoinColumn(name = "IDDEPARTAMENTOFK", referencedColumnName = "IDDEPARTAMENTO")
	@ManyToOne(optional = false)
	private Departamento iddepartamentofk;

	@JoinColumn(name = "IDFUNCIONFK", referencedColumnName = "IDFUNCION")
	@ManyToOne(optional = false)
	private Funcion idfuncionfk;

	public Long getIdEmpleado() {
		return idEmpleado;
	}

	public void setIdEmpleado(Long idEmpleado) {
		this.idEmpleado = idEmpleado;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	public Double getSalario() {
		return salario;
	}

	public void setSalario(Double salario) {
		this.salario = salario;
	}

	public Departamento getIddepartamentofk() {
		return iddepartamentofk;
	}

	public void setIddepartamentofk(Departamento iddepartamentofk) {
		this.iddepartamentofk = iddepartamentofk;
	}

	public Funcion getIdfuncionfk() {
		return idfuncionfk;
	}

	public void setIdfuncionfk(Funcion idfuncionfk) {
		this.idfuncionfk = idfuncionfk;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof Empleado)) {
			return false;
		} else {
			Empleado other = (Empleado) object;
			if ((this.idEmpleado == null && other.idEmpleado != null)
					|| (this.idEmpleado != null && !this.idEmpleado.equals(other.idEmpleado))) {
				return false;
			}
			return true;
		}
	}

	@Override
	public String toString() {
		return "entity.Empleado[ idEmpleado=" + idEmpleado + " ]";
	}

}
