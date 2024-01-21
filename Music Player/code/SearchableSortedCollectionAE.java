import java.util.ArrayList;
import java.util.List;

public class SearchableSortedCollectionAE<T extends Comparable<T>> extends RedBlackTree<T>
    implements SearchableSortedCollectionInterface<T>{
  /**
   * Removes the value data from the tree if the tree contains the value.
   * This method will balance the tree after the removal
   * and should be updated once the tree uses Red-Black Tree insertion.
   *
   * @return true if the value was remove, false if it didn't exist
   * @throws NullPointerException     when the provided data argument is null
   * @throws IllegalArgumentException when data is not stored in the tree
   */
  @Override
  public boolean remove(T data) throws NullPointerException, IllegalArgumentException {
    // null references will not be stored within this tree
    if (data == null) {
      throw new NullPointerException("This RedBlackTree cannot store null references.");
    } else {
      Node<T> nodeWithData = this.findNodeWithData(data);
      // throw exception if node with data does not exist
      if (nodeWithData == null) {
        throw new IllegalArgumentException(
            "The following value is not in the tree and cannot be deleted: " + data);
      }
      boolean hasRightChild = (nodeWithData.context[2] != null);
      boolean hasLeftChild = (nodeWithData.context[1] != null);
      if (hasRightChild && hasLeftChild) {
        // has 2 children
        Node<T> successorNode = this.findMinOfRightSubtree(nodeWithData);
        // replace value of node with value of successor node
        nodeWithData.data = successorNode.data;
        // remove successor node
        if (successorNode.context[2] == null) {
          // successor has no children, need to check the double black situation before removal
          successorNode.blackHeight++;
          enforceRBTreePropertiesAfterRemoval(successorNode);
          this.replaceNode(successorNode, null);
        } else {
          // successor has a right child, replace successor with its child
          successorNode.context[2].blackHeight = 1;
          this.replaceNode(successorNode, successorNode.context[2]);
        }
      } else if (hasRightChild) {
        // only right child, replace with right child
        nodeWithData.context[2].blackHeight = 1;
        this.replaceNode(nodeWithData, nodeWithData.context[2]);
      } else if (hasLeftChild) {
        // only left child, replace with left child
        nodeWithData.context[1].blackHeight = 1;
        this.replaceNode(nodeWithData, nodeWithData.context[1]);
      } else {
        // no children, need to check the double black situation before removal
        nodeWithData.blackHeight++;
        enforceRBTreePropertiesAfterRemoval(nodeWithData);
        this.replaceNode(nodeWithData, null);
      }
      this.size--;
      return true;
    }
  }

  /**
   * Helper method to ensure that after each removal the RBT properties are maintained.
   * @param node the node that are removed.
   */
  protected void enforceRBTreePropertiesAfterRemoval(Node<T> node) {
    if (node.context[0] != null && node.blackHeight == 2) { // Double black situation except for the root
      Node<T> sibling; // Define the sibling node
      if (node.isRightChild())
        sibling = node.context[0].context[1];
      else
        sibling = node.context[0].context[2];
      if (sibling.blackHeight == 1) { // Case 1 and Case 3: sibling is black
        // Case 3: Sibling is black and Sibling does not have red child
        if ((sibling.context[1] == null && sibling.context[2] == null) ||
            sibling.context[1] != null && sibling.context[1].blackHeight == 1 &&
            sibling.context[2] != null && sibling.context[2].blackHeight == 1) {
          RBTRemovalCase3(node);
          // Case 1: Sibling is black and sibling's red child is on the same side
        } else if (sibling.isRightChild() && sibling.context[2] != null && sibling.context[2].blackHeight == 0 ||
            !sibling.isRightChild() && sibling.context[1] != null && sibling.context[1].blackHeight == 0) {
          RBTRemovalCase1(node);
          // Case 1.5: Sibling is black and sibling's red child is on the different side, need to rotate first to Case 1
        } else if (sibling.isRightChild() && sibling.context[1] != null && sibling.context[1].blackHeight == 0) {
          swapColor(sibling, sibling.context[1]);
          rotate(sibling.context[1], sibling);
          RBTRemovalCase1(node);
          // Case 1.5: Sibling is black and sibling's red child is on the different side, need to rotate first to Case 1
        } else if (!sibling.isRightChild() && sibling.context[2] != null && sibling.context[2].blackHeight == 0) {
          swapColor(sibling, sibling.context[2]);
          rotate(sibling.context[2], sibling);
          RBTRemovalCase1(node);
        }
        // Case 2: Sibling is red, need to transform into either Case 1 or Case 3.
      } else if (sibling.blackHeight == 0){
        swapColor(sibling, node.context[0]);
        rotate(sibling, node.context[0]);
        enforceRBTreePropertiesAfterRemoval(node);
      }
    }
    super.root.blackHeight = 1; // Always makes the root black.
  }

  /**
   * Helper method for RBT removal Case 1 of color maintenance: Black sibling with red child.
   * @param node double black node to deal with.
   */
  protected void RBTRemovalCase1 (Node<T> node) {
    Node<T> sibling; // get sibling node.
    if (node.isRightChild())
      sibling = node.context[0].context[1];
    else
      sibling = node.context[0].context[2];
    node.blackHeight = 1; // change node to normal black node and do the remaining balancing work.
    if (sibling.isRightChild())
      sibling.context[2].blackHeight = 1;
    else
      sibling.context[1].blackHeight = 1;
    swapColor(sibling, node.context[0]);
    rotate(sibling, node.context[0]);
  }

  /**
   * Helper method for RBT removal Case 3 of color maintenance: Black sibling with black child.
   * @param node double black node to deal with.
   */
  protected void RBTRemovalCase3 (Node<T> node) {
    Node<T> sibling;
    if (node.isRightChild())
      sibling = node.context[0].context[1];
    else
      sibling = node.context[0].context[2];
    node.blackHeight = 1; // change node to normal black node and do the remaining balancing work.
    sibling.blackHeight = 0;
    node.context[0].blackHeight++; // may cause the parent node to become a double black.
    enforceRBTreePropertiesAfterRemoval(node.context[0]); // further restoration.
  }

  /**
   * Implement a similarity search functionality with the given search term. A list of most similar result
   * with be given as the searching result, namely, 3 predecessors and 2 successors in the RBT. if either ways reaches
   * the end, i.e. no more predecessors, more successors will be added. If there are less than 5 elements in the collection,
   * this method will return as many as possible.
   * @param term the search term for similarity search.
   * @return a list containing at most 5 most similar results.
   * @throws IllegalStateException if the collection is empty.
   */
  @Override
  public List<T> search(T term) throws IllegalStateException {
    if (super.root == null)
      throw new IllegalStateException("Empty collection");
    Node<T> node = findNodeWithData(term); // try to check if this node is in the tree.
    List<T> result = new ArrayList<>();
    boolean removeNeeded = false; // if the node is not in the tree, for simplicity of getting successors
    // and predecessors we will temporarily add this node to the tree and then remove it after search.
    int totalNum = 5;
    if (node == null) {
      this.insert(term);
      removeNeeded = true; // we need to remove it at the end.
    } else {
      result.add(node.data);
      totalNum--;
    }
    node = findNodeWithData(term);
    Node<T> pre = predecessor(node), post = successor(node);
    while (true) {
      if (pre != null) { // has predecessor
        result.add(pre.data);
        pre = predecessor(findNodeWithData(pre.data)); // keep finding predecessor's predecessor
        totalNum--;
        if (totalNum == 0)
          break;
      }
      if (post != null) { // has successor
        result.add(post.data);
        post = successor(findNodeWithData(post.data)); // keep finding successor's successor
        totalNum--;
        if (totalNum == 0)
          break;
      }
      if (post == null && pre == null) // no predecessor and no successor
        break;
    }
    if (removeNeeded)
      this.remove(term);
    return result;
  }

  /**
   * Helper method for the search() method to acquire the predecessor of the given node.
   * @param node node to get the predecessor from
   * @return the predecessor of the given node.
   */
  protected Node<T> predecessor (Node<T> node) {
    if (node.context[1] != null) { // has left child
      return findMaxOfLeftSubtree(node);
    }
    // Does not have left child, find the first ancestor with right child
    Node<T> result = node;
    while(result != null) {
      if (result.isRightChild())
        return result.context[0];
      result = result.context[0];
    }
    return null;
  }

  /**
   * Helper method for the search() method to acquire the successor of the given node.
   * @param node node to get the successor from
   * @return the successor of the given node.
   */
  protected Node<T> successor (Node<T> node) {
    if (node.context[2] != null) { // has right child
      return findMinOfRightSubtree(node);
    }
    // Does not have right child, find the first ancestor with left child
    Node<T> result = node;
    while(result != null) {
      if (!result.isRightChild())
        return result.context[0];
      result = result.context[0];
    }
    return null;
  }

  /**
   * Helper method for the predecessor method to get the maximum node in the left subtree
   * @param node parent of the root of the left subtree
   * @return maximum node in the left subtree of node.
   */
  protected Node<T> findMaxOfLeftSubtree(Node<T> node) {
    // take a step to the left
    Node<T> current = node.context[1];
    while (true) {
      // then go left as often as possible to find the predecessor
      if (current.context[2] == null) {
        // we found the predecessor
        return current;
      } else {
        current = current.context[2];
      }
    }
  }
}
