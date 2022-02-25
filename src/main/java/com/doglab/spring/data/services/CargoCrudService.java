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
	
	@Override
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
	
	@Override
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
	
	@Override
	protected void listAll(Scanner scanner)
	{
		Iterable<Cargo> cargos = repo.findAll();
		cargos.forEach(cargo -> System.out.println(cargo));
	}
	
	@Override
	protected void delete(Scanner scanner)
	{
		System.out.print("Descrição do cargo: ");
		String desc = scanner.next();
		
		if(repo.existsByDescription(desc))
		{
			Optional<Cargo> optCargo = repo.findWithDescription(desc);
			Cargo cargo = optCargo.get();
			repo.deleteById(cargo.getId());
			System.out.printf("CARGO %s DELETADO!", cargo.getDescription());
		}
		else
		{
			System.out.println("DESCRIÇÃO INVÁLIDA!");
		}

	}
	
}
