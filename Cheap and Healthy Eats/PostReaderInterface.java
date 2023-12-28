// --== CS400 Project One File Header ==--
// Name: James Cui
// CSL Username: jcui
// Email: jcui57@wisc.edu
// Lecture #: 002
// Notes to Grader: None

import java.io.FileNotFoundException;
import java.util.List;


public interface PostReaderInterface {
    // public PostReaderInterface();
    public List<PostInterface> readPostsFromFile(String filename) throws FileNotFoundException;
}
