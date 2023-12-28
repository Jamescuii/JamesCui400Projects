// --== CS400 Project One File Header ==--
// Name: James Cui
// CSL Username: jcui
// Email: jcui57@wisc.edu
// Lecture #: 002
// Notes to Grader: None

public class PostBD implements PostInterface {

  String title;
  String url;
  String body;
  
  public PostBD(String title, String url, String body) {
    this.title = title;
    this.url = url;
    this.body = body;
  }
  
  @Override
  public String getTitle() {
    return title;
  }
  
  @Override
  public String getUrl() {
    return url;
  }
  
  @Override
  public String getBody() {
    return body;
  }

}
