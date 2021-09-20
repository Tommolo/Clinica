package it.uniroma3.siw.spring.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.spring.model.Esame;
import it.uniroma3.siw.spring.model.Medico;

import it.uniroma3.siw.spring.repository.EsameRepository;



@Service
public class EsameService {
	
	@Autowired
	private EsameRepository esameRepository;
	
	@Transactional
	public Esame inserisci(Esame es) {
		return this.esameRepository.save(es);
	}
	
	@Transactional
	public List<Esame> tutti() {
		return (List<Esame>) esameRepository.findAll();
	}
	
	@Transactional
	public List<Esame> tuttiOrdinatiPerTitolo() {
		return this.esameRepository.findByOrderByTitoloAsc();
	}
	
	
	@Transactional
	public List<Esame> perArtista(Medico med) {
		return (List<Esame>) esameRepository.findByMedico(med);
	}
	
	@Transactional
	public Esame quadroPerId(Long id) {
		Optional<Esame> optional = esameRepository.findById(id);
		if (optional.isPresent())
			return optional.get();
		else 
			return null;
	}
	
	
	
	@Transactional 
	public boolean alreadyExists(Esame op) {
		List<Esame> quadri = this.esameRepository.findByTitoloAndMedico(op.getTitolo(), op.getMedico());
		if (quadri.size() > 0)
			return true;
		else 
			return false;
	}
	
	@Transactional
	public void cancella(Long id) {
		this.esameRepository.deleteById(id);
	}

}
