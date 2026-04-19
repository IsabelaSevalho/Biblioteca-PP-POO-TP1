package br.edu.uea.biblioteca.model;

public class Professor extends Usuario implements Autenticavel{
	
	private String titulacao;
	private int senha;
	
	public Professor() {}

	public Professor(String cpf, String nome, String email, String titulacao, int senha) {
		super(cpf, nome, email);
		this.titulacao = titulacao;
		this.senha = senha;
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

	@Override
	public boolean login(int senha) {
		return this.getSenha()==senha;
	}
}
