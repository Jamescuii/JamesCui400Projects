import java.util.List;

public interface RouteInterface {
    // private List<Airport> airports;

    /**
     * Default constructor. Empty airport list
     */
    // public Route();

    /**
     * Initialize Route with set Airports
     * @param Airports
     */
    // public Route(List<Airport> Airports);

    /**
     * Check if a specific airport is in the route
     * @param airport airport to check whether it exists in the route
     * @return
     */
    public boolean contains(AirportInterface airport);

    /**
     * A route is formatted as the toString of each airport in order, seperated by a ' -> \n'
     */
    @Override
    public String toString();

    /**
     * return the total route List
     * @return
     */
    public List<AirportInterface> getRoute();

    /**
     * Returns size of the route
     * @return
     */
    public int size();

    /**
     * Add new airport to end of the route
     * @param airport
     */
    public void add(AirportInterface airport);

    /**
     * Add new airport to specific index of the route
     * @param airport
     * @throws ArrayIndexOutOfBound
     */
    public void add(AirportInterface airport, int index);

    /**
     * Remove airport from the route, if it is in the route
     * @param airport
     */
    public void remove(AirportInterface airport);
}
