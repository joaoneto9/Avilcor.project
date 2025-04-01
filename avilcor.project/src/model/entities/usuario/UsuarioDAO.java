package model.entities.usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import db.DB;
import db.DbException;

public class UsuarioDAO {
	

	private static String sql = "INSERT INTO usuario (nome, email) VALUES (?, ?)";
	private static String sql2 = "SELECT * FROM usuario WHERE id = ?";
	private static String sql3 = "SELECT * FROM usuario WHERE email = ?";
	

	public static int cadastrarUsuario(Connection conn, String nome, String email) {
		try(PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
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
		try(PreparedStatement ps = conn.prepareStatement(sql3)) {
			ps.setString(1, email); // determino a busca po email
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return new Usuario(rs.getInt(1), 
						rs.getString(2),
						rs.getString(3));
			}
			return null; // se nao existir 
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
	}
	
	
	public static Usuario getUsuarioId(Connection conn, int id) {
		try(PreparedStatement ps = conn.prepareStatement(sql2)) {
			ps.setInt(1, id); // determino a busca po email
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return new Usuario(rs.getInt(1), 
						rs.getString(2),
						rs.getString(3));
			}
			return null; // se nao existir 
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
	}
	
}
