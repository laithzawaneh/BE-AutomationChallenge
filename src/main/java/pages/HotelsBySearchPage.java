package pages;

import common.ApiVariables;
import org.json.JSONArray;
import org.json.JSONObject;

import java.time.LocalDate;
import java.util.Random;

public class HotelsBySearchPage {


    public void fillApiVariables(ApiVariables apiVariables, int numberOfKids) {

        LocalDate checkInDate = LocalDate.now().plusDays(2);
        LocalDate checkOutDate = LocalDate.now().plusWeeks(2);
        Random random = new Random();

        apiVariables.setCityId(76);
        if (apiVariables.isInValidCheckInDate()) {
            apiVariables.setCheckInDate("InvalidDate");
        }else{
            apiVariables.setCheckInDate(checkInDate.toString());
        }
        apiVariables.setCheckOutDate(checkOutDate.toString());
        apiVariables.setAdultsCount(random.nextInt(8) + 1);

        JSONArray kidsAges = new JSONArray();
        for (int i = 0; i < numberOfKids; i++) {
            kidsAges.put(random.nextInt(6) + 1);
        }

        apiVariables.setKidsAges(kidsAges);
    }

    public String generateValidRequestDynamicBody(ApiVariables apiVariables) {

        LocalDate checkInDate = LocalDate.now().plusDays(2);
        LocalDate checkOutDate = LocalDate.now().plusWeeks(2);

        JSONObject requestBody = new JSONObject();
        requestBody.put("cityId", apiVariables.getCityId());
        requestBody.put("checkIn", checkInDate.toString());
        requestBody.put("checkOut", checkOutDate.toString());

        JSONObject roomInfo = new JSONObject();
        roomInfo.put("adultsCount", apiVariables.getAdultsCount());
        roomInfo.put("kidsAges", apiVariables.getKidsAges());

        JSONArray roomsArray = new JSONArray();
        roomsArray.put(roomInfo);

        requestBody.put("roomsInfo", roomsArray);

        return requestBody.toString();
    }

    public String generateInValidBody(ApiVariables apiVariables) {
        if (apiVariables.isEmptyBody()) {
            return "";
        }

        JSONObject requestBody = new JSONObject();
        requestBody.put("cityId", apiVariables.getCityId());

        if (apiVariables.isInValidCheckInDate()) {
            requestBody.put("checkIn", apiVariables.getCheckInDate());
        } else {
            requestBody.put("checkIn", apiVariables.getCheckInDate());
        }

        requestBody.put("checkOut", apiVariables.getCheckOutDate());

        JSONObject roomInfo = new JSONObject();
        roomInfo.put("adultsCount", apiVariables.getAdultsCount());
        roomInfo.put("kidsAges", apiVariables.getKidsAges());

        JSONArray roomsArray = new JSONArray();
        roomsArray.put(roomInfo);

        requestBody.put("roomsInfo", roomsArray);

        return requestBody.toString();
    }

    // for testing call
    public String generateValidRequestBody() {
        String body = "{\"cityId\":76,\"checkIn\":\"2024-10-14\",\"checkOut\":\"2024-10-15\",\"roomsInfo\":[{\"adultsCount\":1,\"kidsAges\":[]}]}";
        return body;
    }
}