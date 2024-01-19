// --== CS400 Project One File Header ==--
// Name: James Cui
// CSL Username: jcui
// Email: jcui57@wisc.edu
// Lecture #: 002
// Notes to Grader: None

import java.io.FileNotFoundException;
import java.util.List;
import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
* The backend class of the Application that finds key words
* in Reddit Posts given a huge data set
* @author jamescui
*/
public class CHSearchBackend implements CHSearchBackendInterface {
  
  // Creates instances of the Hashmap and PostReader  
  HashtableWithDuplicateKeysInterface<String,PostInterface> hashtable;
  PostReaderInterface postReader;
  
  /*
  public CHSearchBackend() {
    hashtable = new HashtableWithDuplicateKeysInterface<String,PostInterface>();
    postReader = new PostReaderInterface();
  }
  */
  
  /**
  * Constructor for the Backend which requires a hashmap and PostReader.
  * The Hashmap finds the Posts associated with a key while PostReader
  * Goes through posts of data set to add them to the Hashmap.
  */
  public CHSearchBackend(HashtableWithDuplicateKeysInterface<String,PostInterface> hashtable, PostReaderInterface postReader) {
    this.hashtable = hashtable;
    this.postReader = postReader;
  }
  /**
  * Loads all the Data from a file of Posts into a Hashmap
  * Hashmap has a keyValue which is a word from the post and loads a List of Posts with a key word
  * into the hashmap with the prefix of title or body.
  * @param filename is the file with all the data that will be loaded into the backend Hashmap
  */
  @Override
  public void loadData(String filename) throws FileNotFoundException {
    //File file = new File(filename);
    /*
    if(!file.exists()) {
      throw new FileNotFoundException("File Not Found");
    }
    */
    List<PostInterface> posts = postReader.readPostsFromFile(filename);
    for(PostInterface post : posts) {
      // Filters posts for consistency
      String title = post.getTitle().replaceAll("\\p{Punct}", "").toLowerCase();
      String body = post.getBody().replaceAll("\\p{Punct}", "").toLowerCase();
      String[] titleWords = title.split(" ");
      String[] bodyWords = body.split(" ");
      for(String word : titleWords) { // Iterates through all words in title 
        word = "title:" + word;
        hashtable.putOne(word, post);
        //System.out.println(word);
      }
      for(String word : bodyWords) { // Iterates through all words in body
        word = "body:" + word;
        hashtable.putOne(word, post);
        //System.out.println(word);
      }
    }
    
  }
  /**
  * Takes in words and uses the hashmap to check if the given word is associated with certain posts.
  * Then takes the titles of the posts and puts them into a List to be returned.
  * @param words the input of words that are to be searched through the Hashmap.
  * @allPosts the List<String> of Titles of the posts that are associated with the input word
  */
  @Override
  public List<String> findPostsByTitleWords(String words) {

    List<String> allPosts = new ArrayList<>();
    String newWords = words.replaceAll("\\p{Punct}", "").toLowerCase();
    String[] newTitle = newWords.split(" ");
    for(String word : newTitle) { // Iterates through every word with title prefix
      word = "title:" + word;
      try {
        List<PostInterface> postsFromWord = hashtable.get(word);
        if(postsFromWord.size() != 0) {
          for(PostInterface post : postsFromWord) {          // Lower Time Complexity using a HashSet and then use addAll into set
            // Set<PostBD> posts = new HashSet<>();   // Move Hashset into ArrayList
            if(!allPosts.contains(post.getTitle())) { // Makes sure post isnt already in list
              allPosts.add(post.getTitle()); // Add new post to list
            }
          }
        }
      }
      catch(NoSuchElementException e) {
        
      }
    }
    
    return allPosts;
  }

  /**
   * Takes in words and uses the hashmap to check if the given word is associated with certain posts.
   * Then takes the body of the posts and puts them into a List to be returned.
   * @param words the input of words that are to be searched through the Hashmap.
   * @allPosts the List<String> of Bodies of the posts that are associated with the input word. 
   */
  @Override
  public List<String> findPostsByBodyWords(String words) {

    List<String> allPosts = new ArrayList<>();
    String newWords = words.replaceAll("\\p{Punct}", "").toLowerCase();
    String[] newBody = newWords.split(" ");
    for(String word : newBody) { // Iterates through every word with body prefix
      word = "body:" + word;
      try {
        List<PostInterface> postsFromWord = hashtable.get(word);
        if(postsFromWord.size() != 0) {
          for(PostInterface post : postsFromWord) {
            if(!allPosts.contains(post.getBody())) { // Makes sure post isnt already in list
              allPosts.add(post.getBody()); // Add new post to list
            }
          }
        }
      }
      catch(NoSuchElementException e) {
        
      }
    }
    
    return allPosts;
  }

  /**
   * Takes in words and uses the hashmap to check if the given word is associated with certain posts.
   * Then takes the titles and bodies of the posts and puts them into a List to be returned.
   * @param words the input of words that are to be searched through the Hashmap.
   * @allPosts the List<String> of Titles and bodies of the posts that are associated with the input word. 
   */
  @Override
  public List<String> findPostsByTitleOrBodyWords(String words) {

    List<String> allPosts = new ArrayList<>();
    String newWords = words.replaceAll("\\p{Punct}", "").toLowerCase();
    String[] newTitleOrBody = newWords.split(" ");
    for(String word : newTitleOrBody) { // Iterates through every word with title prefix
      word = "title:" + word;
      try {
        List<PostInterface> postsFromTitle = hashtable.get(word);

        if(postsFromTitle.size() != 0) { // Checks if word is associated with any posts
          for(PostInterface post : postsFromTitle) { 
            if(!allPosts.contains(post.getTitle() + " " + post.getBody())) { // Makes sure post isnt already in list
              allPosts.add(post.getTitle() + " " + post.getBody());
            }
          }
        }
      }
      catch(NoSuchElementException e) {

      }
    }
    for(String word : newTitleOrBody) { // Iterates through every word with body prefix
      word = "body:" + word;
      try {
        List<PostInterface> postsFromBody = hashtable.get(word);
        
        if(postsFromBody.size() != 0) { // Checks if word is associated with any posts
          for(PostInterface post : postsFromBody) { 
            if(!allPosts.contains(post.getTitle() + " " + post.getBody())) { // Makes sure post isnt already in list
              allPosts.add(post.getTitle() + " " + post.getBody()); // Add new post to list
            }
          }
        }
      }
      catch(NoSuchElementException e) {

      }
    }
    
    return allPosts;
  }

  /**
  * Returned certain statistics about the data set that is loaded into the Hashmap.
  * @return a String with statistics about the hashmap that contains all the data.
  */
  @Override
  public String getStatisticsString() {
    
    return ("Number of Values: " + hashtable.getNumberOfValues() + "\n" + "Total Unique Words: "
        + hashtable.getSize() + "\n" + "Capacity of Hashtable: " + hashtable.getCapacity());
  }

}
