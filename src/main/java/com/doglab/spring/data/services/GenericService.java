package com.doglab.spring.data.services;

import java.util.Scanner;

public class GenericService {

	protected boolean run;

	public void service(Scanner scanner)
	{
		run = true;
		while(run)
		{
			
			System.out.println("--------------------");
			System.out.println("Digite para: ");
			System.out.println("0 - VOLTAR");
			System.out.println("1 - SALVAR");
			System.out.println("2 - ATUALIZAR");
			System.out.println("3 - LISTAR");
			System.out.println("4 - DELETAR");
			System.out.println("--------------------");
			System.out.print("Opção: ");
			
			Integer option = scanner.nextInt();
			switch(option)
			{
				case 0:
					run=false;
					break;
				case 1:
					save(scanner);
					break;
				case 2:
					update(scanner);
					break;
				case 3:
					listAll();
					break;
				case 4:
					delete(scanner);
					break;
				default:
					System.out.println("OPÇÃO INVÁLIDA!");
					break;
			}
		}
	}
	
	protected void save(Scanner scanner) {}
	
	protected void update(Scanner scanner) {}
	
	protected void listAll() {}
	
	protected void delete(Scanner scanner) {}
	
}
