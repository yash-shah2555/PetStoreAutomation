package api.test;

import org.apache.logging.log4j.LogManager;

import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.StoreEndPoints;
import api.payload.Store;
import io.restassured.response.Response;

public class StoreTest {

    Faker faker;
    Store storePayload;
    public Logger logger;

    @BeforeTest
    public void setupData() {
        faker = new Faker();
        storePayload = new Store();

        storePayload.setId(faker.number().numberBetween(1000, 9999));
        storePayload.setPetId(faker.number().numberBetween(1, 100));
        storePayload.setQuantity(faker.number().numberBetween(1, 5));
        storePayload.setShipData("2025-07-08T13:24:31.621+0000"); // ✅ fixed method name
        storePayload.setStatus("placed");
        storePayload.setComplete(true);
        
      
      		logger = LogManager.getLogger(this.getClass());
      		logger.debug("debugging.....");
    }

    @Test(priority = 1)
    public void testPlaceOrder() {
    	logger.info("*********** Create Place Order ************");
        Response response = StoreEndPoints.placeOrder(storePayload);
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), 200);
        logger.info("*********** Order Display ************");
    }

    @Test(priority = 2)
    public void testOrderById() {
    	logger.info("*********** Read Order Info ************");
        Response response = StoreEndPoints.getOrderById(this.storePayload.getId());
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), 200);
        logger.info("*********** Order Info Display ************");
    }

    @Test(priority = 3)
    public void deleteOrderById() {
        Response response = null;
        int attempts = 0;
        int maxAttempts = 3;

        while (attempts < maxAttempts) {
        	logger.info("*********** Delete User ************");
            response = StoreEndPoints.deleteOrder(this.storePayload.getId());
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
        Assert.assertEquals(response.getStatusCode(), 200, "Order deletion failed after retries.");
        logger.info("***********  User Deleted ************");
    }
}
