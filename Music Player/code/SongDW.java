// --== CS400 Spring 2023 File Header Information ==--
// Name: James Cui
// Email: jcui57@wisc.edu
// Team: BC
// TA: Naman Gupta
// Lecturer: Gary Dahl

/**
 * The Song class that bundles the information of the Song
 * into one class. This song is loaded into the 
 * library and playlist.
 * @author jamescui
 *
 */
public class SongDW implements SongInterface {

  private String title;
  private String author;
  private String genre;
  private int year;
  private int loudness;
  private int duration;
  private int popularity;
  
  public SongDW(String title, String author, String genre, int year, int loudness, int duration, int popularity) {
      this.title = title;
      this.author = author;
      this.genre = genre;
      this.year = year;
      this.loudness = loudness;
      this.duration = duration;
      this.popularity = popularity; 
    }
    public String getTitle() {
      return title;
    }
    public String getAuthor() {
      return author;
    }
    public String getGenre() {
      return genre;
    }
    public int getYear() {
      return year;
    }
    public int getLoudness() {
      return loudness;
    }
    public int getDuration() {
      return duration;
    }
    public int getPopularity() {
      return popularity;
    }
    
    /**
     * Compares the song with another input song
     * an int based on -1 alphabetically earlier by title
     * and -1 alphabetically earlier by author
     */
    @Override
    public int compareTo(SongInterface other) {
      String otherTitle = other.getTitle();
      String otherAuthor = other.getAuthor();
      //int otherPopularity = other.getPopularity();
      
      if(this.title.compareTo(otherTitle) != 0) {
        return this.title.compareTo(otherTitle);
      }
      else if(this.author != otherAuthor) {
        return this.author.compareTo(otherAuthor);
      }
      else {
        return 0;
      }

    }
    
    /**
     * Determines if two songs are equal to each other.
     * Two songs are equal if its title and author are the same.
     */
    @Override
    public boolean equals(Object other) {
      if(other instanceof SongDW) {
        SongDW otherSong = (SongDW) other;
        if(compareTo(otherSong) == 0) {
          return true;
        }
      }
      return false;
    }
    
    /**
     * Returns a String format of the Song.
     */
    public String toString() {
      return (this.title + " by " + author);
    }
    // title + “ by “ + author;

  }

