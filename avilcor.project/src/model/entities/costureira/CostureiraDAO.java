package model.entities.costureira;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.DB;
import db.DbException;

public class CostureiraDAO {
	
	private static final String INSERIR_COSTUREIRA = "INSERT INTO costureira (nome, cpf, qtd_max_servicos, qtd_servico) VALUES (?, ?, ?, ?)";
	private static final String SELECIONAR_ID = "SELECT * FROM costureira WHERE id = ?";
	private static final String ADD_SERVICO = "UPDATE costureira SET qtd_servico = qtd_servico + 1 WHERE id = ?";
	private static final String SELECIONAR_COSTUREIRAS = "SELECT * FROM costureira"; // pega todas as costureiras.
	
	public static int salvarCostureira(Connection conn, Costureira costureira) {
		try(PreparedStatement ps = conn.prepareStatement(INSERIR_COSTUREIRA, Statement.RETURN_GENERATED_KEYS)) {
			ps.setString(1, costureira.getNome());
			ps.setString(2, costureira.getCpf());
			ps.setInt(3, costureira.getQuantidadeMaxima());
			ps.setInt(4, costureira.getQuantServico());
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
	
	public static boolean addServico(Connection conn, int id) {
		try (PreparedStatement ps = conn.prepareStatement(ADD_SERVICO)) {
			Costureira costureira = buscarId(conn, id);
			if (costureira == null || costureira.getQuantServico() == costureira.getQuantidadeMaxima()) 
				return false;
			ps.setInt(1, id);
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
	}
	
	public static Costureira buscarId(Connection conn, int id) {
		try(PreparedStatement ps = conn.prepareStatement(SELECIONAR_ID)) {
			ps.setInt(1, id);
	        ResultSet rs = ps.executeQuery(); // executa a busca e retorna a quantidade de dados
	        if (rs.next()) { // se tem um dado a frente
	        	 return new Costureira(rs.getInt(1), 
	        			 rs.getString(2), 
	        			 rs.getString(3), // determinei pelas colunas. 
	        			 rs.getInt(4),
	        			 rs.getInt(5));
	        }
	        return null;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
	}
	
	public static List<Costureira> todasCostureiras(Connection conn) {
		try (PreparedStatement ps = conn.prepareStatement(SELECIONAR_COSTUREIRAS)) {
			ResultSet rs = ps.executeQuery();
			List<Costureira> costureiras = new ArrayList<>();
			while (rs.next()) {
				costureiras.add(new Costureira(rs.getInt(1), 
	        			 rs.getString(2), 
	        			 rs.getString(3), // determinei pelas colunas. 
	        		 	 rs.getInt(4),
	        			 rs.getInt(5)));
			}
			return costureiras;
		} catch (Exception e) {
			throw new DbException(e.getMessage());
		}
	} 
	
	
	
	
	

}
