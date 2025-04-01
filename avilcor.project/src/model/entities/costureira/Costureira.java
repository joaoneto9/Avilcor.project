package model.entities.costureira;

public class Costureira {
	
	private int id;
	private String nome;
	private String cpf;
	private int QUANTIDADE_MAXIMA;
	
	public Costureira(int id, String nome, String cpf, int QUANTIDADE_MAXIMA) {
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.QUANTIDADE_MAXIMA = QUANTIDADE_MAXIMA;
	}
	
	public Costureira(String nome, String cpf, int QUANTIDADE_MAXIMA) {
		this.nome = nome;
		this.cpf = cpf;
		this.QUANTIDADE_MAXIMA = QUANTIDADE_MAXIMA;
	}
	
	@Override
	public String toString() {
		return "Costureira #" + id + ": \nnome: " + nome + "\ncpf: " + cpf;
	}

	public int getQuantidadeMaxima() {
		return QUANTIDADE_MAXIMA;
	}

	public int getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getCpf() {
		return cpf;
	}
	
	
	
	
	

}
