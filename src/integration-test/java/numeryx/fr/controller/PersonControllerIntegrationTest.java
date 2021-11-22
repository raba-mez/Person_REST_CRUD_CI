package numeryx.fr.controller;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Objects;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;

import numeryx.fr.PersonRestCrudCiApplication;
import numeryx.fr.config.MysqlConfig;
import numeryx.fr.model.Person;
import numeryx.fr.service.PersonService;

@ExtendWith(SpringExtension.class)
@SpringBootTest(
		webEnvironment = WebEnvironment.RANDOM_PORT, 
		classes = {PersonRestCrudCiApplication.class, MysqlConfig.class}) //MysqlConfig Config another DataSource for tests purposes
@AutoConfigureMockMvc
@ActiveProfiles("integration-test")
@TestMethodOrder(OrderAnnotation.class)
@Tag("integration")
@WithMockUser(username = "admin", password = "admin", authorities = {"ROLE_ADMINSYSTEM"})
public class PersonControllerIntegrationTest {

	//@Autowired
	private MockMvc mvc;
	
	@Autowired
	private WebApplicationContext context;
	
	@Autowired
	private PersonService service;
	
	@BeforeEach
	public void setup() {
		
		mvc = MockMvcBuilders.webAppContextSetup(context)
				.apply(springSecurity())
				.build();
		
	}
	
	ObjectMapper mapper = new ObjectMapper();
	String resultContent = null;
	
	@Test
	@Order(1)
	public void createPersonTest() throws Exception {
		//Arrange
		Person p = new Person("Cheikh", "ANTA DIOP", "Historien", "000000010", "Senegal");
		String person = mapper.writeValueAsString(p);
		this.mvc.perform(post("/persons")
				.content(person)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andDo(print());
	}
	
	@Test
	@Order(2)
	public void deletePersonTest() throws Exception {
		//Act
		Person p = service.getByTel("000000010");
		if(Objects.nonNull(p))
			this.mvc.perform(delete("/persons/"+p.getIdPerson())
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andDo(print());
		
	}
}