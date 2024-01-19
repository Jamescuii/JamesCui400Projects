import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Assertions;
import java.io.FileNotFoundException;
import java.security.InvalidAlgorithmParameterException;
import edu.wisc.cs.cs400.JavaFXTester;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.event.ActionEvent;
import javafx.scene.text.Text;
import javafx.scene.control.TextField;
import javafx.scene.text.TextAlignment;


/**
 * Junit5 tester class for the BackendDeveloper. Since in this project the major responsibility for BD
 * is to connect the DW and AE code to FD, so this tester mainly focus on testing whether the BD methods
 * correctly calls the corresponding AE/DW methods.
 */
public class BackendDeveloperTests extends JavaFXTester {

  protected BackendDeveloper backend;

  /**
   * Constructor for frontend JavaFX application
   */
  public BackendDeveloperTests() {
	  super(FrontendDeveloper.class);
  }
  
  /**
   * Code review for Frontend button display. Simulate the input of users and check if the buttons are 
   * correctly displayed and labelled.
   */
  @Test
  void CodeReviewForFrontendDeveloper1() {
        write("p3huge.dot"); // directly write in the file name to load data in since the focus is
                             // originally on this text box
        clickOn("#buttonWel"); // click on the start button
        Button routeOption1 = lookup("#button1").query(); // look up the 4 route options buttons
        Button routeOption2 = lookup("#button2").query();
        Button routeOption3 = lookup("#button3").query();
        Button routeOption4 = lookup("#button4").query();
        Button ExitButton = lookup("#exitButton").query(); // look up the exit button
        Assertions.assertEquals("Minimum Distance", routeOption1.getText()); // compare the corresponding text
        Assertions.assertEquals("Minimum Distance +", routeOption2.getText());
        Assertions.assertEquals("Minimum Stops", routeOption3.getText());
        Assertions.assertEquals("Minimum Stops +", routeOption4.getText());	
        Assertions.assertEquals("Exit", ExitButton.getText());
    }


  /**
   * Code review for Frontend result display. Simulate the input of users and check if the results are
   * correctly displayed.
   */ 
  @Test
  void CodeReviewForFrontendDeveloper2() {
    	write("p3huge.dot"); // directly write in the file name to load data in since the focus is
			     // originally on this text box
    	clickOn("#buttonWel"); // click on the startbutton
	clickOn("#button1"); // select the first route option
        clickOn("#start"); // edit the starting airport
	write("BOS");
 	clickOn("#dest"); // edit the destination airport
	write("LAX");
	clickOn("#goBut1"); // click on the search button	
	Text bottomText = lookup("#bottomText").query();
	Assertions.assertTrue(!bottomText.getText().isBlank()); // Since this is the role test for FD, so we
								// are just checking for the existence of content.
  }

  /**
   * Integration test for minimum distance route between LAX and BOS with a required layover of GYY.
   */
  @Test
  void IntegrationTest1() {
        write("p3huge.dot"); // directly write in the file name to load data in since the focus is
                             // originally on this text box
        clickOn("#buttonWel"); // click on the star tbutton
        clickOn("#button2"); // select the second route option
        clickOn("#start"); // edit the starting airport
        write("BOS");
        clickOn("#dest"); // edit the destination airport
        write("LAX");
	clickOn("#middle");
	write("GYY"); // edit the layover
        clickOn("#goBut2"); // click on the search button       
        Text bottomText = lookup("#bottomText").query();
        Assertions.assertEquals("BOS -> GYY -> COS -> LAX", bottomText.getText().trim()); // check for result
  }

  /**
   * Integration test for minimum distance route between BOS and FLG with a required layover of STL.
   */
  @Test
  void IntegrationTest2() {
        write("p3huge.dot"); // directly write in the file name to load data in since the focus is
                             // originally on this text box
        clickOn("#buttonWel"); // click on the star tbutton
        clickOn("#button2"); // select the second route option
        clickOn("#start"); // edit the starting airport
        write("BOS");
        clickOn("#dest"); // edit the destination airport
        write("FLG");
        clickOn("#middle");
        write("STL"); // edit the layover
        clickOn("#goBut2"); // click on the search button       
        Text bottomText = lookup("#bottomText").query();
        Assertions.assertEquals("BOS -> STL -> ICT -> FMN -> FLG", bottomText.getText().trim()); // check for result
  }


  /**
   * Initialization for backend, set all the boolean flag for correct calling methods to false for
   * further testing.
   */
  @BeforeEach
  void initBackend() {
    backend = new BackendDeveloper(new AirportNetworkReaderBD());
    AirportBD.airportConstructed = false;
    AirportNetworkReaderBD.ReaderCalled = false;
    RouteBD.RouteConstructed = false;
    TwoModePathFinderBD.getNodeCountCalled = false;
    TwoModePathFinderBD.getEdgeCountCalled = false;
    TwoModePathFinderBD.SDPath2 = false;
    TwoModePathFinderBD.SDPath3 = false;
    TwoModePathFinderBD.FDPath2 = false;
    TwoModePathFinderBD.FDPath3 = false;
    TwoModePathFinderBD.ContainsCalled = false;
  }

