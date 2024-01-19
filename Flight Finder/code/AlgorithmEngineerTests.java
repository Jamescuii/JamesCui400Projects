// --== CS400 File Header Information ==--
// Name: Cornelia (Zhouzhou) Chu
// Email: zchu26@wisc.edu
// Group and Team: BC Red
// Group TA: Naman Gupta
// Lecturer: Gary Dahl
// Notes to Grader: <optional extra notes>

import java.util.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import edu.wisc.cs.cs400.JavaFXTester;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.event.ActionEvent;
import javafx.scene.text.Text;
import javafx.scene.control.TextField;
import javafx.scene.text.TextAlignment;
import static org.junit.jupiter.api.Assertions.*;

/**
 * test the functionality of AE's code
 * @author Cornelia Chu
 */
public class AlgorithmEngineerTests extends JavaFXTester {
    protected TwoModePathFinderAE pathFinderAE;

	/**
     * Constructor for frontend JavaFX application
     */
    public AlgorithmEngineerTests() {
        super(FrontendDeveloper.class);
    }

    /**
     * Integration test for minimum distance route between SFO and ORD
     * with a required layover of FLG.
     * expected: SFO to LAX to FLG to DRO to COS to ORD
     */
    @Test
    public void IntegrationTest1() {
        write("p3huge.dot"); // load data into graph
        clickOn("#buttonWel"); // click on the start button
        clickOn("#button2"); // select the second route option
                             // which is the minimum distance route with layover
        clickOn("#start"); // edit the starting airport
        write("SFO"); // input starting airport
        clickOn("#dest"); // edit the destination airport
        write("ORD"); // input destination airport
        clickOn("#middle"); // edit the layover airport
        write("FLG"); // input layover airport
        clickOn("#goBut2"); // click on the search button
        Text bottomText = lookup("#bottomText").query();
        assertEquals("SFO -> LAX -> FLG -> DRO -> COS -> ORD",
                bottomText.getText().trim(), "Implementation is wrong"); // check for result
    }

	/**
     * Integration test for minimum stop route between SFO and ORD
     * with a required layover of FLG.
     * expected: SFO to LAX to FLG to GJT to ORD
     */
    @Test
    public void IntegrationTest2() {
        write("p3huge.dot"); // load data into graph
        clickOn("#buttonWel"); // click on the start button
        clickOn("#button4"); // select the fourth route option
                             // which is the minimum stop route with layover
        clickOn("#start"); // edit the starting airport
        write("SFO"); // input starting airport
        clickOn("#dest"); // edit the destination airport
        write("ORD"); // input destination airport
        clickOn("#middle"); // edit the layover airport
        write("FLG"); // input layover airport
        clickOn("#goBut4"); // click on the search button
        Text bottomText = lookup("#bottomText").query();
        assertEquals("SFO -> LAX -> FLG -> GJT -> ORD",
                bottomText.getText().trim(), "Implementation is wrong"); // check for result
    }

  /**
   * Code review for Data Wrangler's toString() in the RouteDW class
   */
  @Test
  void CodeReviewOfDataWrangler1(){
      RouteInterface route = new RouteDW(); // create route
      AirportInterface Airport1 = new AirportDW("PDX","Portland - OR",45.5887,-122.598);
      AirportInterface Airport2 = new AirportDW("RDD","Redding - CA",40.509,-122.293);
      AirportInterface Airport3 = new AirportDW("SEA","Seattle - WA",47.449,-122.309);
      route.add(Airport1);
      route.add(Airport2);
      route.add(Airport3); // add airports to route
      String routeStr = route.toString();
      assertEquals("PDX -> RDD -> SEA", routeStr);
  }

  /**
   * Code review for Data Wrangler's contains() in the RouteDW class
   */
  @Test
  void CodeReviewOfDataWrangler2(){
      RouteInterface route = new RouteDW(); // create route
      AirportInterface Airport1 = new AirportDW("PDX","Portland - OR",45.5887,-122.598);
      AirportInterface Airport2 = new AirportDW("RDD","Redding - CA",40.509,-122.293);
      AirportInterface Airport3 = new AirportDW("SEA","Seattle - WA",47.449,-122.309);
      route.add(Airport1);
      route.add(Airport2);
      route.add(Airport3); // add airports to route
      assertEquals(true, route.contains(Airport1));
      assertEquals(true, route.contains(Airport2));
      assertEquals(true, route.contains(Airport3));
  }

