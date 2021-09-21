package it.uniroma3.siw.spring.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import it.uniroma3.siw.spring.controller.validator.MedicoValidator;
import it.uniroma3.siw.spring.misc.FileUploadUtil;
import it.uniroma3.siw.spring.model.Medico;
import it.uniroma3.siw.spring.service.MedicoService;


@Controller
@RequestMapping
public class MedicoController {
	
	@Autowired
	private MedicoService medicoService;
	
	@Autowired
	private MedicoValidator medicoValidator;
	


	
	/**
	 * l'amministratore vuole inserire i dati di un nuovo medico
	 * @param model
	 * @return String
	 */
	@RequestMapping(value="/admin/medico", method = RequestMethod.GET)
	public String addMedico(Model model) {
	    model.addAttribute("medico", new Medico());
	    return "medicoForm";
	}

	/**
	 * un utente (anche un amministratore) vuole visionare la pagina di uno
	 * specifico medico
	 * @param id
	 * @param model
	 * @return String
	 */
    @RequestMapping(value = "/medico/{id}", method = RequestMethod.GET)
    public String getMedico(@PathVariable("id") Long id, Model model) {
    	model.addAttribute("medico", this.medicoService.medicoPerId(id));
    	return "medico";
    }
    
    /**
     * l'amministratore vuole cancellare un medico dal db
     * @param id
     * @param model
     * @return String
     */
    @RequestMapping(value = "/admin/medico/{id}", method = RequestMethod.GET)
    public String deleteMedico(@PathVariable("id") Long id, Model model) {
    	this.medicoService.cancella(id);
    	model.addAttribute("medici", this.medicoService.tuttiOrdinati());
    	return "medici";
    }

    /**
     * un utente (anche un amministratore) vuole visualizzare la 
     * lista di tutti i medici salvati all'interno del db
     * della clinica
     * @param model
     * @return String
     */
    @RequestMapping(value = "/medici", method = RequestMethod.GET)
    public String getMedici(Model model) {
    		model.addAttribute("medici", this.medicoService.tuttiOrdinati());
    		return "medici";
    }
    
    /**
     * metodo post di inserimento del medico
     * il medico inserito viene validato, se la verifica è corretta
     * viene settato il path del file dell'immagine del profilo del medico
     * @param medico
     * @param model
     * @param bindingResult
     * @param multipartFile
     * @return String
     * @throws IOException
     */
    @RequestMapping(value = "/admin/medico", method = RequestMethod.POST)
    public String addMedico(@ModelAttribute("medico") Medico medico, 
    									Model model, BindingResult bindingResult, @RequestParam("image") MultipartFile multipartFile) throws IOException {
    	this.medicoValidator.validate(medico, bindingResult);
        if (!bindingResult.hasErrors()) {
        	
        	String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        	medico.setFoto(fileName);
        	this.medicoService.inserisci(medico);
        	String uploadDir = "uploadable/medico-foto/" + medico.getId();
            FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);           
            model.addAttribute("medici", this.medicoService.tuttiOrdinati());
            return "medici";
        
        }
        
        return "medicoForm";
    
    }

}
