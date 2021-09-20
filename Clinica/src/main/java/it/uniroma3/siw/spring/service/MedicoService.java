package it.uniroma3.siw.spring.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.spring.model.Medico;
import it.uniroma3.siw.spring.repository.MedicoRepository;



@Service
public class MedicoService {
	
	@Autowired
	private MedicoRepository medicoRepository; 
	
	@Transactional
	public Medico inserisci(Medico medico) {
		return medicoRepository.save(medico);
	}

	@Transactional
	public List<Medico> tutti() {
		return (List<Medico>) medicoRepository.findAll();
	}
	
	@Transactional
	public List<Medico> tuttiOrdinati() {
		return this.medicoRepository.findByOrderByCognomeAsc();
	}

	@Transactional
	public Medico medicoPerId(Long id) {
		Optional<Medico> optional = medicoRepository.findById(id);
		if (optional.isPresent())
			return optional.get();
		else 
			return null;
	}

	@Transactional
	public boolean alreadyExists(Medico medico) {
		List<Medico> artisti = this.medicoRepository.findByNomeAndCognome(medico.getNome(), medico.getCognome());
		if (artisti.size() > 0) {
			return true;
		}
		return false;
	}
	
	@Transactional
	public void cancella(Long id) {
		this.medicoRepository.deleteById(id);
	}
	

}
