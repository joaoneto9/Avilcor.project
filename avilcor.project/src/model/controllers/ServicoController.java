package model.controllers;

import java.sql.Connection;
import java.util.List;

import model.entities.servico.Servico;
import model.entities.servico.ServicoDAO;

public class ServicoController {
	
	private Connection conn;
	
	public ServicoController(Connection conn) {
		this.conn = conn;
	}
	
	public int salvarServicoDB(int idOs, double preco, String desc) {
		return ServicoDAO.salvarServico(conn, new Servico(idOs, preco, desc));
	}
	
	public List<Servico> listarServicosOrdemid(int idOs) {
		return ServicoDAO.pegarServicosOsId(conn, idOs);
	}

}
