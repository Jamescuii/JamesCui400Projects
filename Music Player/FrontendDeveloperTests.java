// --== CS400 Spring 2023 File Header Information ==--
// Name: Ziqi Shen
// Email: zshen266@wisc.edu
// Team: BC
// TA: Naman Gupta
// Lecturer: Gary Dahl
// Notes to Grader: None

/*** JUnit imports ***/
// imports for JUnit tests
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
/*** JUnit imports end  ***/

import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Tester class for FrontendDeveloper
 * 
 */
public class FrontendDeveloperTests {

    // instance of FrontendDeveloper for JUnit tests
    protected FrontendDeveloper _instance = null;

    /**
     * This is a JUnit test method test for mainMenuPrompt() to check if
     * correctly performed
     */
    @Test
    public void test1() {
        // test case 1, test mainMenuPrompt() with valid input "Q\n"
        {
            TextUITester tester = new TextUITester("Q\n");
            Scanner scanner = new Scanner(System.in);
            SearchableSortedCollectionInterface<SongInterface> redBlackTree = new SearchableSortedCollectionBD();
            SongReaderInterface songReader = new SongReaderDW();
            BackendInterface backend = new BackendBD(redBlackTree, songReader);
            _instance = new FrontendDeveloper(scanner, backend); // initialize instance
            String returnString = _instance.mainMenuPrompt();
            String output = tester.checkOutput();
            assertEquals(true, (output.contains("Choose a command from the list below:")));
            assertEquals(true, (output.contains("    [L]Load data from file")));
            assertEquals(true, (output.contains("    [T]Search/Add Music by Title and Author")));
            assertEquals(true, (output.contains("    [RP]Remove from Playlist")));
            assertEquals(true, (output.contains("    [RL]Remove from Library")));
            assertEquals(true, (output.contains("    [DP]Display Playlist")));
            assertEquals(true, (output.contains("    [DL]Display Playlist Length")));
            assertEquals(true, (output.contains("    [DS]Display Playlist Size")));
            assertEquals(true, (output.contains("    [Q]Quit")));
            assertEquals("Q", returnString);
        }
    }

    /**
     * This is a JUnit test method test for runCommandLoop() to check if
     * correctly performed
     */
    @Test
    public void test2() {
        // test case 1, test runCommandLoop() non-valid input "C\nQ\n"
        {
            TextUITester tester = new TextUITester("C\nQ\n");
            Scanner scanner = new Scanner(System.in);
            SearchableSortedCollectionInterface<SongInterface> redBlackTree = new SearchableSortedCollectionBD();
            SongReaderInterface songReader = new SongReaderDW();
            BackendInterface backend = new BackendBD(redBlackTree, songReader);
            _instance = new FrontendDeveloper(scanner, backend); // initialize instance
            _instance.runCommandLoop();
            String output = tester.checkOutput();
            assertEquals(true, (output.contains("Didn't recognize that command.")));
            assertEquals(true, (output.contains("Please type one of the")));
            assertEquals(true, (output
                    .contains("letters presented within []s to identify the command you would like to choose.")));
        }
        // test case 2, test runCommandLoop() valid input "Q\n" to quit
        {
            TextUITester tester = new TextUITester("Q\n");
            Scanner scanner = new Scanner(System.in);
            SearchableSortedCollectionInterface<SongInterface> redBlackTree = new SearchableSortedCollectionBD();
            SongReaderInterface songReader = new SongReaderDW();
            BackendInterface backend = new BackendBD(redBlackTree, songReader);
            _instance = new FrontendDeveloper(scanner, backend); // initialize instance
            _instance.runCommandLoop();
            String output = tester.checkOutput();
            assertEquals(true, (output.contains("Thank you for using the Music Library Assistant App.")));
        }
    }

