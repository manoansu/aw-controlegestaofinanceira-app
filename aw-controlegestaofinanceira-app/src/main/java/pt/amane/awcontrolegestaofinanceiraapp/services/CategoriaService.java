package pt.amane.awcontrolegestaofinanceiraapp.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pt.amane.awcontrolegestaofinanceiraapp.dtos.CategoriaDTO;
import pt.amane.awcontrolegestaofinanceiraapp.entities.Categoria;
import pt.amane.awcontrolegestaofinanceiraapp.repositories.CategoriaRepository;
import pt.amane.awcontrolegestaofinanceiraapp.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repository;

	@Transactional(readOnly = true)
	public CategoriaDTO findById(Long id) {
		Optional<Categoria> categoriaId = repository.findById(id);
		Categoria categoria = categoriaId.orElseThrow(
				() -> new ObjectNotFoundException("Object not found! Id: " + id + " Type: " + Categoria.class));
		return new CategoriaDTO(categoria);
	}

	@Transactional(readOnly = true)
	public List<CategoriaDTO> findAll() {
		List<Categoria> categorias = repository.findAll();
		return categorias.stream().map(obj -> new CategoriaDTO(obj)).collect(Collectors.toList());
	}
	
	

}
