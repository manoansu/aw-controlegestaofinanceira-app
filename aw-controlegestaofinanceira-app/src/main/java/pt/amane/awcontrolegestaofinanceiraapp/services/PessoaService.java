package pt.amane.awcontrolegestaofinanceiraapp.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pt.amane.awcontrolegestaofinanceiraapp.dtos.ContactoDTO;
import pt.amane.awcontrolegestaofinanceiraapp.dtos.PessoaDTO;
import pt.amane.awcontrolegestaofinanceiraapp.entities.Contacto;
import pt.amane.awcontrolegestaofinanceiraapp.entities.Pessoa;
import pt.amane.awcontrolegestaofinanceiraapp.repositories.ContactoRepository;
import pt.amane.awcontrolegestaofinanceiraapp.repositories.PessoaRepository;
import pt.amane.awcontrolegestaofinanceiraapp.services.exceptions.DataBaseIntegrityViolationException;
import pt.amane.awcontrolegestaofinanceiraapp.services.exceptions.ResourceNotFoundException;

@Service
public class PessoaService {

	@Autowired
	private PessoaRepository repository;
	
	@Autowired
	private ContactoRepository contactoRepository;

	@Transactional(readOnly = true)
	public PessoaDTO findById(Long id) {
		Optional<Pessoa> pessoaId = repository.findById(id);
		Pessoa pessoa = pessoaId.orElseThrow(() -> new ResourceNotFoundException(
				"Object not found! Id: " + id + ", Type: " + Pessoa.class.getName()));
		return new PessoaDTO(pessoa, pessoa.getContatos());
	}

	@Transactional(readOnly = true)
	public List<PessoaDTO> findAll() {
		List<Pessoa> pessoas = repository.findAll();
		return pessoas.stream().map(obj -> new PessoaDTO(obj, obj.getContatos())).collect(Collectors.toList());
	}
	
	@Transactional
	public PessoaDTO create(PessoaDTO dto) {
		Pessoa pessoa = new Pessoa();
		copyPessoa(pessoa, dto);
		pessoa = repository.save(pessoa);
		return new PessoaDTO(pessoa);
	}
	
	@Transactional
	public PessoaDTO update(Long id, PessoaDTO dto) {
		try {
			@SuppressWarnings("deprecation")
			Pessoa pessoa = repository.getOne(id);
			copyPessoa(pessoa, dto);
			pessoa = repository.save(pessoa);
			return new PessoaDTO(pessoa);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Object not found! Id:" + id + ", Type: " + Pessoa.class.getName());
		}
	}
	
	public void delete(Long id) {
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Id not found! Id: " + id);
		}catch (DataIntegrityViolationException e) {
			throw new DataBaseIntegrityViolationException("Person cannot be deleted! Has associated object");
		}
	}

	private void copyPessoa(Pessoa pessoa, PessoaDTO dto) {
		pessoa.setNome(dto.getNome());
		pessoa.setAtivo(dto.getAtivo());
		pessoa.setEndereco(dto.getEndereco());
						
		if(pessoa.getContatos().isEmpty()) {
			salvarContacto(pessoa, dto);
		}else {
				
			List<Contacto> contactos = dto.getContactos().stream()
					.map(c -> new Contacto(null, c.getNome(), c.getEmail(), c.getTelefone(), pessoa))
					.collect(Collectors.toList());
			// limpa a lista de contacto para poder inserir novo contacto..
			pessoa.getContatos().clear();
			pessoa.getContatos().addAll(contactos);
			
//			for(Contacto obj: pessoa.getContatos()) {
//				@SuppressWarnings("deprecation")
//				Contacto contacto = contactoRepository.getOne(obj.getId());
//				pessoa.getContatos().add(contacto);
//			}
		}
	}

	private void salvarContacto(Pessoa pessoa, PessoaDTO dto) {
		Contacto contacto = new Contacto();
		for(ContactoDTO obj: dto.getContactos()) {
			contacto.setEmail(obj.getEmail());
			contacto.setNome(obj.getNome());
			contacto.setTelefone(obj.getTelefone());
			contacto.setPessoa(pessoa);
			contactoRepository.save(contacto);
		}
		pessoa.getContatos().add(contacto);
	}

}
