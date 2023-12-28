// --== CS400 Spring 2023 File Header Information ==--
// Name: Cornelia (Zhouzhou) Chu
// Email: zchu26@wisc.edu
// Team: BC
// TA: Naman Gupta
// Lecturer: Gary Dahl Lec002 1:00 to 2:15 pm
// Notes to Grader: <optional extra notes>
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;

/**
 * test the functionality of backend code
 * @author Cornelia Chu
 */
public class BackendDeveloperTests {
    protected BackendBD bd;

    /**
     * create backend instance before each test
     */
    @BeforeEach
    @SuppressWarnings("unchecked")
    public void createRBT() {
        this.bd = new BackendBD(new SearchableSortedCollectionBD(), new SongReaderBD());
        try {
            bd.loadSongs("test");
	} catch (Exception e) {
	
	}
    }

    /**
     * test loadSongs() which should load all the songs from a file into the music library
     */
    @Test
    public void test1()  {
        try {
            // check size of music library after loadSongs()
            assertEquals(4, bd.redBlackTree.size(),
                    "size of music library after loadSongs() is not correct");
            // check the contents of music library after loadSongs()
            SongInterface song1 = new SongBD("Lose You to Love Me", "", "", -1, -1, -1, -1);
            assertEquals(true, bd.redBlackTree.contains(song1),
                    "\"Lose You to Love Me\" is not in the music library");
            SongInterface song2 = new SongBD("Same Old Love", "", "", -1, -1, -1, -1);
            assertEquals(true, bd.redBlackTree.contains(song2),
                    "\"Same Old Love\" is not in the music library");
            SongInterface song3 = new SongBD("Who Says", "", "", -1, -1, -1, -1);
            assertEquals(true, bd.redBlackTree.contains(song3),
                    "\"Who Says\" is not in the music library");
            SongInterface song4 = new SongBD("Who Says Remix", "", "", -1, -1, -1, -1);
            assertEquals(true, bd.redBlackTree.contains(song4),
                    "\"Who Says Remix\" is not in the music library");
        } catch (Exception e) {
            //assertEquals("file not found", e.getMessage(), "error message doesn't match");
        }
    }

    /**
     * test searchSongsByTitle() which should search similar songs based on the song's title from the music library
     */
    @Test
    public void test2() {
        try {
            // find similar songs based on song title input from music library
            List<SongInterface> foundSimilarSongs = bd.searchSongsByTitle("Who Says", "Selena Gomez");
            SongInterface song1 = new SongBD("Who Says", "", "", -1, -1, -1, -1);
            SongInterface song2 = new SongBD("Who Says Remix", "", "", -1, -1, -1, -1);
            // check if similar songs based on song title input are found
            assertEquals(true, foundSimilarSongs.contains(song1),
                    "\"Who Says\" is not found in the music library");
            assertEquals(true, foundSimilarSongs.contains(song2),
                    "\"Who Says Remix\" is not found in the music library");
        } catch (Exception e) {
            //assertEquals("file not found", e.getMessage(), "error message doesn't match");
        }
    }

    /**
     * test addSong() which should add the song requested by FD into the playlist
     */
    @Test
    public void test3() {
        try {
            // add exact-match song into the playlist
            SongInterface song1 = new SongBD("Who Says", "", "", -1, -1, -1, -1);
            bd.addSong(song1);
            // check if the song is added into the playlist
            assertEquals(true, bd.playlist.contains(song1),
                    "\"Who Says\" is not added into playlist");
	    // check the size of playlist
	    assertEquals(1, bd.songCount, "size of playlist is not correct");
        } catch (Exception e) {
            //assertEquals("file not found", e.getMessage(), "error message doesn't match");
        }
    }

    /**
     * test removeSongFromPlaylist() which should remove the song from playlist based the title and author of the song
     */
    @Test
    public void test4() {
        try {
            // check if playlist is empty before addSong()
            assertEquals(true, bd.playlist.isEmpty(), "playlist is not empty before addSong()");
            // add songs into playlist
            SongInterface song1 = new SongBD("Who Says", "", "", -1, -1, -1, -1);
            bd.addSong(song1);
	    SongInterface song2 = new SongBD("Who Says Remix", "", "", -1, -1, -1, -1);
	    bd.addSong(song2);
            // remove song based on title and author of the song
            bd.removeSongFromPlaylist("Who Says Remix", "Selena Gomez");
            // check if the song is removed from playlist
            assertEquals(true, !bd.playlist.contains(song2),
                    "\"Who Says Remix\" by Selena Gomez is not removed from playlist");
            // check if the not removed song is still in playlist
            assertEquals(true, bd.playlist.contains(song1),
                    "\"Who Says\" is not in playlist");
            // check the size of playlist after remove
            assertEquals(1, bd.songCount, "size of playlist is not correct");
        } catch (Exception e) {
            //assertEquals("file not found", e.getMessage(), "error message doesn't match");
        }
    }

    /**
     * test removeSongFromLibrary() which should remove the song from the music library based on title and author of
     * the song
     */
    @Test
    public void test5() {
        try {
            // remove song from music library
            bd.removeSongFromLibrary("Who Says", "Selena Gomez");
            // check the size of music library after remove
            assertEquals(3, bd.redBlackTree.size(),
                    "size of music library after remove is not correct");
        } catch (Exception e) {
            //assertEquals("file not found", e.getMessage(), "error message doesn't match");
        }
    }

