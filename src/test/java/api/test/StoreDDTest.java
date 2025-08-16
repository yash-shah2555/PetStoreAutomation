package api.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.endpoints.StoreEndPoints;
import api.payload.Store;
import api.utilities.DataProviders;
import io.restassured.response.Response;
public class StoreDDTest {
	
	@Test(dataProvider = "StoreData",dataProviderClass = DataProviders.class)
	public void testPlaceOrderDDTestData(String id,String petId,String quantity,String shipData,String status,String complete) {
		
		Store store = new Store();
		
		store.setId(Integer.parseInt(id));
		store.setPetId(Integer.parseInt(petId));
		store.setQuantity(Integer.parseInt(quantity));
		store.setShipData(shipData);
		store.setStatus(status);
		store.setComplete(Boolean.parseBoolean(complete));
		
		Response response = StoreEndPoints.placeOrder(store);
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
	}
}
