package model.entities.usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.DB;
import db.DbException;

public class UsuarioDAO {

	private static final String INSERIR_USUARIO = "INSERT INTO usuario (nome, email) VALUES (?, ?)";
	private static final String BUSCAR_POR_ID = "SELECT * FROM usuario WHERE id = ?";
	private static final String BUSCAR_POR_EMAIL = "SELECT * FROM usuario WHERE email = ?";
	private static final String SELECIONAR_USUARIOS = "SELECT * FROM usuario";
	
	public static int cadastrarUsuario(Connection conn, String nome, String email) {
		try(PreparedStatement ps = conn.prepareStatement(INSERIR_USUARIO, Statement.RETURN_GENERATED_KEYS)) {
			ps.setString(1, nome);
			ps.setString(2, email);
			int linhas = ps.executeUpdate();
	        if (linhas > 0) { 
	            try (ResultSet rs = ps.getGeneratedKeys()) { 
	                if (rs.next()) {
	                    return rs.getInt(1); // retorna o id gerado
	                }
	            }
	        }
	        return -1;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}	
	}
	
	public static Usuario getUsuarioEmail(Connection conn, String email) {
		try(PreparedStatement ps = conn.prepareStatement(BUSCAR_POR_EMAIL)) {
			ps.setString(1, email); // determino a busca po email
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return new Usuario(rs.getInt("id"), 
						rs.getString("nome"),
						rs.getString("email"));
			}
			return null; // se nao existir 
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
	}
	
	
	public static Usuario getUsuarioId(Connection conn, int id) {
		try(PreparedStatement ps = conn.prepareStatement(BUSCAR_POR_ID)) {
			ps.setInt(1, id); // determino a busca po email
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return new Usuario(rs.getInt("id"), 
						rs.getString("nome"),
						rs.getString("email"));
			}
			return null; // se nao existir 
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
	}
	
	public static List<Usuario> listarUsuarios(Connection conn) {
		try (PreparedStatement ps = conn.prepareStatement(SELECIONAR_USUARIOS)) {
			ResultSet rs = ps.executeQuery();
			List<Usuario> usuarios = new ArrayList<>();
			while (rs.next()) {
				usuarios.add(new Usuario(rs.getInt("id"), 
						rs.getString("nome"),
						rs.getString("email")));
			}
			return usuarios;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
	}
	
	
	
	
	
	
	
	
	
	
	
}
