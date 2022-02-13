package av4.compass.microservice.partido.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import av4.compass.microservice.partido.controller.dto.RequestCadastroDTO;
import av4.compass.microservice.partido.controller.dto.PartidoDTO;

import av4.compass.microservice.partido.service.PartidoService;

@RestController
@RequestMapping("/service")
public class ServiceController {
	
	
	@Autowired
	private PartidoService ptService;

	
	
	@RequestMapping(method = RequestMethod.POST, value = "/cadastrar")
	@CacheEvict(value = "listagemPartidos", allEntries = true)	
	public ResponseEntity<PartidoDTO> cadastrarIdAssociado(@RequestBody RequestCadastroDTO dto) {		
		return ptService.cadastrarAssociadoPartido(dto);
	}
	
	
	@RequestMapping(method = RequestMethod.POST, value = "/descadastrar")
	@CacheEvict(value = "listagemPartidos", allEntries = true)
	public void descadastrarIdAssociado(@RequestBody RequestCadastroDTO dto) {	
		 ptService.descadastrarAssociado(dto);
	}
	
	

}
