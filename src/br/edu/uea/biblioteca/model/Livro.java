package br.edu.uea.biblioteca.model;

import java.util.ArrayList;

public class Livro {
	private String titulo;
	private ArrayList<String> autores;
	private String editora;
	private int numeroPaginas;
	private String isbn;
	private String genero;
	private String sinopse;
	private String idioma;
	
	public Livro() {}
	
	public Livro(String titulo, ArrayList<String> autores, String editora, int numeroPaginas, String isbn,
			String genero, String sinopse, String idioma) {
		this.titulo = titulo;
		this.autores = autores;
		this.editora = editora;
		this.numeroPaginas = numeroPaginas;
		this.isbn = isbn;
		this.genero = genero;
		this.sinopse = sinopse;
		this.idioma = idioma;
	}

	public String getTitulo() {
		return titulo;
	}

	public ArrayList<String> getAutores() {
		return autores;
	}

	public String getEditora() {
		return editora;
	}

	public int getNumeroPaginas() {
		return numeroPaginas;
	}

	public String getIsbn() {
		return isbn;
	}

	public String getGenero() {
		return genero;
	}

	public String getSinopse() {
		return sinopse;
	}

	public String getIdioma() {
		return idioma;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public void setAutores(ArrayList<String> autores) {
		this.autores = autores;
	}

	public void setEditora(String editora) {
		this.editora = editora;
	}

	public void setNumeroPaginas(int numeroPaginas) {
		this.numeroPaginas = numeroPaginas;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public void setSinopse(String sinopse) {
		this.sinopse = sinopse;
	}

	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}

	
}
