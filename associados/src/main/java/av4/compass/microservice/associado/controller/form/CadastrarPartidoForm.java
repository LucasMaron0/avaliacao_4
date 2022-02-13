package av4.compass.microservice.associado.controller.form;

import javax.validation.constraints.NotNull;

public class CadastrarPartidoForm {
	
	@NotNull
	private Long idAssociado;
	@NotNull
	private long idPartido;
	
	
	
	public Long getIdAssociado() {
		return idAssociado;
	}
	public void setIdAssociado(Long idAssociado) {
		this.idAssociado = idAssociado;
	}
	public long getIdPartido() {
		return idPartido;
	}
	public void setIdPartido(long idPartido) {
		this.idPartido = idPartido;
	}
}
