package it.uniroma3.siw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages={"it.uniroma3.siw"})
public class SiwSpringClinicaApplication {
	public static void main(String[] args) {
		SpringApplication.run(SiwSpringClinicaApplication.class, args);

	}

}
