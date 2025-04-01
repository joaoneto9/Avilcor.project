package Sistema;

import java.sql.Connection;

import model.controllers.CostureiraController;
import model.controllers.OrdemServicosController;
import model.controllers.ServicoController;
import model.controllers.UsuarioController;

public class Sistema {
	
	private CostureiraController cs;
	private UsuarioController us;
	private ServicoController sc;
	private OrdemServicosController os;
	
	public Sistema(Connection conn) {
		this.cs = new CostureiraController(conn);
		this.us =  new UsuarioController(conn);
		this.sc = new ServicoController(conn);
		this.os = new OrdemServicosController(conn);
	}
	
    public int cadastrarUsuario(String nome, String email) {
        return us.cadastrarUsuario(nome, email);
    }

    public String toStringUsuarioPorEmail(String email) {
        return us.getUsuarioEmail(email).toString();
    }

    public String toStringUsuarioPorId(int id) {
    	return us.getUsuarioId(id).toString();
    }

    public int adicionarCostureira(String nome, String cpf, int quantMaximaServico) {
        return cs.adicionarCostureiraDBB(nome, cpf, quantMaximaServico);
    }

    public String toStringCostureiraPorId(int id) {
    	return cs.pegarCostureiraId(id).toString();
    }

    public String listarHistoricoDeBuscas() {
       return cs.listarHistorico();
    }

    public int cadastrarOrdemDeServico(int usuarioId, int costureiraId) {
        return os.cadastrarOrdemServicoDDB(costureiraId, usuarioId);
    }

    public String listarOrdensDeUmUsuario(int usuarioId) {
        return os.getOrdensServicoUserId(usuarioId);
    }

    public String listarServicosDeUmaOrdem(int ordemId) {
        return os.getServicos(sc, ordemId);
    }

    public void deletarOrdensFinalizadas() {
    	os.deletOrdemServicoFinalizada();
    }

    public void atualizarStatusParaEmAndamento(int ordemId) {
        os.setStatusAndamento(ordemId);
    }

    public void atualizarStatusParaFinalizado(int ordemId) {
        os.setStatusFinalizado(ordemId);
    }

    public int adicionarServicoAOrdem(int ordemId, double preco, String descricao) {
        return sc.salvarServicoDB(ordemId, preco, descricao);
    }


}
