package model.entities.ordem.servico;
import java.time.LocalDate;

public class OrdemServico {
	
	private int id;
	private int costureiraId;
	private int usuarioId;
	private double valorTotal;
	private LocalDate data;
	private StatusOS status;
	
	
	public OrdemServico(int costureiraId, int usuarioId) {
		this.costureiraId = costureiraId;
		this.usuarioId = usuarioId;
		this.data = LocalDate.now(); // data criada na hora
		this.status = StatusOS.Pendente;
	}
	
	public OrdemServico(int id, int costureiraId, int usuarioId, double valor) {
		this(costureiraId, usuarioId);
		this.id = id;
		this.valorTotal = valor;
	}
	
	@Override
	public String toString() {
		return "OrdemServico#" + id + ": usuarioId: " + usuarioId + ", valorTotal: " + valorTotal + ", data: " + data
				+ ", status: ";
	}

	public void addValorTotal(double valor) {
		this.valorTotal += valor;
	}

	public int getId() {
		return id;
	}

	public StatusOS getStatus() {
		return status;
	}

	public void setStatusAndamento() {
		this.status = StatusOS.Em_Andamento;
	}
	public void setStatusFinalizada() {
		this.status = StatusOS.Finalizada;
	}

	public int getCostureiraId() {
		return costureiraId;
	}

	public int getUsuarioId() {
		return usuarioId;
	}

	public LocalDate getData() {
		return data;
	}

	public double getValorTotal() {
		return valorTotal;
	}
	

}
