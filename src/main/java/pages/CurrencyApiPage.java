package pages;

import io.restassured.response.Response;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

public class CurrencyApiPage {

    public Map<String, Object> getBaseCurrency(Response response) {
        return response.jsonPath().getMap("base");
    }

    public List<Map<String, Object>> getEquivalentCurrencies(Response response) {
        return response.jsonPath().getList("equivalent");
    }

    public Map<String, Object> getCurrencyByCode(Response response, String code) {
        List<Map<String, Object>> currencies = getEquivalentCurrencies(response);
        for (Map<String, Object> currency : currencies) {
            if (currency.get("code").equals(code)) {
                return currency;
            }
        }
        return null;
    }

    public double getCurrencyRate(Map<String, Object> currency) {
        Object rateObj = currency.get("rate");
        if (rateObj instanceof Number) {
            return ((Number) rateObj).doubleValue();
        }
        return 0.0;
    }

//    public boolean validateCurrencyRate(Response response, String code, double expectedRate) {
//        Map<String, Object> currency = getCurrencyByCode(response, code);
//        if (currency != null) {
//            double rate = getCurrencyRate(currency);
//            return rate == expectedRate;
//        }
//        return false;
//    }

    public boolean isValidDateFormat(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sdf.setLenient(false);
        try {
            sdf.parse(date);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    public boolean validateAllCurrenciesDates(Response response) {
        List<Map<String, Object>> currencies = getEquivalentCurrencies(response);
        for (Map<String, Object> currency : currencies) {
            if (!isValidDateFormat((String) currency.get("date"))) {
                return false;
            }
        }
        return true;
    }
}
