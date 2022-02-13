package av4.compass.microservice.associado.controller;



import java.net.URI;



import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(OrderAnnotation.class)
public class AssociadoControllerTest {

	@Autowired
	private  MockMvc mock;
	
	
	

	@Test
	@Order(1)
	void getAllDeveriaRetornar200() throws Exception {
		URI uri = new URI("/associados");

		mock.perform(MockMvcRequestBuilders.get(uri))
		.andExpect(MockMvcResultMatchers.status().is(200));
	}

	@Test
	@Order(2)
	void getByIdDeveriaRetornar200SeIdExiste() throws Exception {
		URI uri = new URI("/associados/1");

		mock.perform(MockMvcRequestBuilders.get(uri))
		.andExpect(MockMvcResultMatchers.status().is(200));
	}

	@Test
	@Order(3)
	void PostDeveriaRetornar201SeCriado() throws Exception {
		URI uri = new URI("/associados");
		String json = "{"
				+ "  \"cargo\": \"VEREADOR\","
				+ "  \"nascimento\": \"2021-01-01\","
				+ "  \"nome\": \"nome exemplo\","
				+ "  \"sexo\": \"FEMININO\""
				+ "}";

		mock.perform(MockMvcRequestBuilders.post(uri)
				.content(json).contentType(MediaType.APPLICATION_JSON))

		.andExpect(MockMvcResultMatchers.status().is(201));
	}
	
	@Test
	@Order(4)
	void PutDeveriaRetornar200SeAtualizado() throws Exception {
		URI uri = new URI("/associados/1");
		String json = "{"
				+ "  \"cargo\": \"VEREADOR\","
				+ "  \"nascimento\": \"2021-01-01\","
				+ "  \"nome\": \"nome exemplo\","
				+ "  \"sexo\": \"FEMININO\""
				+ "}";

		mock.perform(MockMvcRequestBuilders.put(uri)
				.content(json).contentType(MediaType.APPLICATION_JSON))

		.andExpect(MockMvcResultMatchers.status().is(200));
	}
	
	
	@Test
	@Order(5)
	void  DeleteDeveriaRetornar200SeDeletado() throws Exception {
		URI uri = new URI("/associados/1");		

		mock.perform(MockMvcRequestBuilders.delete(uri))
		.andExpect(MockMvcResultMatchers.status().is(200));
	}
	
	
	
}
