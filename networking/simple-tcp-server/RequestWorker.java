import java.net.Socket;
import java.io.*;
public class RequestWorker implements Runnable {
  private Socket client;

  public RequestWorker(Socket client) {
    this.client = client;
  }

  public void run(){
    String line;
    BufferedReader in = null;
    PrintWriter out = null;
    try{
      in = new BufferedReader(new
                              InputStreamReader(client.getInputStream()));
      out = new
        PrintWriter(client.getOutputStream(), true);
    } catch (IOException e) {
      System.err.println("Error getting streams: " + e.getMessage());      
    }
    if(in==null || out==null){
      System.out.println("No streams.");
      // We're done.
      return;
    }
    // Say hello to the client
    out.println("HELO " + client + " from server");      
    try{
      while( (line = in.readLine()) != null){
         // Log the message received
        System.out.println("Got: " + line + " from " + client);
      }
    }catch (IOException e) {
      System.err.println("Error ");
      return;
    }
    try{
      in.close();
      out.close();
      client.close();
    }catch(Exception ignore){ }
  }  
}
