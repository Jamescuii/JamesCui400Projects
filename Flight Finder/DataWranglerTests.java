// --== CS400 Spring 2023 File Header Information ==--
// Name: Ziqi Shen
// Email: zshen266@wisc.edu
// Team: BC
// TA: Naman Gupta
// Lecturer: Gary Dahl
// Notes to Grader: None

import java.io.FileNotFoundException;

import org.junit.Assert;
/*** JUnit imports ***/
// imports for JUnit tests
import org.junit.jupiter.api.Test;
import java.lang.Math;
import java.util.List;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

/*** JUnit imports end  ***/
import edu.wisc.cs.cs400.JavaFXTester;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.event.ActionEvent;
import javafx.scene.text.Text;
import javafx.scene.control.TextField;
import javafx.scene.text.TextAlignment;

/**
 * Tester class for DataWrangler
 * 
 */
public class DataWranglerTests extends JavaFXTester {

  // instance of DataWrangler for JUnit tests
  protected AirportNetworkReaderDW _instance = null;

  /**
   * Constructor for frontend JavaFX application
   */
  public DataWranglerTests() {
	  super(FrontendDeveloper.class);
  }


  /**
   * This is a JUnit test method test for readGraphFromFile() in the AirportNetworkReaderDW class
   * to check if the method read valid file correctly and thrown FileNotFoundException when input
   * file is unvalid
   */
  @Test
  public void test1() {
      // test case 1, user load a valid file
      {
        try{
          _instance = new AirportNetworkReaderDW();
          _instance.readGraphFromFile("p3tiny.dot");
        }
        catch(FileNotFoundException e){
          fail("not excepted to throw FileNotFoundException");
        }
      }

      // test case 2, user load a unvalid file
      {
        String expString = "File not found.";
        try{
          _instance = new AirportNetworkReaderDW();
          _instance.readGraphFromFile("test.dot");
        }
        catch(FileNotFoundException e){
          String aclString = e.getMessage();
          assertEquals(aclString, expString);
        }
      }
  }

  /**
   * This is a JUnit test method test for readGraphFromFile() in the AirportNetworkReaderDW class
   * to check if the method return graph correctly 
   */
  @Test
  public void test2(){
    try{
      _instance = new AirportNetworkReaderDW();
      GraphADT<AirportInterface, Double> graph; 
      graph = _instance.readGraphFromFile("p3tiny.dot");
      AirportInterface testAirport1 = new AirportDW("LMT","Klamath Falls - OR",42.1561,-121.733);
      AirportInterface testAirport2 = new AirportDW("EUG","Eugene - OR",44.1246,-123.212);
      AirportInterface testAirport3 = new AirportDW("RDM","Bend - OR",44.2541,-121.15);
      assertEquals(true, graph.containsNode(testAirport1));
      assertEquals(true, graph.containsNode(testAirport2));
      assertEquals(true, graph.containsNode(testAirport3));
      assertEquals(true, graph.containsEdge(testAirport1,testAirport3));
      assertEquals(true, graph.containsEdge(testAirport2,testAirport3));
    }catch(FileNotFoundException e){
      fail("not excepted to throw FileNotFoundException");
    }
  }

  /**
   * This is a JUnit test method test for equals() in the AirportDW class
   * to check if the method performed correctly 
   */
  @Test
  public void test3(){
    AirportInterface testAirport1 = new AirportDW("LMT","Klamath Falls - OR",42.1561,-121.733);
    AirportInterface testAirport2 = new AirportDW("LMT","Eugene - OR",44.1246,-123.212);
    AirportInterface testAirport3 = new AirportDW("RDM","Bend - OR",44.2541,-121.15);
    assertEquals(true, testAirport1.equals(testAirport2));
    assertEquals(false, testAirport1.equals(testAirport3));
  }

