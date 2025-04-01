package model.controllers;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import db.DbException;
import model.entities.costureira.Costureira;
import model.entities.costureira.CostureiraDAO;

public class CostureiraController {
	
	private Map<Integer, Costureira> historico;
	private Connection conn;
	
	public CostureiraController(Connection conn) {
		this.historico = new HashMap<>();
		this.conn = conn;
	}
	
	public int adicionarCostureiraDBB(String nome, String cpf, int quantMaxima) {
		return CostureiraDAO.salvarCostureira(conn, new Costureira(nome, cpf, quantMaxima));
	}
	
	public Costureira pegarCostureiraId(int id) {
		Costureira costureira = CostureiraDAO.buscarId(conn, id);
		if (costureira == null) 
			throw new IllegalArgumentException("id errado");
		historico.put(costureira.getId(), costureira);
		return costureira;
	} 
	
	public String listarHistorico() {
		String msg = "historico de busca: \n";
		for (Integer id : historico.keySet()) {
			msg += historico.get(id).toString() + "\n";
		}
		return msg;
	}
	
	

}
