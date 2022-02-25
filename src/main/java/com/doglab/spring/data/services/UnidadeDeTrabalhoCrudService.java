package com.doglab.spring.data.services;

import java.util.Optional;
import java.util.Scanner;

import org.springframework.stereotype.Service;

import com.doglab.spring.data.orm.UnidadeDeTrabalho;
import com.doglab.spring.data.repositories.UnidadeDeTrabalhoRepository;

@Service
public class UnidadeDeTrabalhoCrudService extends GenericService{

	private final UnidadeDeTrabalhoRepository repo;
	
	public UnidadeDeTrabalhoCrudService(UnidadeDeTrabalhoRepository repo)
	{
		this.repo = repo;
	}
	
	@Override
	protected void save(Scanner scanner) {
		
		System.out.println("Description: ");
		String desc = scanner.next();
		System.out.println("Adress: ");
		String adress = scanner.next();
		
		UnidadeDeTrabalho unidade = new UnidadeDeTrabalho(desc, adress);
		
		repo.save(unidade);
		
		System.out.printf("UNIDADE EM %s CRIADA COM SUCESSO!", adress);
		
	}
	
	@Override
	protected void update(Scanner scanner) {
		
		System.out.println("Id da Unidade: ");
		Long id = scanner.nextLong();
		
		if(repo.existsById(id))
		{
			Optional<UnidadeDeTrabalho> optUnity = repo.findById(id);
			UnidadeDeTrabalho unity = optUnity.get();

			System.out.println("Description: ");
			String desc = scanner.next();
			System.out.println("Adress: ");
			String adress = scanner.next();
			
			unity.setDescription(desc);
			unity.setAdress(adress);
			repo.save(unity);
		}
		else
		{
			System.out.println("ID DA UNIDADE INVÁLIDO!");
		}
	}
	
	@Override
	protected void listAll(Scanner scanner) {
		Iterable<UnidadeDeTrabalho> unidades = repo.findAll();
		unidades.forEach(unidade -> System.out.println(unidade));
	}
	
	@Override
	protected void delete(Scanner scanner) {
		System.out.println("Unity Id: ");
		Long id = scanner.nextLong();
		if(repo.existsById(id))
		{
			Optional<UnidadeDeTrabalho> optUnity = repo.findById(id);
			UnidadeDeTrabalho unity = optUnity.get();
			repo.deleteById(id);
			System.out.printf("UNIDADE %s DELETADA COM SUCESSO!", unity.getDescription());
		}
		else
		{
			System.out.println("ID DA UNIDADE INVÁLIDO!");
		}
	}
	
}
