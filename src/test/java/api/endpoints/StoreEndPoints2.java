package api.endpoints;

import static io.restassured.RestAssured.*;



import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.ResourceBundle;

import api.payload.Store;
import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class StoreEndPoints2 {
	
	static ResourceBundle getURL(){
		
		ResourceBundle routes = ResourceBundle.getBundle("routes");//load properties file
		return routes;
	}
	
	public static Response placeOrder(Store payload) {
		
		String store_post_url = getURL().getString("store_post_url");
		
		Response response = given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.body(payload)
		
		.when()
			.post(store_post_url);
		
		return response;
			
		
	}
	
	public static Response getOrderById(int orderId) {
		
		String store_get_url = getURL().getString("store_get_url");
		
		Response response = given()
			.pathParam("orderId", orderId)
		
		.when()
			.get(store_get_url);
		
		return response;
		
	}
	
	public static Response deleteOrder(int orderId) {
		
		String store_delete_url = getURL().getString("store_delete_url");
		Response response = given()
			.pathParam("orderId", orderId)
		
		.when()
			.delete(store_delete_url);
		
		return response;
	}
	
	
}
