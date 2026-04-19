package br.edu.uea.biblioteca.control;

import java.util.ArrayList;

import br.edu.uea.biblioteca.model.*;

public class GerenciadorBiblioteca {
	
	private ArrayList<Usuario> usuarios;
	private ArrayList<Cargo> cargos;
	private ArrayList<Livro> livros;
	
	public GerenciadorBiblioteca() {
		usuarios = new ArrayList<>();
		cargos = new ArrayList<>();
		livros = new ArrayList<>();		
	}
		
	public Usuario buscarUsuario(String cpf) {
		for(Usuario u : usuarios) {
			if(cpf.equals(u.getCpf())) {
				return u;
			}
		}
		return null;
	}
	
	public boolean cadastrarUsuario(Usuario usuario) {
		Usuario u = buscarUsuario(usuario.getCpf());
	    if (u != null) return false;
	    
	    usuarios.add(u);
		return true;
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
	    
		livros.add(l);
		return true;
	}
	
	public boolean login(String cpf, int senha) {
		Usuario u = buscarUsuario(cpf);
		if(u!=null && (u instanceof Autenticavel)) {
			return ((Autenticavel)u).login(senha);
		}
		return false;
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
	
	public ArrayList<Funcionario> getFuncionarios() {
	    ArrayList<Funcionario> funcionarios = new ArrayList<>();
	    for (Usuario u : usuarios) {
	        if (u instanceof Funcionario) {
	            funcionarios.add((Funcionario) u);
	        }
	    }
	    return funcionarios;
	}
	
	public ArrayList<Professor> getProfessores() {
	    ArrayList<Professor> professores = new ArrayList<>();
	    for (Usuario u : usuarios) {
	        if (u instanceof Professor) {
	        	professores.add((Professor) u);
	        }
	    }
	    return professores;
	}
	
	public ArrayList<Aluno> getAlunos() {
	    ArrayList<Aluno> alunos = new ArrayList<>();
	    for (Usuario u : usuarios) {
	        if (u instanceof Aluno) {
	            alunos.add((Aluno) u);
	        }
	    }
	    return alunos;
	}
	
	
	public ArrayList<Cargo> getCargos(){
		return this.cargos;
	}
	
	public ArrayList<Livro> getLivros() {
		return this.livros;
	}

}
