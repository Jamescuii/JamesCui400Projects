import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
/**
 * This This is a placeholder represents a collection that backend developer need to fullfill 
 * for the Music Library Assistant project.
 * 
 * @author Ziqi Shen
 */
public class BackendDeveloperFD implements BackendInterface {
    @Override
    public void loadSongs(String filename) throws FileNotFoundException{
        String existfile = "Exist.file";
        if (existfile.equals(filename)){
            return;
        }
        throw new FileNotFoundException();
    } 
    
    @Override
    public List<SongInterface> searchSongsByTitle(String title, String author){
        List<SongInterface> songTest = new ArrayList<>();
        Song style = new Song();
        songTest.add(style);
        return songTest;} 
    
    @Override
    public void addSong(SongInterface song){
        return;
    } 
    
    @Override
    public SongInterface removeSongFromPlaylist(String title, String author){
        return null;} 
    
    @Override
    public SongInterface removeSongFromLibrary(String title, String author){
        return null;} 
    
    @Override
    public String getPlaylistLength(){
        return null;}

    @Override
    public String getPlaylistSize(){
        return null;}
    
    @Override
    public List<String> getPlaylistInfoString(){
        return null;}

    }
