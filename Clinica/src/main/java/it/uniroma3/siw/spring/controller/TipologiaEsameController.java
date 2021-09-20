package it.uniroma3.siw.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.uniroma3.siw.spring.controller.validator.TipologiaEsameValidator;
import it.uniroma3.siw.spring.model.TipologiaEsame;
import it.uniroma3.siw.spring.service.MedicoService;

import it.uniroma3.siw.spring.service.TipologiaEsameService;



@Controller
public class TipologiaEsameController {
	
	@Autowired
	private TipologiaEsameService tipologiaService;
	
    @Autowired
    private TipologiaEsameValidator tipologiaValidator;


    @Autowired
	private MedicoService medicoService;
    
    /**
     * azione dell'amministratore
     * Crea un nuovo oggetto collezione e reindirizza alla pagina collezioneForm
     * @param model
     * @return string
     */
    @RequestMapping(value="/admin/tipoEsame", method = RequestMethod.GET)
    public String addtipoEsame(Model model) {
    	model.addAttribute("tipoEsame", new TipologiaEsame());
    	model.addAttribute("medici", this.medicoService.tuttiOrdinati());
        return "tipoEsameForm";
    }

    /**
     * un utente vuole visualizzare la pagina di una specifica tipologia di esame
     * @param id
     * @param model
     * @return string
     */
    @RequestMapping(value = "/tipoEsame/{id}", method = RequestMethod.GET)
    public String gettipoEsame(@PathVariable("id") Long id, Model model) {
    	model.addAttribute("tipoEsame", this.tipologiaService.tipoEsamePerId(id));
    	return "tipoEsame";
    }

    /**
     * un utente vuole visualizzare la lista di tutte le tipologie di esami salvate
     * @param model
     * @return string
     */
    @RequestMapping(value = "/tipoEsami", method = RequestMethod.GET)
    public String getCollezioni(Model model) {
    		model.addAttribute("tipoEsami", this.tipologiaService.tuttiOrdinati());
    		return "tipoEsami";
    }
    
    /**
     * un amministratore vuole cancellare una tipologia di esami salvata
     * @param id
     * @param model
     * @return string
     */
    @RequestMapping(value = "/admin/tipoEsame/{id}", method = RequestMethod.GET)
    public String deleteCollezione(@PathVariable("id") Long id, Model model) {
    	this.tipologiaService.cancella(id);
    	model.addAttribute("tipoEsami", this.tipologiaService.tuttiOrdinati());
    	return "tipoEsami";
    }
    
    @RequestMapping(value = "/admin/tipoEsame", method = RequestMethod.POST)
    public String addQuadro(@ModelAttribute("tipoEsame") TipologiaEsame tipoEsame, 
    									Model model, BindingResult bindingResult) {
    	this.tipologiaValidator.validate(tipoEsame, bindingResult);
        if (!bindingResult.hasErrors()) {
        	
        	this.tipologiaService.inserisci(tipoEsame);
        	
            model.addAttribute("tipoEsami", this.tipologiaService.tuttiOrdinati());
            return "tipoEsami";
        }
        model.addAttribute("medici", this.medicoService.tuttiOrdinati());
        return "tipoEsameForm";
    }


}
