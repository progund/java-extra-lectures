import java.net.*;
import java.io.*;
import java.util.*;

public class SimpleChatServer{
  /**
   * A thread class for each client connecting
   */
  class ClientThread implements Runnable{
    Socket s;
    SimpleChatServer server;
    public ClientThread(Socket s, SimpleChatServer server){
      this.s = s;
      this.server=server;
      server.broadcast(s.getInetAddress() + " joined");
    }
    /**
     * The run() method is started with Thread.start()
     */
    public void run(){
      String input=null;
      try{
        /* Read lines from this client */
        BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
        while( (input = in.readLine()) != null){
          /* Unless clients wants to quit, send message to all
           * connected clients...
           */
          if(! input.equals("bye")){
            server.broadcast(s.getInetAddress() + ": " + input);
            System.out.println("Got: " + input + " from " + s);
          }else{
            break;
          }
        }
      }catch(IOException e){
        System.err.println("Error in client thread: " + e);
      }finally{
        /* Don't keep this connection */
        System.out.println("Removing connection to " + s);
        server.dropConnection(s);
      }       
    }
  } // End inner class

    /* Keep sockets in a Vector - Vector is thread safe */
  private Vector<Socket> sockets = new Vector<Socket>();
  /* A way to remove logged out (or lost) connections */
  private void dropConnection(Socket s){
    sockets.remove(s);
    try{
      s.close();
    }catch(IOException e){
      System.err.println("Error closing " + s + ": " + e);
    }
  }
  /* Send message to all connected clients */
  private void broadcast(String msg){
    try{
      System.out.println(msg.length());
      if(msg==null || msg.endsWith("]: ")){
        return; // Don't write empty lines
      }
      PrintWriter out;
      for(Socket s : sockets){
        out=new PrintWriter(s.getOutputStream(), true);
        out.println(msg);
      }
    }catch(IOException ioe){
      System.err.println("Error writing to clients: " + ioe);
    }
  }
  /* Main listen loop handling connections */
  private void listen(){
    try{
      ServerSocket server = new ServerSocket(1066);
      while(true){
        /* Whenever a client connects, welcome it
         * and save the connection 
         */
        Socket s = server.accept();
        sockets.add(s);
        /* Log this connection on the server side */
        System.out.println(new Date() + " - Got connection from: " + s.getInetAddress());
        /* Greeting to new client */
        PrintWriter out = new PrintWriter(s.getOutputStream(), true);
        String input=null;
        out.println("HELLO - log out with 'bye'");
        /* Let a new thread handle i/o for this client */
        new Thread(new ClientThread(s, this)).start();
      }
    }catch(IOException e){
      System.err.println("Error in Server: " + e.getMessage());
      e.printStackTrace();
    }
  }
  public static void main(String[] args){
    /* Start this server */
    new SimpleChatServer().listen();
  }
}
