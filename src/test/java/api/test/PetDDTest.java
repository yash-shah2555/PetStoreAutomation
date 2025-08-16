package api.test;

import java.util.Arrays;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.endpoints.PetEndPoints;
import api.payload.Pet;
import api.utilities.DataProviders;
import io.restassured.response.Response;

public class PetDDTest {
	
	@Test(priority = 1,dataProvider="PetData", dataProviderClass = DataProviders.class)
	public void testCreatePetDD(String id, String name,String photoUrl,String status) {
		
		Pet petPayload = new Pet();
		
		petPayload.setId(Integer.parseInt(id));
		petPayload.setName(name);
		petPayload.setPhotoUrls(Arrays.asList(photoUrl));
		petPayload.setStatus(status);
		
		Response response = PetEndPoints.createPet(petPayload);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		
	}
}
