package week4.day1.restassured;

import java.util.List;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GetAllIncidentAssertJsonValueFromList {
	
	@Test
	public void getAllIncidentAssert() {
		
		RestAssured.baseURI="https://dev142770.service-now.com/api/now/table/incident";
		RestAssured.authentication=RestAssured.basic("admin", "Cz*tn1U0vS!V");
		
		RequestSpecification inputRequest = RestAssured.given();
		Response response = inputRequest.get()
				.then().assertThat().body("result.number", Matchers.hasItem("INC0000053")).extract().response();
		List<String> sysidList = response.jsonPath().getList("result.sys_id");
		
		for (String eachitem : sysidList) {
			System.out.println(eachitem);
		}
//		response.prettyPrint();
	}
	
	
	
	


}
