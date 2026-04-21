package br.edu.uea.biblioteca.control;

import java.util.ArrayList;

import br.edu.uea.biblioteca.model.Aluno;
import br.edu.uea.biblioteca.model.Autenticavel;
import br.edu.uea.biblioteca.model.Funcionario;
import br.edu.uea.biblioteca.model.Professor;
import br.edu.uea.biblioteca.model.Usuario;

public class UsuarioController {
	
	private static int contadorProfessores;
	private ArrayList<Usuario> usuarios;
	
	public UsuarioController() {
		usuarios = new ArrayList<>();
	}
	
	//Create (Criar), Read (Ler/Consultar), Update (Atualizar) e Delete (Deletar/Excluir)
	
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
	    
	    if(usuario instanceof Professor) {
	    	++contadorProfessores;
	    	((Professor)usuario).setSenha(contadorProfessores);
	    }
	    usuarios.add(usuario);
		return true;
	}
	
	public boolean login(String cpf, int senha) {
		Usuario u = buscarUsuario(cpf);
		if(u!=null && (u instanceof Autenticavel)) {
			return ((Autenticavel)u).login(senha);
		}
		return false;
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

}
