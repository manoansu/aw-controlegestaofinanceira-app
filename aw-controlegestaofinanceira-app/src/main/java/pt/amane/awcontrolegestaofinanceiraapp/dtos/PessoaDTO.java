package pt.amane.awcontrolegestaofinanceiraapp.dtos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import pt.amane.awcontrolegestaofinanceiraapp.entities.Contacto;
import pt.amane.awcontrolegestaofinanceiraapp.entities.Endereco;
import pt.amane.awcontrolegestaofinanceiraapp.entities.Pessoa;

public class PessoaDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	private String nome;

	private Endereco endereco;

	private Boolean ativo;

	private List<ContactoDTO> contactos = new ArrayList<>();

	public PessoaDTO() {
	}

	public PessoaDTO(Long id, String nome, Endereco endereco, Boolean ativo) {
		this.id = id;
		this.nome = nome;
		this.endereco = endereco;
		this.ativo = ativo;
	}

	public PessoaDTO(Pessoa pessoa) {
		this.id = pessoa.getId();
		this.nome = pessoa.getNome();
		this.endereco = pessoa.getEndereco();
		this.ativo = pessoa.getAtivo();
	}

	public PessoaDTO(Pessoa pessoa, List<Contacto> listacontactos) {
		this(pessoa);
		listacontactos.forEach(obj -> this.contactos.add(new ContactoDTO(obj)));
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

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public List<ContactoDTO> getContactos() {
		return contactos;
	}

}
