import java.io.FileNotFoundException;
import java.security.InvalidAlgorithmParameterException;
import java.util.List;
import java.util.NoSuchElementException;


/**
 * Backend class for the route organizer application.
 * Majorly is responsible for connect the DW+AE and FD.
 */
public class BackendDeveloper implements BackendInterface{

  private TwoModePathFinderInterface<AirportInterface, Double> graph; // Parameter for AE's path finder
  private AirportNetworkReaderInterface reader; // Parameter for DW's reader
  /**
   * Default Constructor for Backend
   * @param reader DW's reader application
   */
  public BackendDeveloper(AirportNetworkReaderInterface reader) {
    this.reader = reader;
  }

  /**
   * Constructor for Backend, with file path to load in graph data.
   * @param reader DW's reader application
   * @param graphDataPath file path with graph data
   * @throws FileNotFoundException Invalid file path
   */
  public BackendDeveloper(AirportNetworkReaderInterface reader, String graphDataPath) throws FileNotFoundException {
    this.reader = reader;
    this.loadFile(graphDataPath);
  }

  /**
   * Returns the quickest path between two airport. Specific details are in AE's corresponding method
   * @param originCode IATA code for origin airport
   * @param destinationCode IATA code for destination airport
   * @return A route object with the list of airports traversed
   * @throws IllegalArgumentException IATA code for the airports are not valid
   * @throws InvalidAlgorithmParameterException The route between two airport does not exist
   */
  @Override
  public RouteInterface findQuickestPath(String originCode, String destinationCode)
      throws IllegalArgumentException, InvalidAlgorithmParameterException {
    if (!this.isValidAirport(originCode) || !this.isValidAirport(destinationCode)) // Airports not valid
      throw new IllegalArgumentException();
    try {
      // Construct airports object and call AE corresponding route finder
      List<AirportInterface> result =
          graph.shortestPathData(new AirportDW(originCode.toUpperCase()), new AirportDW(destinationCode.toUpperCase()));
      return new RouteDW(result);
    } catch (NoSuchElementException e) { // AE throws exception, meaning no such route exist
      throw new InvalidAlgorithmParameterException();
    }
  }

  /**
   * Returns the quickest path between two airport with a required layover.
   * Specific details are in AE's corresponding method
   * @param originCode IATA code for origin airport
   * @param destinationCode IATA code for destination airport
   * @return A route object with the list of airports traversed
   * @throws IllegalArgumentException IATA code for the airports are not valid
   * @throws InvalidAlgorithmParameterException The route between two airport does not exist
   */
  @Override
  public RouteInterface findQuickestPath(String originCode, String layoverCode,
      String destinationCode) throws IllegalArgumentException, InvalidAlgorithmParameterException {
    if (!this.isValidAirport(originCode) || !this.isValidAirport(layoverCode) || !this.isValidAirport(destinationCode))
      // Airports not valid
      throw new IllegalArgumentException();
    try {
      // Construct airports object and call AE corresponding route finder
      List<AirportInterface> result =
          graph.shortestPathData(new AirportDW(originCode.toUpperCase()), new AirportDW(layoverCode.toUpperCase()),
              new AirportDW(destinationCode.toUpperCase()));
      return new RouteDW(result);
    } catch (NoSuchElementException e) { // AE throws exception, meaning no such route exist
      throw new InvalidAlgorithmParameterException();
    }
  }

  /**
   * Returns the fastest path between two airport. Specific details are in AE's corresponding method
   * @param originCode IATA code for origin airport
   * @param destinationCode IATA code for destination airport
   * @return A route object with the list of airports traversed
   * @throws IllegalArgumentException IATA code for the airports are not valid
   * @throws InvalidAlgorithmParameterException The route between two airport does not exist
   */
  @Override
  public RouteInterface findFastestPath(String originCode, String destinationCode)
      throws IllegalArgumentException, InvalidAlgorithmParameterException {
    if (!this.isValidAirport(originCode) || !this.isValidAirport(destinationCode)) // Airports not valid
      throw new IllegalArgumentException();
    try {
      // Construct airports object and call AE corresponding route finder
      List<AirportInterface> result =
          graph.fastestPathData(new AirportDW(originCode.toUpperCase()), new AirportDW(destinationCode.toUpperCase()));
      return new RouteDW(result);
    } catch (NoSuchElementException e) { // AE throws exception, meaning no such route exist
      throw new InvalidAlgorithmParameterException();
    }
  }

  /**
   * Returns the fastest path between two airport with a required layover.
   * Specific details are in AE's corresponding method
   * @param originCode IATA code for origin airport
   * @param destinationCode IATA code for destination airport
   * @return A route object with the list of airports traversed
   * @throws IllegalArgumentException IATA code for the airports are not valid
   * @throws InvalidAlgorithmParameterException The route between two airport does not exist
   */
  @Override
  public RouteInterface findFastestPath(String originCode, String layoverCode,
      String destinationCode) throws IllegalArgumentException, InvalidAlgorithmParameterException {
    if (!this.isValidAirport(originCode) || !this.isValidAirport(layoverCode) || !this.isValidAirport(destinationCode))
      // Airports not valid
      throw new IllegalArgumentException();
    try {
      // Construct airports object and call AE corresponding route finder
      List<AirportInterface> result =
          graph.fastestPathData(new AirportDW(originCode.toUpperCase()), new AirportDW(layoverCode.toUpperCase()), new AirportDW(destinationCode.toUpperCase()));
      return new RouteDW(result);
    } catch (NoSuchElementException e) { // AE throws exception, meaning no such route exist
      throw new InvalidAlgorithmParameterException();
    }
  }

  /**
   * Test if the given IATA code is valid.
   * @param code IATA code given for testing
   * @return True if it is a valid code, false otherwise.
   */
  @Override
  public boolean isValidAirport(String code) {
    code = code.toUpperCase();
    AirportInterface airport = new AirportDW(code); // Construct an airport object for AE's contain method
    return graph.containsNode(airport);
  }

  /**
   * Load the graph data from the given DOT file path.
   * @param graphDataPath file path given for the graph data
   * @throws FileNotFoundException Invalid file path
   * @throws IllegalArgumentException Malformed data file
   */
  @Override
  public void loadFile(String graphDataPath) throws FileNotFoundException, IllegalArgumentException {
    // Downcast the GraphADT object to TwoModePathFinder
    this.graph = reader.readGraphFromFile(graphDataPath);
  }

  /**
   * Get the total node count of the graph
   * @return node count of the graph
   */
  @Override
  public int getNodeCount() {
    return graph.getNodeCount();
  }

  /**
   * Get the total edge count of the graph
   * @return edge count of the graph
   */
  @Override
  public int getEdgeCount() {
    return graph.getEdgeCount();
  }
}
