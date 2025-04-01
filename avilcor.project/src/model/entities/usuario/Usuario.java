package model.entities.usuario;

public class Usuario {

	private int id;
	private String email;
	private String nome;
	
	
	public Usuario(String nome, String email) {
		this.nome = nome;
		this.email = email;
	}
	
	public Usuario(int id, String nome, String email) {
		this(nome, email);
		this.id = id;
	}

	@Override
	public String toString() {
		return "Usuario#" + id + ": email: " + email + ", nome: " + nome;
	}

	public int getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	public String getNome() {
		return nome;
	}
	
}
