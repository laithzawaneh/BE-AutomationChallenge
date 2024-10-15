package tests;

import enums.URLServices;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import utils.ApiUtils;

public class HotelsApiTest {

    private String endpoint;
    private String token;
    private Response response;

    @BeforeTest
    public void setup() {
        endpoint = URLServices.Get_Hotels_URL.getValue();
        token = URLServices.Token.getValue();
    }

    @Test(description = "Validates that successful status code 200.")
    public void testGetHotels() {
        // Call the Hotels API using GET
        response = ApiUtils.get(endpoint, token);

        // Validate the response
        Assert.assertEquals(response.statusCode(), 200, "Unexpected status code");
        Assert.assertNotNull(response.jsonPath().getJsonObject("chains"), "Chains object should not be null");
    }
}
