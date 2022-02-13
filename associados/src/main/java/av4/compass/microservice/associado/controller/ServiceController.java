package av4.compass.microservice.associado.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import av4.compass.microservice.associado.controller.dto.AssociadoDTO;
import av4.compass.microservice.associado.modelo.Associado;
import av4.compass.microservice.associado.service.AssociadoService;




@RestController
@RequestMapping("/service")
public class ServiceController {
	
	@Autowired
	private AssociadoService aService;
	
	
	@RequestMapping(method = RequestMethod.POST)
	public List<AssociadoDTO>  cadastrarIdAssociado(@RequestBody List<Long> ids) {		
		return aService.buscarAssociados(ids);
	}
	
	
	

}
