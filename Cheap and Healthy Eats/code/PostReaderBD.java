// --== CS400 Project One File Header ==--
// Name: James Cui
// CSL Username: jcui
// Email: jcui57@wisc.edu
// Lecture #: 002
// Notes to Grader: None                                          

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class PostReaderBD implements PostReaderInterface {

  public PostReaderBD() {
    // TODO Auto-generated constructor stub
  }
  
  @Override
  public List<PostInterface> readPostsFromFile(String filename) throws FileNotFoundException {
    PostBD one = new PostBD("Apple", "www.com", "Apple");
    PostBD two = new PostBD("Banana,", "www.com", "Good Food");
    PostBD three = new PostBD("Orange", "www.com", "Yummy!!");
    
    List<PostInterface> toReturn = new ArrayList<>();
    toReturn.add(one);
    toReturn.add(two);
    toReturn.add(three);

    return toReturn;
  }
}

    
