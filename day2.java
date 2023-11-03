package day1;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class day2 {
	@Test        

	public void ListUsers() {
		RestAssured.baseURI = "https://reqres.in/api";

		Response response = given().param("page", 2).when().get("/users").then().extract().response();
		int id = response.jsonPath().getInt("data[0].id");

		Response res = given().pathParam("userId", id).when().get("users/{userId}");
		res.then().statusCode(200);
		res.then().assertThat().body("data.id", equalTo(7)).body("data.email", equalTo("michael.lawson@reqres.in"))
				.body("data.first_name", equalTo("Michael")).body("data.last_name", equalTo("Lawson"))
				.body("data.avatar", equalTo("https://reqres.in/img/faces/9-image.jpg"));
		response.then().log().all();

	}

}