  /**
   * This is a JUnit test method test for toString() in the AirportDW class
   * to check if the method performed correctly 
   */
  @Test
  public void test4(){
    AirportInterface testAirport1 = new AirportDW("LMT","Klamath Falls - OR",42.1561,-121.733);
    assertEquals("LMT (Klamath Falls - OR : 42.1561, -121.733)", testAirport1.toString());
    assertEquals("Klamath Falls - OR", testAirport1.getCity());
    assertEquals(Math.abs(42.1561 - testAirport1.getLatitude()) < 0.0001, true);
    assertEquals(Math.abs(-121.733 - testAirport1.getLongitude()) < 0.0001, true);
  }

    /**
   * This is a JUnit test method test for methods in the RouteDW class
   * to check if the method performed correctly 
   */
  @Test
  public void test5(){
    // test case 1, add() and getRoute
    {
      RouteInterface route1 = new RouteDW();
      AirportInterface testAirport1 = new AirportDW("LMT","Klamath Falls - OR",42.1561,-121.733);
      AirportInterface testAirport2 = new AirportDW("LMT","Eugene - OR",44.1246,-123.212);
      AirportInterface testAirport3 = new AirportDW("RDM","Bend - OR",44.2541,-121.15);
      route1.add(testAirport1);
      route1.add(testAirport2);
      route1.add(testAirport3);
      List<AirportInterface> testList = route1.getRoute();
      assertEquals(testAirport1, testList.get(0));
      assertEquals(testAirport2, testList.get(1));
      assertEquals(testAirport3, testList.get(2));
    }
    // test case 2, size()
    {
      RouteInterface route1 = new RouteDW();
      AirportInterface testAirport1 = new AirportDW("LMT","Klamath Falls - OR",42.1561,-121.733);
      AirportInterface testAirport2 = new AirportDW("LMT","Eugene - OR",44.1246,-123.212);
      AirportInterface testAirport3 = new AirportDW("RDM","Bend - OR",44.2541,-121.15);
      route1.add(testAirport1);
      route1.add(testAirport2);
      route1.add(testAirport3);
      assertEquals(3, route1.size());
    }
    // test case 3, add() to a specific index of the route
    {
      RouteInterface route1 = new RouteDW();
      AirportInterface testAirport1 = new AirportDW("LMT","Klamath Falls - OR",42.1561,-121.733);
      AirportInterface testAirport2 = new AirportDW("LMT","Eugene - OR",44.1246,-123.212);
      AirportInterface testAirport3 = new AirportDW("RDM","Bend - OR",44.2541,-121.15);
      route1.add(testAirport1);
      route1.add(testAirport2);
      route1.add(testAirport3, 0);
      List<AirportInterface> testList = route1.getRoute();
      assertEquals(testAirport1, testList.get(1));
      assertEquals(testAirport2, testList.get(2));
      assertEquals(testAirport3, testList.get(0));
    }
    // test case 3, remove() 
    {
      RouteInterface route1 = new RouteDW();
      AirportInterface testAirport1 = new AirportDW("LMT","Klamath Falls - OR",42.1561,-121.733);
      AirportInterface testAirport2 = new AirportDW("LMT","Eugene - OR",44.1246,-123.212);
      AirportInterface testAirport3 = new AirportDW("RDM","Bend - OR",44.2541,-121.15);
      route1.add(testAirport1);
      route1.add(testAirport2);
      route1.add(testAirport3);
      route1.remove(testAirport1);
      List<AirportInterface> testList = route1.getRoute();
      assertEquals(testAirport2, testList.get(0));
      assertEquals(testAirport3, testList.get(1));
    }
  }

