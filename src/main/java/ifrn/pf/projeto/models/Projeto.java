package ifrn.pf.projeto.models;

public class Projeto {

	private String nome;
	private int cartaoSus;
	private String enfermidade;
	private String dataNascimento;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getCartaoSus() {
		return cartaoSus;
	}

	public void setCartaoSus(int cartaoSus) {
		this.cartaoSus = cartaoSus;
	}

	public String getEnfermidade() {
		return enfermidade;
	}

	public void setEnfermidade(String enfermidade) {
		this.enfermidade = enfermidade;
	}

	public String getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

}
