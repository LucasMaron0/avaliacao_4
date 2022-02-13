package av4.compass.microservice.associado.controller.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.data.domain.Page;

import av4.compass.microservice.associado.modelo.Associado;
import av4.compass.microservice.associado.modelo.CargoPolitico;
import av4.compass.microservice.associado.modelo.Sexo;

public class AssociadoDTO {

	private Long id;
	private String nome;
	private CargoPolitico cargo;
	private  String nascimento;
	private Sexo sexo;
	private String partido;
	

	public AssociadoDTO (Associado associado) {
		this.id = associado.getId();
		this.nome = associado.getNome();
		this.cargo=associado.getCargo();
		this.nascimento = converterData(associado.getNascimento());
		this.sexo = associado.getSexo();
		this.partido = associado.getPartido();
	}


	public static Page<AssociadoDTO> converter(Page <Associado> associados){
		return associados.map(AssociadoDTO::new);

	}
	
	public String converterData (LocalDate data) {
		int year = data.getYear();
		int month = data.getMonthValue();
		int day = data.getDayOfMonth();
		
		return (day+"/"+month+"/"+year);
		
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public CargoPolitico getCargo() {
		return cargo;
	}


	public void setCargo(CargoPolitico cargo) {
		this.cargo = cargo;
	}


	public String getNascimento() {
		return nascimento;
	}


	public void setNascimento(LocalDate nascimento) {
		this.nascimento = converterData(nascimento);
	}


	public Sexo getSexo() {
		return sexo;
	}


	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}




	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getPartido() {
		return partido;
	}


	public void setPartido(String partido) {
		this.partido = partido;
	}

	




}
