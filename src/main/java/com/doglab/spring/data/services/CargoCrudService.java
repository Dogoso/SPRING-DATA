package com.doglab.spring.data.services;

import java.util.Optional;
import java.util.Scanner;

import org.springframework.stereotype.Service;

import com.doglab.spring.data.orm.Cargo;
import com.doglab.spring.data.repositories.CargoRepository;

@Service
public class CargoCrudService extends GenericService 
{

	private final CargoRepository repo;
	
	public CargoCrudService(CargoRepository repo)
	{
		this.repo = repo;
	}
	
	protected void save(Scanner scanner)
	{
		System.out.print("Descrição do CARGO: ");
		String description = scanner.next();
		
		Cargo cargo = new Cargo(description);
		repo.save(cargo);
		
		System.out.println("");
		System.out.printf("Cargo : %s salvo com SUCESSO!", description);
		System.out.println();
	}
	
	protected void update(Scanner scanner)
	{
		System.out.print("Id do cargo: ");
		Long id = scanner.nextLong();
		
		if(repo.existsById(id)) 
		{
			System.out.println("Nova descrição: ");
			String newDescription = scanner.next();
			Cargo cargo = new Cargo(newDescription);
			cargo.setId(id);
			repo.save(cargo);
		}
		else 
		{
			System.out.println("ID INVÁLIDO!");
		}
	}
	
	protected void listAll()
	{
		Iterable<Cargo> cargos = repo.findAll();
		cargos.forEach(cargo -> System.out.println(cargo));
	}
	
	protected void delete(Scanner scanner)
	{
		System.out.print("Id do cargo: ");
		Long id = scanner.nextLong();
		
		if(repo.existsById(id))
		{
			Optional<Cargo> optCargo = repo.findById(id);
			Cargo cargo = optCargo.get();
			repo.deleteById(id);
			System.out.printf("CARGO %s DELETADO!", cargo.getDescription());
		}
		else
		{
			System.out.println("ID INVÁLIDO!");
		}

	}
	
}
