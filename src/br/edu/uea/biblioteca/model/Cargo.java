package br.edu.uea.biblioteca.model;

public class Cargo {
	
	private int codigo;
	private String nome;
	private String descricao;
	private int cargaHoraria;
	
	public Cargo() {}

	public Cargo(int codigo, String nome, String descricao, int cargaHoraria) {
		this.codigo = codigo;
		this.nome = nome;
		this.descricao = descricao;
		this.cargaHoraria = cargaHoraria;
	}

	public int getCodigo() {
		return codigo;
	}

	public String getNome() {
		return nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public int getCargaHoraria() {
		return cargaHoraria;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public void setCargaHoraria(int cargaHoraria) {
		this.cargaHoraria = cargaHoraria;
	}
}
