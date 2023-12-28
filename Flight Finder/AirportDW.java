// --== CS400 Spring 2023 File Header Information ==--
// Name: Ziqi Shen
// Email: zshen266@wisc.edu
// Team: BC red
// TA: Naman Gupta
// Lecturer: Gary Dahl
// Notes to Grader: None

/**
 * Each instance of this class represents a different airport, and includes
 * accessor methods to retrieve the airport's IAIACode, city, Latitude and Longitude.
 * 
 * Latitude and Longitude are stored as floats, (0, 0) means special case (no location data)
 * 
 * @author Ziqi Shen
 *
 */
public class AirportDW implements AirportInterface {
    private String iataCode;
    private String city;
    private Double latitude;
    private Double longitude;

  
    /**
     * Instantiation of new airport objects requires the following data:
     * 
     * @param iataCode describing the airport's iataCode
     * @param city   describing the airport's city
     * @param latitude  describing the airport's latitude
     * @param longitude  describing the airport's longitude
     */
    public AirportDW(String iataCode, String city, double latitude, double longitude) {
        this.iataCode = iataCode;
        this.city = city;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    /**
     * Basic constructor for an Airport object with just the IATA code
     *
     * @param iataCode describing the airport's iataCode
     */
    public AirportDW(String iataCode) {
	this.iataCode = iataCode;
    }

    /**
     * Airport's accessor method for iataCode
     * @return iataCode in string
     */
    @Override
    public String getIATACode() {
        return iataCode;
    }

    /**
     * Airport's accessor method for city
     * @return city in string
     */
    @Override
    public String getCity() {
        return city;
    }

    /**
     * Airport's accessor method for latitude
     * Latitude and Longitude are stored as floats, (0, 0) means special case (no location data)
     * @return latitude in float
     */
    @Override
    public float getLatitude() {
        return latitude.floatValue();
    }

    /**
     * Airport's accessor method for longitude
     * Latitude and Longitude are stored as floats, (0, 0) means special case (no location data)
     * @return longitude in float
     */
    @Override
    public float getLongitude() {
        return longitude.floatValue();
    }

    
    /**
     * returns the airport, formatted as "iata (city : latitude, longitude)"
     * latitude and longitude should be clipped at three decimal places.
     * If latitude or longitude are missing, just say "iata (city)"
     * If city is missing: "iata (latitude, longitude)"
     * If all are missing: "iata"
     */
    @Override
    public String toString(){
	if ((this.latitude == null || this.longitude == null) && this.city != null)
		return iataCode + " (" + city + ")";
	if ((this.latitude == null || this.longitude == null) && this.city == null)
		return iataCode;
        return iataCode + " (" + city + " : " + latitude.toString() +", " + longitude.toString() +")" ;
    }

    /**
     * An airport is equal to an object if that object is an Airport and has the same exact IATA code, regardless of city.
     */
    @Override
    public boolean equals(Object obj){
        if(obj instanceof AirportInterface){
            AirportInterface testAirport = (AirportInterface) obj;
            if(testAirport.getIATACode().equals(this.iataCode)){
                return true;
            }
        }
        return false;
    }

    /**
     * An airport hashcode only determined by it's iata code.
     */
    @Override
    public int hashCode(){
        return 9832 + iataCode.hashCode(); // magic number to avoid hash collision with String IATA
    }
 }
