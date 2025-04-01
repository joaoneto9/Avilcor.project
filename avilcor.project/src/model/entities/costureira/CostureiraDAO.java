package model.entities.costureira;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import db.DB;
import db.DbException;

public class CostureiraDAO {
	
	private static final String sql = "INSERT INTO costureira (nome, cpf, qtd_max_servicos) VALUES (?, ?, ?)";
	private static final String sql2 = "SELECT * FROM costureira WHERE id = ?";
	
	public static int salvarCostureira(Connection conn, Costureira costureira) {
		try(PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			ps.setString(1, costureira.getNome());
			ps.setString(2, costureira.getCpf());
			ps.setInt(3, costureira.getQuantidadeMaxima());
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
	
	public static Costureira buscarId(Connection conn, int id) {
		try(PreparedStatement ps = conn.prepareStatement(sql2)) {
			ps.setInt(1, id);
	        ResultSet rs = ps.executeQuery(); // executa a busca e retorna a quantidade de dados
	        if (rs.next()) { // se tem um dado a frente
	        	 return new Costureira(rs.getInt(1), 
	        			 rs.getString(2), 
	        			 rs.getString(3), // determinei pelas colunas. 
	        			 rs.getInt(4));
	        }
	        return null;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
	}
	
	
	
	
	

}
