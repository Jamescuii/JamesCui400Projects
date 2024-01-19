import java.util.List;

/**
 * BD placeholder class for TwoModePathFinder.
 */
public class TwoModePathFinderBD<NodeType, EdgeType extends Number> implements
    TwoModePathFinderInterface<NodeType, EdgeType>{
  public static boolean SDPath2, FDPath2, SDPath3, FDPath3, ContainsCalled,
      getNodeCountCalled, getEdgeCountCalled; // Boolean flag object to test if the method are correctly called

  @Override
  public boolean insertNode(NodeType data) {
    return false;
  }

  @Override
  public boolean removeNode(NodeType data) {
    return false;
  }

  @Override
  public boolean containsNode(NodeType data) {
    ContainsCalled = true;
    return false;
  }

  @Override
  public int getNodeCount() {
    getNodeCountCalled = true;
    return 0;
  }

  @Override
  public boolean insertEdge(NodeType pred, NodeType succ, EdgeType weight) {
    return false;
  }

  @Override
  public boolean removeEdge(NodeType pred, NodeType succ) {
    return false;
  }

  @Override
  public boolean containsEdge(NodeType pred, NodeType succ) {
    return false;
  }

  @Override
  public EdgeType getEdge(NodeType pred, NodeType succ) {
    return null;
  }

  @Override
  public int getEdgeCount() {
    getEdgeCountCalled = true;
    return 0;
  }

  @Override
  public List<NodeType> shortestPathData(NodeType start, NodeType end) {
    SDPath2 = true;
    return null;
  }

  @Override
  public double shortestPathCost(NodeType start, NodeType end) {
    return 0;
  }

  @Override
  public List<NodeType> shortestPathData(NodeType start, NodeType stop, NodeType end) {
    SDPath3 = true;
    return null;
  }

  @Override
  public double shortestPathCost(NodeType start, NodeType stop, NodeType end) {
    return 0;
  }

  @Override
  public List<NodeType> fastestPathData(NodeType start, NodeType end) {
    FDPath2 = true;
    return null;
  }

  @Override
  public List<NodeType> fastestPathData(NodeType start, NodeType stop, NodeType end) {
    FDPath3 = true;
    return null;
  }

  @Override
  public int fastestPathCost(NodeType start, NodeType stop, NodeType end) {
    return 0;
  }

  @Override
  public int fastestPathCost(NodeType start, NodeType end) {
    return 0;
  }
}

