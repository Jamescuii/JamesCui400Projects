// --== CS400 File Header Information ==--
// Name: James Cui
// Email: jcui57@wisc.edu
// Group and Team: BCred
// Group TA: Naman Gupta
// Lecturer: Gary Dahl
// Notes to Grader: <optional extra notes>

import static org.junit.jupiter.api.Assertions.*;
import edu.wisc.cs.cs400.JavaFXTester;
import java.io.FileNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;

public class FrontendDeveloperTests extends JavaFXTester {

    public FrontendDeveloperTests() {
        super(FrontendDeveloper.class); // loads FrontendDeveloper class into tester
    }
    
    /**
     * This test checks the Welcome layout of the Flight Finder application.
     * Specifically when the user enters the opening menu.
     * This test makes sure that the proper buttons and text exist.
    */
    @Test
    void FrontendDeveloperTest1() {
      try {
        //Button button = lookup("#exitButton").query();
        Button buttonWel = lookup("#buttonWel").query();
        assertEquals("Click Me to Begin!", buttonWel.getText()); // checks the welcome button text

        Label welcomeMsg = lookup("#welcomeMsg").query();
        assertEquals("\n \n Welcome to the Flight Finder App!", welcomeMsg.getText()); 
        // checks if the welcome text is correct
        Label askFile = lookup("#askFile").query();

        // checks if the file text is correct
        assertEquals("\n \n \n \n \nPlease enter the data file you would like to load below \n", askFile.getText());

      }
      catch(AssertionError e) {
        fail("Not Supposed to Happen");
      }
      
    }
  
    /**
     * This test checks the behavior of the menu of Flight Finder application.
     * Specifically after the user passes the welcome menu.
     * This tester confirms that there is a search button, exit button
     * and a button for each search filter.
     */
    @Test
    void FrontendDeveloperTest2() {
      try {
        Button buttonWel = lookup("#buttonWel").query();
        write("p3mid.dot"); // load data file
        clickOn("#buttonWel"); // Tester clicks on welcome button
        interact( () -> buttonWel.fireEvent(new ActionEvent()) );

        Button button1 = lookup("#button1").query(); // finds all the buttons
        Button button2 = lookup("#button2").query();
        Button button3 = lookup("#button3").query();
        Button button4 = lookup("#button4").query();

        // checks if the texts on the buttons are all correct
        assertEquals("Minimum Distance", button1.getText());
        assertEquals("Minimum Distance +", button2.getText());
        assertEquals("Minimum Stops", button3.getText());
        assertEquals("Minimum Stops +", button4.getText());
        Button exitButton = lookup("#exitButton").query();
        // checks exit button text
        assertEquals("Exit", exitButton.getText());
      }
      catch(AssertionError e) {
        fail("Not Supposed to Happen");
      }
    }

    /**
     * This test checks the behavior of Flight Finder application.
     * Specifically when the user clicks on the Minimum Distance + tab.
     * This tester confirms that the interface has the proper text.
     */
    @Test
    void FrontendDeveloperTest3() {
        try {
            Button buttonWel = lookup("#buttonWel").query();
            write("p3mid.dot"); // loads data file
            clickOn("#buttonWel"); // Tester clicks on welcome button
            interact( () -> buttonWel.fireEvent(new ActionEvent()) );

            Button button2 = lookup("#button2").query();
            clickOn("#button2"); // clicks on second tab
            interact( () -> button2.fireEvent(new ActionEvent()) );
            // sets the ids for all the text on the second tab
            Text start2 = lookup("#textStart").query();
            Text dest2 = lookup("#textDest").query();
            Text middle2 = lookup("#textMiddle").query();
            Button goBut2 = lookup("#goBut2").query();
            // checks if the text is correct
            assertEquals("Search!", goBut2.getText());
            assertEquals("\n \n \n Enter the IATA of the Airport you will like to Travel From", start2.getText());
            assertEquals("\n \n Enter the IATA of the Airport you will like to Travel To", dest2.getText());
            assertEquals("\n \n Enter the IATA of the Airport you will like to Pass Through", middle2.getText());

            Text result2 = lookup("#result2").query();
            assertEquals("\n \n \n \n \n \n \n Fastest Path by Distance Adv.", result2.getText());
          }
      catch(AssertionError e) {
        fail("Not Supposed to Happen");
      }
    }

    /**
     * This test checks the behavior of Flight Finder application.
     * Specifically when the user clicks on the Minimum Stops tab.
     * This tester confirms that the interface has the proper text.
     */
    @Test
    void FrontendDeveloperTest4() {
        try {
            Button buttonWel = lookup("#buttonWel").query();
    
            write("p3mid.dot"); // loads data file 
            clickOn("#buttonWel"); // Tester clicks on the welcome button 
            interact( () -> buttonWel.fireEvent(new ActionEvent()) );
            
            Button button3 = lookup("#button3").query();
            clickOn("#button3"); // Tester clicks on the third tab
            interact( () -> button3.fireEvent(new ActionEvent()) );
            // Settings all the ideas for all the text/button
            Text start3 = lookup("#textStart").query();
            Text dest3 = lookup("#textDest").query();
            Button goBut3 = lookup("#goBut3").query();
            // Tests if the text is correct
            assertEquals("Search!", goBut3.getText());
            assertEquals("\n \n \n Enter the IATA of the Airport you will like to Travel From", start3.getText());
            assertEquals("\n \n Enter the IATA of the Airport you will like to Travel To", dest3.getText());
            //System.out.println(start3.getText());
            //System.out.println(dest3.getText());
            Text result3 = lookup("#result3").query();
            assertEquals("\n \n \n \n \n \n \n \n \n \nFastest Path by Stops", result3.getText());
          }
      catch(AssertionError e) {
        fail("Not Supposed to Happen");
      }
    }

