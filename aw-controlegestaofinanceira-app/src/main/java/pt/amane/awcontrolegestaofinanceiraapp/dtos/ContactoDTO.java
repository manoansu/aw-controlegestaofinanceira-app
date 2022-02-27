package pt.amane.awcontrolegestaofinanceiraapp.dtos;

import java.io.Serializable;

import pt.amane.awcontrolegestaofinanceiraapp.entities.Contacto;

public class ContactoDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String nome;
	private String email;
	private String telefone;

	public ContactoDTO() {
	}

	public ContactoDTO(Long id, String nome, String email, String telefone) {
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.telefone = telefone;
	}

	public ContactoDTO(Contacto contato) {
		id = contato.getId();
		nome = contato.getNome();
		email = contato.getEmail();
		telefone = contato.getTelefone();
	}

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
}
