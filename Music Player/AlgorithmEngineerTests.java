import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Assertions;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileNotFoundException;

public class AlgorithmEngineerTests {

  protected SearchableSortedCollectionAE<Integer> RBTInteger;
  protected SearchableSortedCollectionAE<Character> RBTCharacter;

  @BeforeEach
  public void instantiateTree() {
    RBTInteger = new SearchableSortedCollectionAE<>();
    RBTCharacter = new SearchableSortedCollectionAE<>();
  }

  /**
   * Opaque box, general properties, and specific output tester method for the integer RBT removal method.
   * Check after each removal if the tree yields expected results as well as maintains the RBT properties.
   */
  @Test
  public void testRBTIntegerToyCase () {
    RBTInteger.insert(1);
    RBTInteger.insert(2);
    RBTInteger.insert(3);
    RBTInteger.insert(4);
    RBTInteger.insert(5);

    RBTInteger.remove(1);
    Assertions.assertEquals("[ 4, 2, 5, 3 ]", RBTInteger.toLevelOrderString());
    Assertions.assertEquals("[ 2, 3, 4, 5 ]", RBTInteger.toInOrderString());
    Assertions.assertEquals(2, getBlackHeight(RBTInteger.root));
    Assertions.assertTrue(checkRBTColor(RBTInteger.root));
  }

  /**
   * Opaque box, general properties, and specific output tester method for the integer RBT removal method.
   * Check after each removal if the tree yields expected results as well as maintains the RBT properties.
   */
  @Test
  public void testRBTIntegerGeneralCase1 () {
    RBTInteger.insert(45);
    RBTInteger.insert(22);
    RBTInteger.insert(72);
    RBTInteger.insert(18);
    RBTInteger.insert(31);
    RBTInteger.insert(68);
    RBTInteger.insert(91);
    RBTInteger.insert(13);
    RBTInteger.insert(20);
    RBTInteger.insert(28);
    RBTInteger.insert(70);
    RBTInteger.insert(88);

    RBTInteger.remove(70);
    Assertions.assertEquals("[ 45, 22, 72, 18, 31, 68, 91, 13, 20, 28, 88 ]", RBTInteger.toLevelOrderString());
    Assertions.assertEquals("[ 13, 18, 20, 22, 28, 31, 45, 68, 72, 88, 91 ]", RBTInteger.toInOrderString());
    Assertions.assertEquals(2, getBlackHeight(RBTInteger.root));
    Assertions.assertTrue(checkRBTColor(RBTInteger.root));
    RBTInteger.remove(68);
    Assertions.assertEquals("[ 45, 22, 88, 18, 31, 72, 91, 13, 20, 28 ]", RBTInteger.toLevelOrderString());
    Assertions.assertEquals("[ 13, 18, 20, 22, 28, 31, 45, 72, 88, 91 ]", RBTInteger.toInOrderString());
    Assertions.assertEquals(2, getBlackHeight(RBTInteger.root));
    Assertions.assertTrue(checkRBTColor(RBTInteger.root));
    RBTInteger.remove(91);
    Assertions.assertEquals("[ 45, 22, 88, 18, 31, 72, 13, 20, 28 ]", RBTInteger.toLevelOrderString());
    Assertions.assertEquals("[ 13, 18, 20, 22, 28, 31, 45, 72, 88 ]", RBTInteger.toInOrderString());
    Assertions.assertEquals(2, getBlackHeight(RBTInteger.root));
    Assertions.assertTrue(checkRBTColor(RBTInteger.root));
    RBTInteger.remove(88);
    Assertions.assertEquals("[ 45, 22, 72, 18, 31, 13, 20, 28 ]", RBTInteger.toLevelOrderString());
    Assertions.assertEquals("[ 13, 18, 20, 22, 28, 31, 45, 72 ]", RBTInteger.toInOrderString());
    Assertions.assertEquals(2, getBlackHeight(RBTInteger.root));
    Assertions.assertTrue(checkRBTColor(RBTInteger.root));
    RBTInteger.remove(72);
    Assertions.assertEquals("[ 22, 18, 31, 13, 20, 28, 45 ]", RBTInteger.toLevelOrderString());
    Assertions.assertEquals("[ 13, 18, 20, 22, 28, 31, 45 ]", RBTInteger.toInOrderString());
    Assertions.assertEquals(2, getBlackHeight(RBTInteger.root));
    Assertions.assertTrue(checkRBTColor(RBTInteger.root));
  }

