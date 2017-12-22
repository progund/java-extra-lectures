package org.contactcompany.application.list;

import org.contactcompany.api.container.SimpleMutableList;
import org.contactcompany.api.entry.Contact;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.io.*;
import java.util.Iterator;

public class ContactList implements SimpleMutableList<Contact>{
  
  private static final String LOG_FILE =
    System.getProperty("user.home") +
    System.getProperty("file.separator") +
    ".address_book.log";
    

  private static final String ADDRESS_FILE = 
    System.getProperty("user.home") +
    System.getProperty("file.separator") +
    ".address_book";
  
  private List<Contact> entries;
  public List<Contact> entries(){
    return entries;
  }
  public ContactList(){
    entries = new ArrayList<>();
  }
  
  public int numberOfEntries(){
    return entries.size();
  }

  public void listEntries(){
    List<Contact> copy = new ArrayList<>(entries);
    Collections.sort(copy);
    for(Contact c : copy){
      System.out.println(c);
    }
  }

  public void addEntry(Contact c){
    entries.add(c);
  }

  /*
   * Log exception in the application logfile.
   *
   * This method exists so that we can log exceptions
   * for the developers to investigate. This allows us
   * to hide the Java specific texts of the exceptions
   * from the user and show the users some meaningful
   * text instead.
   */
  private void logException(Exception e){
    try{
      PrintWriter pw = new PrintWriter(new FileWriter(LOG_FILE,true));
      pw.println(new java.util.Date());
      e.printStackTrace(pw);
      pw.close();
    }catch(Exception logErr){
      System.err.print("Could not log exception: ");
      System.err.println(e.getMessage());
      logErr.printStackTrace();
    }
  }

  @SuppressWarnings("unchecked")
  public void load(){
    try{
      if (!new File(ADDRESS_FILE).exists()){
        System.out.println("INFO: There is no address book file.");
        return;
      }
      ObjectInputStream in = 
        new ObjectInputStream(new FileInputStream
                              (ADDRESS_FILE));
      Contact c;
      entries = (List<Contact>)in.readObject();
      in.close();
    }catch(Exception e){
      // Write a user friendly error message,
      // log the exception, and, 
      // rethrow the exception as a RuntimeException
      // to be handle by the Main class.
      System.err.println("Could not load address book");
      logException(e);
      throw new RuntimeException("Your address book is corrupted.");
    }
  }

  public void save(){
    try{
      System.out.println("Saving in " + ADDRESS_FILE + "...");
      ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(ADDRESS_FILE));
      out.writeObject(entries);
      out.close();
    }catch(Exception e){
      e.printStackTrace();
    }
  }

  /*
  public Contact get(Contact contact){
    for(Contact c : entries){
      if(c.equals(contact)){
        return c;
      }
    }
    return null;
  }
  */

  public boolean contains(Contact c){
    return entries.contains(c);
  }
}