    /**
     * test getPlaylistLength() and getPlaylistSize() which should show the minutes and seconds of total duration of
     * all songs in playlist and how many songs are in playlist
     */
    @Test
    public void test6() {
        try {
            // add songs into playlist
            SongInterface song1 = new SongBD("Who Says", "", "", -1, -1, -1, -1);
            bd.addSong(song1);
            SongInterface song2 = new SongBD("Who Says Remix", "", "", -1, -1, -1, -1);
	    bd.addSong(song2);
            // check getPlaylistLength() result
            String length = bd.getPlaylistLength();
            assertEquals("The length of the playlist is: 6 minutes and 40 seconds.", length,
                    "Playlist length is not correct");
            // check getPlaylistSize() result
            String size = bd.getPlaylistSize();
            assertEquals("The playlist contains 2 songs.", size, "Playlist size is not correct");
        } catch (Exception e) {
            //assertEquals("file not found", e.getMessage(), "error message doesn't match");
        }
    }

    /**
     * test getPlaylistInfoString() which should show the information, such as title, author, genre,..., of all songs
     * in playlist
     */
    @Test
    public void test7() {
        try {
            // add songs into playlist
            SongInterface song1 = new SongBD("Who Says", "", "", -1, -1, -1, -1);
            bd.addSong(song1);
	    SongInterface song2 = new SongBD("Who Says Remix", "", "", -1, -1, -1, -1);
	    bd.addSong(song2);
            // check getPlaylistInfoString() result
            List<String> info = bd.getPlaylistInfoString();
            assertEquals("Title: Who Says; Author: Selena Gomez; Genre: Pop; Year: 2023; "
                    + "Loudness: -5; Duration: 200; Popularity: 71", info.get(0),
                    "getPlaylistInfoString() output is not correct");
            assertEquals("Title: Who Says Remix; Author: Selena Gomez; Genre: Pop; Year: 2023; "
                            + "Loudness: -5; Duration: 200; Popularity: 71", info.get(1),
                    "getPlaylistInfoString() output is not correct");
        } catch (Exception e) {
            assertEquals("file not found", e.getMessage(), "error message doesn't match");
        }
    }

    /**
     * This is a JUnit integration test method for AE and DW
     * on loading songs into library
     */
    @Test
    public void IntegrationTest1() {
        SearchableSortedCollectionInterface<SongInterface> redBlackTree = new
                SearchableSortedCollectionAE<>();
        SongReaderInterface songReader = new SongReaderDW();
        BackendInterface backend = new BackendBD(redBlackTree, songReader);
        try {
            // load songs
            backend.loadSongs("dataset.csv");
            SongInterface songInLibrary = new SongDW("Uninvited", "Alanis Morissette",
                    "alternative rock",2005, -5, 276, 57);
            // check if songInLibrary is added successfully into library
            assertEquals(true, redBlackTree.contains(songInLibrary),
                    "Song is not successfully loaded");
        } catch (FileNotFoundException e) {

        }
    }

    /**
     * This is a JUnit integration test method for AE and DW
     * on removing song into library
     */
    @Test
    public void IntegrationTest2() {
        SearchableSortedCollectionInterface<SongInterface> redBlackTree = new
                SearchableSortedCollectionAE<>();
        SongReaderInterface songReader = new SongReaderDW();
        BackendInterface backend = new BackendBD(redBlackTree, songReader);
        try {
            // load songs
            backend.loadSongs("dataset.csv");
            SongInterface songRemovedFromLibrary = backend.removeSongFromLibrary("Uninvited",
                    "Alanis Morissette");
            // check if there's match for the song in library for removal
            assertEquals(true, songRemovedFromLibrary != null,
                    "No match found in library for removal");
            // check if song is successfully removed from library
            assertEquals(true, !redBlackTree.contains(songRemovedFromLibrary),
                    "Song is not successfully removed from library");
        } catch (FileNotFoundException e) {

        }
    }

    /**
     * This is a JUnit test method for checking code from FrontendDeveloper
     * on choosing search command without loading data
     */
    @Test
    public void CodeReviewOfFrontendDeveloper1() {
        // input user command which doesn't load songs before search
        TextUITester tester = new TextUITester("T\nlove stroy\ntaylor swift\naQ\nQ\n");
        Scanner scanner = new Scanner(System.in);
        SearchableSortedCollectionInterface<SongInterface> redBlackTree = new SearchableSortedCollectionAE<SongInterface>();
        SongReaderInterface songReader = new SongReaderDW();
        BackendInterface backend = new BackendBD(redBlackTree, songReader);
        FrontendInterface frontend = new FrontendDeveloper(scanner, backend); // initialize instance
        frontend.runCommandLoop();
        String output = tester.checkOutput();
        System.out.println(output);
        assertEquals(true, (output.contains("Please load data first")));
    }

    /**
     * This is a JUnit test method test for checking code from FrontendDeveloper
     * on chooseSearchAuthorPrompt()
     */
    @Test
    public void CodeReviewOfFrontendDeveloper2() {
        // input author name to search
        TextUITester tester = new TextUITester("Norah Jones\n");
        Scanner scanner = new Scanner(System.in);
        SearchableSortedCollectionInterface<SongInterface> redBlackTree = new SearchableSortedCollectionBD();
        SongReaderInterface songReader = new SongReaderDW();
        BackendInterface backend = new BackendBD(redBlackTree, songReader);
        FrontendInterface frontend = new FrontendDeveloper(scanner, backend); // initialize instance
        String returnString = frontend.chooseSearchTitlePrompt();
        assertEquals("Norah Jones", returnString);
        tester.checkOutput();
    }
}