  /**
   * Opaque box, general properties, and specific output tester method for the integer RBT removal method.
   * Check after each removal if the tree yields expected results as well as maintains the RBT properties.
   */
  @Test
  public void testRBTIntegerGeneralCase2 () {
    RBTInteger.insert(1);
    RBTInteger.insert(2);
    RBTInteger.insert(3);
    RBTInteger.insert(4);
    RBTInteger.insert(5);
    RBTInteger.insert(6);
    RBTInteger.insert(7);
    RBTInteger.insert(8);
    RBTInteger.insert(9);
    RBTInteger.insert(10);

    RBTInteger.remove(1);
    Assertions.assertEquals("[ 6, 4, 8, 2, 5, 7, 9, 3, 10 ]", RBTInteger.toLevelOrderString());
    Assertions.assertEquals("[ 2, 3, 4, 5, 6, 7, 8, 9, 10 ]", RBTInteger.toInOrderString());
    Assertions.assertEquals(3, getBlackHeight(RBTInteger.root));
    Assertions.assertTrue(checkRBTColor(RBTInteger.root));
    RBTInteger.remove(5);
    Assertions.assertEquals("[ 6, 3, 8, 2, 4, 7, 9, 10 ]", RBTInteger.toLevelOrderString());
    Assertions.assertEquals("[ 2, 3, 4, 6, 7, 8, 9, 10 ]", RBTInteger.toInOrderString());
    Assertions.assertEquals(3, getBlackHeight(RBTInteger.root));
    Assertions.assertTrue(checkRBTColor(RBTInteger.root));
    RBTInteger.remove(7);
    Assertions.assertEquals("[ 6, 3, 9, 2, 4, 8, 10 ]", RBTInteger.toLevelOrderString());
    Assertions.assertEquals("[ 2, 3, 4, 6, 8, 9, 10 ]", RBTInteger.toInOrderString());
    Assertions.assertEquals(3, getBlackHeight(RBTInteger.root));
    Assertions.assertTrue(checkRBTColor(RBTInteger.root));
    RBTInteger.remove(10);
    Assertions.assertEquals("[ 6, 3, 9, 2, 4, 8 ]", RBTInteger.toLevelOrderString());
    Assertions.assertEquals("[ 2, 3, 4, 6, 8, 9 ]", RBTInteger.toInOrderString());
    Assertions.assertEquals(2, getBlackHeight(RBTInteger.root));
    Assertions.assertTrue(checkRBTColor(RBTInteger.root));
  }

  /**
   * Opaque box, and specific output tester method for the character RBT removal method.
   * Check after each removal if the tree yields expected results.
   */
  @Test
  public void testRBTCharacter () {
    RBTCharacter.insert('A');
    RBTCharacter.insert('B');
    RBTCharacter.insert('C');
    RBTCharacter.insert('D');
    RBTCharacter.insert('E');
    RBTCharacter.insert('F');
    RBTCharacter.insert('G');
    RBTCharacter.insert('H');
    RBTCharacter.insert('I');
    RBTCharacter.insert('J');

    RBTCharacter.remove('C');
    Assertions.assertEquals("[ F, D, H, B, E, G, I, A, J ]", RBTCharacter.toLevelOrderString());
    Assertions.assertEquals("[ A, B, D, E, F, G, H, I, J ]", RBTCharacter.toInOrderString());
    RBTCharacter.remove('E');
    Assertions.assertEquals("[ F, B, H, A, D, G, I, J ]", RBTCharacter.toLevelOrderString());
    Assertions.assertEquals("[ A, B, D, F, G, H, I, J ]", RBTCharacter.toInOrderString());
    RBTCharacter.remove('G');
    Assertions.assertEquals("[ F, B, I, A, D, H, J ]", RBTCharacter.toLevelOrderString());
    Assertions.assertEquals("[ A, B, D, F, H, I, J ]", RBTCharacter.toInOrderString());
    RBTCharacter.remove('D');
    Assertions.assertEquals("[ F, B, I, A, H, J ]", RBTCharacter.toLevelOrderString());
    Assertions.assertEquals("[ A, B, F, H, I, J ]", RBTCharacter.toInOrderString());
    RBTCharacter.remove('J');
    Assertions.assertEquals("[ F, B, I, A, H ]", RBTCharacter.toLevelOrderString());
    Assertions.assertEquals("[ A, B, F, H, I ]", RBTCharacter.toInOrderString());
  }

