package av4.compass.microservice.associado.controller.form;


import java.time.LocalDate;


import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.springframework.beans.factory.annotation.Autowired;

import av4.compass.microservice.associado.modelo.Associado;
import av4.compass.microservice.associado.modelo.CargoPolitico;
import av4.compass.microservice.associado.modelo.Sexo;
import av4.compass.microservice.associado.repository.AssociadoRepository;

public class AssociadoForm {
	
	
	
	@NotNull@NotEmpty @Pattern(regexp="^[A-Za-z ]*$",message = "Digite um nome válido (apenas letras)")
	private String nome;
	
	@NotNull
	private CargoPolitico cargo;
	
	@NotNull
	private  LocalDate nascimento;
	@NotNull
	private Sexo sexo;
	

	public Associado converter() {
		return new Associado (nome, cargo, nascimento, sexo);
	
	}
	
	public Associado atualizar(Long id,  AssociadoRepository repo) {
		Associado associado = repo.getById(id);
		associado.setNome(this.nome);
		associado.setCargo(this.cargo);
		associado.setNascimento(this.nascimento);
		associado.setSexo(this.sexo);
	
		return associado;
		
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


	
	
	


}
