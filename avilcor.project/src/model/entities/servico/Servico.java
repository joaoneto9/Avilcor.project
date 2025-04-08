package model.entities.servico;
import model.controllers.OrdemServicosController;

public class Servico {
	
	private String descricao;
	private Double preco;
	private int idOrdemServico;
	private int id;
	public Servico(int idOrdemServico, String descricao, Double preco) {
		this.preco = preco;
		this.descricao = descricao;
		this.idOrdemServico = idOrdemServico;
	}

	public Servico(int id, int idOrdemServico, String descricao, Double preco) {
		this(idOrdemServico, descricao, preco);
		this.id = id;
	}
	
	@Override
	public String toString() {
		return "Servico#" + id + ":\npreco: " + preco + "\ndescricao: " 
				+ descricao + "\nidOrdemServico: " + idOrdemServico;
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
