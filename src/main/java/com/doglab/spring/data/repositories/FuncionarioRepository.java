package com.doglab.spring.data.repositories;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.doglab.spring.data.orm.Funcionario;

@Repository
public interface FuncionarioRepository 
		extends PagingAndSortingRepository<Funcionario, Long>{

	Optional<Funcionario> findByName(String name);
	
	boolean existsByName(String name);
	
	@Query(value="SELECT f FROM Funcionario AS f "
			+ "LEFT JOIN FETCH f.unities "
			+ "WHERE f.name = :name")
	Optional<Funcionario> findByNameEager(String name);
	
	@Query("SELECT f FROM Funcionario AS f WHERE f.income > :greatherThan")
	List<Funcionario> findIncomeWhere(BigDecimal greatherThan);
	
}