  /**
   * Tester for the search() method. Tests different scenarios including searching for a node in the
   * tree, not in the tree, a node have sufficient predecessor and successor, without one of them, etc.
   */
  @Test
  public void testSearch() {
    RBTCharacter.insert('A');
    RBTCharacter.insert('B');
    RBTCharacter.insert('C');
    RBTCharacter.insert('D');
    RBTCharacter.insert('E');
    RBTCharacter.insert('F');
    RBTCharacter.insert('G');
    RBTCharacter.insert('H');
    RBTCharacter.insert('I');
    RBTCharacter.insert('J');
    Assertions.assertEquals("[E, D, F, C, G]", RBTCharacter.search('E').toString()); // searching for a node in the middle
    Assertions.assertEquals("[B, A, C, D, E]", RBTCharacter.search('B').toString()); // searching for a node in the first half
    Assertions.assertEquals("[I, H, J, G, F]", RBTCharacter.search('I').toString()); // searching for a node in the second half
    Assertions.assertEquals("[J, I, H, G, F]", RBTCharacter.search('M').toString()); // searching for a big node not in the tree
    RBTCharacter.remove('E');
    Assertions.assertEquals("[D, F, C, G, B]", RBTCharacter.search('E').toString()); // searching for a middle node not in the tree
  }

  /**
   * Code review for DataWrangler's compareTo() method.
   */
  @Test
  public void CodeReviewOfDataWrangler1() {
    SongDW song1 = new SongDW("Without Me", "Eminem", "detroit hip hop", 2002, -3, 290, 82);
    SongDW song2 = new SongDW("Without Me", "Eminem", "detroit hip hop", 2002, -3, 290, 82);
    SongDW song3 = new SongDW("Cry Me a River", "Justin Timberlake", "dance pop", 2002, -7, 288, 74);
    int x = song1.compareTo(song2);
    int y = song2.compareTo(song3);
    int z = song3.compareTo(song2);
    Assertions.assertEquals(0, x);
    Assertions.assertTrue(y > 0);
    Assertions.assertTrue(z < 0);
  }

  /**
   * Code review for DataWrangler's SongReader class.
   */
  @Test
  public void CodeReviewOfDataWrangler2() {
      try {
      List<SongInterface> correct = new ArrayList<>();
      SongDW song1 = new SongDW("Without Me", "Eminem", "detroit hip hop", 2002, -3, 290, 82);
      SongDW song2 = new SongDW("Cry Me a River", "Justin Timberlake", "dance pop", 2002, -7, 288, 74);
      SongDW song3 = new SongDW("Bloed, Zweet En Tranen", "Andre Hazes", "dutch pop", 2002, -4, 263, 80);
      correct.add(song1);
      correct.add(song2);
      correct.add(song3);
      SongReaderDW tester = new SongReaderDW();
      List<SongInterface> testList = tester.readSongsFromFile("test3.txt");
      Assertions.assertEquals(correct, testList);
      Assertions.assertEquals(correct.size(), testList.size());
      } catch (FileNotFoundException e) {
           Assertions.fail("File not found");
      }
  }

  /**
     * Integration test for the entire application with the action of load data, search Uptown Funk by 
     * Mark Ronson, add to playlist, and the display functions
     */
    @Test
    public void IntegrationTest1(){
        {
            TextUITester tester = new TextUITester("L\ndataset.csv\nT\nUptown Funk\nMark Ronson\n1\nDP\nDL\nDS\nQ\n");
            SongReaderInterface songReader = new SongReaderDW();
            SearchableSortedCollectionInterface<SongInterface> redBlackTree = new SearchableSortedCollectionAE<>();
            BackendInterface backend = new BackendBD(redBlackTree, songReader);
            FrontendDeveloper frontend = new FrontendDeveloper(new Scanner(System.in), backend);
            frontend.runCommandLoop();
            String output = tester.checkOutput();
	    String menu = """
          Choose a command from the list below:
              [L]Load data from file
              [T]Search/Add Music by Title and Author
              [RP]Remove from Playlist
              [RL]Remove from Library
              [DP]Display Playlist
              [DL]Display Playlist Length
              [DS]Display Playlist Size
              [Q]Quit""".trim();
      String searchResult = """
          [1] Uptown Funk by Mark Ronson
          [2] Uprising by Muse
          [3] Uptown Girl by Billy Joel
          [4] Up&Up by Coldplay
          [5] Us and Them by Pink Floyd""".trim();
      String playlistContent = "[Title: Uptown Funk; Author: Mark Ronson; Genre: dance pop; Year: 2015; Loudness: -7; Duration: 270; Popularity: 82]";
      String playlistLength = "The length of the playlist is: 4 minutes and 30 seconds.";
      String playlistSize = "The playlist contains 1 songs.";
      Assertions.assertTrue(output.contains(menu));
      Assertions.assertTrue(output.contains(searchResult));
      Assertions.assertTrue(output.contains(playlistContent));
      Assertions.assertTrue(output.contains(playlistLength));
      Assertions.assertTrue(output.contains(playlistSize));
        }
    }