  /**
   * Tester for the loadFile method, should call DW's reader method. If correctly called,
   * AirportNetworkReaderBD.ReaderCalled will be set to true.
   */
  @Test
  void testLoadFile() {
    try { // Directly call loadFile method.
      backend.loadFile("text.txt");
    } catch (FileNotFoundException ignored) {}
    Assertions.assertTrue(AirportNetworkReaderBD.ReaderCalled);
    AirportNetworkReaderBD.ReaderCalled = false; // Reset the flag
    try { // Implicitly call loadFile method with constructor.
      backend = new BackendDeveloper(new AirportNetworkReaderBD(), "text.txt");
    } catch (FileNotFoundException ignored) {}
    Assertions.assertTrue(AirportNetworkReaderBD.ReaderCalled);
  }

  /**
   * Tester for BD isValidAirport method. Should call AE's contain method. If correctly called,
   * AirportBD.airportConstructed and TwoModePathFinderBD.ContainsCalled should all be true
   */
  @Test
  void testIsValidAirport() {
    try {
      backend.loadFile("validFile.txt");
    } catch (FileNotFoundException ignored) {}
    backend.isValidAirport("ABC");
    //Assertions.assertTrue(AirportBD.airportConstructed);
    Assertions.assertTrue(TwoModePathFinderBD.ContainsCalled);
  }

  /**
   * Tester for BD getNodeCount method. Should call AE's getNodeCount method. If correctly called,
   * TwoModePathFinderBD.getNodeCountCalled should be true
   */
  @Test
  void testGetNodeCount() {
    try {
      backend.loadFile("validFile.txt");
    } catch (FileNotFoundException ignored) {}
    backend.getNodeCount();
    Assertions.assertTrue(TwoModePathFinderBD.getNodeCountCalled);
  }

  /**
   * Tester for BD getEdgeCount method. Should call AE's getEdgeCount method. If correctly called,
   * TwoModePathFinderBD.getEdgeCountCalled should be true
   */
  @Test
  void testGetEdgeCount() {
    try {
      backend.loadFile("validFile.txt");
    } catch (FileNotFoundException ignored) {}
    backend.getEdgeCount();
    Assertions.assertTrue(TwoModePathFinderBD.getEdgeCountCalled);
  }

  /**
   * Tester for BD findQuickestPath method with two parameters. Should call AE's findQuickestPath method.
   * If correctly called, TwoModePathFinderBD.SDPath2 and RouteBD.RouteConstructed should be true
   */
  @Test
  void testFindQuickestPath2Args() {
    try {
      backend.loadFile("validFile.txt");
      backend.findQuickestPath("ABX",  "MSN");
      Assertions.assertTrue(TwoModePathFinderBD.SDPath2);
      //Assertions.assertTrue(RouteBD.RouteConstructed);
    } catch (IllegalArgumentException | InvalidAlgorithmParameterException | FileNotFoundException ignored) {}
  }

  /**
   * Tester for BD findQuickestPath method with three parameters. Should call AE's findQuickestPath method.
   * If correctly called, TwoModePathFinderBD.SDPath3 and RouteBD.RouteConstructed should be true
   */
  @Test
  void testFindQuickestPath3Args() {
    try {
      backend.loadFile("validFile.txt");
      backend.findQuickestPath("ABX", "PVG", "MSN");
      Assertions.assertTrue(TwoModePathFinderBD.SDPath3);
      //Assertions.assertTrue(RouteBD.RouteConstructed);
    } catch (IllegalArgumentException | InvalidAlgorithmParameterException | FileNotFoundException ignored) {}
  }

  /**
   * Tester for BD findFastestPath method with two parameters. Should call AE's findFastestPath method.
   * If correctly called, TwoModePathFinderBD.FDPath2 and RouteBD.RouteConstructed should be true
   */
  @Test
  void testFindFastestPath2Args() {
    try {
      backend.loadFile("validFile.txt");
      backend.findFastestPath("ABX", "MSN");
      Assertions.assertTrue(TwoModePathFinderBD.FDPath2);
      //Assertions.assertTrue(RouteBD.RouteConstructed);
    } catch (IllegalArgumentException | InvalidAlgorithmParameterException | FileNotFoundException ignored) {}
  }

  /**
   * Tester for BD findFastestPath method with three parameters. Should call AE's findFastestPath method.
   * If correctly called, TwoModePathFinderBD.FDPath3 and RouteBD.RouteConstructed should be true
   */
  @Test
  void testFindFastestPath3Args() {
    try {
      backend.loadFile("validFile.txt");
      backend.findFastestPath("ABX", "PVG", "MSN");
      Assertions.assertTrue(TwoModePathFinderBD.FDPath3);
      //Assertions.assertTrue(RouteBD.RouteConstructed);
    } catch (IllegalArgumentException | InvalidAlgorithmParameterException | FileNotFoundException ignored) {}
  }
}
