package com.doglab.spring.data.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.doglab.spring.data.orm.Funcionario;

@Repository
public interface FuncionarioRepository 
		extends CrudRepository<Funcionario, Long>{

}
