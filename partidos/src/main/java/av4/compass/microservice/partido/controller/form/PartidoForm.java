package av4.compass.microservice.partido.controller.form;

import java.time.LocalDate;
import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;


import av4.compass.microservice.partido.modelo.Ideologia;
import av4.compass.microservice.partido.modelo.Partido;
import av4.compass.microservice.partido.repository.PartidoRepository;

public class PartidoForm {
	
	@NotNull@NotEmpty @Pattern(regexp="^[A-Za-z ]*$",message = "Digite um nome válido (apenas letras)")
	private String nome;
	@NotNull@NotEmpty @Pattern(regexp="^[A-Z]*$",message = "Digite uma sigla válida (apenas letras maisculas e sem espaço)")
	private String sigla;
	@NotNull
	private Ideologia ideologia;
	@NotNull
	private LocalDate fundacao;
	
	
	
	
	public Partido converter () {
		return new Partido (nome, sigla, ideologia, fundacao);
	}

	
	public Partido atualizar(Partido partido) {
		
		partido.setNome(this.nome);
		partido.setSigla(this.sigla);
		partido.setIdeologia(this.ideologia);
		partido.setFundacao(this.fundacao);
		

		return partido;	
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

	
	
	
	
}
