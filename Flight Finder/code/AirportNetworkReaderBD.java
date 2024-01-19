import java.io.FileNotFoundException;

/**
 * BD placeholder for AirportNetworkReader
 */
public class AirportNetworkReaderBD implements AirportNetworkReaderInterface{

  public static boolean ReaderCalled; // Boolean flag object to test if the method are correctly called
  @Override
  public TwoModePathFinderInterface<AirportInterface, Double> readGraphFromFile(String filename)
      throws FileNotFoundException {
    ReaderCalled = true;
    return new TwoModePathFinderBD<>();
  }
}