    /**
     * This is a JUnit test method test for loadDataCommand() to check if
     * correctly performed
     */
    @Test
    public void test3() {
        // test case 1 , invalide file to load
        {
            TextUITester tester = new TextUITester("music\n");
            Scanner scanner = new Scanner(System.in);
            SearchableSortedCollectionInterface<SongInterface> redBlackTree = new SearchableSortedCollectionBD();
            SongReaderInterface songReader = new SongReaderDW();
            BackendInterface backend = new BackendBD(redBlackTree, songReader);
            _instance = new FrontendDeveloper(scanner, backend); // initialize instance
            _instance.loadDataCommand();
            String output = tester.checkOutput();
            assertEquals(true, (output.contains("Error: Could not find or load file: music")));
        }
        // test case 2, valid file to load
        {

            TextUITester tester = new TextUITester("dataset.csv\n");
            Scanner scanner = new Scanner(System.in);
            SearchableSortedCollectionInterface<SongInterface> redBlackTree = new SearchableSortedCollectionBD();
            SongReaderInterface songReader = new SongReaderDW();
            BackendInterface backend = new BackendBD(redBlackTree, songReader);
            _instance = new FrontendDeveloper(scanner, backend); // initialize instance
            _instance.loadDataCommand();
            String output = tester.checkOutput();
            assertEquals(false, (output.contains("Error: Could not find or load file: music")));

        }
    }

    /**
     * This is a JUnit test method test for chooseSearchTitlePrompt() to check if
     * correctly performed
     */
    @Test
    public void test4() {
        TextUITester tester = new TextUITester("Style\n");
        Scanner scanner = new Scanner(System.in);
        SearchableSortedCollectionInterface<SongInterface> redBlackTree = new SearchableSortedCollectionBD();
        SongReaderInterface songReader = new SongReaderDW();
        BackendInterface backend = new BackendBD(redBlackTree, songReader);
        _instance = new FrontendDeveloper(scanner, backend); // initialize instance
        String returnString = _instance.chooseSearchTitlePrompt();
        assertEquals("Style", returnString);
        tester.checkOutput();
    }

    /**
     * This is a JUnit test method test for searchTitleCommand() to check if
     * correctly performed
     */
    @Test
    public void test5() {
        // test case 1, user would like to add to playlist with search result and input
        // a valid index
        {
            TextUITester tester = new TextUITester("L\ndataset.csv\nT\nlove stroy\ntaylor swift\n1\n0\nQ\n");
            Scanner scanner = new Scanner(System.in);
            SearchableSortedCollectionInterface<SongInterface> redBlackTree = new SearchableSortedCollectionBD();
            SongReaderInterface songReader = new SongReaderDW();
            BackendInterface backend = new BackendBD(redBlackTree, songReader);
            _instance = new FrontendDeveloper(scanner, backend); // initialize instance
            _instance.runCommandLoop();
            String output = tester.checkOutput();
            assertEquals(true, (output.contains("Song added successfully")));

        }
        // test case 2, user would like to add to playlist with search result and input
        // is out correct index range
        {
            TextUITester tester = new TextUITester("L\ndataset.csv\nT\nlove stroy\ntaylor swift\n5\n0\nQ\n");
            Scanner scanner = new Scanner(System.in);
            BackendInterface backend = new BackendDeveloperFD();
            _instance = new FrontendDeveloper(scanner, backend); // initialize instance
            _instance.runCommandLoop();
            String output = tester.checkOutput();
            assertEquals(true, (output.contains("Your input 5 is out of correct index range 0-1")));
        }
        // test case 3, user would not like to add to playlist with search result
        {
            TextUITester tester = new TextUITester("L\ndataset.csv\nT\nlove stroy\ntaylor swift\n0\nQ\n");
            Scanner scanner = new Scanner(System.in);
            BackendInterface backend = new BackendDeveloperFD();
            _instance = new FrontendDeveloper(scanner, backend); // initialize instance
            _instance.runCommandLoop();
            String output = tester.checkOutput();
            assertEquals(true, (output.contains("No song was added - go back to main menu")));
        }
    }

