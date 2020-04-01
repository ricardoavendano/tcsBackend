package com.tcs.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "funcion")
public class Funcion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false, name = "idfuncion")
	private Long idFuncion;

	@Column(nullable = false, name = "nombre")
	private String nombre;

	@Column(nullable = false, name = "descripcion")
	private String descripcion;

	@JoinColumn(name = "IDDEPARTAMENTOPK", referencedColumnName = "IDDEPARTAMENTO")
	@ManyToOne(optional = false)
	private Departamento iddepartamentopk;

	public Long getIdFuncion() {
		return idFuncion;
	}

	public void setIdFuncion(Long idFuncion) {
		this.idFuncion = idFuncion;
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

	public Departamento getIddepartamentopk() {
		return iddepartamentopk;
	}

	public void setIddepartamentopk(Departamento iddepartamentopk) {
		this.iddepartamentopk = iddepartamentopk;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof Funcion)) {
			return false;
		} else {
			Funcion other = (Funcion) object;
			if ((this.idFuncion == null && other.idFuncion != null)
					|| (this.idFuncion != null && !this.idFuncion.equals(other.idFuncion))) {
				return false;
			}
			return true;
		}
	}

	@Override
	public String toString() {
		return "entity.Funcion[ idFuncion=" + idFuncion + " ]";
	}
}
