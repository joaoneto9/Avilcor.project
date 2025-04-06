package program;
import java.sql.Connection;
import java.util.Scanner;

import fachada.*;

public class Menu {
	
	private FachadaAvilcor sistema;
	private Scanner sc;
	
	public Menu(Connection conn, Scanner sc) {
		this.sistema = new FachadaAvilcor(conn);
		this.sc = sc;
	}
	
	public String menuInterface() {
		String menu = """
			    ====================================
			    🏠 MENU PRINCIPAL - GESTÃO DE COSTURA 🏠
			    ====================================
			    
			    📌 [1] Gestão de Usuários
			       (A) Cadastrar Usuário
			       (B) Buscar Usuário por E-mail
			       (C) Buscar Usuário por ID
			       (D) Listar Todos os Usuarios

			    📌 [2] Gestão de Costureiras
			       (E) Adicionar Costureira
			       (F) Buscar Costureira por ID
			       (G) Listar Histórico de Buscas
			       (H) Listar Todas as Costureiras

			    📌 [3] Gestão de Ordens de Serviço
			       (I) Cadastrar Nova Ordem de Serviço
			       (J) Listar Ordens de um Usuário
			       (K) Listar Ordens de Servico
			       (L) Listar Serviços de uma Ordem
			       (M) Deletar Ordens Finalizadas
			       (N) Atualizar Status para 'Em Andamento'
			       (O) Atualizar Status para 'Finalizado'

			    📌 [4] Gestão de Serviços
			       (P) Adicionar Serviço a uma Ordem
			       (Q) Listar Serviços de uma Ordem

			    ❌ [X] Sair do Sistema

			    ====================================
			    Escolha uma opção: 
			    """;
		return menu;
	}
	
	public String functionInput(String option) {
		switch (option.toUpperCase().charAt(0)) {
	    case 'A':
	        return "Usuario cadastrado, com id: #" + sistema.cadastrarUsuario(setString("nome"), setString("email"));
	    case 'B':
	        return "detalhes do user: " + sistema.toStringUsuarioPorEmail(setString("email"));
	    case 'C':
	        return "detalhes do user: " + sistema.toStringUsuarioPorId(setInt("id"));
	    case 'D':
	        return sistema.listarUsuarios();
	    case 'E':
	        return "costureira adicionada, com id: #" + sistema.adicionarCostureira(setString("nome"), setString("cpf"), setInt("quantidade maxima de servicos"));
	    case 'F':
	        return "detalhes da costureira: " + sistema.toStringCostureiraPorId(setInt("id"));
	    case 'G':
	        return sistema.listarHistoricoDeBuscas();
	    case 'H':
	        return sistema.listraCostureiras();
	    case 'I':
	        return "ordem de servico cadastrada, com id#" + sistema.cadastrarOrdemDeServico(setInt("id user"), setInt("id costureira"));
	    case 'J':
	        return "ordens de servico do usuario: " + sistema.listarOrdensDeUmUsuario(setInt("id"));
	    case 'K':
	        return sistema.listarOrdensDeServicos();
	    case 'L':
	        return "servicos de uma ordem de servicos: " + sistema.listarServicosDeUmaOrdem(setInt("id"));
	    case 'M':
	        sistema.deletarOrdensFinalizadas();
	        return "removendo as ordens de servicos finalizadas para serem entregues";
	    case 'N':
	        int id = setInt("id");
	        sistema.atualizarStatusParaEmAndamento(id);
	        return "colocando ordem de servico #" + id + "em andamento";
	    case 'O':
	        int id2 = setInt("id");
	        sistema.atualizarStatusParaFinalizado(id2);;
	        return "finalizando ordem de servico #" + id2;
	    case 'P':
	        return "adicinado servico a ordem de servico: " + sistema.adicionarServicoAOrdem(setInt("id"), setDouble("preco"), setString("descricao"));
	    case 'Q':
	    	int id3 = setInt("id");
	        return "servicos da ordem de servico#" + id3 + ":\n" + sistema.listarServicosDeUmaOrdem(id3);
	    case 'X':
	        return "FIM DO PROGRAMA";
	    default:
	        return "Opção inválida.";
	}
		
	} 
	
	private double setDouble(String msg) {
		System.out.println(msg + ": ");
		double dado = sc.nextDouble();
		sc.nextLine();
		return dado;
	}
	
	private int setInt(String msg) {
		System.out.println(msg + ": ");
		int dado = sc.nextInt();
		sc.nextLine();
		return dado;
	}
	
	private String setString(String msg) {
		System.out.print(msg + ": ");
		return sc.nextLine();
	}
	
}
