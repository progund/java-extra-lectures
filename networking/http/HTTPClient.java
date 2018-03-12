import java.io.*;
import java.net.*;

public class HTTPClient {

  public static void main(String[] args) {
    
    HTTPClient client = new HTTPClient();

    try {
      client.getFile(args[0]);
    } catch(IOException ioe) {
      ioe.printStackTrace();
    }
  }
  
  public void getFile(String addr) throws IOException {

    URL url = new URL(addr);
    HttpURLConnection connection =
      (HttpURLConnection) url.openConnection();
    connection.setRequestMethod("GET");

    BufferedReader in =
      new BufferedReader
      (new InputStreamReader(connection.getInputStream()));

    String line;
    while ((line = in.readLine()) != null) {
      System.out.println(line);
    }
    
    in.close();
  }
}
