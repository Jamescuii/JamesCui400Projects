import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Provide a text-based user interface to the searching capaibilities of the
 * CHSearchApplication.
 * 
 * @author FrontendDeveloper, courtesy of the CS400 course staff.
 */
public class CHSearchFrontendFD implements CHSearchFrontendInterface {
	private Scanner userInput;
	private CHSearchBackendInterface backend;

	/**
	 * Initialize frontend to make use of a provided Scanner and CHSearchBackend.
	 * 
	 * @param userInput can be used to read input from use, or to read from files
	 *                  for testing
	 * @param backend   placeholder by me, working implementation by Backend
	 *                  Developer
	 */
	public CHSearchFrontendFD(Scanner userInput, CHSearchBackendInterface backend) {
		this.userInput = userInput;
		this.backend = backend;
	}

	/**
	 * Helper method to display a 79 column wide row of dashes: a horizontal rule.
	 */
	private void hr() {
		System.out.println("-------------------------------------------------------------------------------");
	}

	/**
	 * This loop repeated prompts the user for commands and displays appropriate
	 * feedback for each. This continues until the user enters 'q' to quit.
	 */
	public void runCommandLoop() {
		hr(); // display welcome message
		System.out.println("Welcome to the Cheap and Healthy Search App.");
		hr();

		List<String> words;
		char command = '\0';
		while (command != 'Q') { // main loop continues until user chooses to quit
			command = this.mainMenuPrompt();
			switch (command) {
				case 'L': // System.out.println(" [L]oad data from file");
					loadDataCommand();
					break;
				case 'T': // System.out.println(" Search Post [T]itles");
					words = chooseSearchWordsPrompt();
					searchTitleCommand(words);
					break;
				case 'B': // System.out.println(" Search Post [B]odies");
					words = chooseSearchWordsPrompt();
					searchBodyCommand(words);
					break;
				case 'P': // System.out.println(" Search [P]ost titles and bodies");
					words = chooseSearchWordsPrompt();
					searchPostCommand(words);
					break;
				case 'S': // System.out.println(" Display [S]tatistics for dataset");
					displayStatsCommand();
					break;
				case 'Q': // System.out.println(" [Q]uit");
					// do nothing, containing loop condition will fail
					break;
				default:
					System.out.println(
							"Didn't recognize that command.  Please type one of the letters presented within []s to identify the command you would like to choose.");
					break;
			}
		}

		hr(); // thank user before ending this application
		System.out.println("Thank you for using the Cheap and Healthy Search App.");
		hr();
	}

	/**
	 * Prints the command options to System.out and reads user's choice through
	 * userInput scanner.
	 */
	public char mainMenuPrompt() {
		// display menu of choices
		System.out.println("Choose a command from the list below:");
		System.out.println("    [L]oad data from file");
		System.out.println("    Search Post [T]itles");
		System.out.println("    Search Post [B]odies");
		System.out.println("    Search [P]ost titles and bodies");
		System.out.println("    Display [S]tatistics for dataset");
		System.out.println("    [Q]uit");

		// read in user's choice, and trim away any leading or trailing whitespace
		System.out.print("Choose command: ");
		String input = userInput.nextLine().trim();
		if (input.length() == 0) // if user's choice is blank, return null character
			return '\0';
		// otherwise, return an uppercase version of the first character in input
		return Character.toUpperCase(input.charAt(0));
	}

	/**
	 * Prompt user to enter filename, and display error message when loading fails.
	 */
	public void loadDataCommand() {
		System.out.print("Enter the name of the file to load: ");
		String filename = userInput.nextLine().trim();
		try {
			backend.loadData(filename);
		} catch (FileNotFoundException e) {
			System.out.println("Error: Could not find or load file: " + filename);
		}
	}

	/**
	 * This method gives user the ability to interactively add or remove individual
	 * words from their query, before performing any kind of search.
	 */
	public List<String> chooseSearchWordsPrompt() {
		List<String> words = new ArrayList<>();
		while (true) { // this loop ends when the user pressed enter (without typing any words)
			System.out.println("List of words to search for: " + words.toString());
			System.out.print("Word(s) to add-to/remove-from query, or press enter to search: ");
			String input = userInput.nextLine().replaceAll(",", "").trim();
			if (input.length() == 0) // an empty string ends this loop and method call
				return words;
			else
				// otherwise the specified word's presence in the list is toggled:
				for (String word : input.split(" "))
					if (words.contains(word))
						words.remove(word); // remove any words that were already in the list
					else
						words.add(word); // add any words that were previously missing
		}
	}

	/**
	 * Prompts user for a list of words to search post titles for, and then displays
	 * the list of results.
	 */
	public void searchTitleCommand(List<String> words) {
		List<String> results = backend.findPostsByTitleWords(words.toString());
		int resultIndex = 1;
		if (results.size() > 0)
			System.out.println("Found Results:");
		else
			System.out.println("No matches found.");
		for (String result : results)
			System.out.println("[" + (resultIndex++) + "] " + result);
	}

	/**
	 * Prompts user for a list of words to search post bodies for, and then displays
	 * the list of results.
	 */
	public void searchBodyCommand(List<String> words) {
		List<String> results = backend.findPostsByBodyWords(words.toString());
		int resultIndex = 1;
		if (results.size() > 0)
			System.out.println("Found Results:");
		else
			System.out.println("No matches found.");
		for (String result : results)
			System.out.println("[" + (resultIndex++) + "] " + result);
	}

	/**
	 * Prompts user for a list of words to search post titles and bodies for, and
	 * then displays the list of results.
	 */
	public void searchPostCommand(List<String> words) {
		List<String> results = backend.findPostsByTitleOrBodyWords(words.toString());
		int resultIndex = 1;
		if (results.size() > 0)
			System.out.println("Found Results:");
		else
			System.out.println("No matches found.");
		for (String result : results)
			System.out.println("[" + (resultIndex++) + "] " + result);
	}

	/**
	 * Displays dataset statistics to System.out.
	 */
	public void displayStatsCommand() {
		System.out.println(backend.getStatisticsString());
	}
}
