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

			    📌 [2] Gestão de Costureiras
			       (D) Adicionar Costureira
			       (E) Buscar Costureira por ID
			       (F) Listar Histórico de Buscas
			       (G) Listar Todas as Costureiras

			    📌 [3] Gestão de Ordens de Serviço
			       (H) Cadastrar Nova Ordem de Serviço
			       (I) Listar Ordens de um Usuário
			       (J) Listar Ordens de Servico
			       (K) Listar Serviços de uma Ordem
			       (L) Deletar Ordens Finalizadas
			       (M) Atualizar Status para 'Em Andamento'
			       (N) Atualizar Status para 'Finalizado'

			    📌 [4] Gestão de Serviços
			       (O) Adicionar Serviço a uma Ordem
			       (P) Listar Serviços de uma Ordem

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
                return "costureira adicionada, com id: #" + sistema.adicionarCostureira(setString("nome"), setString("cpf"), setInt("quantidade maxima de servicos"));
           	case 'E':
                return "detalhes da costureira: " + sistema.toStringCostureiraPorId(setInt("id"));
           	case 'F':
                return sistema.listarHistoricoDeBuscas();
           	case 'G':
           		return sistema.listraCostureiras();
           	case 'H':
                return "ordem de servico cadastrada, com id#" + sistema.cadastrarOrdemDeServico(setInt("id user"), setInt("id costureira"));
           	case 'I':
                return "ordens de servico do usuario: " + sistema.listarOrdensDeUmUsuario(setInt("id"));
           	case 'J':
           		return sistema.listarOrdensDeServicos();
           	case 'K':
                return "servicos de uma ordem de servicos: " + sistema.listarServicosDeUmaOrdem(setInt("id"));
           	case 'L':
                sistema.deletarOrdensFinalizadas();
                return "removendo as ordens de servicos finalizadas para serem entregues";
           	case 'M':
            	int id = setInt("id");
                sistema.atualizarStatusParaEmAndamento(id);
                return "colocando ordem de servico #" + id + "em andamento";
           	case 'N':
            	int id2 = setInt("id");
                sistema.atualizarStatusParaEmAndamento(id2);
                return "finalizando ordem de servico #" + id2;
           	case 'O':
           		return "adicinado servico a ordem de servico: " + sistema.adicionarServicoAOrdem(setInt("id"), setDouble("preco"), setString("descricao"));
           	case 'P':
           		return "servicos da ordem: " + sistema.listarServicosDeUmaOrdem(1);
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
