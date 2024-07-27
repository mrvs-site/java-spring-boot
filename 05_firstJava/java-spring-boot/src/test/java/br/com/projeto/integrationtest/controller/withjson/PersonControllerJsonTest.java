package br.com.projeto.integrationtest.controller.withjson;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.projeto.configs.TestConfigs;
import br.com.projeto.data.vo.v1.security.AccountCredentialsVO;
import br.com.projeto.data.vo.v1.security.TokenVO;
import br.com.projeto.integrationtest.testcontainers.AbstractIntegrationTest;
import br.com.projeto.integrationtest.vo.PersonVO;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;


@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
@TestMethodOrder(OrderAnnotation.class)
public class PersonControllerJsonTest extends AbstractIntegrationTest{
	
	private static RequestSpecification specification;
	private static ObjectMapper objectMapper;
	
	private static PersonVO person;
	
	@BeforeAll
	public static void setup() {
		objectMapper = new ObjectMapper();
		objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		
		person = new PersonVO();
	}

	@Test
	@Order(0)
	public void authorization() throws JsonMappingException, JsonProcessingException {
		
		AccountCredentialsVO user = new AccountCredentialsVO("mr", "admin123");
		
		var accessToken = given()
				  .basePath("/auth/signin")
				  .port(TestConfigs.SERVER_PORT)
				  .contentType(TestConfigs.CONTENT_TYPE_JSON)
				  .body(user) 
				  .when()
				  	.post()
				  .then()
				  	.statusCode(200)
				  .extract()
				  	.body()
				  .as(TokenVO.class)
				  	.getAccessToken();
		
		
		specification = new RequestSpecBuilder()
				.addHeader(TestConfigs.HEADER_PARAM_AUTHORIZATION, "Bearer "+accessToken)
				.setBasePath("/api/person")
				.setPort(TestConfigs.SERVER_PORT)
				.addFilter(new RequestLoggingFilter(LogDetail.ALL))
				.addFilter(new ResponseLoggingFilter(LogDetail.ALL))
				.build();
		
	}
	
	
	@Test
	@Order(1)
	public void testCreate() throws JsonMappingException, JsonProcessingException {

	mockPerson();
	
	System.out.println("--+-++-+-"+person.getFirstName());
	
	var content = given(specification)
	  .contentType(TestConfigs.CONTENT_TYPE_JSON)
	  	.header(TestConfigs.HEADER_PARAM_ORIGIN, "http://localhost:8080")
	    .body(person) 
	  .when()
	  	.post()
	  .then()
	  	.statusCode(200)
	  .extract()
	  	.body()
	  .asString();
	
	PersonVO createdPerson = objectMapper.readValue(content, PersonVO.class);
	person = createdPerson;
	
	assertNotNull(createdPerson);
	assertNotNull(createdPerson.getId());
	assertNotNull(createdPerson.getFirstName());
	assertNotNull(createdPerson.getLastName());
	
	assertEquals("Antonio",createdPerson.getFirstName());
	assertEquals("Silva",createdPerson.getLastName());
	assertEquals("Recife-PE",createdPerson.getEndereco());
	assertEquals("M",createdPerson.getGender());
	
	
	assertTrue(createdPerson.getId() > 0);
	}

	private void mockPerson() {
		person.setFirstName("Antonio");
		person.setLastName("Silva");
		person.setEndereco("Recife-PE");
		person.setGender("M");
		
	}
}
