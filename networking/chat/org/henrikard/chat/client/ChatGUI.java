package org.henrikard.chat.client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import com.sun.java.swing.plaf.gtk.GTKLookAndFeel;

/* 
 * The GUI part of the Chat client. This
 * class implements Runnable, so that it
 * can be used as a thread that runs in
 * the background, reading output from the
 * server and appending that to the text area
 * component of the GUI.
 */
public class ChatGUI implements Runnable{

  /* GUI components */
  private JFrame frame;
  private JTextArea messages;
  private JTextField input;
  private JScrollPane sp;

  /* The network component */
  private ChatClientInitializer client;

  /* Read messages in a background thread */
  /* This method contains the code that runs
   * in the background when this instance is
   * started as a new Thread.
   */
  public void run(){
    input.requestFocusInWindow();
    while(true){
      try{
        // Declare a Socket and assign it the client's socket
        Socket s=client.getSocket();
        BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
        String input=null;
        while( (input = in.readLine()) != null){
          messages.append(input);
          messages.append("\n");
        }
      }catch(IOException e){
        System.err.println("Error reading from server: " + e);
        messages.append("\nError reading from server: " + e.getMessage());
        messages.append("\n");
      }
    }
  }

  /* Send message to server */
  private void send(String msg){
    try{
      PrintWriter out=new PrintWriter(client.getSocket().getOutputStream(), true);
      out.println(msg);
      input.setText("");
    }catch(IOException e){
      System.err.println("Error writing to server: " + e);
      messages.append("\nError sending to server: " + e.getMessage());
      messages.append("\n");
      input.setText("");
    }
  }
  private void initComponents(){
    frame=new JFrame("Chat client");
    frame.setSize(400,400);
    frame.setLayout(new BorderLayout());
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    messages=new JTextArea();
    messages.setEditable(false);
    sp = new JScrollPane(messages);
    sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    input=new JTextField();
    /* When user hits enter, send message to server */
    input.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent ae){
          send(input.getText());
        }
      });
  }
  private void layout(){
    frame.add(sp, BorderLayout.CENTER);
    frame.add(input, BorderLayout.SOUTH);
  }
  private void show(){
    frame.setVisible(true);
  }

  /**
   * This constructor creates the GUI components
   * and layout, and renders the GUI visible.
   * @param client The ChatClientInitializer
   * which contains the network connection to the
   * server from which we are reading messages
   * and sending messages to.
   */
  public ChatGUI(ChatClientInitializer client){
    this.client=client;
    try{
      UIManager.setLookAndFeel(new GTKLookAndFeel());
    }catch(Exception e){}
    initComponents();
    layout();
    show();
  }
}