    /**
     * This is a JUnit test method test for checking code from different roles
     * working together to display Playlist
     */
    @Test
    public void Integration1() {
        SongReaderInterface songReader = new SongReaderDW();
        TextUITester tester;
        if (System.getProperty("user.dir").contains("jdt_ws")){
            tester = new TextUITester("L\nbin/dataset.csv\nT\nsunrise\nnorah jones\n1\nDP\nQ\n");
        } // my VSCODE will run JUnit in another folder causing dataset loaction problem
        else{
            tester = new TextUITester("L\ndataset.csv\nT\nsunrise\nnorah jones\n1\nDP\nQ\n");
        }
        Scanner scanner = new Scanner(System.in);
        SearchableSortedCollectionInterface<SongInterface> redBlackTree = new SearchableSortedCollectionAE<SongInterface>();
        BackendInterface backend = new BackendBD(redBlackTree, songReader);
        _instance = new FrontendDeveloper(scanner, backend); // initialize instance
        _instance.runCommandLoop();
        String output = tester.checkOutput();
        assertEquals(true, (output.contains("[Title: Sunrise; Author: Norah Jones; Genre: adult standards; " +
        "Year: 2004; Loudness: -14; Duration: 201; Popularity: 71]")));
    }

    /**
     * This is a JUnit test method test for checking code from different roles
     * working together to romove a song from playlist
     */
    @Test
    public void Integration2() {
        SongReaderInterface songReader = new SongReaderDW();
        TextUITester tester;
        if (System.getProperty("user.dir").contains("jdt_ws")){
            tester = new TextUITester("L\nbin/dataset.csv\nT\nsunrise\nnorah jones\n1\nRP\nsunrise\nnorah jones\nQ\n");
        } // my VSCODE will run JUnit in another folder causing dataset loaction problem
        else{
            tester = new TextUITester("L\nbin/dataset.csv\nT\nsunrise\nnorah jones\n1\nRP\nsunrise\nnorah jones\nQ\n");
        }
        Scanner scanner = new Scanner(System.in);
        SearchableSortedCollectionInterface<SongInterface> redBlackTree = new SearchableSortedCollectionAE<SongInterface>();
        BackendInterface backend = new BackendBD(redBlackTree, songReader);
        _instance = new FrontendDeveloper(scanner, backend); // initialize instance
        _instance.runCommandLoop();
        String output = tester.checkOutput();
        assertEquals(false, (output.contains("[Title: Sunrise; Author: Norah Jones; Genre: adult standards; " +
        "Year: 2004; Loudness: -14; Duration: 201; Popularity: 71]")));
    }

    /**
     * This is a JUnit test method for checking code from BackendDeveloper
     * for loadSongs() if input an incorrect file path
     */
    @Test
    public void CodeReviewOfBackendDeveloper1() {
        SearchableSortedCollectionInterface<SongInterface> redBlackTree = new
        SearchableSortedCollectionAE<SongInterface>();
        SongReaderInterface songReader = new SongReaderDW();
        BackendInterface backend = new BackendBD(redBlackTree, songReader);
        try {
            backend.loadSongs("test.txt");
        } catch (Exception e) {
            assertEquals("test.txt (No such file or directory)", e.getMessage(),
                    "Did not throw expected exception from AE for loadSongs() in BD with incorrect file path");
        }
    }

    /**
     * This is a JUnit test method test for for checking  code from BackendDeveloper
     * for removeSongFromLibrary() if there's no match in the library
     */
    @Test
    public void CodeReviewOfBackendDeveloper2() {

        SearchableSortedCollectionInterface<SongInterface> redBlackTree = new
        SearchableSortedCollectionAE<SongInterface>();
        SongReaderInterface songReader = new SongReaderDW();
        BackendInterface backend = new BackendBD(redBlackTree, songReader);
        try {
            backend.loadSongs("dataset.csv");
            SongInterface songRemoved = backend.removeSongFromLibrary("Ice Cream","Selena Gomez");
            // if songRemoved == null, then no match is found in library
            assertEquals(true, songRemoved == null,
                    "Removed wrong song when song should not be removed");
        }  catch (FileNotFoundException e) {

        }
        }
    }

