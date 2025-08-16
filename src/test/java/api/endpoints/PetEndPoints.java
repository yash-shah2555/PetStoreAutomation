package api.endpoints;

import static io.restassured.RestAssured.*;



import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import api.payload.Pet;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class PetEndPoints {
	
	public static Response createPet(Pet payload) {
		
		Response response = given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.body(payload)
		
		.when()
			.post(Routes.pet_post_url);
		
		return response;
		
	}
	
	public static Response getPetById(int petId) {
		
		Response response = given()
			.pathParam("petId", petId)
		
		.when()
			.get(Routes.pet_get_url);
		
		return response;
	}
	
	public static Response updatePet(Pet payload) {
		
		Response response = given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.body(payload)
		
		.when()
			.put(Routes.pet_put_url);
		
		return response;
	}
	
	public static Response deletePet(int petId) {
		
		Response response = given()
			.pathParam("petId", petId)
		
		.when()
			.delete(Routes.pet_delete_url);
		
		return response;
	}
}
