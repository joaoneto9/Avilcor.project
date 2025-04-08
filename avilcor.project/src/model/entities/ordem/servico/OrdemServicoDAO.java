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
	
	private static final String INSERIR_ORDEM_SERVICO = "INSERT INTO ordem_servico (costureira_id, usuario_Id, valor_total) VALUES (?, ?, ?)";
	private static final String BUSCAR_POR_USUARIO_ID = "SELECT * FROM ordem_servico WHERE usuario_id = ?";
	private static final String SOMAR_VALOR_TOTAL = "UPDATE ordem_servico SET valor_total = valor_total + ? WHERE id = ?";
	private static final String DELETAR_FINALIZADAS = "DELETE FROM ordem_servico WHERE status = Finalizada";
	private static final String ATUALIZAR_STATUS = "UPDATE ordem_servico SET status = ? WHERE id = ?";
	private static final String BUSCAR_COSTUREIRA_ID_POR_OS_ID = "SELECT costureira_id FROM ordem_servico WHERE id = ?";
	private static final String LISTAR_TODAS_OS = "SELECT * FROM ordem_servico";
	
	public static int cadastrarOrdemServico(Connection conn, OrdemServico os) {
		try (PreparedStatement ps = conn.prepareStatement(INSERIR_ORDEM_SERVICO, Statement.RETURN_GENERATED_KEYS)) {
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

	public static List<OrdemServico> getOrdemServicoUserID(Connection conn, int userId) {
		try (PreparedStatement ps = conn.prepareStatement(BUSCAR_POR_USUARIO_ID)) {
			List<OrdemServico> lista = new ArrayList<>();
			ps.setInt(1, userId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				lista.add(new OrdemServico(rs.getInt("id"), 
						rs.getInt("costureira_id"),  
						rs.getInt("usuario_id"),
						rs.getDate("data_criacao").toLocalDate(),
						StatusOS.valueOf(rs.getString("status")),
						rs.getDouble("valor_total"))); 
			}
			return lista; 
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
	}

	public static void somarValor(Connection conn, double valor, int Osid) {
		try(PreparedStatement ps = conn.prepareStatement(SOMAR_VALOR_TOTAL)) {
			ps.setDouble(1, valor);
			ps.setInt(2, Osid);
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
	}

	public static void deletarOsFinalizada(Connection conn) {
		try(PreparedStatement ps = conn.prepareStatement(DELETAR_FINALIZADAS)) {
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
	}

	public static void setStatusAndamento(Connection conn, int idOs) {
		setStatus(conn, StatusOS.Em_Andamento.name(), idOs);
	}

	public static void setStatusFinalizado(Connection conn, int idOs) {
		setStatus(conn, StatusOS.Finalizada.name(), idOs);
	}

	public static int getIdCostureira(Connection conn, int idOs) {
		try (PreparedStatement ps = conn.prepareStatement(BUSCAR_COSTUREIRA_ID_POR_OS_ID)) {
			ps.setInt(1, idOs);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) 
				return rs.getInt("costureira_id");
			return -1;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
	}

	public static List<OrdemServico> listarOrdemServicos(Connection conn) {
		try (PreparedStatement ps = conn.prepareStatement(LISTAR_TODAS_OS)) {
			ResultSet rs = ps.executeQuery();
			List<OrdemServico> pedidos = new ArrayList<>();
			while (rs.next()) {
				pedidos.add(new OrdemServico(rs.getInt("id"), 
						rs.getInt("costureira_id"),  
						rs.getInt("usuario_id"),
						rs.getDate("data_criacao").toLocalDate(),
						StatusOS.valueOf(rs.getString("status")),
						rs.getDouble("valor_total")));
			}
			return pedidos;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
	}

	private static void setStatus(Connection conn, String status, int idOs) {
		try(PreparedStatement ps = conn.prepareStatement(ATUALIZAR_STATUS)) {
			ps.setString(1, status);
			ps.setInt(2, idOs);
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
	}
}
