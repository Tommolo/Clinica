package it.uniroma3.siw.spring.controller;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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

import it.uniroma3.siw.spring.controller.validator.EsameValidator;
import it.uniroma3.siw.spring.misc.FileUploadUtil;
import it.uniroma3.siw.spring.model.Esame;
import it.uniroma3.siw.spring.service.CredentialsService;
import it.uniroma3.siw.spring.service.EsameService;
import it.uniroma3.siw.spring.service.MedicoService;
import it.uniroma3.siw.spring.service.TipologiaEsameService;




@Controller
public class EsameController {
	
	@Autowired
	private EsameService esameService;
	
    @Autowired
    private EsameValidator esameValidator;
    
    @Autowired
    private MedicoService medicoService;
    
    @Autowired
    private TipologiaEsameService tipologiaService;

    @Autowired
	private CredentialsService credentialsService;


    
  
    
    /**
     * un amministratore vuole inserire un nuovo esame
     * @param model
     * @return string
     */
    @RequestMapping(value="/admin/esame", method = RequestMethod.GET)
    public String addEsame(Model model) {
    	model.addAttribute("esame", new Esame());
    	model.addAttribute("medici", this.medicoService.tuttiOrdinati());
    	model.addAttribute("credentials", this.credentialsService.tuttiOrdinati());
    	model.addAttribute("tipoEsami", this.tipologiaService.tuttiOrdinati());
        return "esameForm";
    }

    /**
     * un utente vuole visualizzare la pagina di uno specifico esame
     * @param id
     * @param model
     * @return string
     */
    @RequestMapping(value = "/esame/{id}", method = RequestMethod.GET)
    public String getEsame(@PathVariable("id") Long id, Model model) {
    	model.addAttribute("esame", this.esameService.esamePerId(id));
    	return "esame";
    }
    
    /**
     * un amministratore vuole cancellare un determinato esame
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "/esameCancella/{id}", method = RequestMethod.GET)
    public String deleteEsame(@PathVariable("id") Long id, Model model) {
    	this.esameService.cancella(id);
    	model.addAttribute("esami", this.esameService.tuttiOrdinatiPerTitolo());
    	return "esami";
    }

    /**
     * un utente vuole visualizzare la lista di esami per paziente
     * @param model
     * @return string
     */
    @RequestMapping(value = "/esami", method = RequestMethod.GET)
    public String getEsami(Model model) {
    		UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    		model.addAttribute("credential",this.credentialsService.getCredentials(userDetails.getUsername()));
    		model.addAttribute("esami", this.esameService.perPaziente(this.credentialsService.getCredentials(userDetails.getUsername())));
    		return "esami";
    }

    @RequestMapping(value = "/admin/esame", method = RequestMethod.POST)
    public String addEsame(@ModelAttribute("esame") Esame esame, 
    									Model model, BindingResult bindingResult, @RequestParam("image") MultipartFile multipartFile) throws IOException {
    	this.esameValidator.validate(esame, bindingResult);
        if (!bindingResult.hasErrors()) {
        	
            String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
            esame.setFoto(fileName);
        	this.esameService.inserisci(esame);
            String uploadDir = "uploadable/esami/" + esame.getId();
            FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
            model.addAttribute("esami", this.esameService.tuttiOrdinatiPerTitolo());
            return "esami";
        }
        model.addAttribute("medici", this.medicoService.tuttiOrdinati());
        model.addAttribute("tipoEsami", this.tipologiaService.tuttiOrdinati());
        return "esameForm";
    }

}
