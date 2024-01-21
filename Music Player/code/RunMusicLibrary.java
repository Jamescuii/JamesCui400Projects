import java.util.Scanner;

public class RunMusicLibrary {
	public static void main(String[] args) {
		SongReaderInterface songReader = new SongReaderDW();
		SearchableSortedCollectionInterface<SongInterface> redBlackTree = new SearchableSortedCollectionAE<SongInterface>();
		BackendInterface backend = new BackendBD(redBlackTree, songReader);
		FrontendDeveloper frontend = new FrontendDeveloper(new Scanner(System.in), backend);
		frontend.runCommandLoop();
	}
}
