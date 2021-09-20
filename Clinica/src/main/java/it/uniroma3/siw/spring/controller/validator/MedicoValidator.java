package it.uniroma3.siw.spring.controller.validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import it.uniroma3.siw.spring.model.Medico;
import it.uniroma3.siw.spring.service.MedicoService;



@Component
public class MedicoValidator implements Validator {
	
	@Autowired
	private MedicoService medicoService;
	
    private static final Logger logger = LoggerFactory.getLogger(EsameValidator.class);

	@Override
	public void validate(Object o, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nome", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cognome", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dataDiNascita", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "luogoDiNascita", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nazionalita", "required");

		if (!errors.hasErrors()) {
			logger.debug("confermato: valori non nulli");
			if (this.medicoService.alreadyExists((Medico)o)) {
				logger.debug("e' un duplicato");
				errors.reject("medico.duplicato");
			}
		}
	}

	@Override
	public boolean supports(Class<?> aClass) {
		return Medico.class.equals(aClass);
	}


}
