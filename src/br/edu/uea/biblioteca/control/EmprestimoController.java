package br.edu.uea.biblioteca.control;

import java.util.ArrayList;

import br.edu.uea.biblioteca.model.Aluno;
import br.edu.uea.biblioteca.model.Emprestimo;
import br.edu.uea.biblioteca.model.Professor;
import br.edu.uea.biblioteca.model.Usuario;


public class EmprestimoController {
	
	private ArrayList<Emprestimo> emprestimos;
	private AcervoController acervoController;
    private UsuarioController usuarioController;
    
    public EmprestimoController(AcervoController acervoController,  UsuarioController usuarioController) {
    	this.acervoController= acervoController;
    	this.usuarioController = usuarioController;
    	emprestimos = new ArrayList<>();
    }
    
    public boolean realizarEmprestimo(String isbn, String cpf) {
    	Usuario usuario= usuarioController.buscarUsuario(cpf);
    	
    	if (acervoController.buscarLivro(isbn) == null) return false;
    	if (usuario == null || !(usuario instanceof Professor || usuario instanceof Aluno)) return false;
    	if(!verificarDisponibilidade(isbn)) return false;
    	
    	Emprestimo empNovo = new Emprestimo(isbn, cpf);
    	emprestimos.add(empNovo);
    	return true;
    }
    
    public boolean devolucao(String isbn, String cpf) {
    	for(int i=0;i<emprestimos.size();i++) {
    		
    		Emprestimo emprestimo = emprestimos.get(i);
    		
    		if(isbn.equals(emprestimo.getIsbnLivro()) && cpf.equals(emprestimo.getCpfEmprestante())) {
    			emprestimos.remove(i);
    			return true;
    		}
    	}return false;
    }
    
    public boolean verificarDisponibilidade(String isbn) {
    	
    	for(int i=0;i<emprestimos.size();i++) {
    		Emprestimo emprestimo = emprestimos.get(i);
    		
    		if(isbn.equals(emprestimo.getIsbnLivro())) {
    			return false; //nao disponivel
    		}
    	}return true;
    }

}
