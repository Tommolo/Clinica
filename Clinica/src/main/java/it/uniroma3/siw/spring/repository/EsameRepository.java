package it.uniroma3.siw.spring.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.uniroma3.siw.spring.model.Credentials;
import it.uniroma3.siw.spring.model.Esame;



@Repository
public interface EsameRepository extends CrudRepository<Esame, Long> {
	
	public List<Esame> findByTitolo(String titolo);

	public List<Esame> findByTitoloAndCredential(String titolo, Credentials cred);
	
	public List<Esame> findByCredential(Credentials cred);
	
	public List<Esame> findByOrderByTitoloAsc();
	

	


}
