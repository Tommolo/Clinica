package it.uniroma3.siw.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AdminController {
	
	/**
	 * l'amministratore vuole visualizzare l'area admin
	 * @return String
	 */
	@RequestMapping(value="/admin/areaAdmin", method = RequestMethod.GET)
    public String viewAdmin() {
        return "admin/areaAdmin";
        
    }

}
