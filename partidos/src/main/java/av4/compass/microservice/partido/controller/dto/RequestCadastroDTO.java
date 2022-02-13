package av4.compass.microservice.partido.controller.dto;

public class RequestCadastroDTO {
	
	private long idAssociado;
	private long idPartido;
	
	
	public long getIdAssociado() {
		return idAssociado;
	}
	public void setIdAssociado(long idAssociado) {
		this.idAssociado = idAssociado;
	}
	public long getIdPartido() {
		return idPartido;
	}
	public void setIdPartido(long idPartido) {
		this.idPartido = idPartido;
	}
}
