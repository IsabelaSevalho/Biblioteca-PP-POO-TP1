package br.edu.uea.biblioteca.view;

import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import br.edu.uea.biblioteca.control.*;
import br.edu.uea.biblioteca.model.*;

public class TelaBiblioteca {
	
	private UsuarioController usuarioController;
	private AcervoController acervoController;
	private EmprestimoController emprestimoController;
	private CargoController cargoController;
	private Usuario usuario;
	
	//teste
	private Funcionario funcionarioAdm;

	
	public TelaBiblioteca() {
		usuarioController = new UsuarioController();
		acervoController= new AcervoController();
		emprestimoController= new EmprestimoController();
		cargoController = new CargoController();
		
		//teste----------------------------------------------------------------------
		Cargo adm = new Cargo(123, "Administrador", "Gerencia o sistema e faz os cadastros iniciais, tendo controle total.", 1200);
		funcionarioAdm = new Funcionario("04503078240", "Ana Banana", "adm@gmail.com", adm, 44000.01, 123);
		usuarioController.cadastrarUsuario(funcionarioAdm);
	}
	
	public boolean login(String cpf, int senha) {
		if(usuarioController.login(cpf, senha)) {
			usuario = usuarioController.buscarUsuario(cpf);
			return true;
		}return false;
	}
	
	
	public void iniciarSessaoFuncionario(Funcionario funcionario) {
		int opcao= -1;
		
		while(opcao!=0) {
			
			String retorno = JOptionPane.showInputDialog(
					null, "Bem-vindo, " + funcionario.getNome() + "!\nEscolha uma opção:"
					+"\n\n1. Consultar Acervo"
					+"\n2. Cadastrar Livro"
					+"\n3. Listar Livros"
					+"\n4. Registrar empréstimo de livro"
					+"\n5. Registrar devolução de livro"
					+"\n6. Cadastrar Funcionário"
					+"\n7. Listar Funcionários"
					+"\n8. Cadastrar Professor"
					+"\n9. Listar Professores"
					+"\n10. Cadastrar Aluno"
					+"\n11. Listar Alunos"
					+"\n12. Cadastrar Cargo"
					+"\n13. Listar Cargos"
					+"\n14. Listar Usuários"
					+"\n0. Sair", 
		            "Menu do Funcionário", JOptionPane.QUESTION_MESSAGE);
						
			if (retorno== null) {
	    		JOptionPane.showMessageDialog(null, "Sessão Funcionário finalizada.");
	            break; 
	        }
			
			opcao =Integer.parseInt(retorno);
			
			switch(opcao) {
				case 0://sair
					JOptionPane.showMessageDialog(null,"Saindo da sua conta...");
					break;
					
				case 1://consultar acervo
					consultarAcervo();
					break;
					
				case 2://cadastrar livro
					cadastrarLivro();
					break;
					
				case 3://listar livros
					listarLivros();
					break;
					
				case 4://Registrar empréstimo de livro
					registrarEmprestimo();
					break;
					
				case 5://Registrar devolução de livro
					registrarDevolucao();
					break;
					
				case 6://cadastrar funcionarios
					cadastrarFuncionarios();
					break;
					
				case 7://listar funcionarios
					listarFuncionarios();
					break;
					
				case 8://cadastrar professor
					cadastrarProfessor();
					break;
					
				case 9://listar professores
					listarProfessores();
					
					break;
					
				case 10://cadastrar aluno
					cadastrarAluno();
					break;
					
					
				case 11://listar alunos
					listarAlunos();
					
					break;
					
				case 12://cadastrar cargo
					cadastrarCargo();
					break;
					
				case 13://listar cargos
					listarCargos();
					break;
					
				case 14://listar usuarios
					listarUsuarios();
					break;

				default:
					JOptionPane.showMessageDialog(null, "Opção inválida! tente novamente.");
			}
		}
	}	


