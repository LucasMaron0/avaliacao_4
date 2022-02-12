package av4.compass.microservice.partido.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import av4.compass.microservice.partido.controller.dto.RetornoRequestDTO;



@FeignClient("associados")
public interface AssociadosClient {
	
	@RequestMapping(method = RequestMethod.POST, value = "/service")
	List<RetornoRequestDTO>  buscarAssociados(List<Long> ids);

}
