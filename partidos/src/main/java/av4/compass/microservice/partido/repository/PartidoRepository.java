package av4.compass.microservice.partido.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RequestParam;

import av4.compass.microservice.partido.modelo.Ideologia;
import av4.compass.microservice.partido.modelo.Partido;

public interface PartidoRepository  extends JpaRepository<Partido, Long> {

	Page<Partido> findByIdeologia(@RequestParam("ideologia") Ideologia ideologia, Pageable paginacao);
}
