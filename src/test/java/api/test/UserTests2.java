package api.test;

import org.apache.logging.log4j.LogManager;

import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints;
import api.endpoints.UserEndPoints2;
import api.payload.User;
import io.restassured.response.Response;
public class UserTests2{
	
	Faker faker;
	User userPayload;
	public Logger logger;
	@BeforeClass
	public void setupData() {
		
		faker = new Faker();
		userPayload = new User();
		
		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setUsername(faker.name().username());
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		userPayload.setPassword(faker.internet().password(5,10));
		userPayload.setPhone(faker.phoneNumber().cellPhone());
		
		//logs
		logger = LogManager.getLogger(this.getClass());
		logger.debug("debugging.....");
		
		
	}
	
	@Test(priority = 1)
	public void testPostUser() {
		
		logger.info("*********** Create User ************");
		Response response = UserEndPoints2.createUser(userPayload);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("*********** User is Created ************");
	}
	
	@Test(priority = 2)
	public void testGetUser() {
		Response response = null;
		int attempts = 0;
		int maxAttempts = 3;

		while (attempts < maxAttempts) {
			logger.info("*********** Reading User Info ************");
			response = UserEndPoints2.readUser(this.userPayload.getUsername());
			if (response.getStatusCode() == 200) {
				break;
			}
			attempts++;
			try {
				Thread.sleep(1000); 
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		response.then().log().all();
		System.out.println("Username used for GET: " + userPayload.getUsername());
		Assert.assertEquals(response.getStatusCode(), 200, "User not found after multiple retries.");
		logger.info("*********** User Info is displayed ************");
	}
	
	@Test(priority = 3)
	public void testUpdateUser() {
		logger.info("*********** Updating User ************");
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		
		Response response = UserEndPoints2.updateUser(this.userPayload.getUsername(), userPayload);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("**********  User Updated  ************");
		//check after Update
		Response responseAfterUpdate = UserEndPoints.updateUser(this.userPayload.getUsername(), userPayload);
		responseAfterUpdate.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		
	}
	
	@Test(priority = 4)
	public void testDeleteUser() {
		
		logger.info("*********** Deleting User ************");
		Response response = UserEndPoints2.deleteUser(this.userPayload.getUsername());
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("*********** User Deleted ************");
		
	}
}