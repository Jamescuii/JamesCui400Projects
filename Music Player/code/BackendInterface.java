import java.io.FileNotFoundException;
import java.util.List;
/**
 * This abstract data type represents a collection that backend developer need to fullfill 
 * for the Music Library Assistant project.
 * @author Cornelia Chu
 */
public interface BackendInterface { 
    // public BackendInterface(SearchableSortedCollectionInterface<String,SongInterface> redBlackTree, SongReaderInterface songReader);
    public void loadSongs(String filename) throws FileNotFoundException; // utilization of songReader and insert() in RedBlackTreeInterface
    public List<SongInterface> searchSongsByTitle(String titlle, String author); // utilization of SongInterface
    public void addSong(SongInterface song); // utilization of SongInterface
    public SongInterface removeSongFromPlaylist(String title, String author); // utilization of SongInterface
    public SongInterface removeSongFromLibrary(String title, String author); // utilization of SongInterface
    public String getPlaylistLength();
    public String getPlaylistSize();
    public List<String> getPlaylistInfoString();
}
    

