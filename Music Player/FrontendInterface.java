/**
 * This abstract data type represents a collection that frontend developer need to fullfill 
 * for the Music Library Assistant project.
 */

public interface FrontendInterface {
    //public FrontendInterfaceXX(Scanner userInput, BackendInterface backend);
    public void runCommandLoop();
    public String mainMenuPrompt();
    public void loadDataCommand();
    public String chooseSearchTitlePrompt();
    public String chooseSearchAuthorPrompt();
    public void searchTitleCommand(String title, String author);
    public void addToPlaylistCommand(String title, String author);
    public void removeFromPlaylistCommand();
    public void removeFromLibraryCommand();
    public void displayPlaylistLengthCommand();
    public void displayPlaylistSizeCommand();
    public void displayPlaylistCommand();
}
