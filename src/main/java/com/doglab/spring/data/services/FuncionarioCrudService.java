package com.doglab.spring.data.services;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.Scanner;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.doglab.spring.data.orm.Cargo;
import com.doglab.spring.data.orm.Funcionario;
import com.doglab.spring.data.orm.UnidadeDeTrabalho;
import com.doglab.spring.data.repositories.FuncionarioRepository;

@Service
public class FuncionarioCrudService extends GenericService 
{

	private final FuncionarioRepository repo;
	
	public FuncionarioCrudService(FuncionarioRepository repo)
	{
		this.repo = repo;
	}
	
	protected void save(Scanner scanner) 
	{
		System.out.println("Name: ");
		String name = scanner.next();
		System.out.println("Cpf: ");
		String cpf = scanner.next();
		System.out.println("Income: ");
		BigDecimal income = scanner.nextBigDecimal();

		System.out.println("Cargo: ");
		Long cargoId = scanner.nextLong();
		Cargo cargo = new Cargo();
		cargo.setId(cargoId);
		
		System.out.println("Unity: ");
		Long unityId = scanner.nextLong();
		UnidadeDeTrabalho unity = new UnidadeDeTrabalho();
		unity.setId(unityId);
		
		Funcionario funcionario = new Funcionario(name, income, LocalDate.now());

		funcionario.setCpf(cpf);
		funcionario.setCargo(cargo);
		funcionario.addUnity(unity);
		
		repo.save(funcionario);
		
		System.out.printf("FUNCIONARIO %s SALVO COM SUCESSO!", name);
		System.out.println("");
	}
	
	protected void listAll(Scanner scanner)
	{
		System.out.println("--- PAG 1 ... 2");
		Integer page = scanner.nextInt() - 1;
		
		Pageable pageable = PageRequest.of(page, 3, Sort.by(Sort.Direction.ASC ,"name"));
		
		Page<Funcionario> funcionarios = repo.findAll(pageable);
		funcionarios.forEach(funcionario -> System.out.println(funcionario));
	}
	
	protected void update(Scanner scanner)
	{
		System.out.println("Nome do Funcionario: ");
		String nameTyped = scanner.next();
		
		if(repo.existsByName(nameTyped))
		{
			Optional<Funcionario> optFuncionario = repo.findByNameEager(nameTyped);
			Funcionario funcionario = optFuncionario.get();
			
			System.out.println("Name: ");
			String name = scanner.next();
			System.out.println("Cpf: ");
			String cpf = scanner.next();
			System.out.println("Income: ");
			BigDecimal income = scanner.nextBigDecimal();
			System.out.println("Hired Date: ");

			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			String newDate = scanner.next();
			LocalDate hiredDate = LocalDate.parse(newDate, formatter);
			
			System.out.println("Cargo: ");
			Long cargoId = scanner.nextLong();
			Cargo cargo = new Cargo();
			cargo.setId(cargoId);
			
			setUnities(funcionario, scanner);
			
			funcionario.setName(name);
			funcionario.setCpf(cpf);
			funcionario.setIncome(income);
			funcionario.setHiredDate(hiredDate);
			funcionario.setCargo(cargo);

			repo.save(funcionario);
		}
		else
		{
			System.out.println("ID INVÁLIDO!");
		}
	}
	
	protected void delete(Scanner scanner)
	{
		System.out.print("Id do Funcionario: ");
		Long id = scanner.nextLong();
		if(repo.existsById(id))
		{
			Optional<Funcionario> optFuncionario = repo.findById(id);
			Funcionario funcionario = optFuncionario.get();
			repo.deleteById(id);
			System.out.printf("FUNCIONARIO %s DELETADO!", 
					funcionario.getName());
		}
		else
		{
			System.out.println("ID INVÁLIDO!");
		}
	}
	
	private void setUnities(Funcionario funcionario, Scanner scanner)
	{
		boolean more = true;
		while(more)
		{
			System.out.println("Unity: ");
			Long unityId = scanner.nextLong();
			UnidadeDeTrabalho unity = new UnidadeDeTrabalho();
			unity.setId(unityId);
			funcionario.addUnity(unity);
			
			System.out.println("Quer adicionar mais uma unidade?[0=N, 1=Y] ");
			Integer answer = scanner.nextInt();
			if(answer == 0)
			{
				more = false;
			}
				
		}
	}
	
}
