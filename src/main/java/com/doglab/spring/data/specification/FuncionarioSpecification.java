package com.doglab.spring.data.specification;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.data.jpa.domain.Specification;

import com.doglab.spring.data.orm.Funcionario;

public class FuncionarioSpecification {

	public static Specification<Funcionario> name(String name)
	{
		return (root, criteriaQuery, criteriaBuilder) -> 
								criteriaBuilder.like(root.get("name"), "%"+name+"%");
	}
	
	public static Specification<Funcionario> cpf(String cpf)
	{
		return (root, criteriaQuery, criteriaBuilder) ->
								criteriaBuilder.equal(root.get("cpf"), cpf);
	}
	
	public static Specification<Funcionario> income(BigDecimal income)
	{
		return (root, criteriaQuery, criteriaBuilder) ->
								criteriaBuilder.greaterThan(root.get("income"), income);
	}
	
	public static Specification<Funcionario> hiredDate(LocalDate hiredDate)
	{
		return (root, criteriaQuery, criteriaBuilder) ->
								criteriaBuilder.equal(root.get("hiredDate"), hiredDate);
	}
	
}
