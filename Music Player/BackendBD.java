// --== CS400 Spring 2023 File Header Information ==--
// Name: Cornelia (Zhouzhou) Chu
// Email: zchu26@wisc.edu
// Team: BC
// TA: Naman Gupta
// Lecturer: Gary Dahl Lec002 1:00 to 2:15 pm
// Notes to Grader: <optional extra notes>
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class efficiently adds the songs’ data info into the playlist, retrieves (searches) the song’s information from
 * the music library containing all the Song objects created by SongInterface from DW, and removes the no longer needed
 * song from the playlist and the library. It should also include the functionality that retrieves the statistics of
 * the playlist for the frontend’s use.
 *
 * @author Cornelia Chu
 */
public class BackendBD implements BackendInterface {

    protected SearchableSortedCollectionInterface<SongInterface> redBlackTree;
    // music library
    // in red black tree structure

    protected SongReaderInterface songReader; // song reader which reads songs from file

    protected int songCount; // record the number of songs in the playlist

    protected List<SongInterface> playlist; // the playlist of user

    /**
     * Initialize backend to make use of the provided SearchableSortedCollection and SongReader
     * and initialize the count of songs in the playlist to 0 and initialize the playlist
     * @param redBlackTree placeholder by me, implemented by AE
     * @param songReader placeholder by me, implemented by DW
     */
    public BackendBD(SearchableSortedCollectionInterface<SongInterface> redBlackTree,
                     SongReaderInterface songReader) {
        this.redBlackTree = redBlackTree;
        this.songReader = songReader;
        this.songCount = 0;
        this.playlist = new ArrayList<>();
    }

    /**
     * Use DW's code to load songs from filename and add the songs to the music library
     * Utilization of songReader and insert() in RedBlackTreeInterface
     * @param filename the path and name of a file to load songs from
     *
     */
    @Override
    public void loadSongs(String filename) throws FileNotFoundException {
        List<SongInterface> songs = songReader.readSongsFromFile(filename);
        for (SongInterface song : songs) {
            redBlackTree.insert(song);
        }
    }

    /**
     * Search and find the 5 songs with most similar title from the music library implemented by AE
     * based on the name of the song given by FD.
     * Return the found song to either addSong() or to FD
     * @param title the title of song we want to search for
     * @param author the author of song we want to search for
     * @return list of 5 songs with most similar title and author
     */
    @Override
    public List<SongInterface> searchSongsByTitle(String title, String author) {
        SongInterface songToSearch = new SongDW(title, author, "", -1, -1, -1, -1);
        return redBlackTree.search(songToSearch);
    }

    /**
     * Add the song into user’s playlist (an ArrayList instance)
     * @param song song to add into playlist
     */
    @Override
    public void addSong(SongInterface song) {  
        playlist.add(song);
	this.songCount++;
    }


    /**
     * Remove the song based on the name and author given by FD from the user’s playlist
     * @param title title of song to be removed
     * @param author author of song to be removed
     * @return song removed
     */
    @Override
    public SongInterface removeSongFromPlaylist(String title, String author) {
        for (SongInterface song : playlist) {
            if (song.getTitle() != null && song.getTitle().equals(title) && song.getAuthor() != null &&
                    song.getAuthor().equals(author)) {
                SongInterface songToRemove = song;
                playlist.remove(songToRemove);
                this.songCount--;
                return songToRemove;
            }
        }
        // return null if no match is found
        return null;
    }

     /**
      * Remove the song based on the name and author given by FD from the library
      * @param title title of song to remove
      * @param author author of song to remove
      * @return song removed
      */
     @Override
     public SongInterface removeSongFromLibrary(String title, String author) {
         SongInterface songToRemove = new SongDW(title, author, "", -1, -1, -1, -1);
         try {
             redBlackTree.remove(songToRemove);
             return songToRemove;
         } catch (IllegalArgumentException iae) {
             // return null if no match found
             return null;
         }
     }

    /**
     * Get the length of playlist to FD (in minutes and seconds)
     * @return string of number of minutes for all songs in playlist
     */
    @Override
    public String getPlaylistLength() {
        int allSongsDuration = 0;
        int duration; // duration for single song
        // calculate length of playlist in seconds
        for (SongInterface song : playlist) {
            duration = song.getDuration();
            allSongsDuration += duration;
        }
        // calculate minutes and seconds for the length
        int minutes = allSongsDuration / 60;
        int seconds = allSongsDuration % 60;
        return "The length of the playlist is: " + minutes + " minutes and " + seconds + " seconds.";
    }

    /**
     * Get the size of playlist to FD (in number of songs)
     * @return string of number of songs in playlist
     */
    @Override
    public String getPlaylistSize() {
        return "The playlist contains " + this.songCount + " songs.";
    }

    /**
     * Get all the statistics of the playlist
     * @return list of all the information for each song
     */
    @Override
    public List<String> getPlaylistInfoString() {
        List<String> info = new ArrayList<>();
        for (SongInterface song : playlist) {
            String songInfo = "Title: " + song.getTitle() + "; Author: " + song.getAuthor() + "; Genre: "
                    + song.getGenre() + "; Year: " + song.getYear() + "; Loudness: " + song.getLoudness()
                    + "; Duration: " + song.getDuration() + "; Popularity: " + song.getPopularity();
            info.add(songInfo);
        }
        return info;
    }
}
