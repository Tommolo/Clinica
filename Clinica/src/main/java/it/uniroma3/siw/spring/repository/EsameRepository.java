package it.uniroma3.siw.spring.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.uniroma3.siw.spring.model.Esame;
import it.uniroma3.siw.spring.model.Medico;



@Repository
public interface EsameRepository extends CrudRepository<Esame, Long> {
	
	public List<Esame> findByTitolo(String titolo);
	
	public List<Esame> findByMedico(Medico medico);
	
	public List<Esame> findByTitoloAndMedico(String titolo, Medico med);
	
	public List<Esame> findByOrderByTitoloAsc();
	

	


}
