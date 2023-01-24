package week4.day1.restassured;

import java.io.File;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class UpdateIncidentExtractDescriptionFromJsonCompare {
	@Test
	public void updateIncident() {
		RestAssured.baseURI="https://dev142770.service-now.com/api/now/table/incident";
		RestAssured.authentication=RestAssured.basic("admin", "Cz*tn1U0vS!V");
		
		File inputFile =new File("./src/main/resources/UpdateIncident.json");
		RequestSpecification inputRequest = RestAssured.given();
		inputRequest.contentType("application/json").when().body(inputFile);
		
		Response response = inputRequest.put("1c741bd70b2322007518478d83673af3");
		
		String shortDescription = response.jsonPath().get("result.short_description");
		
		response.then().assertThat().body("result.short_description", Matchers.equalTo(shortDescription)).extract().response();
		System.out.println(shortDescription);
		response.prettyPrint();
		
		
		
	}
	

}
