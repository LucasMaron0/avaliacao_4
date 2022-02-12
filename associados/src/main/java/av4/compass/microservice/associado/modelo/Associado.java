package av4.compass.microservice.associado.modelo;


import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;



@Entity
public class Associado {
	
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String nome;
	
	@Enumerated(EnumType.STRING)
	private CargoPolitico cargo;
	
	private  LocalDate nascimento;
	
	@Enumerated(EnumType.STRING)
	private Sexo sexo;
	
	private String partido = "0";	
	
	
	public Associado () {
		
	}
	
	public Associado (String nome, CargoPolitico cargo, LocalDate nascimento, Sexo sexo) {
		this.nome= nome;
		this.cargo = cargo;
		this.nascimento = nascimento;
		this.sexo = sexo;	
	}
	
	

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public LocalDate getNascimento() {
		return nascimento;
	}

	public void setNascimento(LocalDate nascimento) {
		this.nascimento = nascimento;
	}

	public Sexo getSexo() {
		return sexo;
	}

	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}

	public String getPartido() {
		return partido;
	}

	public void setPartido(String partido) {
		this.partido = partido;
	}

	
	
}
