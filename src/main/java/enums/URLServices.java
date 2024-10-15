package enums;

public enum URLServices {

    Get_Hotels_URL("https://www.almosafer.com/api/enigma/hotel/lookup"),
    Post_Hotels_By_Search_URL("https://www.almosafer.com/api/enigma/carousel"),
    Get_Currency_List_URL("https://www.almosafer.com/api/system/currency/list"),
    Token("skdjfh73273$7268u2j89s");
    private final String url;

    URLServices(String url) {
        this.url = url; // Store the URL
    }

    public String getValue() {
        return this.url; // Return the stored URL
    }
}
