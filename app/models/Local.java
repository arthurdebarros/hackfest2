package models;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;



import models.exceptions.LocalInvalidoException;
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
	private Integer capacidade;
	
	@Required
	@MaxLength(value = 450)
	private String referencias;

	public Local(String nome, String referencias, Integer capacidade) throws LocalInvalidoException {
		super();
		setNome(nome);
		setReferencias(referencias);
		setCapacidade(capacidade);
	}
	
	public void setNome(String titulo) throws LocalInvalidoException {
		if (titulo == null){
			throw new LocalInvalidoException("Parametro nulo");
		}
		if (titulo.length() > 120){
			throw new LocalInvalidoException("Título longo");
		}else{
			this.nome = titulo;
		}
	}
	
	public void setReferencias(String referencias) throws LocalInvalidoException {
		if (referencias == null){
			throw new LocalInvalidoException("Parametro nulo");
		}
		if (referencias.length() > 450){
			throw new LocalInvalidoException("Referencias para local muito longas");
		}else{
			this.nome = referencias;
		}
	}
	
	public void setCapacidade(Integer capacidade) throws LocalInvalidoException {
		if (capacidade == null){
			throw new LocalInvalidoException("Parametro nulo");
		}else{
			if(capacidade == 0){
				throw new LocalInvalidoException("Um local com capacidade 0 não é permitido");
			}else{
				this.capacidade = capacidade;
			}
		}
	}

	public Local() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}
	
	public String getReferencias(){
		return referencias;
	}
	
	public Integer getCapacidade(){
		return capacidade;
	}
	
	@Override
	public String toString() {
		return "Local: " + nome + " (Capacidade: " + capacidade +")";
	}

}

	
