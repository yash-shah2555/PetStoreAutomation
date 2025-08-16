	package api.endpoints;
	
	import static io.restassured.RestAssured.*;
	
	
	
	import static io.restassured.matcher.RestAssuredMatchers.*;
	import static org.hamcrest.Matchers.*;
	
	import java.util.ResourceBundle;
	
	import api.payload.Pet;
	import io.restassured.http.ContentType;
	import io.restassured.response.Response;
	
	public class PetEndPoints2 {
		
			static ResourceBundle getURL(){
			
			ResourceBundle routes = ResourceBundle.getBundle("routes");//load properties file
			return routes;
		}
		
		public static Response createPet(Pet payload) {
			
			String pet_post_url = getURL().getString("pet_post_url");
			
			Response response = given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.body(payload)
			
			.when()
				.post(pet_post_url);
			
			return response;
			
		}
		
		public static Response getPetById(int petId) {
			
			String pet_get_url = getURL().getString("pet_get_url");
			Response response = given()
				.pathParam("petId", petId)
			
			.when()
				.get(pet_get_url);
			
			return response;
		}
		
		public static Response updatePet(Pet payload) {
			
			String pet_put_url = getURL().getString("pet_update_url");
			Response response = given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.body(payload)
			
			.when()
				.put(pet_put_url);
			
			return response;
		}
		
		public static Response deletePet(int petId) {
			String pet_delete_url = getURL().getString("pet_delete_url");
			Response response = given()
				.pathParam("petId", petId)
			
			.when()
				.delete(pet_delete_url);
			
			return response;
		}
	}
