// --== CS400 Spring 2023 File Header Information ==--
// Name: James Cui
// Email: jcui57@wisc.edu
// Team: BC
// TA: Naman Gupta
// Lecturer: Gary Dahl

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DataWranglerTests {

  protected RedBlackTree<Integer> _instance = null;
  
  /**
   * This test tests the equals method in the SongDW class.
   * This test creates various instances and then checks if the
   * method returns the expected output.
   */
  @Test
  void testSong1() {
    try {
      SongDW song1 = new SongDW("Sunrise", "Norah Jones", "adult standards", 2004, -14, 201, 71);
      SongDW song2 = new SongDW("Sunrise", "Norah Jones", "adult standards", 2004, -14, 201, 71);
      SongDW song3 = new SongDW("Black Night", "Deep Purple", "album rock", 2000, -11, 207, 39);
      assertEquals(true, song1.equals(song2));
      assertEquals(false, song1.equals(song3));
    }
    catch(AssertionError e) {
      fail("Not Supposed to Happen");
    }
  }
  
  /**
   * This test tests the compareTo method in the SongDW class
   * This test creates various instances and then checks if the 
   * method returns the output.
   */
  @Test
  void testSong2() {
    try {
      SongDW song1 = new SongDW("Sunrise", "Norah Jones", "adult standards", 2004, -14, 201, 71);
      SongDW song2 = new SongDW("Sunrise", "Norah Jones", "adult standards", 2004, -14, 201, 71);
      SongDW song3 = new SongDW("Black Night", "Deep Purple", "album rock", 2000, -11, 207, 39);
      int x = song1.compareTo(song2);
      int y = song1.compareTo(song3);
      assertTrue(x == 0);
      assertTrue(y > 0);
    }
    catch(AssertionError e) {
      fail("Not Supposed to Happen");
    }
  }
  
  /**
   * This test tests the toString method and the getter methods in
   * the SongDW class. This test checks if the toString matches the 
   * expected output and if the getter methods match the expected
   * output.
   */
  @Test
  void testSong3() {
    try {
      SongDW song1 = new SongDW("Sunrise", "Norah Jones", "adult standards", 2004, -14, 201, 71);
      assertEquals("Sunrise by Norah Jones", song1.toString());
      assertEquals(song1.getYear(), 2004);
      assertEquals(song1.getDuration(), 201);
      assertEquals(song1.getPopularity(), 71);
      assertEquals(song1.getGenre(), "adult standards");
    }
    catch(AssertionError e) {
      fail("Not Supposed to Happen");
    }
  }
  
  /**
   * This test tests the readSongsFromFile method in the SongReaderDW class.
   * This test specifically tests when songs have commas within the CSV file
   * It uses a text file and reads each line and makes a song. The expected
   * output list is made and compared with the method output.
   */
  @Test
  void testSongReader1() {
    try {
      
      List<SongInterface> correct = new ArrayList<>();
      SongDW song1 = new SongDW("Sunrise", "Norah Jones", "adult standards", 2004, -14, 201, 71);
      SongDW song2 = new SongDW("Black Night", "Deep Purple", "album rock", 2000, -11, 207, 39);
      SongDW song3 = new SongDW("You're The First, The Last, My Everything", "Barry White", "adult standards", 2009, -7, 213, 67);
      correct.add(song1);
      correct.add(song2);
      correct.add(song3);
      SongReaderDW tester = new SongReaderDW();
      try {
        List<SongInterface> testList = tester.readSongsFromFile("test1.txt");
        assertEquals(correct, testList);
        assertEquals(correct.size(), testList.size());
      }
      catch(FileNotFoundException e) {
        fail("Not Supposed to Happen");
      }
    }
    catch(AssertionError e) {
      fail("Not Supposed to Happen");
    }
  }
  
  /**
   * This test tests the readSongsFromFile method in the SongReaderDW class.
   * This test specifically tests when songs have quotes within the CSV file
   * It uses a text file and reads each line and makes a song. The expected
   * output list is made and compared with the method output.
   */
  @Test
  void testSongReader2() {
    try {
      List<SongInterface> correct = new ArrayList<>();
      SongDW song1 = new SongDW("Sunrise", "Norah Jones", "adult standards", 2004, -14, 201, 71);
      SongDW song2 = new SongDW("Black Night", "Deep Purple", "album rock", 2000, -11, 207, 39);
      String temp = "Listen (From the Motion Picture " + '\u0022' + "Dreamgirls" +'\u0022' + ")";
      SongDW song3 = new SongDW(temp, "Beyoncé", "dance pop", 2006, -5, 218, 44);
      correct.add(song1);
      correct.add(song2);
      correct.add(song3);
      SongReaderDW tester = new SongReaderDW();
      try {
        List<SongInterface> testList = tester.readSongsFromFile("test2.txt");
        assertEquals(correct, testList);
        assertEquals(correct.size(), testList.size());
      }
      catch(FileNotFoundException e) {
        fail("Not Supposed to Happen");
      }
    }
    catch(AssertionError e) {
      fail("Not Supposed to Happen");
    }
  }
  
  /**
   * This Integration tests the Backend addSong method in the BackendBD class.
   * This test first loads the songs in and then add the songs to the playlist.
   * The load songs uses the DataWrangler method readSongsFromFile.
   * The expected output of playlist and size are compared with the expected output.
   */
  @Test
  void testIntegration1() {
    try {
      SongReaderDW tester = new SongReaderDW();
      SearchableSortedCollectionInterface<SongInterface> testSSC = new SearchableSortedCollectionAE<>();
      BackendInterface testBackend = new BackendBD(testSSC, tester);
      
      SongDW song1 = new SongDW("Sunrise", "Norah Jones", "adult standards", 2004, -14, 201, 71);
      SongDW song2 = new SongDW("Black Night", "Deep Purple", "album rock", 2000, -11, 207, 39);
      List<String> correct = new ArrayList<>();
      correct.add("Title: Sunrise; Author: Norah Jones; Genre: adult standards;"
          + " Year: 2004; Loudness: -14; Duration: 201; Popularity: 71");
      correct.add("Title: Black Night; Author: Deep Purple; Genre: album rock;"
          + " Year: 2000; Loudness: -11; Duration: 207; Popularity: 39");
      try {
        testBackend.loadSongs("test1.txt");
        testBackend.addSong(song1);
        testBackend.addSong(song2);
  
        assertEquals(correct, testBackend.getPlaylistInfoString());
        assertEquals("The playlist contains 2 songs.", testBackend.getPlaylistSize());
      }
      catch(FileNotFoundException fne) {
        fail("Not Supposed to Happen");
      }
    }
    catch(AssertionError e) {
      fail("Not Supposed to Happen");
    }
  }

  /**
   * This Integration tests the Backend remnoveSong method in the BackendBD class.
   * This test first loads the songs in and then add the songs to the playlist.
   * Then the test then removes one song from the playlist.
   * The load songs uses the DataWrangler method readSongsFromFile.
   * The expected output of playlist and size are compared with the expected output.
   */
  @Test
  void testIntegration2() {
    try {
      SongReaderDW tester = new SongReaderDW();
      SearchableSortedCollectionInterface<SongInterface> testSSC = new SearchableSortedCollectionAE<>();
      BackendInterface testBackend = new BackendBD(testSSC, tester);
      
      SongDW song1 = new SongDW("Sunrise", "Norah Jones", "adult standards", 2004, -14, 201, 71);
      SongDW song2 = new SongDW("Black Night", "Deep Purple", "album rock", 2000, -11, 207, 39);
      List<String> correct = new ArrayList<>();
      correct.add("Title: Black Night; Author: Deep Purple; Genre: album rock;"
          + " Year: 2000; Loudness: -11; Duration: 207; Popularity: 39");
      try {
        testBackend.loadSongs("test1.txt");
        testBackend.addSong(song1);
        testBackend.addSong(song2);
        
        testBackend.removeSongFromPlaylist("Sunrise", "Norah Jones");
  
        assertEquals(correct, testBackend.getPlaylistInfoString());
        assertEquals("The playlist contains 1 songs.", testBackend.getPlaylistSize());
      }
      catch(FileNotFoundException fne) {
        fail("Not Supposed to Happen");
      }
    }
    catch(AssertionError e) {
      fail("Not Supposed to Happen");
    }
  }
  
  /**
   * This test tests the search method in the SearchableSortedCollectionAE class.
   * The names are inserted into the RB tree and search is expected
   * to return the 5 closest nodes.
   */
  @Test
  void testCodeReviewOfAlgorithmEngineer1() {
    try {
      
      SearchableSortedCollectionInterface<String> RBTString = new SearchableSortedCollectionAE<>();
      RBTString.insert("Adam");
      RBTString.insert("David");
      RBTString.insert("Brian");
      RBTString.insert("Zack");
      RBTString.insert("Cynthia");
      RBTString.insert("Simon");
      Assertions.assertEquals("[Cynthia, Brian, David, Adam, Simon]", RBTString.search("Cynthia").toString());
      Assertions.assertEquals("[David, Cynthia, Simon, Brian, Zack]", RBTString.search("David").toString());
      Assertions.assertEquals("[Adam, Brian, Cynthia, David, Simon]", RBTString.search("Adam").toString());
      Assertions.assertEquals("[David, Simon, Cynthia, Zack, Brian]", RBTString.search("Elise").toString());
    }
    catch(AssertionError e) {
      fail("Not Supposed to Happen");
    }
  }
  
  /**
   * This test tests the remove method in the SearchableSortedCollectionAE class.
   * The names are inserted into the RB tree and then removed. The toLevelOrderString
   * method is used to determine if the names are actually removed.
   */
  @Test
  void testCodeReviewOfAlgorithmEngineer2() {
    try {
      
      SearchableSortedCollectionAE<String> RBTString = new SearchableSortedCollectionAE<>();
      RBTString.insert("Adam");
      RBTString.insert("David");
      RBTString.insert("Brian");
      RBTString.insert("Zack");
      RBTString.insert("Cynthia");
      RBTString.insert("Simon");
      RBTString.remove("Cynthia");
      Assertions.assertEquals("[ Brian, Adam, Simon, David, Zack ]", RBTString.toLevelOrderString());

      RBTString.remove("Zack");
      Assertions.assertEquals("[ Brian, Adam, Simon, David ]", RBTString.toLevelOrderString());

      RBTString.remove("Adam");
      Assertions.assertEquals("[ David, Brian, Simon ]", RBTString.toLevelOrderString());

    }
    catch(AssertionError e) {
      fail("Not Supposed to Happen");
    }
  }

}
