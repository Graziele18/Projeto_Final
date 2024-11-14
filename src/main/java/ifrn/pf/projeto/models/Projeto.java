package ifrn.pf.projeto.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Projeto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private int cartaoSus;
	private String enfermidade;
	private String dataNascimento;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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
