/**
 * BD placeholder class for Airport
 */
public class AirportBD implements AirportInterface {

  private String iataCode;
  private String city;
  private float latitude;
  private float longitude;
  public static boolean airportConstructed; // Boolean flag object to test if the method are correctly called

  public AirportBD (String iataCode) {
    airportConstructed = true;
    this.iataCode = iataCode;
  }

  @Override
  public String getIATACode() {
    return null;
  }

  @Override
  public String getCity() {
    return null;
  }

  @Override
  public float getLatitude() {
    return 0;
  }

  @Override
  public float getLongitude() {
    return 0;
  }
}

