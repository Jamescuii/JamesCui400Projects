import java.io.FileNotFoundException;
import java.security.InvalidAlgorithmParameterException;

public class runFlightFinder {
       public static void main (String[] args) throws FileNotFoundException, InvalidAlgorithmParameterException {
	       BackendInterface backend = new BackendDeveloper(new AirportNetworkReaderDW());
	       backend.loadFile("p3huge.dot");
	       System.out.println(backend.getEdgeCount());
	       System.out.println(backend.findFastestPath("BOS", "STL", "FLG").toString());
       }
}       
