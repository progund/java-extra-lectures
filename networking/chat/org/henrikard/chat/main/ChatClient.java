package org.henrikard.chat.main;
import org.henrikard.chat.client.*;
import java.io.IOException;

/**
 * The main class for the chat client.
 * This class creates a chat client initializer,
 * which in turn creates the gui and the gui thread
 * (which handles network reads from the server).
 * If the initializer can't connect to the server,
 * this program exits with an error message and
 * the exit code 1.
 */
public class ChatClient{
    public static void main(String[] args){
	try{
	    ChatClientInitializer cci = new ChatClientInitializer();	    
	}catch(IOException ioe){
	    System.err.println("Error initializing chat client: " + ioe.getMessage());
	    System.exit(1);
	}
    }
}
