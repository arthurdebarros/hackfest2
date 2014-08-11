package models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import models.exceptions.EventoInvalidoException;
import play.data.validation.Constraints.MaxLength;
import play.data.validation.Constraints.Required;


@Entity
public class Local {
	
	@Id
	@GeneratedValue
	private long id;
	
	@Required
	@MaxLength(value = 120)
	private String nome;
	
	@Required
	private int capacidade;
	
	@Required
	@MaxLength(value = 450)
	@Column(name = "CONTENT", length = 450)
	private String referencia;

}
