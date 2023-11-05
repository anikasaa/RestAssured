package day1;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Random;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class day4 {

	String token = "Bearer cd2ac6be51d3e141feb492b0cc0ddd8d3eb8d0bc7da80d1a1ef57ecdd74bf517";

	protected String getSaltString() {
		String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		StringBuilder salt = new StringBuilder();
		Random rnd = new Random();
		while (salt.length() < 10) { // length of the random string.
			int index = (int) (rnd.nextFloat() * SALTCHARS.length());
			salt.append(SALTCHARS.charAt(index));
		}
		String saltStr = salt.toString();
		return saltStr;

	}

	@Test
	public void createUser() throws FileNotFoundException {

		// using Hashmap
		String email = getSaltString() + "@gmail.com";
//		HashMap<String, String> payload = new HashMap<>();
//		// Add key-value pairs to the payload
//		payload.put("name", "Tenali Ramakrishna");
//		payload.put("gender", "male");
//		payload.put("email", email);
//		payload.put("status", "active");

		// creating a payload using org.json
//		JSONObject jsonPayload = new JSONObject();
//		jsonPayload.put("name", "John Doe");
//		jsonPayload.put("email", email);
//		jsonPayload.put("gender", "male");
//		jsonPayload.put("status", "active");
//		// Convert the JSON object to a string
//		String payload = jsonPayload.toString();

		// Using pojo class

		// Students payload = new Students("Anisha Tuladhar",email,"female","active");

		// using external json
		// Create a FileReader to read the JSON file
		FileReader fileReader = new FileReader(
				"C:\\Users\\anish\\eclipse-workspace\\restAssured2\\src\\test\\java\\day1\\obj.json");
		// Create a JSONTokener to parse the JSON data
		JSONTokener tokener = new JSONTokener(fileReader);
		// Create a JSONObject from the parsed data
		JSONObject jsonpayload = new JSONObject(tokener);
		String payload = jsonpayload.toString();

		RestAssured.baseURI = "https://gorest.co.in/public/v2/users";
		Response response = RestAssured.given().header("Accept", "application/json")
				.header("Content-Type", "application/json").header("Authorization", token).body(payload).when().post();

		response.then().log().all();
		response.then().statusCode(201);

	}

}
