package br.com.projeto.integrationtest.swagger;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import br.com.projeto.configs.TestConfigs;
import br.com.projeto.integrationtest.testcontainers.AbstractIntegrationTest;


@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
public class SwaggerIntegrationTest extends AbstractIntegrationTest{

	@Test
	public void shouldDisplaySwaggerUiPage() {
		var content = given()
		  .basePath("/swagger-ui/index.html")
		  .port(TestConfigs.SERVER_PORT)
		  .when()
		  .get()
		  .then()
		  .statusCode(200)
		  .extract()
		  .body()
		  .asString();
			
		assertTrue(content.contains("Swagger UI"));
	}
}
