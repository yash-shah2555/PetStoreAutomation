package api.endpoints;

import static io.restassured.RestAssured.*;



import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import api.payload.Store;
import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class StoreEndPoints {
	
	public static Response placeOrder(Store payload) {
		
		Response response = given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.body(payload)
		
		.when()
			.post(Routes.store_post_url);
		
		return response;
			
		
	}
	
	public static Response getOrderById(int orderId) {
		
		Response response = given()
			.pathParam("orderId", orderId)
		
		.when()
			.get(Routes.store_get_url);
		
		return response;
		
	}
	
	public static Response deleteOrder(int orderId) {
		
		Response response = given()
			.pathParam("orderId", orderId)
		
		.when()
			.delete(Routes.store_delete_url);
		
		return response;
	}
	
	
	
	
}