  /**
   * test to find the fastest (time efficient) path with BOS, STL and FLG
   */
  @Test
  public void CodeReviewForAlgorithmEngineer1() {
      AirportNetworkReaderDW airportNetworkReader = new AirportNetworkReaderDW();
      try{
        TwoModePathFinderInterface pathFinder = airportNetworkReader.readGraphFromFile("p3huge.dot");
        AirportInterface BOS = new AirportDW("BOS","Boston - MA",42.36429977416992, -71.00520324707031);
        AirportInterface STL = new AirportDW("STL","St. Louis - MO",38.74869918823242, -90.37000274658203);
        AirportInterface FLG = new AirportDW("FLG","Flagstaff - AZ",35.13850021362305, -111.6709976196289);
        List<AirportInterface> fastestPath = pathFinder.fastestPathData(BOS, STL, FLG);
        assertEquals("[BOS (Boston - MA : 42.36429977416992, -71.00520324707031), STL (St. Louis - MO : 38.74869918823242, " + 
        "-90.37000274658203), GJT (Grand Junction - CO : 39.122398376464844, -108.5270004272461), FLG (Flagstaff - " + 
        "AZ : 35.13850021362305, -111.6709976196289)]", fastestPath.toString(), "AE's implementation for "
              + "fastest path with layover is wrong");
      }catch(Exception e){
        fail("Exception thrown:" + e.getMessage());
      }

      
  }

  /**
   * test to find the shortest (distance efficient) path with BOS, STL and FLG
   */
  @Test
  public void CodeReviewForAlgorithmEngineer2() {
      AirportNetworkReaderInterface airportNetworkReader = new AirportNetworkReaderDW();
      try{
        TwoModePathFinderInterface pathFinder = airportNetworkReader.readGraphFromFile("p3huge.dot");
        AirportInterface BOS = new AirportDW("BOS","Boston - MA",42.36429977416992, -71.00520324707031);
        AirportInterface STL = new AirportDW("STL","St. Louis - MO",38.74869918823242, -90.37000274658203);
        AirportInterface FLG = new AirportDW("FLG","Flagstaff - AZ",35.13850021362305, -111.6709976196289);
        List<AirportInterface> fastestPath = pathFinder.shortestPathData(BOS, STL, FLG);
        assertEquals("[BOS (Boston - MA : 42.36429977416992, -71.00520324707031), STL (St. Louis - MO : 38.74869918823242, " + 
        "-90.37000274658203), ICT (Wichita - KS : 37.649898529052734, -97.43309783935547), FMN (Farmington - NM : 36.7411994934082, " + 
        "-108.2300033569336), FLG (Flagstaff - AZ : 35.13850021362305, -111.6709976196289)]", fastestPath.toString(), 
        "AE's implementation for shortest path with layover is wrong");
      }catch(Exception e){
        fail("Exception thrown:" + e.getMessage());
      }

  }

  /**
   * Integration test for minimum distance route between TBN and ORD with a middle stop of COU.
   */
  @Test
  void IntegrationTest1() {
        write("p3huge.dot"); // load data 
        clickOn("#buttonWel"); // click on the star tbutton
        clickOn("#button2"); // select the second route option
        clickOn("#start"); // edit the starting airport
        write("TBN");
        clickOn("#dest"); // edit the destination airport
        write("ORD");
        clickOn("#middle");
        write("COU"); // edit the middle stop
        clickOn("#goBut2"); // click on the search button       
        Text bottomText = lookup("#bottomText").query();
        assertEquals("TBN -> COU -> STL -> ORD", bottomText.getText().trim()); // check for result
  }

  /**
   * Integration test for minimum stop route between ORD and FLG.
   */
  @Test
  void IntegrationTest2() {
        write("p3huge.dot"); // load data
        clickOn("#buttonWel"); // click on the start button
        clickOn("#button3"); // select the third route option
        clickOn("#start"); // edit the starting airport
        write("ORD");
        clickOn("#dest"); // edit the destination airport
        write("FLG"); 
        clickOn("#goBut3"); // click on the search button       
        Text bottomText = lookup("#bottomText").query();
        String result = bottomText.getText().trim();
        Assert.assertTrue(result.contains("ORD -> MDW -> FLG")); // check for result
        Assert.assertTrue(result.contains("Minimum Stops: 1")); // check for resultc
         
  }


}
