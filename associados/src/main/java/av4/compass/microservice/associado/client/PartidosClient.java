package av4.compass.microservice.associado.client;

import java.util.List;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import av4.compass.microservice.associado.controller.dto.AssociadoDTO;
import av4.compass.microservice.associado.controller.dto.RetornoRequestDTO;
import av4.compass.microservice.associado.controller.form.CadastrarPartidoForm;



@FeignClient("partidos")
public interface PartidosClient {
	
	@RequestMapping(method = RequestMethod.POST, value = "/service/cadastrar")
	@CacheEvict(value = "allAssociados", allEntries = true)	
	RetornoRequestDTO  cadastrar(CadastrarPartidoForm form);
		
	@RequestMapping(method = RequestMethod.POST, value = "/service/descadastrar")
	@CacheEvict(value = "allAssociados", allEntries = true)	
	void descadastrar(CadastrarPartidoForm form);

	
}
