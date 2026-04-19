package br.edu.uea.biblioteca.model;

public class Professor extends Usuario implements Autenticavel{
	
	private String titulacao;
	private int senha;
	
	public Professor() {}
	
	public Professor(String cpf, String nome, String email, String titulacao) {
		super(cpf, nome, email);
		this.titulacao = titulacao;
	}

	public String getTitulacao() {
		return titulacao;
	}

	public int getSenha() {
		return senha;
	}

	public void setTitulacao(String titulacao) {
		this.titulacao = titulacao;
	}
	
	public void setSenha(int senha) {
		this.senha = senha;
	}

	@Override
	public boolean login(int senha) {
		return this.senha==senha;
	}
}
