package model.entities.servico;
import model.controllers.OrdemServicosController;

public class Servico {
	
	private String descricao;
	private Double preco;
	private int idOrdemServico;
	private int id;
	public Servico(int idOrdemServico, Double preco, String descricao) {
		this.preco = preco;
		this.descricao = descricao;
		this.idOrdemServico = idOrdemServico;
	}

	public Servico(int id, int idOrdemServico, Double preco, String descricao) {
		this(idOrdemServico, preco, descricao);
		this.id = id;
	}
	
	@Override
	public String toString() {
		return "Servico#" + id + ": preco: " + preco + "\ndescricao: " 
				+ descricao + "idOrdemServico: " + idOrdemServico;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public double getPreco() {
		return preco;
	}

	public int getIdOrdemServico() {
		return idOrdemServico;
	}

	public int getId() {
		return id;
	}

	
	
}
