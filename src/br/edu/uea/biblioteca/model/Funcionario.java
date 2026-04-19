package br.edu.uea.biblioteca.model;

import java.util.ArrayList;


public class Funcionario extends Usuario implements Autenticavel{
	
	private Cargo cargo;
	private double salario;
	private int senha;

	
	public Funcionario() {
		super();
	}
	
	public Funcionario(String cpf, String nome, String email, Cargo cargo, double salario, int senha) {
		super(cpf, nome, email);
		this.cargo = cargo;
		this.salario=salario;
		this.senha = senha;
	}
	
	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}

	public double getSalario() {
		return salario;
	}

	public void setSalario(double salario) {
		this.salario = salario;
	}
	
	public int getSenha() {
		return senha;
	}

//	public void setSenha(int senha) {
//		this.senha = senha;
//	}
	
	@Override
	public boolean login(int senha) {
		return this.getSenha()==senha;
	}
	
}
