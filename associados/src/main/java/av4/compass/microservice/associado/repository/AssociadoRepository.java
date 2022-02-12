package av4.compass.microservice.associado.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RequestParam;

import av4.compass.microservice.associado.modelo.Associado;
import av4.compass.microservice.associado.modelo.CargoPolitico;

public interface AssociadoRepository extends JpaRepository<Associado, Long>{
	
	Page<Associado> findByCargo(@RequestParam("cargo") CargoPolitico cargo, Pageable paginacao);

}
