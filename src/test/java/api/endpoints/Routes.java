package api.endpoints;

public class Routes {
	
	public static String base_url = "https://petstore.swagger.io/v2";
	
	//USER MODULE
	
	public static String post_url = base_url+"/user";
	public static String get_url = base_url+"/user/{username}";
	public static String update_url = base_url+"/user/{username}";
	public static String delete_url = base_url+"/user/{username}";
	
	//STORE MODULE
	
	public static String store_post_url = base_url +"/store/order";
	public static String store_get_url = base_url +"/store/order/{orderId}";
	public static String store_delete_url = base_url+"/store/order/{orderId}";
	
	//PET MODULE
	
	public static String pet_post_url = base_url +"/pet";
	public static String pet_get_url = base_url +"/pet/{petId}";
	public static String pet_put_url = base_url+"/pet";
	public static String pet_delete_url = base_url+"/pet/{petId}";
}
