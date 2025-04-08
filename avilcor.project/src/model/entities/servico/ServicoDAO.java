package model.entities.servico;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.DbException;
import model.entities.costureira.CostureiraDAO;
import model.entities.ordem.servico.OrdemServicoDAO;

public class ServicoDAO {
	
	private final static String INSERIR_SERVICO = "INSERT INTO servico (ordem_servico_id, descricao, preco) VALUES (?, ?, ?)";
	private final static String SELECIONAR_SERVICO_OS_ID = "SELECT * FROM servico WHERE ordem_servico_id = ?";
	
	public static int salvarServico(Connection conn, Servico servico) {
		try (PreparedStatement ps = conn.prepareStatement(INSERIR_SERVICO, Statement.RETURN_GENERATED_KEYS)) {
			ps.setInt(1, servico.getIdOrdemServico());
			ps.setString(2, servico.getDescricao());
			ps.setDouble(3, servico.getPreco());
			ps.executeUpdate();
			OrdemServicoDAO.somarValor(conn, servico.getPreco(), servico.getIdOrdemServico());
			int idCostureira = OrdemServicoDAO.getIdCostureira(conn, servico.getIdOrdemServico());
			CostureiraDAO.addServico(conn, idCostureira);
			return servico.getIdOrdemServico();
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
	} 
	
	public static List<Servico> pegarServicosOsId(Connection conn, int OrdemId) {
		try (PreparedStatement ps = conn.prepareStatement(SELECIONAR_SERVICO_OS_ID)) {
			List<Servico> servicos = new ArrayList<>();
			ps.setInt(1, OrdemId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				servicos.add(new Servico(rs.getInt("id"),
						rs.getInt("ordem_servico_id"),
						rs.getString("descricao"),
						rs.getDouble("preco")));
			}
			return servicos;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		
	}

}
