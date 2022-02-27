package pt.amane.awcontrolegestaofinanceiraapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pt.amane.awcontrolegestaofinanceiraapp.entities.Contacto;

@Repository
public interface ContactoRepository extends JpaRepository<Contacto, Long>{

}
