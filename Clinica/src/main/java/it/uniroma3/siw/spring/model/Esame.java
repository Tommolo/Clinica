package it.uniroma3.siw.spring.model;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "esame")
@Getter @Setter @AllArgsConstructor @EqualsAndHashCode @NoArgsConstructor
public class Esame {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	
	@Column(nullable = false)
	private String titolo;
	@Column(length=5096)
	private String descrizione;
	
	@ManyToOne(fetch = FetchType.EAGER)	
	private TipologiaEsame tipoEsame;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "medico_id")
	private Medico medico;
	
	
	@JoinColumn(name = "data")
	private LocalDate dataRisultato=LocalDate.now();;
	
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "credential_id")
	private Credentials credential;
	
	@Column(nullable=true)
	private String foto;
	
	 @Transient
	    public String getPhotosImagePath() {
	        if (foto.equals(null) || id.equals(null)) return null;
	         
	        return "/uploadable/esami/" + id + "/" + foto;
	    }

}
