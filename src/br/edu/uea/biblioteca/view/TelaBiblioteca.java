package br.edu.uea.biblioteca.view;

import javax.swing.JOptionPane;
import br.edu.uea.biblioteca.control.GerenciadorBiblioteca;

public class TelaBiblioteca {
	
	private GerenciadorBiblioteca gerenciador;
	private String[] opcoesAutenticaveis = {"Funcionario", "Professor", "Nenhuma das opções"};
	
	public void showLogin() {
		String tipoUsuario="";
		
		while (true) {
	        Object selecao = JOptionPane.showInputDialog(
	                null,
	                "Para realizar o login, informe seu perfil:",
	                "Sistema - Biblioteca UEA",
	                JOptionPane.QUESTION_MESSAGE,
	                null,
	                opcoesAutenticaveis,
	                opcoesAutenticaveis[0] // Define "Funcionario" como padrão
	        );

	        // 1. Tratamento do botão CANCELAR ou fechar a janela
	        if (selecao == null) {
	            JOptionPane.showMessageDialog(null, "Sistema encerrado.");
	            System.exit(0); 
	        }

	        tipoUsuario = selecao.toString();

	        // 2. Se escolher "Nenhuma das opções", volta para o início do loop
	        if (tipoUsuario.equals("Nenhuma das opções")) {
	            JOptionPane.showMessageDialog(null, "Por favor, selecione um perfil válido.");
	            continue; 
	        }

	        // 3. Se chegou aqui, escolheu um perfil válido (Funcionario ou Professor)
	        break; 
	    }
		
		JOptionPane.showMessageDialog(null, "Bem-vindo, " + tipoUsuario + "!");
		
	}
	
	public static void main(String[]args) {
		TelaBiblioteca tela = new TelaBiblioteca();
		tela.showLogin();
	}

}
