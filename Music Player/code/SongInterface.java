/**
 * 
 * @author jamescui
 *
 */
public interface SongInterface extends Comparable<SongInterface>{
   // public SongInterface(String title, String author, String genre, int year, int loudness, int duration, int popularity);
   public String getTitle();
   public String getAuthor();
   public String getGenre();
   public int getYear();
   public int getLoudness();
   public int getDuration();
   public int getPopularity();
   @Override
   public int compareTo(SongInterface other);
   // Title, Author, Popularity
   @Override
   public boolean equals(Object other);
   // use compareTo
   public String toString();
   // title + “ by “ + author;
}
