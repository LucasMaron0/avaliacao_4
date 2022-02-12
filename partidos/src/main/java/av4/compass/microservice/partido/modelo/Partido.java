package av4.compass.microservice.partido.modelo;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Partido {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String sigla;
	@Enumerated(EnumType.STRING)
	private Ideologia ideologia;
	private LocalDate fundacao;
	@ElementCollection
	private List<Long> idAssociados;

	
	public Partido () {
		
	}
	
	
	public Partido(String nome, String sigla, Ideologia ideologia, LocalDate fundacao, List<Long> associados) {
		
		this.nome = nome;
		this.sigla = sigla;
		this.ideologia = ideologia;
		this.fundacao = fundacao;
		this.idAssociados = associados;
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


	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getSigla() {
		return sigla;
	}


	public void setSigla(String sigla) {
		this.sigla = sigla;
	}


	public Ideologia getIdeologia() {
		return ideologia;
	}


	public void setIdeologia(Ideologia ideologia) {
		this.ideologia = ideologia;
	}


	public LocalDate getFundacao() {
		return fundacao;
	}


	public void setFundacao(LocalDate fundacao) {
		this.fundacao = fundacao;
	}


	public List<Long> getIdAssociados() {
		return idAssociados;
	}


	public void setIdAssociados(List<Long> idAssociados) {
		this.idAssociados = idAssociados;
	}



	


}
