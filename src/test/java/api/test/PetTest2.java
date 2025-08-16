package api.test;

import java.util.Arrays;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.PetEndPoints2;
import api.payload.Category;
import api.payload.Pet;
import api.payload.Tag;
import io.restassured.response.Response;

public class PetTest2 {

    Faker faker;
    Pet petPayload;
    Category category;
    Tag tag;

    public Logger logger = LogManager.getLogger(PetTest2.class); // ✅ Fixed

    @BeforeTest
    public void setupData() {
        faker = new Faker();

        // Set category
        category = new Category();
        category.setId(faker.number().numberBetween(1, 10));
        category.setName(faker.animal().name());

        // Set tag
        tag = new Tag();
        tag.setId(faker.number().numberBetween(100, 200));
        tag.setName("pet-tag-" + faker.number().randomDigit());

        // Set pet payload
        petPayload = new Pet();
        petPayload.setId(faker.number().numberBetween(1000, 9999));
        petPayload.setName(faker.dog().name());
        petPayload.setStatus("available");
        petPayload.setCategory(category);
        petPayload.setPhotoUrls(Arrays.asList("https://via.placeholder.com/150"));
        petPayload.setTags(Arrays.asList(tag));

        logger.info("Pet test data setup complete.");
    }

    @Test(priority = 1)
    public void testCreatePet() {
        logger.info("Starting pet creation test...");
        Response response = PetEndPoints2.createPet(petPayload);
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), 200);
        logger.info("Pet created successfully.");
    }

    @Test(priority = 2)
    public void testGetPetById() throws InterruptedException {
        Thread.sleep(2000);
        logger.info("Fetching pet by ID: " + petPayload.getId());
        Response response = PetEndPoints2.getPetById(this.petPayload.getId());
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), 200);
        logger.info("Pet retrieved successfully.");
    }

    @Test(priority = 3)
    public void updatePet() {
        petPayload.setStatus("sold");
        logger.info("Updating pet status to 'sold'.");
        Response response = PetEndPoints2.updatePet(petPayload);
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), 200);
        logger.info("Pet updated successfully.");
    }

    @Test(priority = 4)
    public void deletePet() {
        logger.info("Deleting pet with ID: " + petPayload.getId());
        Response response = PetEndPoints2.deletePet(this.petPayload.getId());
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), 200);
        logger.info("Pet deleted successfully.");
    }
}
