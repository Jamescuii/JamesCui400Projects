// --== CS400 File Header Information ==--
// Name: Cornelia (Zhouzhou) Chu
// Email: zchu26@wisc.edu
// Group and Team: BC Red
// Group TA: Naman Gupta
// Lecturer: Gary Dahl
// Notes to Grader: <optional extra notes>
import java.util.*;

/**
 * This class is the path finder for quickest or fastest path between airports
 */
public class TwoModePathFinderAE<NodeType, EdgeType extends Number>
    extends DijkstraGraph<NodeType,EdgeType>
        implements TwoModePathFinderInterface<NodeType, EdgeType> {
    /**
     * While searching for the shortest path between two nodes, a SearchNode
     * contains data about one specific path between the start node and another
     * node in the graph.  The final node in this path is stored in its node
     * field.  The total cost of this path is stored in its cost field.  And the
     * predecessor SearchNode within this path is referenced by the predecessor
     * field (this field is null within the SearchNode containing the starting
     * node in its node field).
     *
     * SearchNodes are Comparable and are sorted by cost so that the lowest cost
     * SearchNode has the highest priority within a java.util.PriorityQueue.
     */
    @SuppressWarnings("unchecked")
    protected class SearchNode extends DijkstraGraph.SearchNode {
        public Node node;
        public double cost;
        public double breaktie;
        public SearchNode predecessor;
        public SearchNode(Node node, double cost, double breaktie, SearchNode predecessor) {
            super(node, cost, predecessor);
            this.node = node;
            this.cost = cost;
            this.breaktie = breaktie;
            this.predecessor = predecessor;
        }
        @Override
        public int compareTo(DijkstraGraph.SearchNode other) {
            if ( cost > other.cost ) return +1;
            if ( cost < other.cost ) return -1;
            if (cost == other.cost && breaktie > ((SearchNode)other).breaktie) return +1;
            if (cost == other.cost && breaktie < ((SearchNode)other).breaktie) return -1;
            return 0;
        }
    }

    /**
     * This helper method creates a network of SearchNodes while computing the
     * shortest path between the provided start and end locations.  The
     * SearchNode that is returned by this method represents the end of the
     * shortest path that is found: its cost is the cost of that shortest path,
     * and the nodes linked together through predecessor references represent
     * all of the nodes along that shortest path (ordered from end to start).
     * Shortest path prioritizes the distance and breaks tie by the number of stops;
     * cost is distance and breaktie is the number of stops
     *
     * @param start the data item in the starting node for the path
     * @param end   the data item in the destination node for the path
     * @return SearchNode for the final end node within the shortest path
     * @throws NoSuchElementException when no path from start to end is found
     *                                or when either start or end data do not correspond to a graph node
     */
    @SuppressWarnings("unchecked")
    protected SearchNode computeShortestPath(NodeType start, NodeType end) throws NoSuchElementException {
        // TODO: implement in step 6
        // if either start or end data do not correspond to a graph node
        if (nodes.get(start) == null || nodes.get(end) == null) {
            throw new NoSuchElementException("either start or end data do not correspond to a graph node");
        }
        // if start == end with no explicit edge: distance - 0; number of stops: 1
        if (start.equals(end)) {
            return new SearchNode(nodes.get(start), 0, 1,null);
        }
        // keep track of what to be added to visited hashtable next
        PriorityQueue<SearchNode> nodesToVisit = new PriorityQueue<>();
        // keep track of what nodes are visited: tail is the terminal node
        Hashtable<NodeType, Node> visited = new Hashtable<>();
        // initialize the SearchNode: update it every time we add a node into visited
        // distance - 0; number of stops - 1
        SearchNode startNode = new SearchNode(nodes.get(start), 0, 1,null);
        // initialize by adding startNode into visited and its successor nodes to nodesToVisited
        visited.put(start, nodes.get(start));
        for (Edge edge : startNode.node.edgesLeaving) {
            // always update the number of stops (breaktie) by 1 + predecessor's breaktie
            double numberOfStops = startNode.breaktie;
            nodesToVisit.add(new SearchNode(edge.successor,
                    edge.data.doubleValue(), ++numberOfStops, startNode));
        }
        // loop until endNode is visited
        while (!nodesToVisit.isEmpty()) {
            // within nodesToVisit, find the node with min cost from its predecessor
            // find the successor node with min cost
            SearchNode sucNode = nodesToVisit.poll();
            // check if this node is in visited; if it is, continue to next iteration
            if (!visited.containsKey(sucNode.node.data)) {
                // if this node is not visited
                // add this successor node into visited
                visited.put(sucNode.node.data, nodes.get(sucNode.node.data));
                // the cost of current path is the cost of the SearchNode I just added into visited
                // if the node just added into visited is the end node we are reaching
                if (sucNode.node.data != null && sucNode.node.data.equals(end)) {
                    // start to end path is found
                    // return sucNode
                    return sucNode;
                }
                // add this successor's successors into PQ
                for (Edge edge : sucNode.node.edgesLeaving) {
                    Node sucSuc = edge.successor;
                    if (!visited.containsKey(edge.successor.data)) {
                        // if node is not visited
                        // add each node into PQ
                        // the cost in SearchNode = cost of pred SearchNode + edge cost
                        // always update the number of stops (breaktie) by 1 + predecessor's breaktie
                        double numberOfStops = sucNode.breaktie;
                        nodesToVisit.add(new SearchNode(sucSuc, sucNode.cost +
                            edge.data.doubleValue(), ++numberOfStops, sucNode));
                    }
                }
            }
        }
        // if PQ is empty and end node is not in visited, then no path from start to end
        if (visited.get(end) == null) {
            throw new NoSuchElementException("no path from start to end is found");
        }
        return null;
    }

    /**
     * Returns the list of data values from nodes along the shortest (quickest) path
     * from the node with the provided start value through the node with the
     * provided end value by prioritizing the distance and breaking tie
     * by the number of stops.
     * This list of data values starts with the start
     * value, ends with the end value, and contains intermediary values in the
     * order they are encountered while traversing this shorteset path.  This
     * method uses Dijkstra's shortest path algorithm to find this solution.
     * Shortest path prioritizes the distance and breaks tie by the number of stops;
     * cost is distance and breaktie is the number of stops
     * @param start the data item in the starting node for the path
     * @param end the data item in the destination node for the path
     * @return list of data item from node along this shortest path
     */
    @Override
    public List<NodeType> shortestPathData(NodeType start, NodeType end) {
        SearchNode curNode = computeShortestPath(start, end);
        double breaktie = curNode.breaktie;
        LinkedList<NodeType> dataList = new LinkedList<>();
        while (curNode != null) {
            dataList.addFirst(curNode.node.data);
            curNode = curNode.predecessor;
        }
        if (dataList.size() == breaktie) {
            return dataList; // length must match the number of stops
        }
        return null; // null means length doesn't match the number of stops --> something wrong
    }

    /**
     * Returns the cost of the path (sum over distances) of the shortest
     * path from the node containing the start data to the node containing the
     * end data.  This method uses Dijkstra's shortest path algorithm to find
     * this solution.
     *
     * @param start the data item in the starting node for the path
     * @param end the data item in the destination node for the path
     * @return the cost (distance) of the shortest path between these nodes
     */
    @Override
    public double shortestPathCost(NodeType start, NodeType end) {
        if (computeShortestPath(start, end) == null) {
            throw new NoSuchElementException("no path from start to end is found");
        }
        return computeShortestPath(start, end).cost;
    }

    /**
     * return the quickest path with stop requested from BD by prioritizing the
     * distance and breaking the tie by the number of stops;
     * i.e., call shortestPathData(start, stop) and shortestPathData(stop, end)
     * and combine them together
     * @param start the data item in the starting node for the path
     * @param stop required layover
     * @param end the data item in the destination node for the path
     * @return a list of airports of the quickest path
     */
    @Override
    public List<NodeType> shortestPathData(NodeType start, NodeType stop, NodeType end) {
        LinkedList<NodeType> dataList = new LinkedList<>();
        SearchNode secondEnd = computeShortestPath(stop, end);
        double breaktie1 = secondEnd.breaktie;
        while (secondEnd != null) {
            // add NodeType stop here
            dataList.addFirst(secondEnd.node.data);
            secondEnd = secondEnd.predecessor;
        }
        SearchNode firstEnd = computeShortestPath(start, stop);
        double breaktie2 = firstEnd.breaktie;
        while (firstEnd != null) {
            // add NodeType stop here
            dataList.addFirst(firstEnd.node.data);
            firstEnd = firstEnd.predecessor;
        }
        // take out duplicate node which is the stop node
        dataList.remove(stop);
        if (dataList.size() == breaktie1 + breaktie2 - 1) {
            return dataList; // length must match the number of stops
        }
        return null; // null means length doesn't match the number of stops --> something wrong
    }

    /**
     * return the distance for the quickest path with stop requested from BD
     * @param start the data item in the starting node for the path
     * @param stop required layover
     * @param end the data item in the destination node for the path
     * @return the distance of the quickest path
     */
    @Override
    public double shortestPathCost(NodeType start, NodeType stop, NodeType end) {
        return computeShortestPath(start, stop).cost + computeShortestPath(stop, end).cost;
    }

    /**
     * This helper method creates a network of SearchNodes while computing the
     * fastest path between the provided start and end locations.  The
     * SearchNode that is returned by this method represents the end of the
     * fastest path that is found: its cost is the cost of that shortest path,
     * and the nodes linked together through predecessor references represent
     * all of the nodes along that shortest path (ordered from end to start).
     * Fastest path prioritizes the number of stops and breaks tie by distance;
     * cost is number of stops and breaktie is the distance
     * @param start the data item in the starting node for the path
     * @param end the data item in the destination node for the path
     * @return SearchNode for the final end node within the shortest path
     * @throws NoSuchElementException when no path from start to end is found
     *         or when either start or end data do not correspond to a graph node
     */
    @SuppressWarnings("unchecked")
    protected SearchNode computeFastestPath(NodeType start, NodeType end) throws NoSuchElementException {
        // TODO: implement in step 6
        // if either start or end data do not correspond to a graph node
        if (nodes.get(start) == null || nodes.get(end) == null) {
            throw new NoSuchElementException("either start or end data do not correspond to a graph node");
        }
        // if start == end with no explicit edge: cost (number of stops) - 1; breaktie (distance): 0
        if (start.equals(end)) {
            return new SearchNode(nodes.get(start), 1, 0,null);
        }
        // keep track of what to be added to visited hashtable next
        PriorityQueue<SearchNode> nodesToVisit = new PriorityQueue<>();
        // keep track of what nodes are visited: tail is the terminal node
        Hashtable<NodeType, Node> visited = new Hashtable<>();
        // initialize the SearchNode: update it every time we add a node into visited
        // cost (number of stops) - 1; breaktie (distance): 0
        SearchNode startNode = new SearchNode(nodes.get(start), 1, 0,null);
        // initialize by adding startNode into visited and its successor nodes to nodesToVisited
        visited.put(start, nodes.get(start));
        for (Edge edge : startNode.node.edgesLeaving) {
            // always update the number of stops (cost) by 1 + predecessor's breaktie
            // update the breaktie (distance) as the edge data
            double numberOfStops = startNode.cost;
            nodesToVisit.add(new SearchNode(edge.successor,
                    ++numberOfStops, edge.data.doubleValue(), startNode));
        }
        // loop until endNode is visited
        while (!nodesToVisit.isEmpty()) {
            // within nodesToVisit, find the node with min cost from its predecessor
            // find the successor node with min cost
            SearchNode sucNode = nodesToVisit.poll();
            // check if this node is in visited; if it is, continue to next iteration
            if (!visited.containsKey(sucNode.node.data)) {
                // if this node is not visited
                // add this successor node into visited
                visited.put(sucNode.node.data, nodes.get(sucNode.node.data));
                // the cost of current path is the cost of the SearchNode I just added into visited
                // if the node just added into visited is the end node we are reaching
                if (sucNode.node.data != null && sucNode.node.data.equals(end)) {
                    // start to end path is found
                    // return sucNode
                    return sucNode;
                }
                // add this successor's successors into PQ
                for (Edge edge : sucNode.node.edgesLeaving) {
                    Node sucSuc = edge.successor;
                    if (!visited.containsKey(edge.successor.data)) {
                        // if node is not visited
                        // add each node into PQ
                        // the cost (number of stops) in SearchNode = ++cost of pred SearchNode
                        // the breaktie (distance) is breaktie (distance) of pred SearchNode + edge data
                        double numberOfStops = sucNode.cost;
                        nodesToVisit.add(new SearchNode(sucSuc, ++numberOfStops, sucNode.breaktie +
                            edge.data.doubleValue(), sucNode));
                    }
                }
            }
        }
        // if PQ is empty and end node is not in visited, then no path from start to end
        if (visited.get(end) == null) {
            throw new NoSuchElementException("no path from start to end is found");
        }
        return null;
    }

    /**
     * return the fastest path by prioritizing the number of stops and breaking
     * the tie by distance
     * @param start the data item in the starting node for the path
     * @param end the data item in the destination node for the path
     * @return a list of places of the fastest path
     */
    @Override
    public List<NodeType> fastestPathData(NodeType start, NodeType end) {
        SearchNode curNode = computeFastestPath(start, end);

        LinkedList<NodeType> dataList = new LinkedList<>();
        while (curNode != null) {
            dataList.addFirst(curNode.node.data);
            curNode = curNode.predecessor;
        }
        return dataList;
        // no need to check for breaktie (distance) since it's already tested
        // in shortestPath mode;
    }

    /**
     * return the fastest path with stop requested from BD by prioritizing
     * the number of stops and breaking the tie by distance
     * @param start the data item in the starting node for the path
     * @param stop required layover
     * @param end the data item in the destination node for the path
     * @return a list of places of the fastest path with required stop
     */
    @Override
    public List<NodeType> fastestPathData(NodeType start, NodeType stop, NodeType end) {
        LinkedList<NodeType> dataList = new LinkedList<>();
        SearchNode secondEnd = computeFastestPath(stop, end);
        double breaktie1 = secondEnd.breaktie;
        while (secondEnd != null) {
            // add NodeType stop here
            dataList.addFirst(secondEnd.node.data);
            secondEnd = secondEnd.predecessor;
        }
        SearchNode firstEnd = computeFastestPath(start, stop);
        double breaktie2 = firstEnd.breaktie;
        while (firstEnd != null) {
            // add NodeType stop here
            dataList.addFirst(firstEnd.node.data);
            firstEnd = firstEnd.predecessor;
        }
        // take out duplicate node which is the stop node
        dataList.remove(stop);
        return dataList;
        // no need to check for breaktie (distance) since it's already tested
        // in shortestPath mode
    }

    /**
     * return the number of stops for the fastest path
     * @param start the data item in the starting node for the path
     * @param end the data item in the destination node for the path
     * @return the number of stops for the fastest path
     */
    @Override
    public int fastestPathCost(NodeType start, NodeType end) {
        return (int) computeFastestPath(start, end).cost;
    }

    /**
     * return the number of stops for the fastest path with stop requested from BD
     * @param start the data item in the starting node for the path
     * @param stop required layover
     * @param end the data item in the destination node for the path
     * @return the number of stops for the fastest path with stop requested from BD
     */
    @Override
    public int fastestPathCost(NodeType start, NodeType stop, NodeType end) {
        // -1 to remove duplicate of the stop
        return (int) (computeShortestPath(start, stop).cost +
                computeShortestPath(stop, end).cost) - 1;
    }

}
