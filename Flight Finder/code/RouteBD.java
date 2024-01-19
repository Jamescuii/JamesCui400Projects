import java.util.List;

/**
 * BD placeholder class for Route
 */
public class RouteBD implements RouteInterface{

  private List<AirportInterface> airports;

  public static boolean RouteConstructed; // Boolean flag object to test if the method are correctly called

  public RouteBD(List<AirportInterface> airports) {
    RouteConstructed = true;

  }

  @Override
  public boolean contains(AirportInterface airport) {
    return false;
  }

  @Override
  public List<AirportInterface> getRoute() {
    return null;
  }

  @Override
  public void add(AirportInterface airport) {

  }

  @Override
  public void add(AirportInterface airport, int index) {

  }

  @Override
  public void remove(AirportInterface airport) {

  }
  
  @Override
  public int size() {
    return 0;
  }
}

