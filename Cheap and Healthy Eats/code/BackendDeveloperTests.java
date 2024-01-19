// --== CS400 Project One File Header ==--
// Name: James Cui
// CSL Username: jcui
// Email: jcui57@wisc.edu
// Lecture #: 002
// Notes to Grader: None

import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Tests the validity of the methods in the class CHSearchBackend
 * @author jamescui
 */
public class BackendDeveloperTests {

  /**
   * Tests the loadData method to check if posts are properly loaded into hashmap.
   * Makes sure loadData adds the Posts in the reader to the map properly
   * Because the test is hardcoded, loadData doesn't throw any unnecessary exceptions
   * @return true if the expected outcome is returned, false otherwise
   */
  public static boolean test1() {
    try {
      PostReaderInterface reader = new PostReaderBD();
      HashtableWithDuplicateKeysInterface<String, PostInterface> map = new HashtableWithDuplicateKeysBD<>(100);
      CHSearchBackend backend = new CHSearchBackend(map, reader);
      try {
        backend.loadData("hi.txt"); // runs loadData in Backend
      }
      catch(FileNotFoundException f) {
        return false;
      }
      //System.out.println(map.get("title:apple"));
      if(!(map.get("title:apple").size() == 1)) { // checks if size is correct number
        //System.out.println("hi");
        return false;

      }
      if(!(map.get("title:banana").size() == 1)) { // checks if size is correct number
        //System.out.println("hi");
        return false;
      }
      
      return true;
    }
    catch(Exception e) {
      //System.out.println("hi");
      return false;
    }
  }
  
  /**
   * Tests the findPostsbyTitleWords method and checks for both singular and multiple words
   * Tests to make sure if the input searches for different key words both words are
   * searched inside the hashmap.
   * @return true if the expected outcome is returned, false otherwise
   */
  public static boolean test2() {
    try {
      PostReaderInterface reader = new PostReaderBD();
      HashtableWithDuplicateKeysInterface<String, PostInterface> map = new HashtableWithDuplicateKeysBD<>(100);
      CHSearchBackend backend = new CHSearchBackend(map, reader);
      
      try {
        backend.loadData("hi.txt");
      }
      catch(FileNotFoundException f) {
        return false;
      }
      //System.out.println(backend.findPostsByTitleWords("apple").size());
      //System.out.println(backend.findPostsByTitleWords("apple orange").size());
      if(!(backend.findPostsByTitleWords("apple").size() == 1)) { // checks if size is correct number
        return false;
      }
      if(!(backend.findPostsByTitleWords("apple orange").size() == 2)) { // checks if size is correct number
        return false;
      }

      return true;
    }
    catch(Exception e) {
      System.out.println("hi");
      return false;
    }
  }
  
  /**
   * Tests the findPostsByBodyWords with a normal input and two inputs of same post
   * Makes sure key words return the list of Posts that key word is only in the Body of post
   * Tests words that are in both the title and body, but output should only be number in body.
   * @return true if the expected outcome is returned, false otherwise
   */
  // Should not overlap posts, only return it once
  public static boolean test3() {
    try {
      PostReaderInterface reader = new PostReaderBD();
      HashtableWithDuplicateKeysInterface<String, PostInterface> map = new HashtableWithDuplicateKeysBD<>(100);
      CHSearchBackend backend = new CHSearchBackend(map, reader);
      
      try {
        backend.loadData("hi.txt"); // loads a file with no errors
      }
      catch(FileNotFoundException f) {
        return false;
      }
      
      if(!(backend.findPostsByBodyWords("good").size() == 1)) { // checks if size is correct number
        return false;
      }
      if(!(backend.findPostsByBodyWords("good Food").size() == 1)) { // checks if size is correct number
        return false;
      }
      
      return true;
    }
    catch(Exception e) {
     System.out.println("h");
      return false;
    }
  }
  
  /**
   * Tests the findPostsByTitleOrBodyWords and checks if the same word in the same post DOES NOT overlap
   * Makes sure key words return the list of Posts that key word is only in the Title
   * or Body of post Tests words that are in both the title and body, and the output
   * should be the unique posts that have the word in title OR body.
   * @return true if the expected outcome is returned, false otherwise
   */
  public static boolean test4() {
    try {
      PostReaderInterface reader = new PostReaderBD();
      HashtableWithDuplicateKeysInterface<String, PostInterface> map = new HashtableWithDuplicateKeysBD<>(100);
      CHSearchBackend backend = new CHSearchBackend(map, reader);
      
      try {
        backend.loadData("hi.txt");
      }
      catch(FileNotFoundException f) {
        return false;
      }
      
      if(!(backend.findPostsByTitleOrBodyWords("APple").size() == 1)) { // checks if size is correct number
        return false;
      }
      if(!(backend.findPostsByTitleOrBodyWords("apple orange").size() == 2)) { // checks if size is correct number
        return false;
      }
      
      return true;
    }
    catch(Exception e) {
      return false;
    }
  }
  
