package av4.compass.microservice.partido.repository;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;


import av4.compass.microservice.partido.modelo.Ideologia;
import av4.compass.microservice.partido.modelo.Partido;

@RunWith(SpringRunner.class)
@DataJpaTest
class PartidoRepositoryTest {
	
	@Autowired
	private PartidoRepository ptRepo;

	@Test
	void carregarPartidoPorIdeologia() {
		Pageable paginacao = PageRequest.of(0, 10, Sort.by(Direction.ASC, "id"));
		Ideologia ideo = Ideologia.CENTRO;
		Page<Partido> partidos = ptRepo.findByIdeologia(ideo, paginacao);
		Assert.isTrue(partidos.hasContent());
		
	}
	

}
