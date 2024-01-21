// --== CS400 Spring 2023 File Header Information ==--
// Name: Ziqi Shen
// Email: zshen266@wisc.edu
// Team: BC
// TA: Naman Gupta
// Lecturer: Gary Dahl
// Notes to Grader: None

import java.util.List;
import java.util.Scanner;
import java.io.FileNotFoundException;

/**
 * Provide a display for the music library assistant application 
 * that the main menu shows command options, playlists being created, 
 * and similarly named songs for searches, etc.
 * 
 * @author Ziqi Shen
 */
public class FrontendDeveloper implements FrontendInterface {
	private Scanner userInput;
	private BackendInterface backend;

    /**
	 * Initialize frontend to make use of a provided Scanner and backend.
	 * 
	 * @param userInput can be used to read input from user, or to read from files
	 *                  for testing
	 * @param backend   placeholder by me, working implementation by Backend
	 *                  Developer
	 */
	public FrontendDeveloper(Scanner userInput, BackendInterface backend) {
		this.userInput = userInput;
		this.backend = backend;
	}


    /**
	 * This loop repeated prompts the user for commands and displays appropriate
	 * feedback for each. This continues until the user enters 'Q' to quit.
	 */
    @Override
    public void runCommandLoop(){
        // display welcome message
        System.out.println("Welcome to the Music Library Assistant App.");
        String title;
        String author;

		String command = "";
        String returnQ = "Q";
		while (!command.equals(returnQ)) { // main loop continues until user chooses to quit
			command = this.mainMenuPrompt();
			switch (command) {
				case "L": // System.out.println(" [L]Load data from file");
					loadDataCommand();
					break;
				case "T": // System.out.println(" [T]Search Music by Titles");
					title = chooseSearchTitlePrompt();
                    author = chooseSearchAuthorPrompt();
					searchTitleCommand(title, author);
					break;
				case "RP": // System.out.println(" [RP]Remove from Playlist");
                    removeFromPlaylistCommand();
					break;
				case "RL": // System.out.println(" [RL]Remove from Library");
                    removeFromLibraryCommand();
					break;
                case "DP": // System.out.println(" [DP]Display Playlist");
                    displayPlaylistCommand();
					break;
                case "DL": // System.out.println(" [DL]Display Playlist Length");
                    displayPlaylistLengthCommand();
					break;
                case "DS": // System.out.println(" [DS]Display Playlist Size");
                    displayPlaylistSizeCommand();
					break;
				case "Q": // System.out.println(" [Q]Quit");
					// do nothing, containing loop condition will fail
                    // thank user before ending this application
                    System.out.println("Thank you for using the Music Library Assistant App.");
					break;
				default:
					System.out.println(
							"Didn't recognize that command.  Please type one of the " +
                            "letters presented within []s to identify the command you would like to choose.");
					break;
			}
		}

    
    }

    /**
	 * Prints the command options to terminal and reads user's choice through
	 * userInput scanner.
	 */
    @Override
    public String mainMenuPrompt(){
        // display menu of choices
		System.out.println("Choose a command from the list below:");
		System.out.println("    [L]Load data from file");
		System.out.println("    [T]Search/Add Music by Title and Author");
		System.out.println("    [RP]Remove from Playlist");
		System.out.println("    [RL]Remove from Library");
        System.out.println("    [DP]Display Playlist");
        System.out.println("    [DL]Display Playlist Length");
        System.out.println("    [DS]Display Playlist Size");
		System.out.println("    [Q]Quit");

		// read in user's choice, and trim away any leading or trailing whitespace
		System.out.print("Choose command: ");
		String input = userInput.nextLine().trim();
		// otherwise, return an uppercase version of input
		return input.toUpperCase();
	}

    /**
	 * Prompt user to enter filename, and display error message when loading fails.
	 */
    @Override
    public void loadDataCommand(){
        System.out.print("Enter the name of the file to load: ");
		String filename = userInput.nextLine().trim();
		try {
			backend.loadSongs(filename);
		} catch (FileNotFoundException e) {
			System.out.println("Error: Could not find or load file: " + filename);
		}  
    }

    /**
	 * This method gives user the ability to input words for song searching.
	 */
    @Override
    public String chooseSearchTitlePrompt(){
        System.out.print("Enter the title of the song to search: ");
		String title = userInput.nextLine().trim();
        String[] titlewords = title.split(" ");
        String modifiedTitle = "";
        int firstword = 0;
        for (String a : titlewords){
            if(a.length() != 0){
                String newWord = "";
                newWord = newWord + Character.toUpperCase(a.charAt(0));
                newWord = newWord + a.substring(1).toLowerCase();
                if (firstword != 0)
                    modifiedTitle = modifiedTitle + " ";
                firstword +=1;
                modifiedTitle = modifiedTitle + newWord;
            }
        }
        title = modifiedTitle;
        return title;
    }

    /**
	 * This method gives user the ability to input words for song searching.
	 */
    @Override
    public String chooseSearchAuthorPrompt(){
        System.out.print("Enter the author of the song to search: ");
		String author = userInput.nextLine().trim();
        String[] authorwords = author.split(" ");
        String modifiedauthor = "";
        int firstword = 0;
        for (String a : authorwords){
            if(a.length() != 0){
                String newWord = "";
                newWord = newWord + Character.toUpperCase(a.charAt(0));
                newWord = newWord + a.substring(1).toLowerCase();
                if (firstword != 0)
                modifiedauthor = modifiedauthor + " ";
                firstword +=1;
                modifiedauthor = modifiedauthor + newWord;
            }
        }
        author = modifiedauthor;
        return author;
    }
    
