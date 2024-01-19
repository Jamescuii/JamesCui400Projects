import java.io.FileNotFoundException;
import java.security.InvalidAlgorithmParameterException;


public interface BackendInterfac {


  //private TwoModePathFinder graph;


  /**
   * Initialize Graph from file
   */
  // public Backend(String graphDataPath) {


  // }


  /**
   * Initialize Backend with no graph
   */
  // public Backend() {


  // }


  // Methods that run Dijkstra's algorithm on our graph, throwing an error if any
  // of the codes given are not valid nodes.
  // An airport object just needs an IATA code, all else is optional


  // If a path is impossible between two nodes, then an
  // InvalidAlgorithmParameterException is thrown. Theoretically, this should
  // never happens, but it's always good to be prepared.


  // For the methods with three parameters, there's a required layover, meaning
  // that we return shortest path between three nodes, starting as origin, going
  // to layover, then to destination.
  public String findQuickestPath(String originCode, String destinationCode)
      throws IllegalArgumentException, InvalidAlgorithmParameterException;


  public String findQuickestPath(String originCode, String layoverCode,
      String destinationCode) throws IllegalArgumentException, InvalidAlgorithmParameterException;


  public String findFastestPath(String originCode, String destinationCode)
      throws IllegalArgumentException, InvalidAlgorithmParameterException;


  public String findFastestPath(String originCode, String layoverCode,
      String destinationCode) throws IllegalArgumentException, InvalidAlgorithmParameterException;


  /**
   * Checks if airport code exists in our graph, and that its three letters, capitalize it if it
   * isn't capitalized
   *
   * @param code
   * @return
   */
  public boolean isValidAirport(String code);


  /**
   * Loads data from file into instance graph variable
   *
   * @param graphDataPath
   * @throws FileNotFoundException
   * @throws IllegalArgumentException
   */
  public void loadFile(String graphDataPath) throws FileNotFoundException, IllegalArgumentException;


  /**
   * @return Node Count of Graph
   */
  public int getNodeCount();


  /**
   * @return Edge Count of Graph
   */
  public int getEdgeCount();
}
