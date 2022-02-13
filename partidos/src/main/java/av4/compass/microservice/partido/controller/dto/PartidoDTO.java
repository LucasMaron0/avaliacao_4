package av4.compass.microservice.partido.controller.dto;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;


import av4.compass.microservice.partido.modelo.Ideologia;
import av4.compass.microservice.partido.modelo.Partido;

public class PartidoDTO {
	
	
	private Long id;
	private String nome;
	private String sigla;
	private Ideologia ideologia;
	private String fundacao;
	private List<Long> idAssociados;
	
	public PartidoDTO(Partido partido) {
		this.id = partido.getId();
		this.nome= partido.getNome();
		this.sigla = partido.getSigla();
		this.ideologia= partido.getIdeologia();
		this.fundacao= converterData(partido.getFundacao());
		this.idAssociados = partido.getIdAssociados();
	}
	
	public static Page<PartidoDTO> converter(Page <Partido> partidos){
		return partidos.map(PartidoDTO::new);

	}
	
	public String converterData (LocalDate data) {
		int year = data.getYear();
		int month = data.getMonthValue();
		int day = data.getDayOfMonth();
		
		return (day+"/"+month+"/"+year);
		
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

	public String getFundacao() {
		return fundacao;
	}

	public void setFundacao(LocalDate fundacao) {
		this.fundacao = converterData(fundacao);
	}

	public List<Long> getIdAssociados() {
		return idAssociados;
	}

	public void setIdAssociados(List<Long> idAssociados) {
		this.idAssociados = idAssociados;
	}
	
	
	
}
