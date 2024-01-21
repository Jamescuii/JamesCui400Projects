import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 * This is a placeholder represents a collection that data wangler need to fullfill 
 * for the Music Library Assistant project.
 * 
 * @author Ziqi Shen
 */

public class SongReaderFD implements SongReaderInterface{
    @Override
    public List<SongInterface> readSongsFromFile(String filename) 
    throws FileNotFoundException{
        if (filename == null) {
            throw new FileNotFoundException("No such file");
          }
        List<SongInterface> returnSongList = new ArrayList<SongInterface>();
        returnSongList.add(new Song());
        return returnSongList;
    }
}
