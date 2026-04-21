package br.edu.uea.biblioteca.control;

import java.util.ArrayList;

import br.edu.uea.biblioteca.model.Aluno;
import br.edu.uea.biblioteca.model.Professor;
import br.edu.uea.biblioteca.model.Usuario;


public class EmprestimoController {
	
	private ArrayList<String[]> emprestimos;
	private AcervoController acervoController;
    private UsuarioController usuarioController;
    
    public EmprestimoController() {
    	acervoController= new AcervoController();
    	usuarioController = new UsuarioController();
    	emprestimos = new ArrayList<>();
    }
    
    public boolean realizarEmprestimo(String isbn, String cpf) {
    	Usuario usuario= usuarioController.buscarUsuario(cpf);
    	
    	if (acervoController.buscarLivro(isbn) == null) return false;
    	if (usuario == null || !(usuario instanceof Professor || usuario instanceof Aluno)) return false;
    	if(!verificarDisponibilidade(isbn)) return false;
    	
    	String[] empNovo = {isbn, cpf};
    	emprestimos.add(empNovo);
    	return true;
    }
    
    public boolean devolucao(String isbn, String cpf) {
    	for(int i=0;i<emprestimos.size();i++) {
    		
    		Object[] emprestimo = emprestimos.get(i);
    		String isbnEmprestimo = (String) emprestimo[0];
    		String cpfEmprestimo = (String) emprestimo[1]; 
    		
    		if(isbn.equals(isbnEmprestimo) && cpf.equals(cpfEmprestimo)) {
    			emprestimos.remove(i);
    			return true;
    		}
    	}return false;
    }
    
    public boolean verificarDisponibilidade(String isbn) {
    	
    	for(int i=0;i<emprestimos.size();i++) {
    		Object[] emprestimo = emprestimos.get(i);
    		String isbnEmprestimo = (String) emprestimo[0];
    		
    		if(isbn.equals(isbnEmprestimo)) {
    			return false; //nao disponivel
    		}
    	}return true;
    }

}
