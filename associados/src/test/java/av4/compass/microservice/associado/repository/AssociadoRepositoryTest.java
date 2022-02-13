package av4.compass.microservice.associado.repository;




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

import av4.compass.microservice.associado.modelo.Associado;
import av4.compass.microservice.associado.modelo.CargoPolitico;

@RunWith(SpringRunner.class)
@DataJpaTest
public class AssociadoRepositoryTest {

	@Autowired
	private AssociadoRepository aRepo;
	
	@Test
	void carregarAssociadoPeloCargo() {
		Pageable paginacao = PageRequest.of(0, 10, Sort.by(Direction.ASC, "id"));
		CargoPolitico cargo = CargoPolitico.PRESIDENTE;
		Page<Associado> associado = aRepo.findByCargo(cargo, paginacao);
		Assert.isTrue(associado.hasContent());
		
	}
	
	@Test
	void deveriaFalharAoPassarCargoSemEntradasNaBd() {
		Pageable paginacao = PageRequest.of(0, 10, Sort.by(Direction.ASC, "id"));
		CargoPolitico cargo = CargoPolitico.NENHUM;
		Page<Associado> associado = aRepo.findByCargo(cargo, paginacao);
		Assert.isTrue(associado.hasContent());
		
	}

}
