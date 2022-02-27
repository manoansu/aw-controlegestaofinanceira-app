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

import pt.amane.awcontrolegestaofinanceiraapp.dtos.CategoriaDTO;
import pt.amane.awcontrolegestaofinanceiraapp.entities.Categoria;
import pt.amane.awcontrolegestaofinanceiraapp.repositories.CategoriaRepository;
import pt.amane.awcontrolegestaofinanceiraapp.services.exceptions.DataBaseIntegrityViolationException;
import pt.amane.awcontrolegestaofinanceiraapp.services.exceptions.ResourceNotFoundException;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repository;

	@Transactional(readOnly = true)
	public CategoriaDTO findById(Long id) {
		Optional<Categoria> categoriaId = repository.findById(id);
		Categoria categoria = categoriaId.orElseThrow(
				() -> new ResourceNotFoundException("Object not found! Id: " + id + ", Type: " + Categoria.class));
		return new CategoriaDTO(categoria);
	}

	@Transactional(readOnly = true)
	public List<CategoriaDTO> findAll() {
		List<Categoria> categorias = repository.findAll();
		return categorias.stream().map(obj -> new CategoriaDTO(obj)).collect(Collectors.toList());
	}

	@Transactional
	public CategoriaDTO creae(CategoriaDTO dto) {
		Categoria categoria = new Categoria();
		categoria.setNome(dto.getNome());
		categoria = repository.save(categoria);
		return new CategoriaDTO(categoria);
	}

	@Transactional
	public CategoriaDTO update(Long id, CategoriaDTO dto) {
		try {
			@SuppressWarnings("deprecation")
			Categoria categoria = repository.getOne(id);
			categoria.setNome(dto.getNome());
			categoria = repository.save(categoria);
			return new CategoriaDTO(categoria);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Onbject not found! Id: " + id + ", Type: " + dto.getId());
		}
	}
	
	public void delete(Long id) {
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Id not found! Id: " + id);
		}catch (DataIntegrityViolationException e) {
			throw new DataBaseIntegrityViolationException("category cannot be deleted! has associated object..");
		}
	}

}
