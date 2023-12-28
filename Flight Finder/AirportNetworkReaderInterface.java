import java.io.FileNotFoundException;

public interface AirportNetworkReaderInterface { 

    // public AirportNetworkReaderInterface();
    /**
     * Loads Graph data from a .DOT file, and only a .DOT file, and attempts to load it in to a Graph
     * @param filename
     * @return An initialized graph with all of the nodes and edges loaded
     * @throws FileNotFoundException if the files does not exist
     * @throws IllegalArgumentException if the data is malformed
     */
    public TwoModePathFinderInterface<AirportInterface, Double> readGraphFromFile(String filename) throws FileNotFoundException;
 }
 
