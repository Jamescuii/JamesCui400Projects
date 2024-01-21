// --== CS400 Spring 2023 File Header Information ==--
// Name: Cornelia (Zhouzhou) Chu
// Email: zchu26@wisc.edu
// Team: BC
// TA: Naman Gupta
// Lecturer: Gary Dahl Lec002 1:00 to 2:15 pm
// Notes to Grader: <optional extra notes>
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * placeholder class for red-black tree hardcoded to meet backend testers' needs
 * @author Cornelia Chu
 */
public class SearchableSortedCollectionBD implements SearchableSortedCollectionInterface<SongInterface> {
    @SuppressWarnings("unchecked")
    public List<SongInterface> library = new ArrayList<SongInterface>();
    // hardcoded music library to test for loadSongs() and removeSongFromLibrary()

    /**
     *  hardcode and check the found similar songs of music library based on title
     * @return a list of similar songs found based on title
     */
    @Override
    @SuppressWarnings("unchecked") 
    public List<SongInterface> search(SongInterface song) throws IllegalStateException {
        List<SongInterface> list = new ArrayList<>();
        SongInterface song1 = new SongBD("Who Says", "", "", -1, -1, -1, -1);
        list.add(song1);
        SongInterface song2 = new SongBD("Who Says Remix", "", "", -1, -1, -1, -1);
        list.add(song2);
        return list;
    }

    /**
     * hardcode and check the contents of music library
     * @return true if insert is successful; else false
     */
    @Override
    public boolean insert(SongInterface song) throws NullPointerException, IllegalArgumentException {
	library.add(song);
	return true;
    }
  
    /**
     * hardcode and check the contents of music library
     * @return true if remove is successful; else false
     */
    @Override
    public boolean remove(SongInterface song) throws NullPointerException, IllegalArgumentException {
	library.remove(new SongBD("Who Says", "", "", -1, -1, -1, -1));
        return true;
    }

    /**
     * hardcode and check the contents of music library
     * @return true if this song is in music library; else false
     */
    @Override
    public boolean contains(SongInterface song) {
	for (SongInterface songInLibrary : library) {
	    if (songInLibrary != null && songInLibrary.equals(song)) {
		return true;	
	    }
	}
	return false;
    }

    /**
     * hardcode and check the contents of music library
     * @return size of music library
     */
    @Override
    public int size() {
        return library.size();
    }

    /**
     * hardcode and check the contents of music library
     * @return true if music library is empty; else false
     */
    @Override
    public boolean isEmpty() {
        return false;
    }
}