    /**
	 * This method searches and ask if adding songs based on user's input.
	 */
    @Override
    public void searchTitleCommand(String title, String author){
        try{
            List<SongInterface> songRt = backend.searchSongsByTitle(title, author);
            for(int i = 0; i < songRt.size(); i++){
                System.out.printf("[%d] %s\n", i + 1, songRt.get(i).toString());
            }
            // Prompt user to enter the valid index of song to add to playlist
            int songInt;
            while(true){
                System.out.println("Enter 0 to go back to main menu" +
                    " or Enter the index of song you wish to add to playlist: ");
                String songIndex = userInput.nextLine().trim();
                try{
                    songInt = Integer.parseInt(songIndex) - 1;
                    if(-1 <= songInt && songInt < songRt.size()){
                        break;
                    }
                    System.out.printf("Your input %d is out of correct index range 0-%d\n", songInt + 1, songRt.size());
                } catch (NumberFormatException e) {
                    System.out.println("Not a valid index format");
                }
            }
            if(songInt == -1){
                System.out.println("No song was added - go back to main menu");
                return;
            } else {
                SongInterface songToAdd = songRt.get(songInt);
                backend.addSong(songToAdd);
                System.out.println("Song added successfully");
            }
        } catch (Exception e){
            System.out.println("Please load data first");
        }
    }

    /**
	 * This method adds song to playlist.
	 */
    @Override
    public void addToPlaylistCommand(String title, String author){
        searchTitleCommand(title, author);
    }

    /**
	 * This method removes song from playlist.
	 */
    @Override
    public void removeFromPlaylistCommand(){
        System.out.print("Delete From Playlist:\nEnter the title of the song to delete: ");
		String title = userInput.nextLine().trim();
        String[] titlewords = title.split(" ");
        String modifiedTitle = "";
        int firstword = 0;
        for (String a : titlewords){
            if(a.length() != 0){
                String newWord = "";
                newWord = newWord + Character.toUpperCase(a.charAt(0));
                newWord = newWord + a.substring(1).toLowerCase();
                if (firstword != 0)
                    modifiedTitle = modifiedTitle + " ";
                firstword +=1;
                modifiedTitle = modifiedTitle + newWord;
            }
        }
        title = modifiedTitle;
        System.out.print("Delete From Playlist:\nEnter the author of the song to delete: ");
        String author = userInput.nextLine().trim();
        String[] authorwords = author.split(" ");
        String modifiedauthor = "";
        firstword = 0;
        for (String a : authorwords){
            if(a.length() != 0){
                String newWord = "";
                newWord = newWord + Character.toUpperCase(a.charAt(0));
                newWord = newWord + a.substring(1).toLowerCase();
                if (firstword != 0)
                modifiedauthor = modifiedauthor + " ";
                firstword +=1;
                modifiedauthor = modifiedauthor + newWord;
            }
        }
        author = modifiedauthor;
        SongInterface deletedSong = backend.removeSongFromPlaylist(title, author);
        if (deletedSong == null){
            System.out.println("There are no such song in Playlist!");
        }
        else{
            System.out.printf("Successfully deleted: %s\n", deletedSong.toString());
        }
    }

    /**
	 * This method removes song from Library.
	 */
    @Override
    public void removeFromLibraryCommand(){
        System.out.print("Delete From Library:\nEnter the title of the song to delete: ");
		String title = userInput.nextLine().trim();
        String[] titlewords = title.split(" ");
        String modifiedTitle = "";
        int firstword = 0;
        for (String a : titlewords){
            if(a.length() != 0){
                String newWord = "";
                newWord = newWord + Character.toUpperCase(a.charAt(0));
                newWord = newWord + a.substring(1).toLowerCase();
                if (firstword != 0)
                    modifiedTitle = modifiedTitle + " ";
                firstword +=1;
                modifiedTitle = modifiedTitle + newWord;
            }
        }
        title = modifiedTitle;
        System.out.print("Delete From Library:\nEnter the author of the song to delete: ");
		String author = userInput.nextLine().trim();
        String[] authorwords = author.split(" ");
        String modifiedauthor = "";
        firstword = 0;
        for (String a : authorwords){
            if(a.length() != 0){
                String newWord = "";
                newWord = newWord + Character.toUpperCase(a.charAt(0));
                newWord = newWord + a.substring(1).toLowerCase();
                if (firstword != 0)
                modifiedauthor = modifiedauthor + " ";
                firstword +=1;
                modifiedauthor = modifiedauthor + newWord;
            }
        }
        author = modifiedauthor;
        SongInterface deletedSong = backend.removeSongFromLibrary(title, author);
        if (deletedSong == null){
            System.out.println("There are no such song in Library!");
        }
        else{
            System.out.printf("Successfully deleted: %s\n", deletedSong.toString());
        }
    }

    /**
	 * This method displays the length of playlist (number of minutes).
	 */
    @Override
    public void displayPlaylistLengthCommand(){
        String listLength= backend.getPlaylistLength();
        System.out.println(listLength);
    }

    /**
	 * This method displays the size of the playlist (number of songs).
	 */
    @Override
    public void displayPlaylistSizeCommand(){
        String listSize= backend.getPlaylistSize();
        System.out.println(listSize);
    }

    /**
	 * This method displays the songs of the playlist.
	 */
    @Override
    public void displayPlaylistCommand(){
        List<String> list= backend.getPlaylistInfoString();
        System.out.println(list);
    }
}