    /**
     * create AE's instance before each test
     */
    @SuppressWarnings("unchecked")
    @BeforeEach
    public void createPathFinder() {
        this.pathFinderAE = new TwoModePathFinderAE<String, Double>();
        this.pathFinderAE.insertNode("A");
        this.pathFinderAE.insertNode("B");
        this.pathFinderAE.insertNode("D");
        this.pathFinderAE.insertNode("E");
        this.pathFinderAE.insertNode("F");
        this.pathFinderAE.insertNode("G");
        this.pathFinderAE.insertNode("H");
        this.pathFinderAE.insertNode("I");
        this.pathFinderAE.insertNode("L");
        this.pathFinderAE.insertNode("M");
        this.pathFinderAE.insertEdge("A", "B", 1);
        this.pathFinderAE.insertEdge("A", "H", 8);
        this.pathFinderAE.insertEdge("A", "M", 5);
        this.pathFinderAE.insertEdge("B", "M", 3);
        this.pathFinderAE.insertEdge("D", "A", 7);
        this.pathFinderAE.insertEdge("D", "G", 2);
        // no edges leaving node E
        this.pathFinderAE.insertEdge("F", "G", 9);
        this.pathFinderAE.insertEdge("G", "L", 7);
        this.pathFinderAE.insertEdge("H", "B", 6);
        this.pathFinderAE.insertEdge("H", "I", 2);
        this.pathFinderAE.insertEdge("I", "D", 1);
        this.pathFinderAE.insertEdge("I", "L", 5);
        this.pathFinderAE.insertEdge("I", "H", 2);
        // no edges leaving node L
        this.pathFinderAE.insertEdge("M", "E", 3);
        this.pathFinderAE.insertEdge("M", "F", 4);
    }

    /**
     * test shortest path from node A to node M about cost and path contents
     * Prioritize the distance (cost) and break tie by the number of stops
     */
    @SuppressWarnings("unchecked")
    @Test
    public void test1() {
        // check cost (distance)
        double distance = this.pathFinderAE.shortestPathCost("A", "M");
        assertEquals(4, distance, "the distance of my implementation " +
                "does not match what I previously computed by hand");
        // check the contents of path (sequence of data) :
        // breaktie (number of stops) is checked inside of shortestPathData
        List<String> contents = this.pathFinderAE.shortestPathData("A", "M");
        assertEquals("[A, B, M]", contents.toString(), "the contents of path of " +
                "my implementation does not match what I previously computed by hand");
    }

    /**
     * test shortest path from node A to node M with stop at B about cost and path contents
     * Prioritize the distance (cost) and break tie by the number of stops
     * This must give the same result of path as test1
     */
    @SuppressWarnings("unchecked")
    @Test
    public void test2() {
        // check cost (distance)
        double distance = this.pathFinderAE.shortestPathCost("A", "B", "M");
        assertEquals(4, distance, "the distance of my implementation " +
                "does not match what I previously computed by hand");
        // check the contents of path (sequence of data) :
        // breaktie (number of stops) is checked inside of shortestPathData
        List<String> contents = this.pathFinderAE.shortestPathData("A", "B","M");
        assertEquals("[A, B, M]", contents.toString(), "the contents of path of " +
                "my implementation does not match what I previously computed by hand");
    }

    /**
     * test fastest path from node A to node M about cost and path contents
     * Prioritize the number of stops (cost) and break tie by the distance
     */
    @SuppressWarnings("unchecked")
    @Test
    public void test3() {
        // check cost (number of stops instead of distance)
        int numberOfStops = this.pathFinderAE.fastestPathCost("A",  "M");
        assertEquals(2, numberOfStops, "the number of stops of my implementation " +
                "does not match what I previously computed by hand");
        // check the contents of path (sequence of data) :
        // no need to check for breaktie (distance) since it's already tested
        // in shortestPath mode;
        List<String> contents = this.pathFinderAE.fastestPathData("A", "M");
        assertEquals("[A, M]", contents.toString(), "the contents of path of " +
                "my implementation does not match what I previously computed by hand");
    }

    /**
     * test fastest path from node A to node M with stop at B about cost and path contents
     * Prioritize the number of stops (cost) and break tie by the distance
     * This must give the same result of path as test2 instead of test3
     */
    @SuppressWarnings("unchecked")
    @Test
    public void test4() {
        // check cost (number of stops)
        int numberOfStops = this.pathFinderAE.fastestPathCost("A", "B", "M");
        assertEquals(3, numberOfStops, "the distance of my implementation " +
                "does not match what I previously computed by hand");
        // check the contents of path (sequence of data) :
        // no need to check for breaktie (distance) since it's already tested
        // in shortestPath mode;
        List<String> contents = this.pathFinderAE.fastestPathData("A", "B","M");
        assertEquals("[A, B, M]", contents.toString(), "the contents of path of " +
                "my implementation does not match what I previously computed by hand");
    }

    /**
     * Create a test that checks the behavior of my implementation when the node that you are searching for a path
     * between exist in the graph, but there is no sequence of directed edges with given stop
     * that connects them from the start to the end with given stop.
     * here we use L to I to D to test fastestPath mode
     */
    @SuppressWarnings("unchecked")
    @Test
    public void test5() {
        // check cost (number of stops)
        boolean costExceptionThrown = false;
        try {
            this.pathFinderAE.fastestPathCost("L", "I", "D");
        } catch (NoSuchElementException nse) {
            costExceptionThrown = true;
            assertEquals("no path from start to end is found", nse.getMessage(), "Wrong exception");
        }
        // assert true if exception from fastestPathCost is thrown
        assertTrue(costExceptionThrown);
        // check the contents of path (sequence of data)
        boolean dataExceptionThrown = false;
        try {
            this.pathFinderAE.fastestPathData("L", "I", "D");
        } catch (NoSuchElementException nse) {
            dataExceptionThrown = true;
            assertEquals("no path from start to end is found", nse.getMessage(), "Wrong exception");
        }
        // assert true if exception from fastestPathData is thrown
        assertTrue(dataExceptionThrown);
    }
}
