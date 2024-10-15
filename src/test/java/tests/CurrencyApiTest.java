package tests;

import enums.URLServices;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.CurrencyApiPage;
import utils.ApiUtils;

import java.util.Map;

@Listeners(reports.TestReport.class)
public class CurrencyApiTest {

    private String endpoint;
    private Response response;
    private CurrencyApiPage currencyApiPage;

    @BeforeTest
    public void setup() {
        endpoint = URLServices.Get_Currency_List_URL.getValue(); // Currency List API
        currencyApiPage = new CurrencyApiPage();
    }

    @Test(description = "Validates that successful status code 200.")
    public void testGetCurrencies() {
        response = ApiUtils.get(endpoint);

        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertNotNull(currencyApiPage.getBaseCurrency(response), "Base currency is null");
        Assert.assertNotNull(currencyApiPage.getEquivalentCurrencies(response), "Equivalent currencies list is null");
    }

    @Test(description = "Validate the exchange rate for AED")
    public void testCurrencyRateForAED() {
        Response response = ApiUtils.get(endpoint);
        CurrencyApiPage currencyApiPage = new CurrencyApiPage();

        Map<String, Object> aedCurrency = currencyApiPage.getCurrencyByCode(response, "AED");
        double actualRate = currencyApiPage.getCurrencyRate(aedCurrency);
        double expectedRate = 0.97869; // Update this value as needed

        // Round actualRate to 5 decimal places
        double roundedActualRate = Math.round(actualRate * 100000.0) / 100000.0;

        Assert.assertEquals(roundedActualRate, expectedRate, "Rate for AED currency is incorrect!");
    }

    @Test(description = "Validates the date format for all currencies.")
    public void testValidateCurrencyDateFormat() {
        response = ApiUtils.get(endpoint);

        boolean datesValid = currencyApiPage.validateAllCurrenciesDates(response);
        Assert.assertTrue(datesValid, "One or more currencies have an invalid date format!");
    }
}