  /**
     * Integration test for the entire application with the action of load data, remove Uptown Funk by 
     * Mark Ronson, add to playlist, remove from playlist, and exit.
     */
    @Test
    public void IntegrationTest2(){
        {
            TextUITester tester = new TextUITester("L\ndataset.csv\nT\nUptown Funk\nMark Ronson\n1\nRP\nUptown Funk\nMark Ronson\nDP\nDS\nDL\nRL\nUptown Funk\nMark Ronson\nT\nUptown Funk\nMark Ronson\n0\nQ\n");
            SongReaderInterface songReader = new SongReaderDW();
            SearchableSortedCollectionInterface<SongInterface> redBlackTree = new SearchableSortedCollectionAE<>();
            BackendInterface backend = new BackendBD(redBlackTree, songReader);
            FrontendDeveloper frontend = new FrontendDeveloper(new Scanner(System.in), backend);
            frontend.runCommandLoop();
            String output = tester.checkOutput();
            String searchResultAfterRemove = "[1] Uprising by Muse\n[2] Uptown Girl by Billy Joel\n[3] Up&Up by Coldplay\n[4] Us and Them by Pink Floyd\n[5] Up Where We Belong by Joe Cocker";
      	    String playlistContentAfterRemove = "[]";
            String playlistLengthAfterRemove = "The length of the playlist is: 0 minutes and 0 seconds.";
      	    String playlistSizeAfterRemove = "The playlist contains 0 songs.";
      	    Assertions.assertTrue(output.contains(searchResultAfterRemove));
      	    Assertions.assertTrue(output.contains(playlistContentAfterRemove));
      	    Assertions.assertTrue(output.contains(playlistLengthAfterRemove));
      	    Assertions.assertTrue(output.contains(playlistSizeAfterRemove));
        }
    }

  /**
   * Helper tester method for the RBT properties: 1. All nodes are either black or red. 2. No red
   * node has a red child.
   *
   * @param node node as the root of subtree to check with
   * @return true if no property violations are found, false otherwise.
   */
  protected boolean checkRBTColor(RedBlackTree.Node<Integer> node) {
    if (node == null) // empty tree
      return true;
    if (node.blackHeight != 0 && node.blackHeight != 1) // Violation of "either red or black"
      return false;
    if (node.context[0] == null && node.blackHeight == 0) // Violation of "root must be black"
      return false;
    if (bothRed(node, node.context[0]) || bothRed(node, node.context[1]) || bothRed(node,
        node.context[2])) // Violation of "parent child cannot be both red"
      return false;
    return checkRBTColor(node.context[1]) && checkRBTColor(
        node.context[2]); // check for both subtrees
  }

  /**
   * Helper method to check whether both parent and child are red node.
   *
   * @param node1 RBT node to check with
   * @param node2 RBT node to check with
   * @return true if both node are red, false otherwise.
   */
  private boolean bothRed(RedBlackTree.Node<Integer> node1, RedBlackTree.Node<Integer> node2) {
    if (node2 == null || node1 == null) // either node be null should not be considered both red
      return false;
    return node1.blackHeight == 0 && node2.blackHeight == 0;
  }

  /**
   * Helper tester method for the RBT properties: 3. The blackHeight on each branch must be the
   * same.
   *
   * @param node node as the root of subtree to check with
   * @return Black height of this tree if no property violations are found, -1 otherwise.
   */
  private int getBlackHeight(RedBlackTree.Node<Integer> node) {
    if (node == null) // empty tree has black height 0
      return 0;
    int blackHeightLeft = getBlackHeight(node.context[1]); // get the black height from left subtree
    int blackHeightRight =
        getBlackHeight(node.context[2]); // get the black height from right subtree
    if (blackHeightRight != blackHeightLeft || // Mismatch of left and right subtree black height
        blackHeightRight == -1) // Avoid the case for both left and right be problematic (left == right == -1)
      return -1;
    return node.blackHeight + blackHeightLeft;
  }


}
