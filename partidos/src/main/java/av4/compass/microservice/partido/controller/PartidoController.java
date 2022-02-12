package av4.compass.microservice.partido.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;


import av4.compass.microservice.partido.controller.dto.PartidoDTO;
import av4.compass.microservice.partido.controller.dto.RetornoRequestDTO;
import av4.compass.microservice.partido.controller.form.PartidoForm;
import av4.compass.microservice.partido.modelo.Ideologia;
import av4.compass.microservice.partido.modelo.Partido;
import av4.compass.microservice.partido.repository.PartidoRepository;
import av4.compass.microservice.partido.service.PartidoService;



@RestController
@RequestMapping("/partidos")
public class PartidoController {
	
	
	@Autowired
	private PartidoRepository ptRepo;
	
	@Autowired
	private PartidoService ptService;
	
	
	@GetMapping
	@Cacheable(value = "listagemPartidos")
	public Page<PartidoDTO> listAll(
			@PageableDefault(sort="id", direction= Direction.ASC,page=0, size=10)Pageable paginacao){
		
		Page<Partido> associados = ptRepo.findAll(paginacao);
		return PartidoDTO.converter(associados);
		
	}
	
	@GetMapping("/ideologia-{ideologia}")
	public Page<PartidoDTO> listPorCargo(
			@PathVariable String ideologia, 
			@PageableDefault(sort="id", direction= Direction.ASC,page=0, size=10)Pageable paginacao){
		
		String stringUp = ideologia.toUpperCase();
		Ideologia ideoEnum = Ideologia.valueOf(stringUp);
		
		Page<Partido> partidos = ptRepo.findByIdeologia(ideoEnum,paginacao);
		return PartidoDTO.converter(partidos);
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<PartidoDTO> listById(@PathVariable long id){
		Optional<Partido> partido = ptRepo.findById(id);
		if (partido.isPresent()) {
			return ResponseEntity.ok(new PartidoDTO(partido.get()));	
		}else {
			return ResponseEntity.notFound().build();
		}
	}
	
	
	@PostMapping
	@Transactional
	@CacheEvict(value = "listagemPartidos", allEntries = true)
	public ResponseEntity<PartidoDTO> cadastrar(@RequestBody @Valid PartidoForm form, UriComponentsBuilder uriBuilder){
		Partido partido = form.converter();
		ptRepo.save(partido);
		
		
		URI uri = uriBuilder.path("/part/{id}").buildAndExpand(partido.getId()).toUri();
		return ResponseEntity.created(uri).body(new PartidoDTO(partido));
		
	}
	
	
	@PutMapping("/{id}")
	@Transactional
	@CacheEvict(value = "listagemPartidos", allEntries = true)
	public ResponseEntity<PartidoDTO> atualizar(@PathVariable Long id, @RequestBody @Valid PartidoForm form) {
		Optional<Partido> optional = ptRepo.findById(id);
		if (optional.isPresent()) {
			Partido partido = form.atualizar(id, ptRepo);
			return ResponseEntity.ok(new PartidoDTO(partido));
		}

		return ResponseEntity.notFound().build();
		}
	
	@DeleteMapping("/{id}")
	@Transactional
	@CacheEvict(value = "allAssociados", allEntries = true)
	public ResponseEntity<?> remover(@PathVariable Long id) {
		Optional<Partido> optional = ptRepo.findById(id);
		if (optional.isPresent()) {
			ptRepo.deleteById(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}
	
	@GetMapping("/{id}/associados")
	public ResponseEntity<List<RetornoRequestDTO>> listAssociados(@PathVariable long id){
		
		Optional<Partido> partido = ptRepo.findById(id);
		
		if (partido.isPresent()) {
			List<Long> ids = partido.get().getIdAssociados();
			List<RetornoRequestDTO> associados = ptService.receberAssociados(ids);
			System.out.println(associados.size());
			
			return ResponseEntity.ok(associados);	
			
		}else {
			return ResponseEntity.notFound().build();
		}
	}
	
}