	public void iniciarSessaoProfessor(Professor professor) {//apenas consultar acervo
		int opcao= -1;
		
		while(opcao!=0) {
			
			String retorno = JOptionPane.showInputDialog(
					null, "Bem-vindo, Prof. " + professor.getNome() + "!\nEscolha uma opção:"
					+"\n\n1. Consultar Acervo \n0. Sair", 
		            "Menu do Professor", JOptionPane.QUESTION_MESSAGE);
						
			if (retorno== null) {
	    		JOptionPane.showMessageDialog(null, "Sessão Professor finalizada.");
	            break; 
	        }
			
			opcao =Integer.parseInt(retorno);
			
			switch(opcao) {
				case 0:
					JOptionPane.showMessageDialog(null,"Finalizando sistema...");
					break;
				case 1:
					consultarAcervo();
					break;
				default:
					JOptionPane.showMessageDialog(null, "Opção inválida! tente novamente.");
			}
		}
		
	}
	
	public void consultarAcervo() {
		boolean continuarBusca = true;

	    while (continuarBusca) {
	    	
	    	String titulo = JOptionPane.showInputDialog(
					null, "Digite o título do livro ou parte dele:", 
		            "Consulta ao Acervo", JOptionPane.QUESTION_MESSAGE);
			
	    	if (titulo == null) {
	    		continuarBusca=false;
	    		JOptionPane.showMessageDialog(null, "Consulta ao acervo cancelada.");
	            break; 
	        }
	    	
	    	ArrayList<Livro> resultadoBusca = acervoController.consultarAcervo(titulo);

            if (resultadoBusca.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Nenhum livro encontrado com o título: \"" + titulo + "\".");
                
            } else {
            	String msg = "Livros encontrados no acervo:\n\n";
                 
                for(Livro livro : resultadoBusca) {
                	msg+= "Título: "+livro.getTitulo()+"\nAutores: "+livro.getAutores()
                		+"\nEditora: "+livro.getEditora()+"\nNúmero de páginas: "+livro.getNumeroPaginas()
                		+"\nISBN: "+livro.getIsbn()+"\nGênero: "+livro.getGenero()+"\nSinopse: "+livro.getSinopse()
                		+"\nIdioma: "+livro.getIdioma()+"\nStatus: "+(emprestimoController.verificarDisponibilidade(livro.getIsbn())? "Disponível" : "Indisponível");
                }

                JOptionPane.showMessageDialog(null, msg, "Resultado da Consulta ao Acervo", JOptionPane.INFORMATION_MESSAGE);
                continuarBusca = false; 
            }  
	    }
	}
	
	
	private void cadastrarLivro() {
		boolean exibirCadastroLivro =true;
		
		while(exibirCadastroLivro) {
			JPanel painelDadosLivro = new JPanel();
			JTextField campoTitulo = new JTextField(20);
			JTextField campoAutores = new JTextField(20);
			JTextField campoEditora = new JTextField(20);
			JTextField campoNumeroPaginas = new JTextField(10);
			JTextField campoIsbn = new JTextField(15);
			JTextField campoGenero = new JTextField(15);
			JTextField campoSinopse = new JTextField(30);
			JTextField campoIdioma = new JTextField(10);

			painelDadosLivro.add(new JLabel("Título:"));
			painelDadosLivro.add(campoTitulo);
			painelDadosLivro.add(new JLabel("Autores (separados por vírgula):"));
			painelDadosLivro.add(campoAutores);
			painelDadosLivro.add(new JLabel("Editora:"));
			painelDadosLivro.add(campoEditora);
			painelDadosLivro.add(new JLabel("Número de páginas:"));
			painelDadosLivro.add(campoNumeroPaginas);
			painelDadosLivro.add(new JLabel("ISBN:"));
			painelDadosLivro.add(campoIsbn);
			painelDadosLivro.add(new JLabel("Gênero:"));
			painelDadosLivro.add(campoGenero);
			painelDadosLivro.add(new JLabel("Sinopse:"));
			painelDadosLivro.add(campoSinopse);
			painelDadosLivro.add(new JLabel("Idioma:"));
			painelDadosLivro.add(campoIdioma);

			int confirmacao = JOptionPane.showConfirmDialog(null, painelDadosLivro, "Sistema - Biblioteca UEA - Cadastro Livros", JOptionPane.OK_CANCEL_OPTION);

			if (confirmacao == JOptionPane.OK_OPTION) {
				String titulo = campoTitulo.getText();
			    String autoresTexto = campoAutores.getText();

			    ArrayList<String> autores = new ArrayList<>();
			    for (String autor : autoresTexto.split(",")) {
			        autores.add(autor.trim());
			    }
			    
			    String editora = campoEditora.getText();
			    int numeroPaginas = Integer.parseInt(campoNumeroPaginas.getText());
			    String isbn = campoIsbn.getText();
			    String genero = campoGenero.getText();
			    String sinopse = campoSinopse.getText();
			    String idioma = campoIdioma.getText();
			    
			    Livro livro = new Livro(titulo,autores,editora, numeroPaginas, isbn, genero, sinopse, idioma);
			    
			    if(acervoController.cadastrarLivro(livro)) {
			    	JOptionPane.showMessageDialog(null, "Cadastro realizado com sucesso!");
			    	exibirCadastroLivro=false;
			    }else {
			    	JOptionPane.showMessageDialog(null, "Não foi possível cadastrar o livro.");
			    }
			}else {
				JOptionPane.showMessageDialog(null, "Fechando Cadastro de Livros.");
				exibirCadastroLivro=false;
			}
		}
			
	}
	
	public void listarLivros() {
		ArrayList<Livro> livros = acervoController.getLivros();
		String msg = "Livros encontrados no acervo:\n\n";
        
		if(livros.isEmpty()) msg="Nenhum livro está cadastrado no sistema!";
			
        for(Livro livro : livros) {
        	msg+= "Título: "+livro.getTitulo()+"\nAutores: "+livro.getAutores()
        		+"\nEditora: "+livro.getEditora()+"\nNúmero de páginas: "+livro.getNumeroPaginas()
        		+"\nISBN: "+livro.getIsbn()+"\nGênero: "+livro.getGenero()+"\nSinopse: "+livro.getSinopse()
        		+"\nIdioma: "+livro.getIdioma()+"\nStatus: "+(emprestimoController.verificarDisponibilidade(livro.getIsbn())? "Disponível" : "Indisponível")
        		+"\n";
        }
        
        JOptionPane.showMessageDialog(null, msg);
	}
	
	public void registrarEmprestimo() {
		boolean exibirRegistroEmprestimo=true;
		
		while(exibirRegistroEmprestimo) {
			JPanel painelEmprestimo = new JPanel();
			JTextField campoCPF = new JTextField(10);
			JTextField campoISBN = new JTextField(10);
			
			String cpf ="";
			String isbn="";

			painelEmprestimo.add(new JLabel("CPF:")); 
			painelEmprestimo.add(campoCPF);
			painelEmprestimo.add(new JLabel("ISBN:"));
			painelEmprestimo.add(campoISBN);

			int confirmacao = JOptionPane.showConfirmDialog(null, painelEmprestimo, "Sistema - Biblioteca UEA - Registro empréstimo", JOptionPane.OK_CANCEL_OPTION);

			if (confirmacao == JOptionPane.OK_OPTION) {
			    cpf = campoCPF.getText();
			    isbn = campoISBN.getText();
			    
			    if(emprestimoController.realizarEmprestimo(isbn, cpf)) {
			    	JOptionPane.showMessageDialog(null, "Empréstimo realizado com sucesso!");
			    	exibirRegistroEmprestimo=false;
			    }else {
			    	JOptionPane.showMessageDialog(null, "Usuário ou livro inexistentes ou Usuário não pode fazer empréstimos.");
			    }
			}else {
				JOptionPane.showMessageDialog(null, "Fechando registro de empréstimos.");
				exibirRegistroEmprestimo=false;
			}
		}
		
	}
	
	public void registrarDevolucao() {
		boolean exibirRegistroDevolucao=true;
		
		while(exibirRegistroDevolucao) {
			JPanel painelDevolucao = new JPanel();
			JTextField campoCPF = new JTextField(10); 
			JTextField campoISBN = new JTextField(10);
			
			String cpf ="";
			String isbn="";

			painelDevolucao.add(new JLabel("CPF:"));
			painelDevolucao.add(campoCPF);
			painelDevolucao.add(new JLabel("ISBN:"));
			painelDevolucao.add(campoISBN);

			int confirmacao = JOptionPane.showConfirmDialog(null, painelDevolucao, "Sistema - Biblioteca UEA - Registro devolução", JOptionPane.OK_CANCEL_OPTION);

			if (confirmacao == JOptionPane.OK_OPTION) {
			    cpf = campoCPF.getText();
			    isbn = campoISBN.getText();
			    
			    if(emprestimoController.devolucao(isbn, cpf)) {
			    	JOptionPane.showMessageDialog(null, "Devolução realizada com sucesso!");
			    	exibirRegistroDevolucao=false;
			    }else {
			    	JOptionPane.showMessageDialog(null, "Usuário, livro ou empréstimo inexistentes ");
			    }
			}else {
				JOptionPane.showMessageDialog(null, "Fechando registro de devoluções.");
				exibirRegistroDevolucao=false;
			}
		}
	}
	
	public void cadastrarFuncionarios() {
		boolean exibirCadastroFuncionario =true;
		
		while(exibirCadastroFuncionario) {
			JPanel painelDadosFuncionario = new JPanel();
			JTextField campoCpf = new JTextField(15);
			JTextField campoNome = new JTextField(20);
			JTextField campoEmail = new JTextField(20);
			
			ArrayList<Cargo> cargos = cargoController.getCargos();
			
			if(cargos.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Não existem registros de cargos, então não é possível adicionar um novo funcionário.");
				exibirCadastroFuncionario=false;
				break;
			}
			
			JComboBox<String> comboCargo = new JComboBox<>(); //p poder add as opções pre-definidas
			for (Cargo c : cargos) {
			    comboCargo.addItem(c.getCodigo()+ " - " +c.getNome());
			}
			
			JTextField campoSalario = new JTextField(10);
			JTextField campoSenha = new JTextField(10);

			painelDadosFuncionario.add(new JLabel("CPF:"));
			painelDadosFuncionario.add(campoCpf);
			painelDadosFuncionario.add(new JLabel("Nome:"));
			painelDadosFuncionario.add(campoNome);
			painelDadosFuncionario.add(new JLabel("Email:"));
			painelDadosFuncionario.add(campoEmail);
			painelDadosFuncionario.add(new JLabel("Cargo:"));
			painelDadosFuncionario.add(comboCargo);
			painelDadosFuncionario.add(new JLabel("Salário:"));
			painelDadosFuncionario.add(campoSalario);
			painelDadosFuncionario.add(new JLabel("Senha:"));
			painelDadosFuncionario.add(campoSenha);
			
			int confirmacao = JOptionPane.showConfirmDialog(null, painelDadosFuncionario, "Sistema - Biblioteca UEA - Cadastro Funcionários", JOptionPane.OK_CANCEL_OPTION);

			if (confirmacao == JOptionPane.OK_OPTION) {
				 String cpf = campoCpf.getText();
				    String nome = campoNome.getText();
				    String email = campoEmail.getText();
				    String cargoNome = (String) comboCargo.getSelectedItem();
				    double salario = Double.parseDouble(campoSalario.getText());
				    int senha = Integer.parseInt(campoSenha.getText());

				    String[] partesCargo = cargoNome.split(" - ");
				    int codigoCargo = Integer.parseInt(partesCargo[0]);
				    
				    Cargo cargoSelecionado = null;
				    for (Cargo cargo : cargos) {
				        if (cargo.getCodigo()==codigoCargo) {
				            cargoSelecionado = cargo;
				            break;
				        }
				    }

				    Funcionario funcionario = new Funcionario(cpf, nome, email, cargoSelecionado, salario, senha);
			    
			    if(usuarioController.cadastrarUsuario(funcionario)) {
			    	JOptionPane.showMessageDialog(null, "Cadastro realizado com sucesso!");
			    	exibirCadastroFuncionario=false;
			    }else {
			    	JOptionPane.showMessageDialog(null, "Não foi possível cadastrar o funcionário.");
			    }
			}else {
				JOptionPane.showMessageDialog(null, "Fechando Cadastro de Funcionários.");
				exibirCadastroFuncionario=false;
			}
		}
	}
	
	public void listarFuncionarios() {
		ArrayList<Funcionario> funcionarios = usuarioController.getFuncionarios();
		String msg = "Funcionários cadastrados no sistema:\n\n";
        
		if(funcionarios.isEmpty()) msg="Nenhum funcionário está cadastrado no sistema!";
		
        for(Funcionario funcionario : funcionarios) {
        	msg+= "CPF: "+funcionario.getCpf()+"\nNome: "+funcionario.getNome()
        		+"\nEmail: "+funcionario.getEmail()+"\nCargo: "+funcionario.getCargo().getNome()
        		+"\nSalário: R$"+funcionario.getSalario()+"\n";
        }
        
        JOptionPane.showMessageDialog(null, msg);
	}
	
	public void cadastrarProfessor() {
		boolean exibirCadastroProfessor =true;
		
		while(exibirCadastroProfessor) {
			JPanel painelDadosProfessor = new JPanel();
			JTextField campoCpf = new JTextField(15);
			JTextField campoNome = new JTextField(20);
			JTextField campoEmail = new JTextField(20);
			JTextField campoTitulacao = new JTextField(15);
			

			painelDadosProfessor.add(new JLabel("CPF:"));
			painelDadosProfessor.add(campoCpf);
			painelDadosProfessor.add(new JLabel("Nome:"));
			painelDadosProfessor.add(campoNome);
			painelDadosProfessor.add(new JLabel("Email:"));
			painelDadosProfessor.add(campoEmail);
			painelDadosProfessor.add(new JLabel("Titulação:"));
			painelDadosProfessor.add(campoTitulacao);
			
			
			int confirmacao = JOptionPane.showConfirmDialog(null, painelDadosProfessor, "Sistema - Biblioteca UEA - Cadastro Professores", JOptionPane.OK_CANCEL_OPTION);

			if (confirmacao == JOptionPane.OK_OPTION) {
				 	String cpf = campoCpf.getText();
				    String nome = campoNome.getText();
				    String email = campoEmail.getText();
				    String titulacao = campoTitulacao.getText();

				    Professor professor = new Professor(cpf, nome, email, titulacao);
			    
			    if(usuarioController.cadastrarUsuario(professor)) {
			    	JOptionPane.showMessageDialog(null, "Cadastro realizado com sucesso! Informe ao professor que a sua senha é: "+professor.getSenha());
			    	exibirCadastroProfessor=false;
			    }else {
			    	JOptionPane.showMessageDialog(null, "Não foi possível cadastrar o professor.");
			    }
			}else {
				JOptionPane.showMessageDialog(null, "Fechando Cadastro de Professores.");
				exibirCadastroProfessor=false;
			}
		}
	}
	
	public void listarProfessores() {
		ArrayList<Professor> professores = usuarioController.getProfessores();
		String msg = "Professores cadastrados no sistema:\n\n";
        
		if(professores.isEmpty()) msg="Nenhum professor está cadastrado no sistema!";
		
        for(Professor professor : professores) {
        	msg+= "CPF: "+professor.getCpf()+"\nNome: "+professor.getNome()
        		+"\nEmail: "+professor.getEmail()+"\nTitulação: "+professor.getTitulacao()+"\n";
        }
        
        JOptionPane.showMessageDialog(null, msg);
	}
	
	public void cadastrarAluno() {
		boolean exibirCadastroAluno =true;
		
		while(exibirCadastroAluno) {
			JPanel painelDadosAluno = new JPanel();
			JTextField campoCpf = new JTextField(15);
			JTextField campoNome = new JTextField(20);
			JTextField campoEmail = new JTextField(20);
			JTextField campoInstituicao= new JTextField(20);
			JTextField campoCurso= new JTextField(15);
			JTextField campoNotaUltimoENEM= new JTextField(4);
			

			painelDadosAluno.add(new JLabel("CPF:"));
			painelDadosAluno.add(campoCpf);
			painelDadosAluno.add(new JLabel("Nome:"));
			painelDadosAluno.add(campoNome);
			painelDadosAluno.add(new JLabel("Email:"));
			painelDadosAluno.add(campoEmail);
			painelDadosAluno.add(new JLabel("Instituição:"));
			painelDadosAluno.add(campoInstituicao);
			painelDadosAluno.add(new JLabel("Curso:"));
			painelDadosAluno.add(campoCurso);
			painelDadosAluno.add(new JLabel("Nota no último ENEM:"));
			painelDadosAluno.add(campoNotaUltimoENEM);
			
			
			int confirmacao = JOptionPane.showConfirmDialog(null, painelDadosAluno, "Sistema - Biblioteca UEA - Cadastro Alunos", JOptionPane.OK_CANCEL_OPTION);

			if (confirmacao == JOptionPane.OK_OPTION) {
				 	String cpf = campoCpf.getText();
				    String nome = campoNome.getText();
				    String email = campoEmail.getText();
				    String instituicaoDeEnsino = campoInstituicao.getText();
				    String curso = campoCurso.getText();
				    float notaUltimoENEM = Float.parseFloat(campoNotaUltimoENEM.getText());

				    Aluno aluno = new Aluno(cpf, nome, email, instituicaoDeEnsino, curso, notaUltimoENEM);
			    
			    if(usuarioController.cadastrarUsuario(aluno)) {
			    	JOptionPane.showMessageDialog(null, "Cadastro realizado com sucesso!");
			    	exibirCadastroAluno=false;
			    }else {
			    	JOptionPane.showMessageDialog(null, "Não foi possível cadastrar o aluno.");
			    }
			}else {
				JOptionPane.showMessageDialog(null, "Fechando Cadastro de Alunos.");
				exibirCadastroAluno=false;
			}
		}
	}
	
	public void listarAlunos() {
		ArrayList<Aluno> alunos = usuarioController.getAlunos();
		String msg = "Alunos cadastrados no sistema:\n\n";
        
		if(alunos.isEmpty()) msg="Nenhum aluno está cadastrado no sistema!";
		
        for(Aluno aluno : alunos) {
        	msg+= "CPF: "+aluno.getCpf()+"\nNome: "+aluno.getNome()
        		+"\nEmail: "+aluno.getEmail()+"\nInstituição de ensino: "+aluno.getInstituicaoDeEnsino()
        		+"\nCurso: "+aluno.getCurso()+"\nNota do último ENEM: "+aluno.getNotaUltimoENEM()+"\n";
        }
        
        JOptionPane.showMessageDialog(null, msg);
	}
	
	public void cadastrarCargo() {
		boolean exibirCadastroCargo =true;
		
		while(exibirCadastroCargo) {
			JPanel painelDadosProfessor = new JPanel();
			JTextField campoCodigo = new JTextField(15);
			JTextField campoNome = new JTextField(20);
			JTextField campoDescricao = new JTextField(20);
			JTextField campoCargaHoraria = new JTextField(15);			

			painelDadosProfessor.add(new JLabel("Código:"));
			painelDadosProfessor.add(campoCodigo);
			painelDadosProfessor.add(new JLabel("Nome:"));
			painelDadosProfessor.add(campoNome);
			painelDadosProfessor.add(new JLabel("Descrição:"));
			painelDadosProfessor.add(campoDescricao);
			painelDadosProfessor.add(new JLabel("Carga horária:"));
			painelDadosProfessor.add(campoCargaHoraria);
			
			
			int confirmacao = JOptionPane.showConfirmDialog(null, painelDadosProfessor, "Sistema - Biblioteca UEA - Cadastro Cargos", JOptionPane.OK_CANCEL_OPTION);

			if (confirmacao == JOptionPane.OK_OPTION) {
				 	int codigo = Integer.parseInt(campoCodigo.getText());
				    String nome = campoNome.getText();
				    String descricao = campoDescricao.getText();
				    int cargaHoraria = Integer.parseInt(campoCargaHoraria.getText());

				    Cargo cargo = new Cargo(codigo, nome, descricao, cargaHoraria);
			    
			    if(cargoController.cadastrarCargo(cargo)) {
			    	JOptionPane.showMessageDialog(null, "Cadastro realizado com sucesso!");
			    	exibirCadastroCargo=false;
			    }else {
			    	JOptionPane.showMessageDialog(null, "Não foi possível cadastrar o cargo.");
			    }
			}else {
				JOptionPane.showMessageDialog(null, "Fechando Cadastro de Cargos.");
				exibirCadastroCargo=false;
			}
		}
	}
	
	public void listarCargos() {
		ArrayList<Cargo> cargos = cargoController.getCargos();
		String msg = "Cargos cadastrados no sistema:\n\n";
        
		if(cargos.isEmpty()) msg="Nenhum cargo está cadastrado no sistema!";
		
        for(Cargo cargo : cargos) {
        	msg+= "Código: "+cargo.getCodigo()+"\nNome: "+cargo.getNome()
        		+"\nDescrição: "+cargo.getDescricao()+"\nCarga Horária: "+cargo.getCargaHoraria()+" horas\n";
        }
        
        JOptionPane.showMessageDialog(null, msg);
	}
	
	public void listarUsuarios() {

		ArrayList<Funcionario> funcionarios = usuarioController.getFuncionarios();
		String msg = "Funcionários:\n\n";
        
		if(funcionarios.isEmpty()) msg+="Nenhum funcionário está cadastrado no sistema!";
		
        for(Funcionario funcionario : funcionarios) {
        	msg+= "CPF: "+funcionario.getCpf()+"\nNome: "+funcionario.getNome()
        		+"\nEmail: "+funcionario.getEmail()+"\nCargo: "+funcionario.getCargo().getNome()
        		+"\nSalário: R$"+funcionario.getSalario()+"\n";
        }
		
        ArrayList<Professor> professores = usuarioController.getProfessores();
		msg += "\nProfessores:\n\n";
        
		if(professores.isEmpty()) msg+="Nenhum professor está cadastrado no sistema!";
		
        for(Professor professor : professores) {
        	msg+= "CPF: "+professor.getCpf()+"\nNome: "+professor.getNome()
        		+"\nEmail: "+professor.getEmail()+"\nTitulação: "+professor.getTitulacao()+"\n";
        }
        
        ArrayList<Aluno> alunos = usuarioController.getAlunos();
		msg += "\nAlunos:\n\n";
        
		if(alunos.isEmpty()) msg+="Nenhum aluno está cadastrado no sistema!";
		
        for(Aluno aluno : alunos) {
        	msg+= "CPF: "+aluno.getCpf()+"\nNome: "+aluno.getNome()
        		+"\nEmail: "+aluno.getEmail()+"\nInstituição de ensino: "+aluno.getInstituicaoDeEnsino()
        		+"\nCurso: "+aluno.getCurso()+"\nNota do último ENEM: "+aluno.getNotaUltimoENEM()+"\n";
        }
        
        JOptionPane.showMessageDialog(null, msg);
	}
	
	public void iniciarLogin() {
		boolean exibirMenu=true;
		
		while(exibirMenu) {
			JPanel painelLogin = new JPanel();//agrupa os elementos p aparecer na janela
			JTextField campoCPF = new JTextField(10); //caixa de entrada
			JTextField campoSenha = new JTextField(10);
			
			String cpf ="";
			int senha;

			painelLogin.add(new JLabel("CPF:")); //rotulo de texto
			painelLogin.add(campoCPF);
			painelLogin.add(new JLabel("Senha:"));
			painelLogin.add(campoSenha);

			int confirmacao = JOptionPane.showConfirmDialog(null, painelLogin, "Sistema - Biblioteca UEA", JOptionPane.OK_CANCEL_OPTION);

			if (confirmacao == JOptionPane.OK_OPTION) {
			    cpf = campoCPF.getText();
			    senha = Integer.parseInt(campoSenha.getText());
			    
			    if(login(cpf, senha)) {
			    	if(usuario instanceof Funcionario) {
			    		iniciarSessaoFuncionario((Funcionario)usuario);
			    	}else {
			    		iniciarSessaoProfessor((Professor)usuario);
			    	}
			    }else {
			    	JOptionPane.showMessageDialog(null, "CPF e senha incorretos ou não cadastrados.");
			    }
			}else {
				JOptionPane.showMessageDialog(null, "Fechando sistema.");
				exibirMenu=false;
			}
		}
	}
	
	public static void main(String[]args) {
		TelaBiblioteca tela = new TelaBiblioteca();
		tela.iniciarLogin();
	}

}
