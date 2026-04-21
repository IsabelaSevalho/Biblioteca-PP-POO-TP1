package br.edu.uea.biblioteca.model;

public class Emprestimo {
	private String isbnLivro;
	private String cpfEmprestante;
	
	public Emprestimo() {}
	
	public Emprestimo(String isbnLivro, String cpfEmprestante) {
		this.isbnLivro=isbnLivro;
		this.cpfEmprestante =cpfEmprestante;
	}

	public String getIsbnLivro() {
		return isbnLivro;
	}

	public String getCpfEmprestante() {
		return cpfEmprestante;
	}

	public void setIsbnLivro(String isbnLivro) {
		this.isbnLivro = isbnLivro;
	}

	public void setCpfEmprestante(String cpfEmprestante) {
		this.cpfEmprestante = cpfEmprestante;
	}
	
	

}
