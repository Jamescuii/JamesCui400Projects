// --== CS400 Spring 2023 File Header Information ==--
// Name: James Cui
// Email: jcui57@wisc.edu
// Team: BC
// TA: Naman Gupta
// Lecturer: Gary Dahl

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SongReaderDW implements SongReaderInterface {

  public SongReaderDW() {
    
  }
  
  /**
   * The Song reader reads the Songs from a file and
   * turns the Songs in the CSV file into Song instances
   * so other classes can use each song.
   * @author jamescui
   */
  @Override
  public List<SongInterface> readSongsFromFile(String filename) throws FileNotFoundException {
    // use scanner to read from the specified file, and store results in posts
    ArrayList<SongInterface> songs = new ArrayList<>();
    Scanner in = new Scanner(new File(filename));

    while (in.hasNextLine()) {
        // for each line in the file being read:
        String line = in.nextLine();
        // split that line into parts around around the delimiter: +++

        String[] parts = new String[15];
        int quoteCounter = 0;
        String temp = "";
        int index = 0;
        //System.out.println(line.length());
        for(int i = 0; i < line.length(); i++) {
          //System.out.println(temp);
          //System.out.println(line.length());
          if((i+1) < line.length() && (line.charAt(i) == ('\u0022') && line.charAt(i+1) == ('\u0022'))) {
            temp += '\u0022';
            i+=1;
          }
          else if(line.charAt(i) == ('\u0022')) {
            quoteCounter++;
          }
          else if(quoteCounter % 2 == 0 && line.charAt(i) == (',')) {
            parts[index] = temp;
            temp = "";
            index++;
          }
          else {
            temp += String.valueOf(line.charAt(i));
          }
        }
        parts[index] = temp;
        /*
        for(int i = 0; i < 15; i++) {
          System.out.println(parts[i]);
        }
        */
        // most lines should have all 15 parts
        if (parts.length == 15)
            songs.add(new SongDW(parts[1], parts[2], parts[3], Integer.valueOf(parts[4]),
                Integer.valueOf(parts[8]), Integer.valueOf(parts[11]), Integer.valueOf(parts[14])));
        if (parts.length != 15)
            System.out.println("Warning: found a line without proper # of parts: " + line);
    }

    // then close the scanner before returning the list of posts
    in.close();
    return songs;
  }

}


