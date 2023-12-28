// --== CS400 File Header Information ==--
// Name: James Cui
// Email: jcui57@wisc.edu
// Group and Team: BCred
// Group TA: Naman Gupta
// Lecturer: Gary Dahl
// Notes to Grader: <optional extra notes>

import java.io.FileNotFoundException;
import java.security.InvalidAlgorithmParameterException;

/**
 * A placeholder BackendDeveloperFD class that returns 
 * hardcoded values to see if the class FrontendDeveloper functions
 * properly.
 */
public class BackendDeveloperFD implements BackendInterface {

    /**
     * Initialize Backend with no graph
     */
    public BackendDeveloperFD() {

    }

    public String findQuickestPath(String originCode, String destinationCode)
            throws IllegalArgumentException, InvalidAlgorithmParameterException {
        return "BOS -> ORD";
    }

    public String findQuickestPath(String originCode, String layoverCode, String destinationCode)
            throws IllegalArgumentException, InvalidAlgorithmParameterException {

        return "BOS -> ORD -> BOS";
    }

    public String findFastestPath(String originCode, String destinationCode)
            throws IllegalArgumentException, InvalidAlgorithmParameterException {
        
        return "HII -> BYE";
    }

    public String findFastestPath(String originCode, String layoverCode, String destinationCode)
            throws IllegalArgumentException, InvalidAlgorithmParameterException {
        
        return "HII -> BYE -> HII";
    }

    /**
    * Checks if airport code exists in our graph, and that its three letters,
    * capitalize it if it isn't capitalized
    * 
    * @param code
    * @return
    */
    public boolean isValidAirport(String code) {
        return false;
    }

    /**
    * Loads data from file into instance graph variable
    * 
    * @param graphDataPath
    * @throws FileNotFoundException
    * @throws IllegalArgumentException
    */
    public void loadFile(String graphDataPath) throws FileNotFoundException, IllegalArgumentException {
        return;
    }

    /**
    * @return Node Count of Graph
    */
    public int getNodeCount() {
        return 0;
    }

    /**
    * @return Edge Count of Graph
    */
    public int getEdgeCount() {
        return 0;
    }
}
