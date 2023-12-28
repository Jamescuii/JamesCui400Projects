import java.util.List;
import java.util.ArrayList;

// --== CS400 Spring 2023 File Header Information ==--
// Name: Ziqi Shen
// Email: zshen266@wisc.edu
// Team: BC red
// TA: Naman Gupta
// Lecturer: Gary Dahl
// Notes to Grader: None

/**
 * This class represents route data, and includes methods to retrieve 
 * the route's data, check if airport containing, add and remove airport .
 * 
 * @author Ziqi Shen
 *
 */
public class RouteDW implements RouteInterface{
    
    private List<AirportInterface> airports;

    /**
     * Constructor of routeDW initialize airports
     */ 
    public RouteDW() {
        airports = new ArrayList<AirportInterface>();
    }
   
    /**
     * Constructor of routeDW with a given list of airports
     */
    public RouteDW(List<AirportInterface> airports) {
	this.airports = airports;
    }

    /**
     * Check if a specific airport is in the route
     * @param airport airport to check whether it exists in the route
     * @return 
     */
    @Override
    public boolean contains(AirportInterface airport) {
        return airports.contains(airport);
    }

    /**
     * return the total route List
     * @return
     */
    @Override
    public List<AirportInterface> getRoute() {
        return airports;
    }

    /**
     * Returns size of the route
     * @return
     */
    @Override
    public int size() {
        return airports.size();
    }

    /**
     * Add new airport to end of the route
     * @param airport
     */
    @Override
    public void add(AirportInterface airport) {
        airports.add(airport);
    }

    /**
     * Add new airport to specific index of the route
     * @param airport
     * @throws ArrayIndexOutOfBound
     */
    @Override
    public void add(AirportInterface airport, int index) {
        airports.add(index, airport);
    }

    /**
     * Remove airport from the route, if it is in the route
     * @param airport
     */
    @Override
    public void remove(AirportInterface airport) {
        airports.remove(airport);
    }
    
    /**
     * A route is formatted as the toString of each airport in order, seperated by a ' -> \n'
     */
    @Override
    public String toString(){
        String returnStr = new String();
        if (this.airports != null){
            for (int i = 0; i < airports.size(); i++){
		if ( i != airports.size()-1)
                	returnStr = returnStr.concat(airports.get(i).getIATACode() + " -> ");
		else
			returnStr = returnStr.concat(airports.get(i).getIATACode().toString());
            }
        }
        else
            return new String("Not initialized!");
        return returnStr;
    }
    
}
