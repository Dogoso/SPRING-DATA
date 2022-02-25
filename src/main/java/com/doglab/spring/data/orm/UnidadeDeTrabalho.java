package com.doglab.spring.data.orm;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="unidades")
public class UnidadeDeTrabalho {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String description;
	private String adress;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "funcionarios_id")
	private List<Funcionario> funcionarios = new ArrayList<Funcionario>();
	
	public UnidadeDeTrabalho()
	{
		
	}
	
	public UnidadeDeTrabalho(String description, String adress)
	{
		this.description = description;
		this.adress = adress;
	}
	
	public void addEmployee(Funcionario employee) 
	{
		funcionarios.add(employee);
		employee.getUnities().add(this);
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getAdress() {
		return adress;
	}
	public void setAdress(String adress) {
		this.adress = adress;
	}
	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}
	public void setFuncionarios(List<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}

	@Override
	public String toString() {
		return "UnidadeDeTrabalho [id=" + id + ", description=" + description + ", adress=" + adress + "]";
	}
	
}
