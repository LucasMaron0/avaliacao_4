package av4.compass.microservice.partido.service;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import av4.compass.microservice.partido.client.AssociadosClient;
import av4.compass.microservice.partido.controller.dto.CadastroAssociadoDTO;
import av4.compass.microservice.partido.controller.dto.PartidoDTO;
import av4.compass.microservice.partido.controller.dto.RetornoRequestDTO;
import av4.compass.microservice.partido.controller.form.PartidoForm;
import av4.compass.microservice.partido.modelo.Partido;
import av4.compass.microservice.partido.repository.PartidoRepository;

@Service
public class PartidoService {

	@Autowired
	private PartidoRepository ptRepo;

	@Autowired
	private AssociadosClient aClient;

	public List<RetornoRequestDTO>  receberAssociados(List<Long> ids) {
		return aClient.buscarAssociados(ids);
	}

	public ResponseEntity<PartidoDTO> cadastrarAssociadoPartido( CadastroAssociadoDTO dto) {		

		Optional<Partido> partido = ptRepo.findById(dto.getIdPartido());
		if (partido.isPresent()) {

			List<Long> list = partido.get().getIdAssociados();
			list.add(dto.getIdAssociado());
			partido.get().setIdAssociados(list);

			ptRepo.save(partido.get());


			return ResponseEntity.ok(new PartidoDTO(partido.get()));	
		}else {
			return ResponseEntity.notFound().build();
		}		
	}

	public void descadastrarAssociado (CadastroAssociadoDTO dto) {

		Optional<Partido> partido = ptRepo.findById(dto.getIdPartido());

		if (partido.isPresent()) {

			List<Long> list = partido.get().getIdAssociados();
			list.remove(dto.getIdAssociado());
			partido.get().setIdAssociados(list);

			ptRepo.save(partido.get());


		}
	}


}

