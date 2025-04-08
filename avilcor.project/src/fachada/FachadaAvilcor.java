package fachada;

import java.sql.Connection;

import model.controllers.CostureiraController;
import model.controllers.OrdemServicosController;
import model.controllers.ServicoController;
import model.controllers.UsuarioController;

public class FachadaAvilcor {
	
	private CostureiraController costureiraController;
	private UsuarioController usuarioController;
	private ServicoController servicoController;
	private OrdemServicosController ordemServicosController;
	
	public FachadaAvilcor(Connection conn) {
		this.costureiraController = new CostureiraController(conn);
		this.usuarioController =  new UsuarioController(conn);
		this.servicoController = new ServicoController(conn);
		this.ordemServicosController = new OrdemServicosController(conn);
	}
	
    public int cadastrarUsuario(String nome, String email) {
        return usuarioController.cadastrarUsuario(nome, email);
    }

    public String toStringUsuarioPorEmail(String email) {
        return usuarioController.getUsuarioEmail(email).toString();
    }

    public String toStringUsuarioPorId(int id) {
    	return usuarioController.getUsuarioId(id).toString();
    }

    public String listarUsuarios() {
    	return usuarioController.listarUsuarios();
    }
    
    public int adicionarCostureira(String nome, String cpf, int quantMaximaServico) {
        return costureiraController.adicionarCostureiraDBB(nome, cpf, quantMaximaServico);
    }

    public String toStringCostureiraPorId(int id) {
    	return costureiraController.pegarCostureiraId(id).toString();
    }

    public String listarHistoricoDeBuscas() {
       return costureiraController.listarHistorico();
    }
    
    public String listraCostureiras() {
    	return costureiraController.listarCostureiras();
    }

    public int cadastrarOrdemDeServico(int usuarioId, int costureiraId) {
        return ordemServicosController.cadastrarOrdemServicoDDB(costureiraId, usuarioId);
    }

    public String listarOrdensDeUmUsuario(int usuarioId) {
        return ordemServicosController.getOrdensServicoUserId(usuarioId);
    }
    
    public String listarOrdensDeServicos() {
        return ordemServicosController.listarOrdensServico();
    }

    public String listarServicosDeUmaOrdem(int ordemId) {
        return ordemServicosController.getServicos(servicoController, ordemId);
    }

    public void deletarOrdensFinalizadas() {
    	ordemServicosController.deletOrdemServicoFinalizada();
    }

    public void atualizarStatusParaEmAndamento(int ordemId) {
        ordemServicosController.setStatusAndamento(ordemId);
    }

    public void atualizarStatusParaFinalizado(int ordemId) {
        ordemServicosController.setStatusFinalizado(ordemId);
    }

    public int adicionarServicoAOrdem(int ordemId, double preco, String descricao) {
        return servicoController.salvarServicoDB(ordemId, preco, descricao);
    }
    


}
