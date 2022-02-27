package pt.amane.awcontrolegestaofinanceiraapp.dtos;

import java.io.Serializable;

import pt.amane.awcontrolegestaofinanceiraapp.entities.Categoria;

public class CategriaDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String nome;

	public CategriaDTO() {
	}

	public CategriaDTO(Long id, String nome) {
		this.id = id;
		this.nome = nome;
	}

	public CategriaDTO(Categoria categoria) {
		id = categoria.getId();
		nome = categoria.getNome();
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

}
