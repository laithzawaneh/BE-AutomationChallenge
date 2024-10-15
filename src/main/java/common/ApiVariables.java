package common;

import lombok.Getter;
import lombok.Setter;
import org.json.JSONArray;

@Setter
@Getter
public class ApiVariables {
    private boolean badRequest;
    private boolean inValidCheckInDate;
    private boolean emptyBody;
    private int cityId;
    private String checkInDate;
    private String checkOutDate;
    private int adultsCount;
    private JSONArray kidsAges;
    private int numberOfRooms;
}
