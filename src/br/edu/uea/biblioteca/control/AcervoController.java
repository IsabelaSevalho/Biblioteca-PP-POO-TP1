package br.edu.uea.biblioteca.control;

import java.util.ArrayList;

import br.edu.uea.biblioteca.model.Livro;

public class AcervoController {
	private ArrayList<Livro> livros;
	
	public AcervoController() {
		livros = new ArrayList<>();	
	}
	
	public Livro buscarLivro(String isbn) {
		for(Livro l : livros) {
			if(isbn.equals(l.getIsbn())) {
				return l;
			}
		}
		return null;
	}
	
	public boolean cadastrarLivro(Livro livro) {
		Livro l = buscarLivro(livro.getIsbn());
	    if (l != null) return false;
	    
		livros.add(livro);
		return true;
	}
	
	public ArrayList<Livro> consultarAcervo(String tituloBuscado){
		ArrayList<Livro> livrosRetornados = new ArrayList<>();
		
		for(Livro l : livros) {
			if(l.getTitulo().toLowerCase().contains(tituloBuscado.toLowerCase())) {
				livrosRetornados.add(l);
			}
		}
		
		return livrosRetornados;
	}
	
	public ArrayList<Livro> getLivros() {
		return this.livros;
	}
}