    /**
     * This test checks the behavior of Flight Finder application.
     * Specifically when the user clicks on the Minimum Stops + tab
     * and types something into the format. This tester confirms that
     * an error will pop up if nothing is entered.
     */
    @Test
    void FrontendDeveloperTest5() {
      try {
        Button buttonWel = lookup("#buttonWel").query();
        write("p3mid.dot"); // loads data file
        clickOn("#buttonWel"); // Tester clicks on welcome button
        interact( () -> buttonWel.fireEvent(new ActionEvent()) );
 
        Button button4 = lookup("#button4").query();
        clickOn("#button4"); // Tester clicks on the fourth tab
        interact( () -> button4.fireEvent(new ActionEvent()) );

        // sets id for buttons and tests if the text is correct
        Button goBut4 = lookup("#goBut4").query();
        assertEquals("Search!", goBut4.getText());

        Text bottomText = lookup("#bottomText").query();
        interact( () -> goBut4.fireEvent(new ActionEvent()) );
        assertEquals("\nERROR: Invalid Airport(s)", bottomText.getText());
      }
      catch(AssertionError e) {
        fail("Not Supposed to Happen");
      }
    }

    /**
    * This is an additional tester for the Backend getter methods
    * This tester checks if the loadFile method works in the Backend
    * and if the getter methods work.
    */
    @Test
    void CodeReviewForBackendDeveloperTest1() {
      BackendDeveloper Backend = new BackendDeveloper(new AirportNetworkReaderDW());
      try {
        Backend.loadFile("Invalid File"); // Test the exception throwing
        Assertions.fail();
      } 
      catch (FileNotFoundException ignored) {}
      try {
        // Test if the load file method correctly loads in the data
        Backend.loadFile("p3mid.dot");
        Assertions.assertEquals(228, Backend.getNodeCount());
	//System.out.println(Backend.getNodeCount());
	//System.out.println(Backend.getEdgeCount());
        Assertions.assertEquals(454, Backend.getEdgeCount());
      } 
      catch (Exception e) {
        Assertions.fail("Not Supposed to Happen");
      }
    }

    /**
    * This is an additional tester for the Backend isValidAirport method
    * This tester checks if the loadFile method works in the Backend
    * and if the method isValidAirport works.
    */
    @Test
    void CodeReviewForBackendDeveloperTest2() {
      BackendDeveloper Backend = new BackendDeveloper(new AirportNetworkReaderDW());
      try {
        // Test if the isValidAirport method correctly judges the following airports
        Backend.loadFile("p3huge.dot");
        Assertions.assertTrue(Backend.isValidAirport("LAX"));
        Assertions.assertTrue(Backend.isValidAirport("BoS"));
        Assertions.assertTrue(Backend.isValidAirport("jfk"));
        Assertions.assertFalse(Backend.isValidAirport("ABCD"));
        Assertions.assertFalse(Backend.isValidAirport("ABC"));
      }
      catch (Exception e) {
        Assertions.fail("Not Supposed to Happen");
      }
    }

    /**
     * This is an Integration test that tests the whole application.
     * In this test the application runs to test if the app finds the
     * correct minimum distance route between BOS and RKO.
    */
    @Test
    void IntegrationTest1() {
      write("p3large.dot"); // load data file
      clickOn("#buttonWel"); // Tester clicks on the start button
      clickOn("#button1"); // Tester clicks on Min. Distance option
      clickOn("#start"); // edit start airport
      write("BOS");
      clickOn("#dest"); // edit destination airport
      write("EKO");
      clickOn("#goBut1"); // Testere clicks Search!
      Text bottomText = lookup("#bottomText").query();
      Assertions.assertEquals("BOS -> GYY -> EKO", bottomText.getText().trim()); // checks result
    }

    /**
     * This is an Integration test that tests the whole application.
     * In this test the application runs to test if the app can find the
     * correct minimum stop route between EKO to CAK, going through BOS.
     */
    @Test
    void IntegrationTest2() {
      write("p3large.dot"); // load data file
      clickOn("#buttonWel"); // Tester clicks on the start button
      clickOn("#button4"); // Tester clicks on Min. Stop + option
      clickOn("#start"); // edit the starting airport
      write("EKO");
      clickOn("#dest"); // edit the destination airport
      write("CAK");
      clickOn("#middle");
      write("BOS"); // edit the layover
      clickOn("#goBut4"); // click on the search button       
      Text bottomText = lookup("#bottomText").query();
      Assertions.assertEquals("EKO -> OMA -> BOS -> CAK", bottomText.getText().trim()); // check for result
    }
}
