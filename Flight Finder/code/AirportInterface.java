public interface AirportInterface {

    // private String iataCode;
    // private String city;
    // private double latitude;
    // private double longitude;

    /**
     * Creates a New Airport Object from the three letter IATA code (eg. STL, MSN, MKE, LAX, ORD). The IATA code
     * should always be capitalized, so capitalize it if it isn't already.
     * 
     * @throws IllegalArgumentException if the code is not exactly three letters
     */
    // public Airport(String iataCode) {

    // }

    /**
     * Creates a New Airport Object from the three letter IATA code and corresponding city. The IATA code
     * should always be capitalized, so capitalize it if it isn't already.
     * 
     * @throws IllegalArgumentException if the code is not exactly three letters
     */
    // public Airport(String iataCode, String city) {

    // }

    public String getIATACode();

    public String getCity();

    public float getLatitude();

    public float getLongitude();

    /**
     * returns the airport, formatted as "iata (city : latitude, longitude)"
     * latitude and longitude should be clipped at three decimal places.
     * If latitude or longitude are missing, just say "iata (city)"
     * If city is missing: "iata (latitude, longitude)"
     * If all are missing: "iata"
     */
    @Override
    public String toString();

    /**
     * An airport is equal to an object if that object is an Airport and has the same exact IATA code, regardless of city.
     */
    @Override
    public boolean equals(Object obj);
}
