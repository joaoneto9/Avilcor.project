package model.entities.costureira;

import java.awt.geom.QuadCurve2D;

public class Costureira {
	
	private int id;
	private String nome;
	private String cpf;
	private int quantServico;
	private int QUANTIDADE_MAXIMA;
	
	public Costureira(String nome, String cpf, int QUANTIDADE_MAXIMA) {
		this.nome = nome;
		this.cpf = cpf;
		this.QUANTIDADE_MAXIMA = QUANTIDADE_MAXIMA;
	}
	
	public Costureira(int id, String nome, String cpf, int QUANTIDADE_MAXIMA, int quantServico) {
		this(nome, cpf, QUANTIDADE_MAXIMA);
		this.id = id;
		this.quantServico = quantServico;
	}
	
	@Override
	public String toString() {
		return "Costureira #" + id + ": \nnome: " + nome + "\ncpf: " + cpf + 
				"\nquantidade de servicos: " + quantServico + "/" + QUANTIDADE_MAXIMA;
	}
	
	public int getQuantServico() {
		return quantServico;
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
