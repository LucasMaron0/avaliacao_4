package av4.compass.microservice.associado.controller;

import java.net.URI;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.beans.factory.annotation.Autowired;
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

import av4.compass.microservice.associado.controller.dto.AssociadoDTO;
import av4.compass.microservice.associado.controller.form.AssociadoForm;
import av4.compass.microservice.associado.controller.form.CadastrarPartidoForm;
import av4.compass.microservice.associado.modelo.Associado;
import av4.compass.microservice.associado.modelo.CargoPolitico;
import av4.compass.microservice.associado.repository.AssociadoRepository;
import av4.compass.microservice.associado.service.AssociadoService;
import av4.compass.microservice.associado.validacao.ErroDeFormularioDto;

@RestController
@RequestMapping("/associados")
public class AssociadoController {
	
	@Autowired
	private AssociadoService aService;
	
	@Autowired
	private AssociadoRepository asRepo;
	
	@GetMapping
	@Cacheable(value = "listagemEstados")
	public Page<AssociadoDTO> listAll(
			@PageableDefault(sort="id", direction= Direction.ASC,page=0, size=10)Pageable paginacao){
		
		Page<Associado> associados = asRepo.findAll(paginacao);
		return AssociadoDTO.converter(associados);
		
	}
	
	@GetMapping("/cargo-{cargo}")
	public Page<AssociadoDTO> listPorCargo(
			@PathVariable String cargo, 
			@PageableDefault(sort="id", direction= Direction.ASC,page=0, size=10)Pageable paginacao){
		
		String stringUp = cargo.toUpperCase();
		CargoPolitico cargoEnum = CargoPolitico.valueOf(stringUp);
		
		Page<Associado> associados = asRepo.findByCargo(cargoEnum,paginacao);
		return AssociadoDTO.converter(associados);
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<AssociadoDTO> listById(@PathVariable long id){
		Optional<Associado> associado = asRepo.findById(id);
		if (associado.isPresent()) {
			return ResponseEntity.ok(new AssociadoDTO(associado.get()));
			
		}else {
			return ResponseEntity.notFound().build();
		}		
	}
	
	@PostMapping
	@Transactional
	@CacheEvict(value = "allAssociados", allEntries = true)
	public ResponseEntity<AssociadoDTO> cadastrar(@RequestBody @Valid AssociadoForm form, UriComponentsBuilder uriBuilder){
		Associado associado = form.converter();
		asRepo.save(associado);
		
		
		URI uri = uriBuilder.path("/assoc/{id}").buildAndExpand(associado.getId()).toUri();
		return ResponseEntity.created(uri).body(new AssociadoDTO(associado));
		
	}
	
	@PutMapping("/{id}")
	@Transactional
	@CacheEvict(value = "allAssociados", allEntries = true)
	public ResponseEntity<AssociadoDTO> atualizar(@PathVariable Long id, @RequestBody @Valid AssociadoForm form) {
		Optional<Associado> optional = asRepo.findById(id);
		if (optional.isPresent()) {
			Associado as = optional.get();
			Associado associadoAtualizado = form.atualizar(as);
			return ResponseEntity.ok(new AssociadoDTO(associadoAtualizado));
		}

		return ResponseEntity.notFound().build();
	}
	

	@DeleteMapping("/{id}")
	@Transactional
	@CacheEvict(value = "allAssociados", allEntries = true)
	public ResponseEntity<?> remover(@PathVariable Long id) {
		Optional<Associado> optional = asRepo.findById(id);
		if (optional.isPresent()) {
			asRepo.deleteById(id);
			return ResponseEntity.ok().build();
		}

		return ResponseEntity.notFound().build();
	}
	
	
	@PostMapping("/partidos")
	@Transactional
	@CacheEvict(value = "allAssociados", allEntries = true)
	public ResponseEntity<?> cadastrar(@RequestBody @Valid CadastrarPartidoForm form){
		
		String siglaPartido = aService.cadastrarAssociado(form);
		
		Optional<Associado> optional = asRepo.findById(form.getIdAssociado());
		
		if (optional.isPresent()) {
			if (optional.get().getPartido().equals("0")) {
				optional.get().setPartido(siglaPartido);
				return ResponseEntity.ok(new AssociadoDTO(optional.get()));
			}else {
				return ResponseEntity.badRequest().body(new ErroDeFormularioDto("Erro","Associado já pertence a outro partido"));
			}
		}
			return ResponseEntity.notFound().build();
		}
	
	@DeleteMapping("/{associadoId}/partidos/{partidoId}")
	@Transactional
	@CacheEvict(value = "allAssociados", allEntries = true)
	public ResponseEntity<?> removerPartido(@PathVariable("associadoId") Long aId, @PathVariable("partidoId") Long ptId){
			
		CadastrarPartidoForm form = new CadastrarPartidoForm();
		form.setIdAssociado(aId);
		form.setIdPartido(ptId);
		
		Optional<Associado> optional = asRepo.findById(aId);
		if (optional.isPresent()) {
			if(!optional.get().getPartido().equals("0")) {
				aService.descadastrarAssociado(form);
				optional.get().setPartido("0");
				return ResponseEntity.ok(new AssociadoDTO(optional.get()));
			}else {
				return ResponseEntity.badRequest().body(new ErroDeFormularioDto("Erro","Associado não pertence a nenhum partido"));
			}
			
			
		}
			return ResponseEntity.notFound().build();
			
	}
	
	}

