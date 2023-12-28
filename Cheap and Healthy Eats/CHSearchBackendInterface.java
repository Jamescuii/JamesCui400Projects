// --== CS400 Project One File Header ==--
// Name: James Cui
// CSL Username: jcui
// Email: jcui57@wisc.edu
// Lecture #: 002
// Notes to Grader: None

import java.io.FileNotFoundException;
import java.util.List;

public interface CHSearchBackendInterface {    
    // public CHSearchBackendInterface(HashtableWithDuplicateKeysInterface<String,PostInterface> hashtable, PostReaderInterface postReader);
    public void loadData(String filename) throws FileNotFoundException;
    public List<String> findPostsByTitleWords(String words);
    public List<String> findPostsByBodyWords(String words);
    public List<String> findPostsByTitleOrBodyWords(String words);
    public String getStatisticsString();
}
