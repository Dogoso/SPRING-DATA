package com.doglab.spring.data.orm;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="employees")
public class Funcionario {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String cpf;
	private BigDecimal income;
	private LocalDate hiredDate;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Cargo cargo;
	
	@ManyToMany(mappedBy = "funcionarios", fetch = FetchType.LAZY)
	private List<UnidadeDeTrabalho> unities = new ArrayList<UnidadeDeTrabalho>();
	
	
	public Funcionario() 
	{
		
	}
	
	public Funcionario(String name, BigDecimal income, LocalDate hiredDate) 
	{
		this.income = income;
		this.name = name;
		this.hiredDate = hiredDate;
	}
	
	public void addUnity(UnidadeDeTrabalho unity)
	{
		unities.add(unity);
		unity.getFuncionarios().add(this);
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public BigDecimal getIncome() {
		return income;
	}
	public void setIncome(BigDecimal income) {
		this.income = income;
	}
	public LocalDate getHiredDate() {
		return hiredDate;
	}
	public void setHiredDate(LocalDate hiredDate) {
		this.hiredDate = hiredDate;
	}
	public Cargo getCargo() {
		return cargo;
	}
	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}
	public List<UnidadeDeTrabalho> getUnities() {
		return unities;
	}
	public void setUnities(List<UnidadeDeTrabalho> unities) {
		this.unities = unities;
	}

	@Override
	public String toString()
	{
		return String.format("%d - %s [income: %s, Hired date: %s]", 
				id, name, income, hiredDate);
	}
	
}
