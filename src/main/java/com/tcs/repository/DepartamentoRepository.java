package com.tcs.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import com.tcs.domain.Departamento;

@Component
public interface DepartamentoRepository extends CrudRepository<Departamento, Long>{

}
