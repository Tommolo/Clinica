package it.uniroma3.siw.spring.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.spring.model.Medico;
import it.uniroma3.siw.spring.model.TipologiaEsame;


public interface TipologiaEsameRepository extends CrudRepository<TipologiaEsame, Long> {
	
	public List<TipologiaEsame> findByNome(String nome);
	
	public List<TipologiaEsame> findByMedico(Medico med);
	
	//riporta le collezione ordinate per ordine alfabetico
	public List<TipologiaEsame> findByOrderByNomeAsc();

}
