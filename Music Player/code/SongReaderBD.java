import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

// --== CS400 Spring 2023 File Header Information ==--
// Name: Cornelia (Zhouzhou) Chu
// Email: zchu26@wisc.edu
// Team: BC
// TA: Naman Gupta
// Lecturer: Gary Dahl Lec002 1:00 to 2:15 pm
// Notes to Grader: <optional extra notes>
/**
 * placeholder class for SongReader hardcoded from Backend's perspective for the test
 * @author Cornelia Chu
 */
public class SongReaderBD implements SongReaderInterface {
    public SongReaderBD() {

    }

    @Override
    public List<SongInterface> readSongsFromFile(String filename) throws FileNotFoundException {
        List<SongInterface> library = new ArrayList<>();
        SongInterface song1 = new SongBD("Lose You to Love Me", "Selena Gomez", "", -1, -1, -1, -1);
        library.add(song1);
        SongInterface song2 = new SongBD("Same Old Love", "Selena Gomez", "", -1, -1, -1, -1);
        library.add(song2);
        SongInterface song3 = new SongBD("Who Says", "Selena Gomez", "", -1, -1, -1, -1);
        library.add(song3);
        SongInterface song4 = new SongBD("Who Says Remix", "Selena Gomez", "", -1, -1, -1, -1);
        library.add(song4);
        return library;
    }
}
