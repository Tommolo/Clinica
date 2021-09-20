package it.uniroma3.siw.spring.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.spring.model.Medico;



public interface MedicoRepository extends CrudRepository<Medico, Long> {
	
	public List<Medico> findByCognome(String cognome);
	
	public List<Medico> findByNome(String nome);
	
	public List<Medico> findByNazionalita(String nazionalita);

	public List<Medico> findByNomeAndCognome(String nome, String cognome);

	//riporta tutti i medici ordinati con cognomi in ordine alfabetico
	public List<Medico> findByOrderByCognomeAsc();
	
}
