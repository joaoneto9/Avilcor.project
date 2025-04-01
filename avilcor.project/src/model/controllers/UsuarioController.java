package model.controllers;

import java.sql.Connection;

import model.entities.usuario.Usuario;
import model.entities.usuario.UsuarioDAO;

public class UsuarioController {
	
	private Connection conn;
	
	public UsuarioController(Connection conn) {
		this.conn = conn;
	}
	
	public int cadastrarUsuario(String nome, String email) {
		return UsuarioDAO.cadastrarUsuario(conn, nome, email);
	}
	
	public Usuario getUsuarioEmail(String email) {
		return UsuarioDAO.getUsuarioEmail(conn, email);
	}
	
	public Usuario getUsuarioId(int id) {
		return UsuarioDAO.getUsuarioId(conn, id);
	}
}
