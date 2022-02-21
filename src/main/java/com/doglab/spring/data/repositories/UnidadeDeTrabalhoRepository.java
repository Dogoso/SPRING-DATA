package com.doglab.spring.data.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.doglab.spring.data.orm.UnidadeDeTrabalho;

@Repository
public interface UnidadeDeTrabalhoRepository 
	extends CrudRepository<UnidadeDeTrabalho, Long>{

}
