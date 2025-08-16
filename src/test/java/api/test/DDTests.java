package api.test;

import org.testng.Assert;


import org.testng.annotations.Test;

import api.endpoints.UserEndPoints;
import api.payload.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;

public class DDTests {

    @Test(dataProvider = "Data", dataProviderClass = DataProviders.class)
    public void testCreateAndDeleteUser(String userID, String userName, String fname, String lname,
                                        String useremail, String pwd, String ph) throws InterruptedException {
        
        User userPayload = new User();

        try {
            userPayload.setId(Integer.parseInt(userID));
        } catch (NumberFormatException e) {
            Assert.fail("Invalid ID format for user: " + userName + " | ID: " + userID);
        }

        userPayload.setUsername(userName);
        userPayload.setFirstName(fname);
        userPayload.setLastName(lname);
        userPayload.setEmail(useremail);
        userPayload.setPassword(pwd);
        userPayload.setPhone(ph);

  
        System.out.println("===== Creating user: " + userName + " =====");
        Response createResp = UserEndPoints.createUser(userPayload);
        System.out.println("Create Status Code: " + createResp.getStatusCode());
        System.out.println("Create Response Body: " + createResp.getBody().asString());

        if (createResp.getStatusCode() != 200) {
            Assert.fail(" User creation failed for: " + userName);
        }


        Thread.sleep(1000);

        System.out.println("===== Deleting user: " + userName + " =====");
        Response deleteResp = UserEndPoints.deleteUser(userName);
        System.out.println("Delete Status Code: " + deleteResp.getStatusCode());
        System.out.println("Delete Response Body: " + deleteResp.getBody().asString());

        Assert.assertEquals(deleteResp.getStatusCode(), 200, "User deletion failed for: " + userName);
    }
}
