package model.controllers;

import java.sql.Connection;
import java.util.Collections;
import java.util.List;

import model.entities.ordem.servico.OrdemServico;
import model.entities.ordem.servico.OrdemServicoDAO;
import model.entities.servico.Servico;

public class OrdemServicosController {
	
	private Connection conn;
	
	public OrdemServicosController(Connection conn) { 
		this.conn = conn;
	}
	
	public int cadastrarOrdemServicoDDB(int costureiraId, int usuarioId) {
		return OrdemServicoDAO.cadastrarOrdemServico(conn, new OrdemServico(costureiraId, usuarioId));
	}

	public String getOrdensServicoUserId(int userId) {
		return OrdemServicoDAO.getOrdemServicoUserID(conn, userId)
				.stream()
				.map(x -> x.toString())
				.reduce((st1, st2) -> st1 + "\n\n" + st2)
				.orElse("");
	}
	
	public String getServicos(ServicoController sc, int OsId) {
		return sc.listarServicosOrdemid(OsId).stream()
				.map(x -> x.toString())
				.reduce((st1, st2) -> st1 + "\n\n" + st2)
				.orElse("");
	}
	
	public void deletOrdemServicoFinalizada() {
		OrdemServicoDAO.deletarOsFinalizada(conn);
	}
	
	public void setStatusAndamento(int OsId) {
		OrdemServicoDAO.setStatusAndamento(conn, OsId);
	}
	
	public void setStatusFinalizado(int OsId) {
		OrdemServicoDAO.setStatusFinalizado(conn, OsId);
	}
	
	public String listarOrdensServico() {
		List<OrdemServico> ordens = OrdemServicoDAO.listarOrdemServicos(conn);
		Collections.sort(ordens);
		return "TODOS AS ORDEM DE SERVICOS (do mais recente ao mais antigo): " + ordens.stream()
													.map(x -> x.toString())
													.reduce("", (x1, x2) -> x1 + "\n\n" + x2);
	}
}
