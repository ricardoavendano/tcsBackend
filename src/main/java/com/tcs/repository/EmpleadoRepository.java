package com.tcs.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import com.tcs.domain.Empleado;

@Component
public interface EmpleadoRepository extends CrudRepository<Empleado, Long> {

	@Query("SELECT u FROM Empleado u WHERE u.numeroDocumento = :numeroDocumento")
	List<Empleado> listaEmpleadoXDocuemnto(@Param("numeroDocumento") String numeroDocumento);
	
	@Transactional
	@Modifying
	@Query("UPDATE Empleado u SET u.iddepartamentofk.idDepartamento = :iddepartamento, u.idfuncionfk.idFuncion = 1 WHERE u.numeroDocumento = :numeroDocumento")
	void empleadoADepartamento(@Param("iddepartamento") Long iddepartamento, @Param("numeroDocumento") String numeroDocumento);

}
