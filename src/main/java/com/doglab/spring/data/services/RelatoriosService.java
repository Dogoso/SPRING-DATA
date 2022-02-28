package com.doglab.spring.data.services;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.doglab.spring.data.orm.Funcionario;
import com.doglab.spring.data.orm.projections.FuncionarioProjection;
import com.doglab.spring.data.repositories.FuncionarioRepository;
import com.doglab.spring.data.specification.FuncionarioSpecification;

@Service
public class RelatoriosService extends GenericService {
	
	private final FuncionarioRepository repo;
	
	public RelatoriosService(FuncionarioRepository repo) 
	{
		this.repo = repo;
	}
	
	@Override
	public void service(Scanner scanner) 
	{
		run = true;
		while(run)
		{
			System.out.println("--------------------");
			System.out.println("Digite para: ");
			System.out.println("0 - VOLTAR");
			System.out.println("1 - RELATORIO DE SALÁRIOS");
			System.out.println("2 - RELATÓRIO DINÂMICO");
			System.out.println("--------------------");
			System.out.print("Opção: ");
			
			Integer option = scanner.nextInt();
			switch(option)
			{
				case 0:
					run=false;
					break;
				case 1:
					listAll(scanner);
					break;
				case 2:
					listLike(scanner);
					break;
				default:
					System.out.println("OPÇÃO INVÁLIDA!");
					break;
			}
		}
	}
	
	@Override
	protected void listAll(Scanner scanner) 
	{
		List<FuncionarioProjection> projections = repo.findAllIncomes();
		projections.forEach(p -> System.out.println(String
				.format("Funcionario: id: %3s, nome: %10s, salario: %5s", 
						p.getId(), p.getName(), p.getIncome())));
	}
	
	protected void listLike(Scanner scanner)
	{
		
		System.out.println("Informe os campos:");
		System.out.println("Nome: ");
		String name = scanner.next();
		if(name.equals("NULL"))
		{
			name = null;
		}
		
		System.out.println("Cpf: ");
		String cpf = scanner.next();
		if(cpf.equals("NULL"))
		{
			cpf = null;
		}
			
		
		System.out.println("Income: ");
		BigDecimal income = scanner.nextBigDecimal();
		if(income == BigDecimal.ZERO)
		{
			income = null;
		}

		System.out.println("Hired Date: ");
		String hiredDateString = scanner.next();
		LocalDate hiredDate = null;
		
		if(!hiredDateString.equals("NULL"))
		{
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			hiredDate = LocalDate.parse(hiredDateString, formatter);
		}
		
		List<Funcionario> funcionarios = repo.findAll(
				Specification.where(FuncionarioSpecification.name(name))
				.or(FuncionarioSpecification.cpf(cpf))
				.or(FuncionarioSpecification.income(income))
				.or(FuncionarioSpecification.hiredDate(hiredDate))
		);
		
		funcionarios.forEach(f -> System.out.println(f));
		
	}
	
}
