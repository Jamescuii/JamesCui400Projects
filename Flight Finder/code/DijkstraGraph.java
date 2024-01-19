import java.util.*;

/**
 * This class extends the BaseGraph data structure with additional methods for
 * computing the total cost and list of node data along the shortest path
 * connecting a provided starting to ending nodes.  This class makes use of
 * Dijkstra's shortest path algorithm.
 */
public class DijkstraGraph<NodeType, EdgeType extends Number>
    extends BaseGraph<NodeType,EdgeType>
    implements GraphADT<NodeType, EdgeType> {

  /**
   * While searching for the shortest path between two nodes, a SearchNode
   * contains data about one specific path between the start node and another
   * node in the graph.  The final node in this path is stored in it's node
   * field.  The total cost of this path is stored in its cost field.  And the
   * predecessor SearchNode within this path is referened by the predecessor
   * field (this field is null within the SearchNode containing the starting
   * node in it's node field).
   *
   * SearchNodes are Comparable and are sorted by cost so that the lowest cost
   * SearchNode has the highest priority within a java.util.PriorityQueue.
   */
  protected class SearchNode implements Comparable<SearchNode> {
    public Node node;
    public double cost;
    public SearchNode predecessor;
    public SearchNode(Node node, double cost, SearchNode predecessor) {
      this.node = node;
      this.cost = cost;
      this.predecessor = predecessor;
    }
    public int compareTo(SearchNode other) {
      if( cost > other.cost ) return +1;
      if( cost < other.cost ) return -1;
      return 0;
    }
  }

  /**
   * This helper method creates a network of SearchNodes while computing the
   * shortest path between the provided start and end locations.  The
   * SearchNode that is returned by this method is represents the end of the
   * shortest path that is found: it's cost is the cost of that shortest path,
   * and the nodes linked together through predecessor references represent
   * all of the nodes along that shortest path (ordered from end to start).
   *
   * @param start the data item in the starting node for the path
   * @param end the data item in the destination node for the path
   * @return SearchNode for the final end node within the shortest path
   * @throws NoSuchElementException when no path from start to end is found
   *         or when either start or end data do not correspond to a graph node
   */
  protected SearchNode computeShortestPath(NodeType start, NodeType end) throws NoSuchElementException{
    if (!nodes.containsKey(start) || !nodes.containsKey(end)) // The nodes provided not in the graph
      throw new NoSuchElementException("Invalid Nodes");

    PriorityQueue<SearchNode> queue = new PriorityQueue<>();
    // Priority queue to keep track of the dijkstra process
    Hashtable<NodeType, SearchNode> visitedNodes = new Hashtable<>();
    // Hashtable object to store the visited nodes.
    Hashtable<NodeType, SearchNode> inQueueNodes = new Hashtable<>();
    // Hashtable object to store the nodes currently in the priority queue. Since the compareTo method
    // aims at the cost, so we need this additional storage to keep track of the nodes in queue with respect
    // to their node data.

    // Initialize the queue with start node.
    SearchNode startNode = new SearchNode(nodes.get(start), 0, null);
    queue.add(startNode);
    inQueueNodes.put(start, startNode);

    while (!queue.isEmpty()) {
      SearchNode currentNode = queue.remove();
      inQueueNodes.remove(currentNode.node.data); // No longer in queue.
      visitedNodes.put(currentNode.node.data, currentNode); // Visit the node with the highest priority each time
      for (Edge edge : currentNode.node.edgesLeaving) { // Traverse the neighbour nodes
        Node nodeToConsider = edge.successor; // neighbour node for consideration
        double newCost = currentNode.cost + edge.data.doubleValue(); // new potential cost for this neighbour
        if (!visitedNodes.containsKey(nodeToConsider.data) && !inQueueNodes.containsKey(nodeToConsider.data)) {
          // Case 1: The neighbour node is not in the queue and has not been visited. Should be added
          // to the priority queue.
          SearchNode newNode = new SearchNode(nodeToConsider, newCost, currentNode);
          queue.add(newNode);
          inQueueNodes.put(nodeToConsider.data, newNode);
        } else if (!visitedNodes.containsKey(nodeToConsider.data) && inQueueNodes.containsKey(nodeToConsider.data)){
          // Case 2: The neighbour node is in the queue and has not been visited. Need to update the cost
          // and predecessor when necessary
          SearchNode nodeNeedsComparison = inQueueNodes.get(nodeToConsider.data);
          if (nodeNeedsComparison.cost > newCost) {
            nodeNeedsComparison.cost = newCost;
            nodeNeedsComparison.predecessor = currentNode;
          }
        }
      }
    }

    if (!visitedNodes.containsKey(end)) // Non-existent path
      throw new NoSuchElementException("No path exist");
    return visitedNodes.get(end);
  }


  /**
   * Returns the list of data values from nodes along the shortest path
   * from the node with the provided start value through the node with the
   * provided end value.  This list of data values starts with the start
   * value, ends with the end value, and contains intermediary values in the
   * order they are encountered while traversing this shorteset path.  This
   * method uses Dijkstra's shortest path algorithm to find this solution.
   *
   * @param start the data item in the starting node for the path
   * @param end the data item in the destination node for the path
   * @return list of data item from node along this shortest path
   */
  public List<NodeType> shortestPathData(NodeType start, NodeType end) throws NoSuchElementException{
    SearchNode endPoint = computeShortestPath(start, end);
    List<NodeType> result = new ArrayList<>();
    while(endPoint != null) {
      result.add(0, endPoint.node.data); // add the predecessor nodes in reverse order.
      endPoint = endPoint.predecessor;
    }
    return result;
  }

  /**
   * Returns the cost of the path (sum over edge weights) of the shortest
   * path freom the node containing the start data to the node containing the
   * end data.  This method uses Dijkstra's shortest path algorithm to find
   * this solution.
   *
   * @param start the data item in the starting node for the path
   * @param end the data item in the destination node for the path
   * @return the cost of the shortest path between these nodes
   */
  public double shortestPathCost(NodeType start, NodeType end) throws NoSuchElementException{
    return computeShortestPath(start, end).cost;
  }
}