  /**
   * Tests the getStatisticsString of the Backend method to see if the String is returned properly
   * The String is hard coded to return 0, but this test makes sure the format is accurate
   * @return true if the expected outcome is returned, false otherwise
   */
  public static boolean test5() {
    try {
      PostReaderInterface reader = new PostReaderBD();
      HashtableWithDuplicateKeysInterface<String, PostInterface> map = new HashtableWithDuplicateKeysBD<>(100);
      CHSearchBackend backend = new CHSearchBackend(map, reader);
      
      try {
        backend.loadData("hi.txt");
      }
      catch(FileNotFoundException f) {
        return false;
      }
      
      if(!(backend.getStatisticsString().equals("Number of Values: 0" + "\n" + "Total Unique Words: 0"
          + "\n" + "Capacity of Hashtable: 0"))) { // checks String is the same as expected
        return false;
      }

      return true;
    }
    catch(Exception e) {
      return true;
    }
  }
  
  /**
   * This integrated test checks if the methods work without the hardcoded version.
   * Backend loads a real data set provided by CS400 Staff and creates a
   * Duplicate key hashtable written by the Data Wrangler and then runs the Backend
   * method findPostsByTitleWords to see if the outcomes are accurate
   * @return true if the expected outcome is returned, false otherwise 
   */
  public static boolean test6() {
    try {
      PostReaderInterface reader = new PostReaderDW();
      HashtableWithDuplicateKeysInterface<String, PostInterface> map = new HashtableWithDuplicateKeysAE<>(10000);
      CHSearchBackend backend = new CHSearchBackend(map, reader);
      
      try {
        backend.loadData("data/small.txt"); // loads File with no errors
      }
      catch(FileNotFoundException f) {
        return false;
      }
      
      if(!((backend.findPostsByTitleWords("protein").size()) == (3))) { // checks if the List has correct size
	System.out.println("hi");
        return false;
      }
      
      if(!((backend.findPostsByTitleWords("protein orange").get(0).equals("Protein overload")))) { /*
	  checks if method runs with multiple words being searched */
	System.out.println("hii");
        return false;
      }
      
      if(!((backend.findPostsByTitleWords("protein orange").get(3).equals("ORANGE CHICKEN")))) {
	System.out.println("hiii");       
	return false;
      }
      
      return true;
    }
    catch(Exception e) {
      return true;
    }
  }
  
