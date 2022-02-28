package com.doglab.spring.data;

import java.util.Scanner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.doglab.spring.data.services.CargoCrudService;
import com.doglab.spring.data.services.FuncionarioCrudService;
import com.doglab.spring.data.services.RelatoriosService;
import com.doglab.spring.data.services.UnidadeDeTrabalhoCrudService;

@SpringBootApplication
public class SpringDataApplication implements CommandLineRunner {
	
	private final CargoCrudService cargoService;
	private final FuncionarioCrudService funcionarioService;
	private final UnidadeDeTrabalhoCrudService unidadesService;
	private final RelatoriosService relatorioService;
	
	private boolean run = true;
	
	public SpringDataApplication(CargoCrudService cargoService,
			FuncionarioCrudService funcionarioService,
			UnidadeDeTrabalhoCrudService unidadesService,
			RelatoriosService relatorioService) 
	{
		this.cargoService = cargoService;
		this.funcionarioService = funcionarioService;
		this.unidadesService = unidadesService;
		this.relatorioService = relatorioService;
	}
	
	public static void main(String[] args) 
	{
		SpringApplication.run(SpringDataApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception 
	{
		Scanner scanner = new Scanner(System.in);
		while(run)
		{
			System.out.println("--------------------");
			System.out.println("Digite para: ");
			System.out.println("0 - SAIR");
			System.out.println("1 - CARGOS ->");
			System.out.println("2 - FUNCIONARIOS ->");
			System.out.println("3 - UNIDADES ->");
			System.out.println("4 - RELATORIOS ->");
			System.out.println("--------------------");
			System.out.print("Opção: ");
			Integer option = scanner.nextInt();
			if(option == 0)
			{
				run = false;
				System.out.println("Aplicação Encerrada...");
			}
			else if(option == 1)
			{
				cargoService.service(scanner);
			}
			else if(option == 2)
			{
				funcionarioService.service(scanner);
			}
			else if(option == 3)
			{
				unidadesService.service(scanner);
			}
			else if(option == 4)
			{
				relatorioService.service(scanner);
			}
			else
			{
				System.out.println("OPÇÃO INVÁLIDA!");
			}
		}
	}

}
