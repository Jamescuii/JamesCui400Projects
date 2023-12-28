// --== CS400 Project One File Header ==--
// Name: James Cui
// CSL Username: jcui
// Email: jcui57@wisc.edu
// Lecture #: 002
// Notes to Grader: None

import java.util.List;

public interface CHSearchFrontendInterface {
    //public CHSearchFrontendXX(Scanner userInput, CHSearchBackendInterface backend);
    public void runCommandLoop();
    public char mainMenuPrompt();
    public void loadDataCommand();
    public List<String> chooseSearchWordsPrompt();
    public void searchTitleCommand(List<String> words);
    public void searchBodyCommand(List<String> words);
    public void searchPostCommand(List<String> words);
    public void displayStatsCommand();
}