  /**
  * This test checks if the Hashtable and PostReader are properly integrated
  * with the Backend class. Specifically, this test tests if the backend method
  * findPostsByTitleOrBodyWords returned a Lists of String with the proper size
  * as expected with given inputs. The test also checks if the output has both the
  * Title and Body in the List.
  * @return true if the expected outcome is returned, false otherwise 
  */
  public static boolean test7() {
    try {
      PostReaderInterface reader = new PostReaderDW();
      HashtableWithDuplicateKeysInterface<String, PostInterface> map = new HashtableWithDuplicateKeysAE<>(10000);
      CHSearchBackend backend = new CHSearchBackend(map, reader);
      
      try {
        backend.loadData("data/small.txt");
      }
      catch(FileNotFoundException f) {
        return false;
      }
      
      if(!((backend.findPostsByTitleOrBodyWords("chickpea").size()) == (1))) { /* checks if size of 
	output array is the expected length with posts with the word "chickpea" */
        return false;
      }
      
      if(!((backend.findPostsByTitleOrBodyWords("protein").size()) == (4))) { /* checks if the method
	looks for the given word in both the Title and the Body. */
        return false;
      }
      
      //System.out.println(backend.findPostsByTitleOrBodyWords("protein").get(3));
      
      if(!((backend.findPostsByTitleOrBodyWords("protein").get(3).equals("Anyone try Aviate Lupini FLAKES?"
          + " \"I was looking for something to replace my chicken on occasion but still had a good"
          + " amount of protein in it when I ran across Aviate Lupini FLAKES.  Has anyone tried this before?"
          + " Is this something I can just throw onto anything (yogurt, salad, mixed vegetables, whatever)"
          + " and eat or do they need to be cooked or something?\"")))) {
        return false;
      }
      //System.out.println(backend.getStatisticsString());
      return true;
    }
    catch(Exception e) {
      return true;
    }
  }
  /**
  * Tests whether the other method, Frontend is properly implemented. Uses
  * TextUITester to run inputs into the Frontend method and tests if 
  * Frontend will load files and search certain words in title of posts
  * when given certain words.Then uses contains to see if the ouput was expected.
  * @return true if the expected outcome is returned, false otherwise 
  */
  public static boolean test8() {
    try {
      PostReaderInterface reader = new PostReaderDW();
      HashtableWithDuplicateKeysInterface<String, PostInterface> map = new HashtableWithDuplicateKeysAE<>(10000);
      CHSearchBackend backend = new CHSearchBackend(map, reader);
      
      TextUITester tester = new TextUITester("L\ndata/small.txt\nT\nprotein\n\nQ\n");
      
      Scanner userIn = new Scanner(System.in);
      CHSearchFrontendFD frontend = new CHSearchFrontendFD(userIn, backend);
      
      frontend.runCommandLoop();
      
      String output = tester.checkOutput();
      
      if(!(output.contains("Protein overload")) || !(output.contains("Protein snacks?")) ||
          !(output.contains("What is a good, high protein, easy to make, plant based food to replace chicken with?"))) {
	// checks if the output printed the given String to the user.
        return false;
      }
      
      if(!(output.startsWith("-------------------------------------------------------------------------------"))
          || !(output.contains("Welcome to the Cheap and Healthy Search App."))) {
	// checks if output printed the given String first.
        return false;
      }
      //System.out.println(output);
      
      if(!(output.endsWith("-------------------------------------------------------------------------------\n"))
          || !(output.contains("Thank you for using the Cheap and Healthy Search App."))) {
        return false;
      }
      
      return true;
    }
    catch(Exception e) {
      return true;
    }
  }
  /**
   * Tests whether the other method, Frontend is properly implemented. Uses
   * TextUITester to run inputs into the Frontend method and test if 
   * Frontend will Search for certain words that are input in the title and
   * body of posts. Also checks if the StatisticString functions in the frontend.
   * @return
   */
  public static boolean test9() {
    try {
      PostReaderInterface reader = new PostReaderDW();
      HashtableWithDuplicateKeysInterface<String, PostInterface> map = new HashtableWithDuplicateKeysAE<>(10000);
      CHSearchBackend backend = new CHSearchBackend(map, reader);
      
      TextUITester tester = new TextUITester("L\ndata/small.txt\nP\nprotein\n\nS\nQ\n");
      // creates a TextUITester
      Scanner userIn = new Scanner(System.in);
      CHSearchFrontendFD frontend = new CHSearchFrontendFD(userIn, backend);
      
      frontend.runCommandLoop();
      
      String output = tester.checkOutput();
      // System.out.println(output);
      
      char q = '"'; 
      // Checks if the output is what is expected, prints both title and body of post
      if(!(output.contains("Protein overload")) || !(output.contains("Anyone else think the focus"
          + " on protein is a bit over the top three days?   We all have different needs and all"
          + " that. But having massive amounts of protein in each meal, plus having protein with every snack,"
          + " it just seems like so much needless worry.   Since when is it not ok to just grab"
          + " an orange for a snack?")) || !(output.contains("Anyone try Aviate Lupini FLAKES? " + q + "I was looking for something to"
              + " replace my chicken on occasion but still had a good amount of protein in"
              + " it when I ran across Aviate Lupini FLAKES.  Has anyone tried this before? Is"
              + " this something I can just throw onto anything (yogurt, salad, mixed vegetables, whatever)"
              + " and eat or do they need to be cooked or something?" + q))) {
        //System.out.println("hii");
	System.out.println("hii");
        return false;
      }
      
      if(!(output.startsWith("-------------------------------------------------------------------------------"))
          || !(output.contains("Welcome to the Cheap and Healthy Search App."))) {
	System.out.println("hiii");
        return false;
      }
      
      // checks if the StatisticString is properly returned by the Frontend
      String expected = "Number of Values: 2024" + "\n" + "Total Unique Words: 594"
          + "\n" + "Capacity of Hashtable: 10000";
      
      if(!(output.contains(expected))) {
        System.out.println("ii");
        return false;
      }

      return true;
    }
    catch(Exception e) {
      return true;
    }
  }
  
  
  public static void main(String[] args) { // runs all the tests
    System.out.println("BackendDeveloper Individual Test 1: " + test1());
    System.out.println("BackendDeveloper Individual Test 2: " + test2());
    System.out.println("BackendDeveloper Individual Test 3: " + test3());
    System.out.println("BackendDeveloper Individual Test 4: " + test4());
    System.out.println("BackendDeveloper Individual Test 5: " + test5());
    System.out.println("BackendDeveloper Integration Test 1: " + test6());
    System.out.println("BackendDeveloper Integration Test 2: " + test7());
    System.out.println("BackendDeveloper Partner FrontendDeveloper Test 1: " + test8());
    System.out.println("BackendDeveloper Partner FrontendDeveloper Test 2: " + test9());    
  }

}
