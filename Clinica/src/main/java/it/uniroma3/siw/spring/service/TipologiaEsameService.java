package it.uniroma3.siw.spring.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.spring.model.Medico;
import it.uniroma3.siw.spring.model.TipologiaEsame;
import it.uniroma3.siw.spring.repository.TipologiaEsameRepository;


@Service
public class TipologiaEsameService {
	
	@Autowired
	private TipologiaEsameRepository tipologiaRepository;
	
	@Transactional
	public TipologiaEsame inserisci(TipologiaEsame col) {
		return tipologiaRepository.save(col);
	}

	@Transactional
	public List<TipologiaEsame> tutti() {
		return (List<TipologiaEsame>) tipologiaRepository.findAll();
	}
	
	@Transactional
	public List<TipologiaEsame> tuttiOrdinati() {
		return tipologiaRepository.findByOrderByNomeAsc();
	}

	@Transactional
	public TipologiaEsame tipoEsamePerId(Long id) {
		Optional<TipologiaEsame> optional = tipologiaRepository.findById(id);
		if (optional.isPresent())
			return optional.get();
		else 
			return null;
	}
	
	
	@Transactional
	public List<TipologiaEsame> perMedico(Medico med) {
		return (List<TipologiaEsame>) tipologiaRepository.findByMedico(med);
	}

	
	@Transactional
	public boolean alreadyExists(TipologiaEsame tip) {
		List<TipologiaEsame> collezioni = this.tipologiaRepository.findByNome(tip.getNome());
		if (collezioni.size() > 0) {
			return true;
		}
		return false;
	}
	
	@Transactional
	public void cancella(Long id) {
		this.tipologiaRepository.deleteById(id);
	}
	
	

}
