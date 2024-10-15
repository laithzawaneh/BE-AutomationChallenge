package tests;

import common.ApiVariables;
import enums.URLServices;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.HotelsBySearchPage;
import utils.ApiUtils;


@Listeners(reports.TestReport.class)
public class HotelsBySearchTest {

    private String endpoint;
    private String token;
    private Response response;
    HotelsBySearchPage hotelsBySearchPage;
    ApiVariables apiVariables;

    @BeforeTest
    public void setup() {
        hotelsBySearchPage = new HotelsBySearchPage();
        apiVariables = new ApiVariables();
        endpoint = URLServices.Post_Hotels_By_Search_URL.getValue();
        token = URLServices.Token.getValue();
    }

    @Test(description = "Validates that successful status code 200.")
    public void testValidStatusCode() {
        // fill Api Variables
        hotelsBySearchPage.fillApiVariables(apiVariables, 2);

        // Call API using POST
        response = ApiUtils.post(endpoint, hotelsBySearchPage.generateValidRequestDynamicBody(apiVariables), token);

        // Validate the response
        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertNotNull(response.jsonPath().getJsonObject("carousels"));
    }

    @Test(description = "Validates that pad request status code 400.")
    public void testInvalidCheckInDate() {
        apiVariables.setInValidCheckInDate(true);

        hotelsBySearchPage.fillApiVariables(apiVariables,2);

        // Call using POST
        response = ApiUtils.post(endpoint, hotelsBySearchPage.generateInValidBody(apiVariables), token);

        // Validate the response
        Assert.assertEquals(response.statusCode(), 400);
        Assert.assertNull(response.jsonPath().getJsonObject("carousels"));
    }

    @Test(description = "Validates that an invalid token returns a 401 status code.")
    public void testInvalidToken() {
        response = ApiUtils.post(endpoint, hotelsBySearchPage.generateValidRequestBody(), "InvalidToken");
        Assert.assertEquals(response.statusCode(), 401);
    }

    @Test(description = "Validates that an empty body returns a 400 status code.")
    public void testEmptyBody() {
        apiVariables.setEmptyBody(true);
        response = ApiUtils.post(endpoint, hotelsBySearchPage.generateInValidBody(apiVariables), token);
        Assert.assertEquals(response.statusCode(), 400);
    }

}