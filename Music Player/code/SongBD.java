// --== CS400 Spring 2023 File Header Information ==--
// Name: Cornelia (Zhouzhou) Chu
// Email: zchu26@wisc.edu
// Team: BC
// TA: Naman Gupta
// Lecturer: Gary Dahl Lec002 1:00 to 2:15 pm
// Notes to Grader: <optional extra notes>
/**
 * placeholder class for Song hardcoded from Backend's perspective for the test
 * @author Cornelia Chu
 */
public class SongBD implements SongInterface {
    protected String title; // song's title
    protected String author; // song's author
    protected String genre; // song's genre
    protected int year; // song's year
    protected int loudness; // song's loudness
    protected int duration; // song's duration
    protected int popularity; // song's popularity
    public SongBD(String title, String author, String genre, int year, 
	int loudness, int duration, int popularity) {
	this.title = title;
	this.author = author;
	this.genre = genre;
	this.year = year;
	this.loudness = loudness;
	this.duration = duration;
	this.popularity = popularity;
    }


    @Override
    public String getTitle() {
        return this.title;
    }

    @Override
    public String getAuthor() {
        return "Selena Gomez";
    }

    @Override
    public String getGenre() {
        return "Pop";
    }

    @Override
    public int getYear() {
        return 2023;
    }

    @Override
    public int getLoudness() {
        return -5;
    }

    @Override
    public int getDuration() {
        return 200;
    }

    @Override
    public int getPopularity() {
        return 71;
    }

    /**
     * have to implement this because I need to use contains() to see if a Song is in the playlist (music library) or
     * not; otherwise, my test won't pass because contains() uses equals(object) to compare if 2 songs are the same
     * @param other the object to be compared.
     * @return 0 if 2 songs are the same; else, 1
     */
    @Override
    public int compareTo(SongInterface other) {
        if (other instanceof SongInterface && other.getTitle() != null && other.getTitle().equals(this.title)) {
            return 0;
        } else {
            return 1;
        }
    }

    /**
     * have to implement this because I need to use contains() to see if a Song is in the playlist (music library) or
     * not; otherwise, my test won't pass because contains() uses equals(object) to compare if 2 songs are the same
     * @param other the object to be compared
     * @return true if the 2 songs are the same; else, false
     */
    @Override
    public boolean equals(Object other) {
        if (compareTo((SongInterface) other) == 0) {
            return true;
        } else {
            return false;
        }
    }

    public String toString() {
        return "Who Says by Selena Gomez";
    }
    // title + “ by “ + author;

}
