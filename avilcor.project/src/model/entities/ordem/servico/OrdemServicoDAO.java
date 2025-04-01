package model.entities.ordem.servico;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.DB;
import db.DbException;

public class OrdemServicoDAO {
	
	private final static String sql = "INSERT INTO ordem_servico (costureira_id, usuario_Id, valor_total) VALUES (?, ?, ?)";
	private final static String sql2 = "SELECT * FROM ordem_servico WHERE usuario_id = ?";
	private final static String sql3 = "UPDATE ordem_servico SET valor_total = valor_total + ? WHERE id = ?";
	private final static String sql4 = "DELETE FROM ordem_servico WHERE status = Finalizada";
	private final static String sql5 = "UDATE ordem_servico SET status = ? WHERE id = ?";
	
	public static int cadastrarOrdemServico(Connection conn, OrdemServico os) {
		try (PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			ps.setInt(1, os.getCostureiraId());
			ps.setInt(2, os.getUsuarioId());
			ps.setDouble(3, os.getValorTotal());
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
	
	//pego as ordem de servico de cada cliente
	public static List<OrdemServico> getOrdemServicoUserID(Connection conn, int userId) {
		try (PreparedStatement ps = conn.prepareStatement(sql2)) {
			List<OrdemServico> lista = new ArrayList<>();
			ps.setInt(1, userId); // coloca no comando Sql o ide do user
			ResultSet rs = ps.executeQuery(); //executa a busca
			while (rs.next()) {
				lista.add(new OrdemServico(rs.getInt(1), 
						rs.getInt(2),  // adiciona mais um servico para cada Os que.
						rs.getInt(3),
						rs.getDouble(6))); // valor total
			}
			return lista; 
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
	}
	
	public static void somarValor(Connection conn, double valor, int Osid) {
		try(PreparedStatement ps = conn.prepareStatement(sql3)) {
			ps.setDouble(1, valor);
			ps.setInt(2, Osid);
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		
	}
	
	public static void deletarOsFinalizada(Connection conn) {
		try(PreparedStatement ps = conn.prepareStatement(sql4)) {
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
	}
	
	public static void setStatusAndamento(Connection conn, int idOs) {
		setStatus(conn, StatusOS.Em_Andamento.name() ,idOs);
	}
	
	public static void setStatusFinalizado(Connection conn, int idOs) {
		setStatus(conn, StatusOS.Em_Andamento.name() ,idOs);
	}
	
	private static void setStatus(Connection conn, String status, int idOs) {
		try(PreparedStatement ps = conn.prepareStatement(sql5)) {
			ps.setString(1, status);
			ps.setInt(2, idOs);
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
	}
	
}
