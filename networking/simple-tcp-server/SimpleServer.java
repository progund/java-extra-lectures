import java.io.*;
import java.net.*;
public class SimpleServer{
  public static void main(String[] args){
    int maxFails = 5;
    int fails = 0;
    ServerSocket server = null;
    try{
      server = new ServerSocket(1234);
    } catch (IOException e) {
      System.err.println("Could not listen on port 1234");
      System.exit(-1);
    }
    while(true){
      RequestWorker w;
      try{
        w = new RequestWorker(server.accept());
        Thread t = new Thread(w);
        t.start();
      } catch (IOException e) {
        System.out.println("Accept failed on port 1234: " + e.getMessage());
        if(fails++ == maxFails){
          System.err.println("Failed " + fails + " times. Shutting down.");
          System.exit(-1);
        }
      }
    }
  }
}
