import java.util.List;


public interface TwoModePathFinder<NodeType, EdgeType extends Number>
    extends GraphADT<NodeType, EdgeType> {


  /**
   * return the quickest path with stop requested from BD by prioritizing the distance and breaking
   * the tie by the number of stops; i.e., call shortestPathData(start, stop) and
   * shortestPathData(stop, end) and combine them together
   *
   * @param start
   * @param stop  required layover
   * @param end
   * @return
   */
  public List<NodeType> shortestPathData(NodeType start, NodeType stop, NodeType end);


  /**
   * return the distance for the quickest path with stop requested from BD
   *
   * @param start
   * @param stop  required layover
   * @param end
   * @return
   */
  public double shortestPathCost(NodeType start, NodeType stop, NodeType end);


  /**
   * return the fastest path by prioritizing the number of stops and breaking the tie by distance
   *
   * @param start
   * @param end
   * @return
   */
  public List<NodeType> fastestPathData(NodeType start, NodeType end);


  /**
   * return the fastest path with stop requested from BD by prioritizing the number of stops and
   * breaking the tie by distance
   *
   * @param start
   * @param stop  required layover
   * @param end
   * @return
   */
  public List<NodeType> fastestPathData(NodeType start, NodeType stop, NodeType end);


  /**
   * return the number of stops for the fastest path with stop requested from BD
   *
   * @param start
   * @param end
   * @return
   */
  public int fastestPathCost(NodeType start, NodeType stop, NodeType end);


  /**
   * return the number of stops for the fastest path
   *
   * * @param start * @param end * @return
   */

  public int fastestPathCost(NodeType start, NodeType end);
}


