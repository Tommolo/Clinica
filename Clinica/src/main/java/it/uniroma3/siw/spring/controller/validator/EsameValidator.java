package it.uniroma3.siw.spring.controller.validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import it.uniroma3.siw.spring.model.Esame;
import it.uniroma3.siw.spring.service.EsameService;



@Component
public class EsameValidator implements Validator {
	
	@Autowired
	private EsameService esameService;
	
    private static final Logger logger = LoggerFactory.getLogger(EsameValidator.class);

	@Override
	public void validate(Object o, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "titolo", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "descrizione", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "tipoEsame", "required");


		if (!errors.hasErrors()) {
			logger.debug("confermato: valori non nulli");
			if (this.esameService.alreadyExists((Esame)o)) {
				logger.debug("e' un duplicato");
				errors.reject("esame.duplicato");
			}
		}
	}

	@Override
	public boolean supports(Class<?> aClass) {
		return Esame.class.equals(aClass);
	}

}
