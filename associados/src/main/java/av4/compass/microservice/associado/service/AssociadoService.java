package av4.compass.microservice.associado.service;



import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import av4.compass.microservice.associado.client.PartidosClient;
import av4.compass.microservice.associado.controller.dto.AssociadoDTO;
import av4.compass.microservice.associado.controller.dto.RetornoRequestDTO;
import av4.compass.microservice.associado.controller.form.CadastrarPartidoForm;
import av4.compass.microservice.associado.modelo.Associado;
import av4.compass.microservice.associado.repository.AssociadoRepository;


@Service
public class AssociadoService {
	
	@Autowired
	private PartidosClient ptClient;
	
	@Autowired
	private AssociadoRepository aRepo;
	

	public String cadastrarAssociado (CadastrarPartidoForm form) {
					
		RetornoRequestDTO retorno = ptClient.cadastrar(form);
		return retorno.getSigla();
	}
	
	public void descadastrarAssociado (CadastrarPartidoForm form) {
		ptClient.descadastrar(form);
	}
	
	

	public List<AssociadoDTO> buscarAssociados(List<Long> ids) {
		List<AssociadoDTO> associados = new ArrayList<>();
		
		for (Long id: ids) {		
			Optional<Associado> associado = aRepo.findById(id);	
			if (associado.isPresent()) {
				associados.add(new AssociadoDTO(associado.get()));
			}
		}
		System.out.println(associados.size());
		
		return associados;
	}
	
	

	
}
